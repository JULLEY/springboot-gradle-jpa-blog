package leo.programming.repository;

import leo.programming.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// DAO
// 자동으로 bean등록
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // SELECT * FROM user WHERE username = 1?;
    Optional<User> findByUsername(String username);
}


/*
 * 시큐리티 사용으로 주석
 * */
// JPA Naming 쿼리전략
// SELECT * FROM user WHERE username = ? AND password = ? // 파라미터 순서대로 ?에 들어간다
// User findByUsernameAndPassword(String username, String password);
// 위와 동일
// @Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
// User login(String username, String password);