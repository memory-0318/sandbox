package tw.brian.jasypt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.brian.jasypt.entity.UserInfoEntity;

import java.util.List;

/**
 * @author Heisenberg created on 05/06/2020
 */
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, String> {
    List<UserInfoEntity> findTop100By();
}
