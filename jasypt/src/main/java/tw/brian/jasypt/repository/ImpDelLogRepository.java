package tw.brian.jasypt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.brian.jasypt.entity.IImpDelLogEntity;

import java.util.List;

public interface ImpDelLogRepository extends JpaRepository<IImpDelLogEntity, Integer> {
    List<IImpDelLogEntity> findTop100By();
}
