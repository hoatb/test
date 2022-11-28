package com.betall.core.retail.addresscontext.repositories;

import org.springframework.http.HttpStatus;

import com.betall.core.retail.addresscontext.models.AddressInfo;
import com.betall.core.retail.addresscontext.requests.UpdateAddressInfo;
import com.betall.core.retail.addresscontext.representations.AddressRepresentation;

/**
 * Declare command actions
 */
public interface CommandAddressRepository {
    AddressRepresentation save(final AddressInfo addressInfo);
    AddressRepresentation update(final UpdateAddressInfo addressInfo);
    HttpStatus delete(final Integer id);
}
