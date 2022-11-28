package com.betall.core.retail.addressinfrastructure.services;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import com.betall.core.retail.addresscontext.models.ProvinceInfo;
import com.betall.core.retail.addresscontext.representations.ProvinceRepresentation;

import com.betall.core.retail.addressinfrastructure.models.Province;
import com.betall.core.retail.addressinfrastructure.utils.EntityMapper;

import com.betall.core.retail.addresscontext.repositories.CommandProvinceRepository;

import com.betall.core.retail.addressinfrastructure.repositories.ReadProvinceRepository;
import com.betall.core.retail.addressinfrastructure.repositories.WriteProvinceRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

@Slf4j
public class WriteProvinceService implements CommandProvinceRepository {
    private WriteProvinceRepository command;
    private ReadProvinceRepository query;

    public WriteProvinceService(final WriteProvinceRepository command, final ReadProvinceRepository query) {
        this.command = command;
        this.query = query;
    }

    @Override
    public HttpStatus saveAll(final List<ProvinceInfo> provinceInfos) {
        deleteAll();

        final List<Province> provinces = new ArrayList<>();
        final EntityMapper converter = new EntityMapper();
        for(ProvinceInfo provinceInfo : provinceInfos) {
            provinceInfo.validate();
            final Province entity = converter.toProvince(provinceInfo);
            provinces.add(entity);
        }

        final List<Province> saved = command.saveAll(provinces);
        log.info(String.format("Successful saved %s province(s).", saved.size()));

        return HttpStatus.OK;
    }

    @Override
    public ProvinceRepresentation save(final ProvinceInfo provinceInfo) {
        provinceInfo.validate();
        final EntityMapper converter = new EntityMapper();
        final Province entity = converter.toProvince(provinceInfo);
        final Province saved = command.save(entity);
        return converter.toProvinceRepresentation(saved);
    }

    @Override
    public ProvinceRepresentation update(final ProvinceInfo provinceInfo) {
        provinceInfo.validate();
        final Province entity = query.findById(provinceInfo.getId()).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find province with id %s", provinceInfo.getId()))
        );
        final EntityMapper converter = new EntityMapper();
        converter.update(entity, provinceInfo);

        final Province saved = command.save(entity);
        return converter.toProvinceRepresentation(saved);
    }

    @Override
    public ProvinceRepresentation delete(final Integer id) {
        final Province entity = query.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find province with id %s", id))
        );
        entity.setIsActive(false);
        final EntityMapper converter = new EntityMapper();
        final Province saved = command.save(entity);
        return converter.toProvinceRepresentation(saved);
    }

    private void deleteAll() {
        log.info("Clear all provinces....");
        final List<Province> provinces = query.findAll();
        command.deleteAll(provinces);
        log.info("Done....");
    }
}
