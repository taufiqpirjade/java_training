package com.taufikpirjade.demo.repository;

import com.taufikpirjade.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
