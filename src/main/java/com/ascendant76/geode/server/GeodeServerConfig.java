package com.ascendant76.geode.server;

import com.ascendant76.geode.domain.Account;
import org.apache.geode.cache.DataPolicy;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.Scope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.ReplicatedRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnableLocator;
import org.springframework.data.gemfire.config.annotation.EnableManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@CacheServerApplication(logLevel = "error")
@EnableLocator
@EnableManager(start = true)
@EnableTransactionManagement
public class GeodeServerConfig {
    @Bean("Accounts")
    public ReplicatedRegionFactoryBean<Long, Account> accountRegion(GemFireCache gemfireCache) {
        ReplicatedRegionFactoryBean<Long, Account> factory = new ReplicatedRegionFactoryBean<>();
        factory.setCache(gemfireCache);
        factory.setRegionName("Accounts");
        factory.setDataPolicy(DataPolicy.REPLICATE);
        factory.setScope(Scope.DISTRIBUTED_ACK);
        return factory;
    }
}
