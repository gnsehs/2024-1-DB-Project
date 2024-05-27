package me.handohun.springbootdeveloper.repository;

import me.handohun.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username); // email로 사용자 정보를 가져옴
    /*
Java 8부터 도입된 Optional은 값이 없는 경우를 표현하기 위한 클래스이다.
Optional 클래스는 제네릭을 사용하여 어떤 타입의 객체도 감싸서 담을 수 있다.

Optional 객체는 값이 존재할 수도 있고, 없을 수도 있다.
이는 NullPointerException 예외를 방지할 수 있고, 코드의 안정성을 높이며 가독성을 향상시킨다.
     */

    /*
    JPA에서 메서드 규칙에 맞춰 메서드를 선언하면 이름을 분석해 자동으로 쿼리를 생성해줌
     */
}
