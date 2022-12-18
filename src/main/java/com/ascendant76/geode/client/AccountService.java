package com.ascendant76.geode.client;

import com.ascendant76.geode.domain.Account;
import org.apache.geode.cache.Region;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Resource(name = "Accounts")
    private final Region<String, Account> accountRegion;

    public AccountService(
            AccountRepository accountRepository,
            @Qualifier("Accounts") Region<String, Account> accountRegion) {
        this.accountRepository = accountRepository;
        this.accountRegion = accountRegion;
    }

    private AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public Optional<Account> findById(String id) {
        return getAccountRepository().findById(id);
    }

    public int numberEntriesStoredOnServer() {
        return accountRegion.keySetOnServer().size();
    }

    @Transactional
    public List<Account> createFiveAccounts() {
        return Arrays.stream(
                        new Account[] {
                            new Account("1@1.com", "John", "Melloncamp"),
                            new Account("2@2.com", "Franky", "Hamilton"),
                            new Account("3@3.com", "Sebastian", "Horner"),
                            new Account("4@4.com", "Chris", "Vettel"),
                            new Account("5@5.com", "Kimi", "Rosberg")
                        })
                .map(accountRepository::save)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateAccountsSuccess() {
        accountRepository.save(new Account("2@2.com", "Humpty", "Hamilton"));
    }

    @Transactional
    public void updateAccountsWithDelay(int millisDelay, Account account)
            throws InterruptedException {
        accountRepository.save(account);
        Thread.sleep(millisDelay);
    }

    @Transactional
    public void updateAccountsFailure() {
        accountRepository.save(new Account("2@2.com", "Numpty", "Hamilton"));
        throw new IllegalArgumentException(
                "This is an expected exception that should fail the transactions");
    }
}
