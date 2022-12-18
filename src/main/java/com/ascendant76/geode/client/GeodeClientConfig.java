package com.ascendant76.geode.client;

import com.ascendant76.geode.domain.Account;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@Configuration
@EnableEntityDefinedRegions(
        basePackageClasses = Account.class,
        clientRegionShortcut = ClientRegionShortcut.CACHING_PROXY)
@EnableGemfireRepositories(basePackageClasses = AccountRepository.class)
public class GeodeClientConfig {}
