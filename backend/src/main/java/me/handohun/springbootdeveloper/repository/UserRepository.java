package me.handohun.springbootdeveloper.repository;

import me.handohun.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username); // email로 사용자 정보를 가져옴

}
