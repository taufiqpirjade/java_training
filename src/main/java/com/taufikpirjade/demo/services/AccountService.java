package com.taufikpirjade.demo.services;

import com.taufikpirjade.demo.model.Account;
import com.taufikpirjade.demo.model.Employee;
import com.taufikpirjade.demo.model.EmployeeResponse;
import com.taufikpirjade.demo.model.IssuedLoans;

import java.util.List;

public interface AccountService {

    Account getAccountInfo(String accountNumber);

    int openAccount(Account account);

    int issueLoans(IssuedLoans issuedLoans);

    IssuedLoans getLoanInfo(Integer loanAccountNumber);

    List<IssuedLoans> getAllLoanDetails();

    List<IssuedLoans> getLoansByAge(Integer age);

    List<Employee> getEmployeeList();

}
