package com.betall.core.retail.addressinfrastructure.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.addresscontext.models.ProvinceInfo;

import com.betall.core.retail.addressinfrastructure.models.Province;
import com.betall.core.retail.addressinfrastructure.utils.EntityMapper;

import com.betall.core.retail.addresscontext.repositories.QueryProvinceRepository;

import com.betall.core.retail.addressinfrastructure.repositories.ReadProvinceRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

public class ReadProvinceService implements QueryProvinceRepository {
    private ReadProvinceRepository query;

    public ReadProvinceService(final ReadProvinceRepository query) {
        this.query = query;
    }

    /**
     * Convert list Province entity to ProvinceInfo with page
     * @param provinces
     *
     * @return Page<ProvinceInfo>
     */
    private Page<ProvinceInfo> processResponses(final Page<Province> provinces, final Pageable page, final Long total) {
        if (provinces.hasContent()) {
            final List<ProvinceInfo> responses = provinces.stream().map(this::processResponse).collect(Collectors.toList());
            return new PageImpl<>(responses, page, total);
        }
        return new PageImpl<>(new ArrayList<>());
    }

    private ProvinceInfo processResponse(final Province province) {
        final EntityMapper converter = new EntityMapper();
        return converter.toProvinceInfo(province);
    }

    @Override
    public Page<ProvinceInfo> findAll(final Pageable page) {
        final Page<Province> provinces = query.findAll(page);
        return processResponses(provinces, page, Long.valueOf(provinces.getSize()));
    }

    @Override
    public Optional<ProvinceInfo> findById(final Integer id) {
        final Province province = query.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find province with id %s", id))
        );
        return Optional.of(processResponse(province));
    }
}
