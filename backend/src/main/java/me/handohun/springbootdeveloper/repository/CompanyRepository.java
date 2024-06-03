package me.handohun.springbootdeveloper.repository;

import me.handohun.springbootdeveloper.domain.Company;
//import me.handohun.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
