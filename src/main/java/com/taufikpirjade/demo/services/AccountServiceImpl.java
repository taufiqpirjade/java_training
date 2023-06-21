package com.taufikpirjade.demo.services;

import com.taufikpirjade.demo.model.Account;
import com.taufikpirjade.demo.model.Employee;
import com.taufikpirjade.demo.model.EmployeeResponse;
import com.taufikpirjade.demo.model.IssuedLoans;
import com.taufikpirjade.demo.repository.AccountRepository;
import com.taufikpirjade.demo.repository.IssuedLoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    IssuedLoansRepository issuedLoansRepository;

    RestTemplate restTemplate;

    @Override
    public Account getAccountInfo(String accountNumber) {
        Optional<Account> account = accountRepository.findById(Integer.valueOf(accountNumber));
        if (!account.isPresent()) {
            return null;
        }
        return account.get();
    }

    @Override
    public int openAccount(Account account) {
        System.out.println(account.toString());
        accountRepository.save(account);
        return 0;
    }

    @Override
    public int issueLoans(IssuedLoans issuedLoans) {
        IssuedLoans response = issuedLoansRepository.save(issuedLoans);
        return response.getLoanId();
    }

    @Override
    public IssuedLoans getLoanInfo(Integer loanAccountNumber) {
        Optional<IssuedLoans> response =  issuedLoansRepository.findById(loanAccountNumber);
        if (response.isPresent()) {
            return response.get();
        }
        return null;
    }

    @Override
    public List<IssuedLoans> getAllLoanDetails() {
        Iterable<IssuedLoans> response = issuedLoansRepository.findAll();




        List<IssuedLoans> issuedLoansList = new ArrayList<>();
        response.forEach( req -> {
            issuedLoansList.add(req);
        } );
        return issuedLoansList;
    }

    @Override
    public List<IssuedLoans> getLoansByAge(Integer age) {
        return issuedLoansRepository.getIssueedLoansByAge(age);
    }

    @Override
    public List<Employee> getEmployeeList() {
        restTemplate = new RestTemplate();
        ResponseEntity<EmployeeResponse> response =  restTemplate.exchange("https://dummy.restapiexample.com/api/v1/employees",
                HttpMethod.GET,
                new HttpEntity<>(getHeaders()),
                EmployeeResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println(response.getBody());
        }
        return response.getBody().getData();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }


}
