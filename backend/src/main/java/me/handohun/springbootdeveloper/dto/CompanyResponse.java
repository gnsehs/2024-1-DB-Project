package me.handohun.springbootdeveloper.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.domain.Company;
import me.handohun.springbootdeveloper.repository.CompanyRepository;
import me.handohun.springbootdeveloper.repository.GameRepository;
import me.handohun.springbootdeveloper.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Getter
public class CompanyResponse {

    private final String name;
    private final String nation;
    private final LocalDate founding_date;
    private final String ceo;

    private final int numGames;




    public CompanyResponse(Company company) {
        this.name = company.getName();
        this.nation = company.getNation();
        this.founding_date = company.getFounding_date();
        this.ceo = company.getCeo();
        this.numGames = company.getGames().size();

    }
}
