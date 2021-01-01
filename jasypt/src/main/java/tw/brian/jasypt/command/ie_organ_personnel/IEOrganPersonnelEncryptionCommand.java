package tw.brian.jasypt.command.ie_organ_personnel;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import tw.brian.jasypt.BaseCommand;
import tw.brian.jasypt.CommandType;
import tw.brian.jasypt.entity.IEOrganPersonnelEntity;
import tw.brian.jasypt.manager.EncryptionManager;
import tw.brian.jasypt.repository.IEOrganPersonnelRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/23
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class IEOrganPersonnelEncryptionCommand extends BaseCommand {
    public static final Integer DEFAULT_PAGE_SIZE = 1000;

    private final EncryptionManager encryptionManager;
    private final IEOrganPersonnelRepository repository;

    public IEOrganPersonnelEncryptionCommand(
        EncryptionManager encryptionManager,
        IEOrganPersonnelRepository repository) {
        super(CommandType.ENCRYPT_IE_ORGAN_PERSONNEL.getCommandName());

        this.encryptionManager = encryptionManager;
        this.repository = repository;
    }

    @Override
    public void execute() throws IOException {
        long total = this.repository.count();
        int pageSize = DEFAULT_PAGE_SIZE;
        long pages = total / DEFAULT_PAGE_SIZE + 1;

        log.info("Total data: {}, Page size: {}, Total pages: {}", total, pageSize, pages);

        ForkJoinPool customPool = new ForkJoinPool(4);
        AtomicInteger counter = new AtomicInteger();
        List<IEOrganPersonnelEntity> encryptedEntitiesList = LongStream.range(0, pages)
            .parallel()
            .mapToObj(page -> {
                try {
                    Pageable pageable = PageRequest.of((int) page, pageSize);
                    Page<IEOrganPersonnelEntity> entityPage = this.repository.findAll(pageable);
                    List<IEOrganPersonnelEntity> results = entityPage.getContent()
                        .stream()
                        .map(entity -> entity.toBuilder()
                            .setEncodePersonnelNm(this.encryptionManager
                                .encrypt(new String(entity.getEncodePersonnelNm(), StandardCharsets.UTF_16LE))
                                .getBytes(StandardCharsets.UTF_8))
                            .setEncodeTelNumber(this.encryptionManager
                                .encrypt(new String(entity.getEncodeTelNumber(), StandardCharsets.UTF_16LE))
                                .getBytes(StandardCharsets.UTF_8))
                            .build())
                        .collect(Collectors.toList());

                    long currentCount = counter.addAndGet(results.size());
                    log.info("Page {} has been encrypted, Progress: {}", page,
                        String.format("%8.4f％", ((double) currentCount / total) * 100));

                    return results;
                } catch (Exception e) {
                    log.error("加密欄位時發生錯誤", e);
                    throw new RuntimeException(e);
                }
            })
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        counter.set(0);

        List<List<IEOrganPersonnelEntity>> partitionedEntitiesList = Lists.partition(encryptedEntitiesList, 30);
        partitionedEntitiesList.forEach(entities -> {
            this.repository.saveAll(entities);
            this.repository.flush();
            long currentCount = counter.addAndGet(entities.size());
            log.info("Progress of writing encrypted entities back to table: {}%", String.format("%8.4f",
                ((double) currentCount / total) * 100));
        });
        //        final Object lock = new Object();
        //        synchronized (lock) {
        //            try {
        //                List<List<IEOrganPersonnelEntity>> partitionedEntitiesList = Lists.partition(encryptedEntitiesList, 30);
        //                customPool.submit(() -> partitionedEntitiesList.parallelStream()
        //                    .forEach(entities -> {
        //                        this.repository.saveAll(entities);
        //                        long currentCount = counter.addAndGet(entities.size());
        //                        log.info("Progress of writing encrypted entities back to table: {}%", String.format("%8.4f",
        //                            ((double) currentCount / total) * 100));
        //                    }));
        //
        //                while (counter.get() != total) {
        //                    lock.wait(1000);
        //                }
        //            } catch (Exception e) {
        //                log.error("儲存加密資料回資料表時發生錯誤", e);
        //            } finally {
        //                customPool.shutdownNow();
        //            }
        //        }
    }
}
