package com.betall.core.retail.addressinfrastructure.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.betall.core.retail.addresscontext.responses.AddressResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.addressinfrastructure.models.Address;
import com.betall.core.retail.addressinfrastructure.utils.EntityMapper;

import com.betall.core.retail.addresscontext.repositories.QueryAddressRepository;

import com.betall.core.retail.addressinfrastructure.repositories.ReadAddressRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

public class ReadAddressService implements QueryAddressRepository {
    private ReadAddressRepository query;

    /**
     * Convert list Address entity to AddressInfo with page
     * @param addresses
     *
     * @return Page<AddressInfo>
     */
    private Page<AddressResponse> processResponses(final Page<Address> addresses, final Pageable page, final Long total) {
        if (addresses.hasContent()) {
            final List<AddressResponse> responses = addresses.stream().map(this::processResponse).collect(Collectors.toList());
            return new PageImpl<>(responses, page, total);
        }
        return new PageImpl<>(new ArrayList<>());
    }

    private AddressResponse processResponse(final Address address) {
        final EntityMapper converter = new EntityMapper();
        return converter.toAddressResponse(address);
    }

    public ReadAddressService(final ReadAddressRepository query) {
        this.query = query;
    }

    @Override
    public Page<AddressResponse> findAll(final Pageable page) {
        final Page<Address> addresses = query.findAll(page);
        return processResponses(addresses, page, Long.valueOf(addresses.getSize()));
    }

    @Override
    public Optional<AddressResponse> findById(final Integer id) {
        final Address address = query.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find address with id %s", id))
        );
        return Optional.of(processResponse(address));
    }
}
