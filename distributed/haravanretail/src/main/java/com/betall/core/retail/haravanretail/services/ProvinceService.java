package com.betall.core.retail.haravanretail.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;

import com.betall.core.retail.addresscontext.services.CommandProvinceService;

import com.betall.core.retail.haravanaddresscontext.services.QueryHaravanAddressService;

@Slf4j
@Service
public class ProvinceService {
    @Value("${haravan.batchRequestSleep}")
    private Integer batchRequestSleep;
    private QueryHaravanAddressService haravanService;
    private CommandProvinceService commandProvince;

    public ProvinceService(
            final QueryHaravanAddressService haravanService,
            final CommandProvinceService commandProvince) {
        this.haravanService = haravanService;
        this.commandProvince = commandProvince;
    }

    public HttpStatus syncAll() {
        final Thread thread = new Thread(new SyncProvince(batchRequestSleep, haravanService, commandProvince));
        thread.start();
        return HttpStatus.OK;
    }
}
