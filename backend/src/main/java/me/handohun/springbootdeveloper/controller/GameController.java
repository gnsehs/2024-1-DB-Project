package me.handohun.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.domain.Company;
import me.handohun.springbootdeveloper.domain.Device;
import me.handohun.springbootdeveloper.domain.Game;
import me.handohun.springbootdeveloper.dto.CompanyResponse;
import me.handohun.springbootdeveloper.dto.DeviceResponse;
import me.handohun.springbootdeveloper.dto.GameResponse;
import me.handohun.springbootdeveloper.service.CompanyService;
import me.handohun.springbootdeveloper.service.DeviceService;
import me.handohun.springbootdeveloper.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GameController {

    private final GameService gameService;
    private final CompanyService companyService;
    private final DeviceService deviceService;

    @GetMapping("/games")
    public ResponseEntity<List<GameResponse>> findAllGames() {
        List<GameResponse> games = gameService.findAll()
                .stream() // 스트림은 여러 데이터가 모여 있는 컬렉션을 간편하게 처리하기 위한 자바 8 에서 추가된 기능
                .map(GameResponse::new)
                .toList();

        return ResponseEntity.ok().body(games);
    }

    @GetMapping("/games/{id}") // 이름이 파라미터 이름과 같다면 자동 매칭
    public ResponseEntity<GameResponse> findGame(@PathVariable Long id) {
        Game game = gameService.findById(id);

        return ResponseEntity.ok()
                .body(new GameResponse(game));
    }

    @GetMapping("/gamecompanys")
    public ResponseEntity<List<CompanyResponse>> findAllCompany() {
        List<CompanyResponse> companys = companyService.findAll()
                .stream() // 스트림은 여러 데이터가 모여 있는 컬렉션을 간편하게 처리하기 위한 자바 8 에서 추가된 기능
                .map(CompanyResponse::new)
                .toList();

        return ResponseEntity.ok().body(companys);
    }
    @GetMapping("/gamecompanys/{id}") // 이름이 파라미터 이름과 같다면 자동 매칭
    public ResponseEntity<CompanyResponse> findCompany(@PathVariable Long id) {
        Company company = companyService.findById(id);

        return ResponseEntity.ok()
                .body(new CompanyResponse(company));
    }


    @GetMapping("/devices")
    public ResponseEntity<List<DeviceResponse>> findAllDevice() {
        List<DeviceResponse> devices = deviceService.findAll()
                .stream()
                .map(DeviceResponse::new)
                .toList();

        return ResponseEntity.ok().body(devices);
    }

    @GetMapping("/devices/{id}") // 이름이 파라미터 이름과 같다면 자동 매칭
    public ResponseEntity<DeviceResponse> findDevice(@PathVariable Long id) {
        Device device = deviceService.findById(id);

        return ResponseEntity.ok()
                .body(new DeviceResponse(device));
    }
}
