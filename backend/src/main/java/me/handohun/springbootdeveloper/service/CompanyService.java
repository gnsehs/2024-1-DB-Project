package me.handohun.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.domain.Company;
import me.handohun.springbootdeveloper.domain.Game;
import me.handohun.springbootdeveloper.repository.CompanyRepository;
import me.handohun.springbootdeveloper.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> findAll() { // 테이블에 저장되어있는 모든 데이터 조회
        return companyRepository.findAll();
    }


}
