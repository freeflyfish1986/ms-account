package com.ms_account.service;

import com.ms_account.entity.Account;
import com.ms_account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> findAll() {                                             // *** ВЫВОД ВСЕХ Users ***
        return repository.findAll();
    }

    public Account findById(Long id) {                                           // *** ПОИСК по ID ***
        Optional<Account> account = repository.findById(id);
        return account.orElse(null);
    }

    @Transactional
    public Account save(Account account) {                                       // *** СОХРАНЕНИЕ ***
        enrichUser(account);
        return repository.save(account);
    }

    @Transactional
    public void delete(Long id) {                                                // *** УДАЛЕНИЕ ***
        repository.deleteById(id);
    }

    private void enrichUser(Account account) {                                   // *** ОБНОВЛЕНИЕ ВРЕМЕНИ ***
        account.setCreated(account.getCreated());
        account.setUpdated(LocalDateTime.now());
    }

    public List<Account> getAccountLogin(String login) {                         // SQL   ЗАПРОС
        return repository.getAccount(login);
        //return repository.getAccountByAgeIsLessThan(login);                    // для параметризованного метода
    }
}
