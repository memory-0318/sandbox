package tw.brian.jasypt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.brian.jasypt.entity.UserInfoAppEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Heisenberg created on 05/06/2020
 */
public interface UserInfoAppRepository extends JpaRepository<UserInfoAppEntity, String> {
    List<UserInfoAppEntity> findTop100By();
}
