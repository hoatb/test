package com.betall.core.retail.haravanaddresscontext;

import com.betall.core.retail.haravanaddresscontext.models.HaravanDistrictInfo;
import com.betall.core.retail.haravanaddresscontext.models.HaravanProvinceInfo;
import com.betall.core.retail.haravanaddresscontext.models.HaravanWardInfo;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanDistrictsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanProvincesRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanWardsRepresentation;

import java.util.ArrayList;
import java.util.List;

public class QueryHaravanAddressDataFactory {
    public static HaravanProvincesRepresentation initHaravanProvincesRepresentation(int size) {
        return HaravanProvincesRepresentation.builder()
            .provinces(initHaravanProvinceInfos(size))
            .build();
    }

    public static List<HaravanProvinceInfo> initHaravanProvinceInfos(int size) {
        final List<HaravanProvinceInfo> haravanProvinceInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final HaravanProvinceInfo haravanProvinceInfo = HaravanProvinceInfo.builder()
                .id(i + 1)
                .countryId(241)
                .code("Code " + i)
                .name("Name " + i)
                .tax(null)
                .taxName(null)
                .taxPercentage(null)
                .taxType(null)
                .build();
            haravanProvinceInfos.add(haravanProvinceInfo);
        }
        return haravanProvinceInfos;
    }

    public static HaravanProvincesRepresentation initHaravanProvincesNoData() {
        return HaravanProvincesRepresentation.builder()
            .provinces(new ArrayList<>())
            .build();
    }

    public static HaravanDistrictsRepresentation initHaravanDistrictsRepresentation(int size) {
        return HaravanDistrictsRepresentation.builder()
            .districts(initHaravanDistrictInfos(size))
            .build();
    }

    public static List<HaravanDistrictInfo> initHaravanDistrictInfos(int size) {
        final List<HaravanDistrictInfo> haravanDistrictInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final HaravanDistrictInfo haravanDistrictInfo = HaravanDistrictInfo.builder()
                .id(1)
                .code("Code " + i)
                .name("Name " + i)
                .provinceId(1)
                .build();
            haravanDistrictInfos.add(haravanDistrictInfo);
        }
        return haravanDistrictInfos;
    }

    public static HaravanDistrictsRepresentation initHaravanDistrictsNoData() {
        return HaravanDistrictsRepresentation.builder()
            .districts(new ArrayList<>())
            .build();
    }

    public static HaravanWardsRepresentation initHaravanWardsRepresentation(int size) {
        return HaravanWardsRepresentation.builder()
            .wards(initHaravanWardInfos(size))
            .build();
    }

    public static List<HaravanWardInfo> initHaravanWardInfos(int size) {
        final List<HaravanWardInfo> haravanWardInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final HaravanWardInfo haravanWardInfo = HaravanWardInfo.builder()
                .id(1)
                .code("Code " + i)
                .name("Name " + i)
                .districtId(1)
                .build();
            haravanWardInfos.add(haravanWardInfo);
        }
        return haravanWardInfos;
    }

    public static HaravanWardsRepresentation initHaravanWardsRepresentation() {
        return HaravanWardsRepresentation.builder()
            .wards(new ArrayList<>())
            .build();
    }
}
