package com.betall.core.retail.addresscontext.services;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.betall.core.retail.addresscontext.models.ProvinceInfo;

import com.betall.core.retail.addresscontext.representations.ProvinceRepresentation;

import com.betall.core.retail.addresscontext.repositories.CommandProvinceRepository;

/**
 * Implementation for command province repository
 */
public class CommandProvinceService {
    private CommandProvinceRepository command;

    public CommandProvinceService(final CommandProvinceRepository command) {
        this.command = command;
    }

    /**
     * Save all province
     *
     * @return HttpStatus
     */
    @Transactional
    public HttpStatus saveAll(final List<ProvinceInfo> provinceInfos) {
        for(ProvinceInfo provinceInfo : provinceInfos) {
            provinceInfo.validate();
        }
        return command.saveAll(provinceInfos);
    }

    /**
     * Save a province
     * @param provinceInfo
     *
     * @return ProvinceRepresentation if successful
     */
    @Transactional
    public ProvinceRepresentation save(final ProvinceInfo provinceInfo) {
        provinceInfo.validate();
        return command.save(provinceInfo);
    }

    /**
     * Update a province
     * @param provinceInfo
     *
     * @return ProvinceRepresentation if successful
     */
    @Transactional
    public ProvinceRepresentation update(final ProvinceInfo provinceInfo) {
        provinceInfo.validate();
        return command.update(provinceInfo);
    }

    /**
     * Soft delete a province
     * @param id
     *
     * @return ProvinceRepresentation if successful
     */
    @Transactional
    public ProvinceRepresentation delete(final Integer id) {
        return command.delete(id);
    }
}
