package tw.brian.jasypt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.brian.jasypt.entity.IEOrganPersonnelEntity;

import java.util.List;

public interface IEOrganPersonnelRepository extends JpaRepository<IEOrganPersonnelEntity, Integer> {
    List<IEOrganPersonnelEntity> findTop100By();
}
