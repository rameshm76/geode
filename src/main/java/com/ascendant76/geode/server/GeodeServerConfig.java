package com.ascendant76.geode.server;

import com.ascendant76.geode.domain.Account;
import org.apache.geode.cache.DataPolicy;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.Scope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.ReplicatedRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnableLocator;
import org.springframework.data.gemfire.config.annotation.EnableManager;
import org.springframework.data.gemfire.transaction.config.EnableGemfireCacheTransactions;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableLocator
@EnableTransactionManagement
@EnableGemfireCacheTransactions
@EnableManager(start = true)
@CacheServerApplication(logLevel = "error")
public class GeodeServerConfig {
    @Bean("Customers")
    public ReplicatedRegionFactoryBean<Long, Account> createCustomerRegion(GemFireCache gemfireCache) {
        ReplicatedRegionFactoryBean<Long, Account> factory = new ReplicatedRegionFactoryBean<>();
        factory.setCache(gemfireCache);
        factory.setRegionName("Accounts");
        factory.setDataPolicy(DataPolicy.REPLICATE);
        factory.setScope(Scope.DISTRIBUTED_ACK);
        return factory;
    }
}
