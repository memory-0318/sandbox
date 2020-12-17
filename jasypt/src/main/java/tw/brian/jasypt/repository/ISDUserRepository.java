package tw.brian.jasypt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.brian.jasypt.entity.ISDUserEntity;

import java.util.List;

public interface ISDUserRepository extends JpaRepository<ISDUserEntity, Integer> {
    List<ISDUserEntity> findTop100By();
}
