package com.taufikpirjade.demo.controller;

import com.taufikpirjade.demo.model.Account;
import com.taufikpirjade.demo.model.Employee;
import com.taufikpirjade.demo.model.EmployeeResponse;
import com.taufikpirjade.demo.model.IssuedLoans;
import com.taufikpirjade.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankingController {

    @Autowired
    AccountService accountService; // field level dependency injection


    @GetMapping("/account")
    public ResponseEntity<Object> getAccountDetails(@RequestParam String accountNumber) {
        Account account = accountService.getAccountInfo(accountNumber);
        if (account == null) {
            return new ResponseEntity("Account number doesn't exist", null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(account, null, HttpStatus.OK);
    }

    @PostMapping("/openAccount")
    public ResponseEntity<Object> openAccount(@RequestBody Account account) {
        int accountId = accountService.openAccount(account);
        return new ResponseEntity<>("Account is opened successfully : "+accountId, null, HttpStatus.CREATED);
    }

    @PostMapping("/issueLoans")
    public ResponseEntity<Object> issueLoans(@RequestBody IssuedLoans issuedLoans) {
        int loanId = accountService.issueLoans(issuedLoans);
        return new ResponseEntity<>("Loan Account is issued successfully : "+loanId, null, HttpStatus.CREATED);
    }


    @GetMapping("/loans")
    public ResponseEntity<Object> getLoanInfo(@RequestParam Integer loanAccountNumber) {
        IssuedLoans issuedLoans = accountService.getLoanInfo(loanAccountNumber);
        if (issuedLoans == null) {
            return new ResponseEntity("Loan Account number doesn't exist", null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(issuedLoans, null, HttpStatus.OK);
    }

    @GetMapping("/allLoans")
    public ResponseEntity<Object> getAllLoanDetails() {
        List<IssuedLoans> issuedLoans = accountService.getLoansByAge(25);
        if (issuedLoans == null) {
            return new ResponseEntity("Loan Account number doesn't exist", null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(issuedLoans, null, HttpStatus.OK);
    }

    @GetMapping("/allemployees")
    public ResponseEntity<Object> getAllLEmp() {
        List<Employee> issuedLoans = accountService.getEmployeeList();
        return new ResponseEntity(issuedLoans, null, HttpStatus.OK);
    }

}