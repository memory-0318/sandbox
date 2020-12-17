package tw.brian.jasypt.command.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import tw.brian.jasypt.BaseCommand;

import java.util.concurrent.TimeUnit;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/11
 */
@Slf4j
public class DefaultCommandListener implements CommandListener {
    private StopWatch stopWatch = new StopWatch();

    @Override
    public void beforeExecute(BaseCommand command) {
        log.info("Start executing {}...", command.getCommandName());
        this.stopWatch.start();
    }

    @Override
    public void afterExecute(BaseCommand command) {
        this.stopWatch.stop();
        log.info("Finish {} command, duration: {}ms", command.getCommandName(),
            String.format("%9.4d", stopWatch.getTime(TimeUnit.MICROSECONDS)));
    }
}
