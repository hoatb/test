package com.betall.core.retail.addresscontext.services;

import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.betall.core.retail.addresscontext.models.AddressListInfo;
import com.betall.core.retail.addresscontext.responses.AddressResponse;

import com.betall.core.retail.addresscontext.representations.AddressRepresentation;
import com.betall.core.retail.addresscontext.representations.AddressesRepresentation;

import com.betall.core.retail.addresscontext.repositories.QueryAddressRepository;

/**
 * Implementation for query province repository
 */
public class QueryAddressService {
    private QueryAddressRepository query;

    public QueryAddressService(final QueryAddressRepository query) {
        this.query = query;
    }

    private AddressesRepresentation processResponses(final Page<AddressResponse> pages) {
        if (pages == null || !pages.hasContent()) {
            return AddressesRepresentation.builder()
                .status(0)
                .message(null)
                .data(
                    AddressListInfo.builder()
                        .lstAddress(new ArrayList<>())
                        .build()
                )
                .build();
        }
        return AddressesRepresentation.builder()
            .status(0)
            .message(null)
            .data(
                AddressListInfo.builder()
                    .lstAddress(pages.getContent())
                    .build()
            )
            .build();
    }

    /**
     * Find all addresses
     *
     * @return AddressesRepresentation
     */
    public AddressesRepresentation findAll(final Integer pageNo, final Integer pageSize) {
        final Page<AddressResponse> pages = query.findAll(PageRequest.of(pageNo, pageSize));
        return processResponses(pages);
    }

    /**
     * Find address by id
     *
     * @return AddressRepresentation
     */
    public AddressRepresentation findById(final Integer id) {
        final Optional<AddressResponse> addressInfo = query.findById(id);
        if (addressInfo.isEmpty()) {
            return AddressRepresentation.builder()
                .status(0)
                .message(null)
                .data(AddressResponse.builder().build())
                .build();
        }
        return AddressRepresentation.builder()
            .status(0)
            .message(null)
            .data(addressInfo.get())
            .build();
    }
}
