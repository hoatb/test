package com.betall.core.retail.addresscontext.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.http.HttpStatus;

import com.betall.core.retail.addresscontext.models.AddressInfo;
import com.betall.core.retail.addresscontext.AddressContextDataFactory;
import com.betall.core.retail.addresscontext.requests.UpdateAddressInfo;
import com.betall.core.retail.addresscontext.representations.AddressRepresentation;

import com.betall.core.retail.addresscontext.repositories.CommandAddressRepository;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

class CommandAddressServiceTest {
    private CommandAddressService service;
    private CommandAddressRepository command;

    private AddressRepresentation addressRepresentation = AddressContextDataFactory.initAddressRepresentation();
    private AddressInfo addressInfo = AddressContextDataFactory.initAddressInfo();
    private UpdateAddressInfo updateAddressInfo = AddressContextDataFactory.initUpdateAddressInfo();

    @BeforeEach
    void setUp() {
        command = mock(CommandAddressRepository.class);
        service = mock(CommandAddressService.class, withSettings().useConstructor(command));

        when(service.save(any(AddressInfo.class))).thenCallRealMethod();
        when(command.save(any(AddressInfo.class))).thenReturn(addressRepresentation);

        when(service.update(any(UpdateAddressInfo.class))).thenCallRealMethod();
        when(command.update(any(UpdateAddressInfo.class))).thenReturn(addressRepresentation);

        when(service.delete(anyInt())).thenCallRealMethod();
        when(command.delete(anyInt())).thenReturn(HttpStatus.OK);
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
    void testUpdateWithCurrentCityIsNull() {
        updateAddressInfo.setCurrentCity(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo currentCity must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo currentCity must be greater than 0."));
    }

    @Test
    void testUpdateWithCurrentCityIsInvalid() {
        updateAddressInfo.setCurrentCity(-1);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo currentCity must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo currentCity must be greater than 0."));
    }

    @Test
    void testUpdateWithCurrentDistrictIsNull() {
        updateAddressInfo.setCurrentDistrict(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo currentDistrict must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo currentDistrict must be greater than 0."));
    }

    @Test
    void testUpdateWithCurrentDistrictInvalid() {
        updateAddressInfo.setCurrentDistrict(-1);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo currentDistrict must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo currentDistrict must be greater than 0."));
    }

    @Test
    void testUpdateWithCurrentWardIsNull() {
        updateAddressInfo.setCurrentWard(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(updateAddressInfo),
            "UpdateAddressInfo currentWard must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("UpdateAddressInfo currentWard must be greater than 0."));
    }

    @Test
    void testUpdateWithCurrentWardInvalid() {
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
}
