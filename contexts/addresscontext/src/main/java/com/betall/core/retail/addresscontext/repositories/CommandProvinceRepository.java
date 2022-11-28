package com.betall.core.retail.addresscontext.repositories;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.betall.core.retail.addresscontext.models.ProvinceInfo;
import com.betall.core.retail.addresscontext.representations.ProvinceRepresentation;

/**
 * Declare command actions
 */
public interface CommandProvinceRepository {
    HttpStatus saveAll(final List<ProvinceInfo> provinceInfos);
    ProvinceRepresentation save(final ProvinceInfo provinceInfo);
    ProvinceRepresentation update(final ProvinceInfo provinceInfo);
    ProvinceRepresentation delete(final Integer id);
}
