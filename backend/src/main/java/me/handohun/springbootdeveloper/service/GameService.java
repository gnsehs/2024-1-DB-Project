package me.handohun.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.domain.Article;
import me.handohun.springbootdeveloper.domain.Company;
import me.handohun.springbootdeveloper.domain.Game;
import me.handohun.springbootdeveloper.repository.GameRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Component
public class GameService {

    private final GameRepository gameRepository;

    public List<Game> findAll() { // 테이블에 저장되어있는 모든 데이터 조회
        return gameRepository.findAll();
    }

    public long countByCompany(Company company) {
        return gameRepository.countByCompany(company);
    }

    public Game findById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not game found : " + id));
    }

}
