package com.ascendant76.geode.client;

import com.ascendant76.geode.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.ascendant76.geode.client")
public class GeodeClientApp implements CommandLineRunner {

    @Autowired private AccountService accountService;

    @Resource(name = "Accounts")
    private Region<String, Account> accounts;

    public static void main(String[] args) {
        new SpringApplicationBuilder(GeodeClientApp.class)
                .web(WebApplicationType.NONE)
                .build()
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Number of Entries stored before = {}", accountService.numberEntriesStoredOnServer());
        accountService.createFiveAccounts();
        assertThat(accountService.numberEntriesStoredOnServer()).isEqualTo(5);
        log.info("Number of Entries stored after = {}", accountService.numberEntriesStoredOnServer());
        log.info("Account for ID before (transaction commit success) = {}", accountService.findById("2@2.com").get());
        accountService.updateAccountsSuccess();
        assertThat(accountService.numberEntriesStoredOnServer()).isEqualTo(5);
        var account = accountService.findById("2@2.com").get();
        assertThat(account.getFirstName()).isEqualTo("Humpty");
        log.info("Account for ID after (transaction commit success) = {}", account);

        account = accountService.findById("2@2.com").get();
        assertThat(account.getFirstName()).isEqualTo("Humpty");
        log.info("Account for ID after (transaction commit failure) = {}", accountService.findById("2@2.com").get());

        var numpty = new Account("2@2.com", "Numpty", "Hamilton");
        var frumpy = new Account("2@2.com", "Frumpy", "Hamilton");
        accountService.updateAccountsWithDelay(1000, numpty);
        accountService.updateAccountsWithDelay(10, frumpy);
        account = accountService.findById("2@2.com").get();
        assertThat(account).isEqualTo(frumpy);
        log.info("Account for ID after 2 updates with delay = {}", account);
    }
}
