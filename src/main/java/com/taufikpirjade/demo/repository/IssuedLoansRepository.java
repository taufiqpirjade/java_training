package com.taufikpirjade.demo.repository;

import com.taufikpirjade.demo.model.IssuedLoans;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuedLoansRepository extends CrudRepository<IssuedLoans, Integer> {

    @Query("select il from IssuedLoans il where age < ?1")
    List<IssuedLoans> getIssueedLoansByAge(Integer age);

}
