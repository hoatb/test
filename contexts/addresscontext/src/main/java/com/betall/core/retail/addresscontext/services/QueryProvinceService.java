package com.betall.core.retail.addresscontext.services;

import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.betall.core.retail.addresscontext.models.ProvinceInfo;
import com.betall.core.retail.addresscontext.representations.ProvinceRepresentation;
import com.betall.core.retail.addresscontext.representations.ProvincesRepresentation;

import com.betall.core.retail.addresscontext.repositories.QueryProvinceRepository;

/**
 * Implementation for query province repository
 */
public class QueryProvinceService {
    private QueryProvinceRepository query;

    public QueryProvinceService(final QueryProvinceRepository query) {
        this.query = query;
    }

    private ProvincesRepresentation processResponses(final Page<ProvinceInfo> pages) {
        if (pages == null || !pages.hasContent()) {
            return ProvincesRepresentation.builder()
                .status(0)
                .message(null)
                .data(new ArrayList<>())
                .build();
        }
        return ProvincesRepresentation.builder()
            .status(0)
            .message(null)
            .data(pages.getContent())
            .build();
    }

    /**
     * Find all province
     *
     * @return ProvincesRepresentation
     */
    public ProvincesRepresentation findAll(final Integer pageNo, final Integer pageSize) {
        final Page<ProvinceInfo> pages = query.findAll(PageRequest.of(pageNo, pageSize));
        return processResponses(pages);
    }

    /**
     * Find province by id
     *
     * @return ProvinceRepresentation
     */
    public ProvinceRepresentation findById(final Integer id) {
        final Optional<ProvinceInfo> provinceInfo = query.findById(id);
        if (provinceInfo.isEmpty()) {
            return ProvinceRepresentation.builder()
                .status(0)
                .message(null)
                .data(ProvinceInfo.builder().build())
                .build();
        }
        return ProvinceRepresentation.builder()
            .status(0)
            .message(null)
            .data(provinceInfo.get())
            .build();
    }
}
