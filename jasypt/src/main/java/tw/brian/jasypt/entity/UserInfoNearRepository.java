package tw.brian.jasypt.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Heisenberg created on 05/06/2020
 */
public interface UserInfoNearRepository extends JpaRepository<UserInfoNearEntity, String> {
    List<UserInfoNearEntity> findTop100By();
}
