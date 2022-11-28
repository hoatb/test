package com.betall.core.retail.haravanretail.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.*;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;
import com.betall.core.retail.producttypecontext.responses.ProductTypeRepresentation;
import com.betall.core.retail.producttypecontext.responses.ProductListTypeRepresentation;

import com.betall.core.retail.producttypecontext.services.QueryProductTypeService;
import com.betall.core.retail.producttypecontext.services.CommandProductTypeService;

@RestController
@RequestMapping("/v1/retail/producttype")
@Tag(name = "Product Type Operations", description = "Operations for Product Type")
public class ProductTypeController {
    private CommandProductTypeService command;
    private QueryProductTypeService query;

    public ProductTypeController(final CommandProductTypeService command, final QueryProductTypeService query) {
        this.command = command;
        this.query = query;
    }

    @GetMapping("/")
    @Operation(description = "Load all Product Type", responses = {
        @ApiResponse(content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = ProductListTypeRepresentation.class)))
        )
    })
    public ProductListTypeRepresentation findAll(
        @RequestParam(required = false) Integer pageNo,
        @RequestParam(required = false) Integer pageSize) {
        pageNo = pageNo == null ? 0 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        return query.findAll(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(description = "Load Product Type by id", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = ProductTypeRepresentation.class)))
    })
    public ProductTypeRepresentation findById(
            @PathVariable final Long id) {
        return query.findById(id);
    }

    @Operation(description = "Create a Product Type", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = ProductTypeRepresentation.class)))
    })
    @PostMapping("/")
    public ProductTypeRepresentation create(@RequestBody final ProductTypeInfo request) {
        return command.save(request);
    }

    @Operation(description = "Update a Product Type", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = ProductTypeRepresentation.class)))
    })
    @PutMapping("/")
    public ProductTypeRepresentation update(@RequestBody final ProductTypeInfo request) {
        return command.update(request);
    }

    @Operation(description = "Delete a Product Type by id", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = ProductTypeRepresentation.class)))
    })
    @DeleteMapping("/{id}")
    public ProductTypeRepresentation delete(@PathVariable final Long id) {
        return command.delete(id);
    }
}
