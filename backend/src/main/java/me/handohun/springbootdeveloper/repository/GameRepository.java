package me.handohun.springbootdeveloper.repository;

import me.handohun.springbootdeveloper.domain.Company;
import me.handohun.springbootdeveloper.domain.Game;
import me.handohun.springbootdeveloper.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    public int countByCompany(Company company);
}
