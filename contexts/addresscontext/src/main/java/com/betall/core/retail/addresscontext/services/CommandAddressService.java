package com.betall.core.retail.addresscontext.services;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.betall.core.retail.addresscontext.models.AddressInfo;
import com.betall.core.retail.addresscontext.requests.UpdateAddressInfo;
import com.betall.core.retail.addresscontext.representations.AddressRepresentation;

import com.betall.core.retail.addresscontext.repositories.CommandAddressRepository;

/**
 * Implementation for command province repository
 */
public class CommandAddressService {
    private CommandAddressRepository command;

    public CommandAddressService(final CommandAddressRepository command) {
        this.command = command;
    }

    /**
     * Save an address
     * @param addressInfo
     *
     * @return AddressRepresentation if successful
     */
    @Transactional
    public AddressRepresentation save(final AddressInfo addressInfo) {
        addressInfo.validate();
        return command.save(addressInfo);
    }

    /**
     * Update an address
     * @param addressInfo
     *
     * @return AddressRepresentation if successful
     */
    @Transactional
    public AddressRepresentation update(final UpdateAddressInfo addressInfo) {
        addressInfo.validate();
        return command.update(addressInfo);
    }

    /**
     * Soft delete an address
     * @param id
     *
     * @return AddressRepresentation if successful
     */
    @Transactional
    public HttpStatus delete(final Integer id) {
        return command.delete(id);
    }
}
