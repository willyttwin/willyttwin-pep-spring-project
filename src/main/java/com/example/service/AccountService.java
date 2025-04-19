package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account register(Account account) {
        if (account.getUsername() == null || account.getUsername().isBlank()) {
            return null;
        }
        if (account.getPassword() == null || account.getPassword().length() < 4) {
            return null;
        }
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            return null;
        }
        return accountRepository.save(account);
    }

    public Account login(String username, String password) {
        Account found = accountRepository.findByUsername(username);
        if (found != null && found.getPassword().equals(password)) {
            return found;
        }
        return null;
    }

    public boolean accountExists(Integer accountId) {
        return accountRepository.existsById(accountId);
    }
}
