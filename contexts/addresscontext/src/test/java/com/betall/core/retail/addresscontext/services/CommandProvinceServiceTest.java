package com.betall.core.retail.addresscontext.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import com.betall.core.retail.addresscontext.models.ProvinceInfo;
import com.betall.core.retail.addresscontext.AddressContextDataFactory;
import com.betall.core.retail.addresscontext.representations.ProvinceRepresentation;

import com.betall.core.retail.addresscontext.repositories.CommandProvinceRepository;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandProvinceServiceTest {
    private CommandProvinceService service;
    private CommandProvinceRepository command;

    private List<ProvinceInfo> provinceInfos = AddressContextDataFactory.initProvinceInfos(5);
    private ProvinceInfo provinceInfo = AddressContextDataFactory.initProvinceInfo();
    private ProvinceRepresentation provinceRepresentation = AddressContextDataFactory.initProvinceRepresentation();

    @BeforeEach
    void setUp() {
        command = mock(CommandProvinceRepository.class);
        service = mock(CommandProvinceService.class, withSettings().useConstructor(command));

        when(service.saveAll(anyList())).thenCallRealMethod();
        when(command.saveAll(anyList())).thenReturn(HttpStatus.OK);

        when(service.save(any(ProvinceInfo.class))).thenCallRealMethod();
        when(command.save(any(ProvinceInfo.class))).thenReturn(provinceRepresentation);

        when(service.update(any(ProvinceInfo.class))).thenCallRealMethod();
        when(command.update(any(ProvinceInfo.class))).thenReturn(provinceRepresentation);

        when(service.delete(anyInt())).thenCallRealMethod();
        when(command.delete(anyInt())).thenReturn(provinceRepresentation);
    }

    @Test
    void testSaveAll() {
        final HttpStatus response = service.saveAll(provinceInfos);
        assertNotNull(response);
        assertEquals(response, HttpStatus.OK);
    }

    @Test
    void testSave() {
        final ProvinceRepresentation response = service.save(provinceInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    void testSaveWithIdNull() {
        provinceInfo.setId(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "ProvinceInfo id must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo id must be greater than 0."));
    }

    @Test
    void testSaveWithIdInvalid() {
        provinceInfo.setId(-1);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "ProvinceInfo id must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo id must be greater than 0."));
    }

    @Test
    void testSaveWithCountryIdNull() {
        provinceInfo.setCountryId(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "ProvinceInfo countryId must be 241 (VN country id in Haravan)."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo countryId must be 241 (VN country id in Haravan)."));
    }

    @Test
    void testSaveWithCountryIdInvalid() {
        provinceInfo.setCountryId(0);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "ProvinceInfo countryId must be 241 (VN country id in Haravan)."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo countryId must be 241 (VN country id in Haravan)."));
    }

    @Test
    void testSaveWithCodeNull() {
        provinceInfo.setCode(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "ProvinceInfo code is null or empty."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo code is null or empty."));
    }

    @Test
    void testSaveWithCodeEmpty() {
        provinceInfo.setCode(" ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "ProvinceInfo code is null or empty."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo code is null or empty."));
    }

    @Test
    void testSaveWithNameNull() {
        provinceInfo.setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "ProvinceInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo name is null or empty."));
    }

    @Test
    void testSaveWithNameEmpty() {
        provinceInfo.setName("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "ProvinceInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo name is null or empty."));
    }

    @Test
    void testSaveWithDistrictsNull() {
        provinceInfo.setDistricts(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "ProvinceInfo districts must not be null or empty."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo districts must not be null or empty."));
    }

    @Test
    void testSaveWithDistrictsEmpty() {
        provinceInfo.setDistricts(new ArrayList<>());

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "ProvinceInfo districts must not be null or empty."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo districts must not be null or empty."));
    }

    @Test
    void testSaveWithDistrictIdNull() {
        provinceInfo.getDistricts().get(0).setId(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "DistrictInfo id must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("DistrictInfo id must be greater than 0."));
    }

    @Test
    void testSaveWithDistrictIdInvalid() {
        provinceInfo.getDistricts().get(0).setId(0);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "DistrictInfo id must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("DistrictInfo id must be greater than 0."));
    }

    @Test
    void testSaveWithDistrictCodeNull() {
        provinceInfo.getDistricts().get(0).setCode(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "DistrictInfo code is null or empty."
        );

        assertTrue(exception.getMessage().contains("DistrictInfo code is null or empty."));
    }

    @Test
    void testSaveWithDistrictCodeEmpty() {
        provinceInfo.getDistricts().get(0).setCode(" ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "DistrictInfo code is null or empty."
        );

        assertTrue(exception.getMessage().contains("DistrictInfo code is null or empty."));
    }

    @Test
    void testSaveWithDistrictNameNull() {
        provinceInfo.getDistricts().get(0).setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "DistrictInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("DistrictInfo name is null or empty."));
    }

    @Test
    void testSaveWithDistrictNameEmpty() {
        provinceInfo.getDistricts().get(0).setName("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "DistrictInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("DistrictInfo name is null or empty."));
    }

    @Test
    void testSaveWithWardsNull() {
        provinceInfo.getDistricts().get(0).setWards(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "DistrictInfo wards must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("DistrictInfo wards must not be null or empty"));
    }

    @Test
    void testSaveWithWardsEmpty() {
        provinceInfo.getDistricts().get(0).setWards(new ArrayList<>());

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "DistrictInfo wards must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("DistrictInfo wards must not be null or empty"));
    }

    @Test
    void testSaveWithWardIdNull() {
        provinceInfo.getDistricts().get(0).getWards().get(0).setId(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "WardInfo id must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("WardInfo id must be greater than 0."));
    }

    @Test
    void testSaveWithWardIdInvalid() {
        provinceInfo.getDistricts().get(0).getWards().get(0).setId(0);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "WardInfo id must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("WardInfo id must be greater than 0."));
    }

    @Test
    void testSaveWithWardCodeNull() {
        provinceInfo.getDistricts().get(0).getWards().get(0).setCode(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "WardInfo code is null or empty."
        );

        assertTrue(exception.getMessage().contains("WardInfo code is null or empty."));
    }

    @Test
    void testSaveWithWardCodeEmpty() {
        provinceInfo.getDistricts().get(0).getWards().get(0).setCode(" ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "WardInfo code is null or empty."
        );

        assertTrue(exception.getMessage().contains("WardInfo code is null or empty."));
    }

    @Test
    void testSaveWithWardNameNull() {
        provinceInfo.getDistricts().get(0).getWards().get(0).setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "WardInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("WardInfo name is null or empty."));
    }

    @Test
    void testSaveWithWardNameEmpty() {
        provinceInfo.getDistricts().get(0).getWards().get(0).setName("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(provinceInfo),
            "WardInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("WardInfo name is null or empty."));
    }

    @Test
    void testUpdate() {
        final ProvinceRepresentation response = service.update(provinceInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    void testUpdateWithIdNull() {
        provinceInfo.setId(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "ProvinceInfo id must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo id must be greater than 0."));
    }

    @Test
    void testUpdateWithIdInvalid() {
        provinceInfo.setId(-1);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "ProvinceInfo id must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo id must be greater than 0."));
    }

    @Test
    void testUpdateWithCountryIdNull() {
        provinceInfo.setCountryId(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "ProvinceInfo countryId must be 241 (VN country id in Haravan)."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo countryId must be 241 (VN country id in Haravan)."));
    }

    @Test
    void testUpdateWithCountryIdInvalid() {
        provinceInfo.setCountryId(0);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "ProvinceInfo countryId must be 241 (VN country id in Haravan)."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo countryId must be 241 (VN country id in Haravan)."));
    }

    @Test
    void testUpdateWithCodeNull() {
        provinceInfo.setCode(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "ProvinceInfo code is null or empty."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo code is null or empty."));
    }

    @Test
    void testUpdateWithCodeEmpty() {
        provinceInfo.setCode(" ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "ProvinceInfo code is null or empty."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo code is null or empty."));
    }

    @Test
    void testUpdateWithNameNull() {
        provinceInfo.setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "ProvinceInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo name is null or empty."));
    }

    @Test
    void testUpdateWithNameEmpty() {
        provinceInfo.setName("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "ProvinceInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo name is null or empty."));
    }

    @Test
    void testUpdateWithDistrictsNull() {
        provinceInfo.setDistricts(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "ProvinceInfo districts must not be null or empty."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo districts must not be null or empty."));
    }

    @Test
    void testUpdateWithDistrictsEmpty() {
        provinceInfo.setDistricts(new ArrayList<>());

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "ProvinceInfo districts must not be null or empty."
        );

        assertTrue(exception.getMessage().contains("ProvinceInfo districts must not be null or empty."));
    }

    @Test
    void testUpdateWithDistrictIdNull() {
        provinceInfo.getDistricts().get(0).setId(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "DistrictInfo id must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("DistrictInfo id must be greater than 0."));
    }

    @Test
    void testUpdateWithDistrictIdInvalid() {
        provinceInfo.getDistricts().get(0).setId(0);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "DistrictInfo id must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("DistrictInfo id must be greater than 0."));
    }

    @Test
    void testUpdateWithDistrictCodeNull() {
        provinceInfo.getDistricts().get(0).setCode(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "DistrictInfo code is null or empty."
        );

        assertTrue(exception.getMessage().contains("DistrictInfo code is null or empty."));
    }

    @Test
    void testUpdateWithDistrictCodeEmpty() {
        provinceInfo.getDistricts().get(0).setCode(" ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "DistrictInfo code is null or empty."
        );

        assertTrue(exception.getMessage().contains("DistrictInfo code is null or empty."));
    }

    @Test
    void testUpdateWithDistrictNameNull() {
        provinceInfo.getDistricts().get(0).setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "DistrictInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("DistrictInfo name is null or empty."));
    }

    @Test
    void testUpdateWithDistrictNameEmpty() {
        provinceInfo.getDistricts().get(0).setName("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "DistrictInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("DistrictInfo name is null or empty."));
    }

    @Test
    void testUpdateWithWardsNull() {
        provinceInfo.getDistricts().get(0).setWards(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "DistrictInfo wards must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("DistrictInfo wards must not be null or empty"));
    }

    @Test
    void testUpdateWithWardsEmpty() {
        provinceInfo.getDistricts().get(0).setWards(new ArrayList<>());

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "DistrictInfo wards must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("DistrictInfo wards must not be null or empty"));
    }

    @Test
    void testUpdateWithWardIdNull() {
        provinceInfo.getDistricts().get(0).getWards().get(0).setId(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "WardInfo id must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("WardInfo id must be greater than 0."));
    }

    @Test
    void testUpdateWithWardIdInvalid() {
        provinceInfo.getDistricts().get(0).getWards().get(0).setId(0);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "WardInfo id must be greater than 0."
        );

        assertTrue(exception.getMessage().contains("WardInfo id must be greater than 0."));
    }

    @Test
    void testUpdateWithWardCodeNull() {
        provinceInfo.getDistricts().get(0).getWards().get(0).setCode(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "WardInfo code is null or empty."
        );

        assertTrue(exception.getMessage().contains("WardInfo code is null or empty."));
    }

    @Test
    void testUpdateWithWardCodeEmpty() {
        provinceInfo.getDistricts().get(0).getWards().get(0).setCode(" ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "WardInfo code is null or empty."
        );

        assertTrue(exception.getMessage().contains("WardInfo code is null or empty."));
    }

    @Test
    void testUpdateWithWardNameNull() {
        provinceInfo.getDistricts().get(0).getWards().get(0).setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "WardInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("WardInfo name is null or empty."));
    }

    @Test
    void testUpdateWithWardNameEmpty() {
        provinceInfo.getDistricts().get(0).getWards().get(0).setName("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(provinceInfo),
            "WardInfo name is null or empty."
        );

        assertTrue(exception.getMessage().contains("WardInfo name is null or empty."));
    }

    @Test
    void testDelete() {
        final ProvinceRepresentation response = service.delete(1);
        assertNotNull(response);
    }
}
