package com.betall.core.retail.addressinfrastructure.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.http.HttpStatus;

import com.betall.core.retail.addresscontext.models.AddressInfo;
import com.betall.core.retail.addresscontext.requests.UpdateAddressInfo;
import com.betall.core.retail.addresscontext.representations.AddressRepresentation;
import com.betall.core.retail.addressinfrastructure.AddressInfrastructureDataFactory;

import com.betall.core.retail.addressinfrastructure.models.Address;

import com.betall.core.retail.addressinfrastructure.repositories.ReadAddressRepository;
import com.betall.core.retail.addressinfrastructure.repositories.WriteAddressRepository;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class WriteAddressServiceTest {
    private WriteAddressService service;
    private WriteAddressRepository command;
    private ReadAddressRepository query;

    private AddressInfo addressInfo = AddressInfrastructureDataFactory.initAddressInfo();
    private UpdateAddressInfo updateAddressInfo = AddressInfrastructureDataFactory.initUpdateAddressInfo();
    private Address address = AddressInfrastructureDataFactory.initAddress();

    @BeforeEach
    void setUp() {
        command = mock(WriteAddressRepository.class);
        query = mock(ReadAddressRepository.class);
        service = mock(WriteAddressService.class, withSettings().useConstructor(command, query));

        when(service.save(any(AddressInfo.class))).thenCallRealMethod();
        when(command.save(any(Address.class))).thenReturn(address);

        when(service.update(any(UpdateAddressInfo.class))).thenCallRealMethod();
        when(query.findById(anyInt())).thenReturn(Optional.of(address));

        when(service.delete(anyInt())).thenCallRealMethod();
    }

    @Test
    void testSave() {
        final AddressRepresentation response = service.save(addressInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    void testSaveWithNameIsNull() {
        addressInfo.setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo name is null or empty."));
    }

    @Test
    void testSaveWithNameIsEmpty() {
        addressInfo.setName("  ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo name is null or empty."));
    }

    @Test
    void testSaveWithPhoneIsNull() {
        addressInfo.setPhoneNumber(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo phoneNumber is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo phoneNumber is null or empty."));
    }

    @Test
    void testSaveWithPhoneIsEmpty() {
        addressInfo.setPhoneNumber("  ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo phoneNumber is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo phoneNumber is null or empty."));
    }

    @Test
    void testSaveWithAddressIsNull() {
        addressInfo.setAddress(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo address is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo address is null or empty."));
    }

    @Test
    void testSaveWithAddressIsEmpty() {
        addressInfo.setAddress("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo address is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo address is null or empty."));
    }

    @Test
    void testSaveWithCityIdIsNull() {
        addressInfo.setCityId(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo cityId must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("AddressInfo cityId must be greater than 0."));
    }

    @Test
    void testSaveWithCityIdIsInvalid() {
        addressInfo.setCityId(-1);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo cityId must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("AddressInfo cityId must be greater than 0."));
    }

    @Test
    void testSaveWithDistrictIdIsNull() {
        addressInfo.setDistrictId(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo districtId must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("AddressInfo districtId must be greater than 0."));
    }

    @Test
    void testSaveWithDistrictIdIsInvalid() {
        addressInfo.setDistrictId(-1);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo districtId must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("AddressInfo districtId must be greater than 0."));
    }

    @Test
    void testSaveWithWardIdIsNull() {
        addressInfo.setWardId(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo wardId must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("AddressInfo wardId must be greater than 0."));
    }

    @Test
    void testSaveWithWardIdIsInvalid() {
        addressInfo.setWardId(-1);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo wardId must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("AddressInfo wardId must be greater than 0."));
    }

    @Test
    void testSaveWithStreetIsNull() {
        addressInfo.setStreet(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo street is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo street is null or empty."));
    }

    @Test
    void testSaveWithStreetIsEmpty() {
        addressInfo.setStreet("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo street is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo street is null or empty."));
    }

    @Test
    void testSaveWithHaravanCityCodeIsNull() {
        addressInfo.setHaravanCityCode(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo haravanCityCode is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo haravanCityCode is null or empty."));
    }

    @Test
    void testSaveWithHaravanCityCodeIsEmpty() {
        addressInfo.setHaravanCityCode("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo haravanCityCode is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo haravanCityCode is null or empty."));
    }

    @Test
    void testSaveWithHaravanDistrictCodeIsNull() {
        addressInfo.setHaravanDistrictCode(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo haravanDistrictCode is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo haravanDistrictCode is null or empty."));
    }

    @Test
    void testSaveWithHaravanDistrictCodeIsEmpty() {
        addressInfo.setHaravanDistrictCode("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo haravanDistrictCode is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo haravanDistrictCode is null or empty."));
    }

    @Test
    void testSaveWithHaravanWardCodeIsNull() {
        addressInfo.setHaravanWardCode(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo haravanWardCode is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo haravanWardCode is null or empty."));
    }

    @Test
    void testSaveWithHaravanWardCodeIsEmpty() {
        addressInfo.setHaravanWardCode("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(addressInfo),
            "AddressInfo haravanWardCode is null or empty."
        );

        assertTrue(exception.getMessage().contains("AddressInfo haravanWardCode is null or empty."));
    }

    @Test
    void testUpdate() {
        final AddressRepresentation response = service.update(updateAddressInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    void testUpdateWithIdNotFound() {
        when(query.findById(anyInt())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.update(updateAddressInfo),
            String.format("Can't find address with id %s", updateAddressInfo.getId())
        );

        assertTrue(exception.getMessage().contains("Can't find address with id "));
    }

    @Test
    void testUpdateWithNameIsNull() {
        updateAddressInfo.setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo name is null or empty."));
    }

    @Test
    void testUpdateWithNameIsEmpty() {
        updateAddressInfo.setName("  ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo name is null or empty."));
    }

    @Test
    void testUpdateWithPhoneIsNull() {
        updateAddressInfo.setPhoneNumber(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo phoneNumber is null or empty."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo phoneNumber is null or empty."));
    }

    @Test
    void testUpdateWithPhoneIsEmpty() {
        updateAddressInfo.setPhoneNumber("  ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo phoneNumber is null or empty."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo phoneNumber is null or empty."));
    }

    @Test
    void testUpdateWithAddressIsNull() {
        updateAddressInfo.setCurrentAddress(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo currentAddress is null or empty."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo currentAddress is null or empty."));
    }

    @Test
    void testUpdateWithAddressIsEmpty() {
        updateAddressInfo.setCurrentAddress("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo currentAddress is null or empty."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo currentAddress is null or empty."));
    }

    @Test
    void testUpdateWithCityIdIsNull() {
        updateAddressInfo.setCurrentCity(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo currentCity must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo currentCity must be greater than 0."));
    }

    @Test
    void testUpdateWithCityIdIsInvalid() {
        updateAddressInfo.setCurrentCity(-1);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo currentCity must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo currentCity must be greater than 0."));
    }

    @Test
    void testUpdateWithDistrictIdIsNull() {
        updateAddressInfo.setCurrentDistrict(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo currentDistrict must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo currentDistrict must be greater than 0."));
    }

    @Test
    void testUpdateWithDistrictIdIsInvalid() {
        updateAddressInfo.setCurrentDistrict(-1);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo currentDistrict must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo currentDistrict must be greater than 0."));
    }

    @Test
    void testUpdateWithWardIdIsNull() {
        updateAddressInfo.setCurrentWard(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo currentWard must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo currentWard must be greater than 0."));
    }

    @Test
    void testUpdateWithWardIdIsInvalid() {
        updateAddressInfo.setCurrentWard(-1);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo currentWard must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo currentWard must be greater than 0."));
    }

    @Test
    void testDelete() {
        final HttpStatus response = service.delete(1);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response);
    }

    @Test
    void testDeleteWithIdNotFound() {
        when(query.findById(anyInt())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.delete(addressInfo.getId()),
            String.format("Can't find address with id %s", addressInfo.getId())
        );

        assertTrue(exception.getMessage().contains("Can't find address with id"));
    }

    @Test
    void testDeleteWithIdNull() {
        when(service.delete(any())).thenCallRealMethod();
        when(query.findById(any())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.delete(1),
            "Can't find address with id 1"
        );

        assertTrue(exception.getMessage().contains("Can't find address with id"));
    }
}
