package com.betall.core.retail.haravanretail.controllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.betall.core.retail.addresscontext.models.AddressInfo;
import com.betall.core.retail.addresscontext.requests.UpdateAddressInfo;
import com.betall.core.retail.addresscontext.representations.AddressRepresentation;
import com.betall.core.retail.addresscontext.representations.ProvinceRepresentation;
import com.betall.core.retail.addresscontext.representations.AddressesRepresentation;
import com.betall.core.retail.addresscontext.representations.ProvincesRepresentation;

import com.betall.core.retail.haravanretail.services.ProvinceService;
import com.betall.core.retail.addresscontext.services.QueryAddressService;
import com.betall.core.retail.addresscontext.services.QueryProvinceService;
import com.betall.core.retail.addresscontext.services.CommandAddressService;

@RestController
@RequestMapping("/v1/retail/address")
@Tag(name = "Address Operations", description = "Operations for Address")
public class HaravanAddressController {
    @Autowired
    private ProvinceService service;

    private QueryProvinceService queryProvince;

    private QueryAddressService queryAddress;
    private CommandAddressService commandAddress;

    public HaravanAddressController(
            final QueryProvinceService queryProvince,
            final CommandAddressService commandAddress,
            final QueryAddressService queryAddress) {
        this.queryProvince = queryProvince;
        this.commandAddress = commandAddress;
        this.queryAddress = queryAddress;
    }

    @PostMapping("/haravan")
    @Operation(description = "Sync all VN Provinces from Haravan", responses = {
        @ApiResponse(content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = HttpStatus.class)))
        )
    })
    public HttpStatus sync() {
        return service.syncAll();
    }

    @GetMapping("/provinces")
    @Operation(description = "Load all Provinces", responses = {
        @ApiResponse(content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = ProvincesRepresentation.class)))
        )
    })
    public ProvincesRepresentation findAllProvince(
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize) {
        pageNo = pageNo == null ? 0 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        return queryProvince.findAll(pageNo, pageSize);
    }

    @GetMapping("/provinces/{province}")
    @Operation(description = "Load Province by id",
        requestBody = @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)),
        responses = {
            @ApiResponse(content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProvinceRepresentation.class)))
            )
        }
    )
    public ProvinceRepresentation findDistrictsByProvince(
            @PathVariable Integer province) {
        return queryProvince.findById(province);
    }

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @Operation(description = "Create a new Address",
        requestBody = @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)),
        responses = {
            @ApiResponse(content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = AddressRepresentation.class)))
            )
        }
    )
    public AddressRepresentation createAddress(final AddressInfo addressInfo) {
        return commandAddress.save(addressInfo);
    }

    @PutMapping(value = "/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @Operation(description = "Update an Address",
        requestBody = @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)),
        responses = {
            @ApiResponse(content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = AddressRepresentation.class)))
            )
        }
    )
    public AddressRepresentation updateAddress(final UpdateAddressInfo addressInfo) {
        return commandAddress.update(addressInfo);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete an Address", responses = {
        @ApiResponse(content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = HttpStatus.class)))
        )
    })
    public HttpStatus deleteAddress(@PathVariable Integer id) {
        return commandAddress.delete(id);
    }

    @GetMapping("/")
    @Operation(description = "Load all Address", responses = {
        @ApiResponse(content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = AddressesRepresentation.class)))
        )
    })
    public AddressesRepresentation findAllAddress(
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize) {
        pageNo = pageNo == null ? 0 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        return queryAddress.findAll(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(description = "Load Address by id", responses = {
        @ApiResponse(content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = AddressRepresentation.class)))
        )
    })
    public AddressRepresentation findAddressById(
            @PathVariable Integer id) {
        return queryAddress.findById(id);
    }
}
