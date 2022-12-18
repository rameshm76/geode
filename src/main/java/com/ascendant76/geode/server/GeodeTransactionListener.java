package com.ascendant76.geode.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.CacheEvent;
import org.apache.geode.cache.EntryEvent;
import org.apache.geode.cache.TransactionEvent;
import org.apache.geode.cache.util.TransactionListenerAdapter;

@Slf4j
public class GeodeTransactionListener extends TransactionListenerAdapter {

    @Override
    public void afterFailedCommit(TransactionEvent event) {}

    @Override
    public void afterRollback(TransactionEvent event) {
        log.info(
                "In afterRollback for entry(s) ["
                        + event.getEvents().stream().map(this::getEventInfo).toList()
                        + "]");
    }

    private String getEventInfo(CacheEvent<?, ?> cacheEvent) {
        if (cacheEvent instanceof EntryEvent) {
            return ((EntryEvent<?, ?>) cacheEvent).getNewValue().toString();
        } else {
            return cacheEvent.toString();
        }
    }
}
