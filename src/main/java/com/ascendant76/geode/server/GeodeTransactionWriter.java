package com.ascendant76.geode.server;

import org.apache.geode.cache.EntryEvent;
import org.apache.geode.cache.TransactionEvent;
import org.apache.geode.cache.TransactionWriter;
import org.apache.geode.cache.TransactionWriterException;

import java.util.concurrent.atomic.AtomicBoolean;

public class GeodeTransactionWriter implements TransactionWriter {

    @Override
    public void beforeCommit(TransactionEvent transactionEvent) throws TransactionWriterException {

        var six_found = new AtomicBoolean(false);

        transactionEvent
                .getEvents()
                .forEach(
                        event -> {
                            if (event instanceof EntryEvent<?, ?>
                                    && ((EntryEvent<?, ?>) event).getKey().equals(6L)) {
                                six_found.set(true);
                            }
                        });

        if (six_found.get()) {
            throw new TransactionWriterException(
                    "Customer for Key: 6 is being changed. Failing transaction");
        }
    }
}
