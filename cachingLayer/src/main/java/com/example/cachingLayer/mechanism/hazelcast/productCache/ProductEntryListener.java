package com.example.cachingLayer.mechanism.hazelcast.productCache;

import com.example.cachingLayer.mechanism.hazelcast.productCache.impl.ProductCache;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryEvictedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;
import com.wit.mpesa.commonlogslib.MdcHelper;
import com.wit.mpesa.commonlogslib.MdcItem;
import com.wit.mpesa.commonlogslib.aop.logexecutiontime.LogMethodOperationName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductEntryListener implements EntryEvictedListener<String, String>, EntryUpdatedListener<String, String> {
    private static final Logger LOG = LoggerFactory.getLogger(ProductEntryListener.class);

    @LogMethodOperationName
    @Override
    public void entryEvicted(EntryEvent<String, String> entryEvent) {
        String bearerTokenKey = entryEvent.getKey();

        MdcHelper.putItem(MdcItem.OPERATION_NAME, "Evicted Bearer token key: " + bearerTokenKey);
        LOG.info("{} Entry Evicted", ProductCache.PRODUCT_ENTRY_LISTENER_NAME);
        MdcHelper.removeItem(MdcItem.OPERATION_NAME);
    }

    @Override
    public void entryUpdated(EntryEvent<String, String> entryEvent) {

    }
}
