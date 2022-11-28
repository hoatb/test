package com.betall.core.retail.addressinfrastructure.services;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

import com.betall.core.retail.addresscontext.models.AddressInfo;
import com.betall.core.retail.addresscontext.requests.UpdateAddressInfo;
import com.betall.core.retail.addresscontext.representations.AddressRepresentation;

import com.betall.core.retail.addressinfrastructure.models.Address;
import com.betall.core.retail.addressinfrastructure.utils.EntityMapper;

import com.betall.core.retail.addresscontext.repositories.CommandAddressRepository;

import com.betall.core.retail.addressinfrastructure.repositories.ReadAddressRepository;
import com.betall.core.retail.addressinfrastructure.repositories.WriteAddressRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

@Slf4j
public class WriteAddressService implements CommandAddressRepository {
    private WriteAddressRepository command;
    private ReadAddressRepository query;

    public WriteAddressService(final WriteAddressRepository command, final ReadAddressRepository query) {
        this.command = command;
        this.query = query;
    }

    @Override
    public AddressRepresentation save(final AddressInfo addressInfo) {
        addressInfo.validate();
        final EntityMapper converter = new EntityMapper();
        final Address entity = converter.toAddress(addressInfo);
        final Address saved = command.save(entity);
        return converter.toAddressRepresentation(saved);
    }

    @Override
    public AddressRepresentation update(final UpdateAddressInfo addressInfo) {
        addressInfo.validate();
        final Address entity = query.findById(addressInfo.getId()).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find address with id %s", addressInfo.getId()))
        );
        final EntityMapper converter = new EntityMapper();
        converter.update(entity, addressInfo);

        final Address saved = command.save(entity);
        return converter.toAddressRepresentation(saved);
    }

    @Override
    public HttpStatus delete(final Integer id) {
        final Address entity = query.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find address with id %s", id))
        );

        command.delete(entity);
        return HttpStatus.OK;
    }
}
