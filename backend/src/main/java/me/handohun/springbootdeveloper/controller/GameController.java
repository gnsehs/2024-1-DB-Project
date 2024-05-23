package me.handohun.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.dto.CompanyResponse;
import me.handohun.springbootdeveloper.dto.GameResponse;
import me.handohun.springbootdeveloper.service.CompanyService;
import me.handohun.springbootdeveloper.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GameController {

    private final GameService gameService;
    private final CompanyService companyService;

    @GetMapping("/games")
    public ResponseEntity<List<GameResponse>> findAllGames() {
        List<GameResponse> games = gameService.findAll()
                .stream() // 스트림은 여러 데이터가 모여 있는 컬렉션을 간편하게 처리하기 위한 자바 8 에서 추가된 기능
                .map(GameResponse::new)
                .toList();

        return ResponseEntity.ok().body(games);
    }

    @GetMapping("/gamecompanys")
    public ResponseEntity<List<CompanyResponse>> findAllCompany() {
        List<CompanyResponse> companys = companyService.findAll()
                .stream() // 스트림은 여러 데이터가 모여 있는 컬렉션을 간편하게 처리하기 위한 자바 8 에서 추가된 기능
                .map(CompanyResponse::new)
                .toList();

        return ResponseEntity.ok().body(companys);
    }
}
