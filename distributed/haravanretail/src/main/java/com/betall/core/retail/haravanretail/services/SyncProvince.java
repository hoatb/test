package com.betall.core.retail.haravanretail.services;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;

import com.betall.core.retail.addresscontext.models.WardInfo;
import com.betall.core.retail.addresscontext.models.DistrictInfo;
import com.betall.core.retail.addresscontext.models.ProvinceInfo;

import com.betall.core.retail.haravanaddresscontext.models.HaravanWardInfo;
import com.betall.core.retail.haravanaddresscontext.models.HaravanDistrictInfo;
import com.betall.core.retail.haravanaddresscontext.models.HaravanProvinceInfo;

import com.betall.core.retail.haravanaddresscontext.representations.HaravanWardsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanDistrictsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanProvincesRepresentation;

import com.betall.core.retail.addresscontext.services.CommandProvinceService;

import com.betall.core.retail.haravanaddresscontext.services.QueryHaravanAddressService;

@Slf4j
public class SyncProvince implements Runnable {
    private Integer batchRequestSleep;
    private QueryHaravanAddressService haravanService;
    private CommandProvinceService commandProvince;

    public SyncProvince(
            final Integer batchRequestSleep,
            final QueryHaravanAddressService haravanService,
            final CommandProvinceService commandProvince) {
        this.batchRequestSleep = batchRequestSleep;
        this.haravanService = haravanService;
        this.commandProvince = commandProvince;
    }

    @Override
    public void run() {
        log.info("============> Starting sync all provinces....");
        syncAll();
        log.info("============> End sync all provinces.");
    }

    private void syncAll() {
        final List<ProvinceInfo> provinceInfos = new ArrayList<>();
        final HaravanProvincesRepresentation provincesRepresentation = haravanService.findAllProvinces();
        for (HaravanProvinceInfo haravanProvinceInfo : provincesRepresentation.getProvinces()) {
            log.info(String.format("Processing Province '%s - %s' from Haravan.", haravanProvinceInfo.getCode(), haravanProvinceInfo.getName()));
            final ProvinceInfo provinceInfo = toProvinceInfo(haravanProvinceInfo);
            final HaravanDistrictsRepresentation districtsRepresentation = haravanService.findAllDistrictsByProvince(provinceInfo.getId());
            final List<DistrictInfo> districtInfos = syncAll(districtsRepresentation.getDistricts());
            provinceInfo.setDistricts(districtInfos);
            provinceInfos.add(provinceInfo);
        }

        commandProvince.saveAll(provinceInfos);
    }

    private ProvinceInfo toProvinceInfo(final HaravanProvinceInfo haravanProvinceInfo) {
        final ProvinceInfo provinceInfo = ProvinceInfo.builder().build();
        BeanUtils.copyProperties(haravanProvinceInfo, provinceInfo);
        return provinceInfo;
    }

    private DistrictInfo toDistrictInfo(final HaravanDistrictInfo haravanDistrictInfo) {
        final DistrictInfo districtInfo = DistrictInfo.builder().build();
        BeanUtils.copyProperties(haravanDistrictInfo, districtInfo);
        return districtInfo;
    }

    private WardInfo toWardInfo(final HaravanWardInfo haravanWardInfo) {
        final WardInfo wardInfo = WardInfo.builder().build();
        BeanUtils.copyProperties(haravanWardInfo, wardInfo);
        return wardInfo;
    }

    private List<WardInfo> toWardInfos(final List<HaravanWardInfo> haravanWardInfos) {
        final List<WardInfo> wardInfos = new ArrayList<>();
        for(HaravanWardInfo haravanWardInfo : haravanWardInfos) {
            final WardInfo wardInfo = toWardInfo(haravanWardInfo);
            wardInfos.add(wardInfo);
        }
        return wardInfos;
    }

    private List<DistrictInfo> syncAll(final List<HaravanDistrictInfo> haravanDistrictInfos) {
        final List<DistrictInfo> districtInfos = new ArrayList<>();
        for(HaravanDistrictInfo haravanDistrictInfo : haravanDistrictInfos) {
            final DistrictInfo districtInfo = toDistrictInfo(haravanDistrictInfo);
            final HaravanWardsRepresentation wardsRepresentation = haravanService.findAllWardsByDistrictId(haravanDistrictInfo.getId());
            final List<WardInfo> wardInfos = toWardInfos(wardsRepresentation.getWards());
            districtInfo.setWards(wardInfos);
            districtInfos.add(districtInfo);
            sleep();
        }
        return districtInfos;
    }

    private void sleep() {
        try {
            if (batchRequestSleep != -1) {
                Thread.sleep(batchRequestSleep);
            }
        } catch (InterruptedException e) {
            log.error("Thread exception => {}", e.getMessage());
        }
    }
}
