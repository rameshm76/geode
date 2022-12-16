package com.ascendant76.geode.client;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableClusterDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnablePdx;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.gemfire.transaction.config.EnableGemfireCacheTransactions;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableClusterDefinedRegions(clientRegionShortcut = ClientRegionShortcut.PROXY)
@EnableTransactionManagement
@EnableGemfireCacheTransactions
@Configuration
@EnablePdx
@ComponentScan(basePackageClasses = AccountService.class)
@EnableGemfireRepositories(basePackageClasses = AccountRepository.class)
@ClientCacheApplication(
        name = "TransactionalClient",
        logLevel = "error",
        pingInterval = 5000L,
        readTimeout = 15000,
        retryAttempts = 1,
locators = {@ClientCacheApplication.Locator(host = "localhost", port = 40404)})
public class GeodeClientConfig {}
