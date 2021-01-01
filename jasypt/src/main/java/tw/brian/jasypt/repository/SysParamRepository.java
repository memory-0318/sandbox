package tw.brian.jasypt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import tw.brian.jasypt.entity.ISysParamEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author:Bernice created on 25/03/2020
 */
public interface SysParamRepository extends JpaRepository<ISysParamEntity, Integer> {
    boolean existsByParamNmEn(String paramName);

    Optional<ISysParamEntity> findByParamNmEn(String paramName);

    List<ISysParamEntity> findByParamNmEnIn(List<String> paramName);

    @Modifying
    void deleteByParamNmEn(String paramName);

    @Modifying
    void deleteBySysParamOid(int oid);
}
