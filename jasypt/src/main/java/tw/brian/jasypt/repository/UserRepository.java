package tw.brian.jasypt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.brian.jasypt.entity.IUserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<IUserEntity, Integer> {
    List<IUserEntity> findTop100By();
}
