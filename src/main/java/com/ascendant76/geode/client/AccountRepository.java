package com.ascendant76.geode.client;

import com.ascendant76.geode.domain.Account;
import org.springframework.data.gemfire.mapping.annotation.ClientRegion;
import org.springframework.data.repository.CrudRepository;

@ClientRegion("Account")
public interface AccountRepository extends CrudRepository<Account, String> {}