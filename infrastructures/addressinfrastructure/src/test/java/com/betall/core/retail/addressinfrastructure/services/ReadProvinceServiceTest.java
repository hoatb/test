package com.betall.core.retail.addressinfrastructure.services;

import com.betall.core.retail.addresscontext.models.ProvinceInfo;
import com.betall.core.retail.addressinfrastructure.AddressInfrastructureDataFactory;
import com.betall.core.retail.addressinfrastructure.models.Province;
import com.betall.core.retail.addressinfrastructure.repositories.ReadProvinceRepository;
import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReadProvinceServiceTest {
    private ReadProvinceService service;
    private ReadProvinceRepository query;

    private List<Province> provinces = AddressInfrastructureDataFactory.initProvinces(5);
    private Province province = AddressInfrastructureDataFactory.initProvince();

    @BeforeEach
    void setUp() {
        query = mock(ReadProvinceRepository.class);
        service = mock(ReadProvinceService.class, withSettings().useConstructor(query));

        when(service.findAll(any(Pageable.class))).thenCallRealMethod();
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(provinces));

        when(service.findById(anyInt())).thenCallRealMethod();
        when(query.findById(anyInt())).thenReturn(Optional.of(province));

        when(service.findById(any())).thenCallRealMethod();
    }

    @Test
    void testFindAll() {
        final Page<ProvinceInfo> pages = service.findAll(PageRequest.of(0, 10));
        assertNotNull(pages);
        assertTrue(pages.hasContent());
        assertEquals(5, pages.getContent().size());
    }

    @Test
    void testFindAllNoData() {
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        final Page<ProvinceInfo> pages = service.findAll(PageRequest.of(0, 10));
        assertNotNull(pages);
        assertFalse(pages.hasContent());
    }

    @Test
    void testFindById() {
        final Optional<ProvinceInfo> response = service.findById(1);
        assertNotNull(response);
        assertTrue(response.isPresent());
    }

    @Test
    void testFindByIdNoData() {
        when(query.findById(anyInt())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.findById(1),
            String.format("Can't find province with id %s", 1)
        );

        assertTrue(exception.getMessage().contains("Can't find province with id"));
    }

    @Test
    void testFindByIdNull() {
        when(query.findById(any())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.findById(null),
            String.format("Can't find province with id %s", null)
        );

        assertTrue(exception.getMessage().contains("Can't find province with id"));
    }
}
