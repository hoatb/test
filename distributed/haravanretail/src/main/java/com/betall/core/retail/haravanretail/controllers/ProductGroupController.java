package com.betall.core.retail.haravanretail.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;

import com.betall.core.retail.productgroupcontext.responses.ProductGroupRepresentation;
import com.betall.core.retail.productgroupcontext.responses.ProductListGroupRepresentation;

import com.betall.core.retail.productgroupcontext.services.QueryProductGroupService;
import com.betall.core.retail.productgroupcontext.services.CommandProductGroupService;

@RestController
@RequestMapping("/v1/retail/productgroup")
@Tag(name = "Product Group Operations", description = "Operations for Product Group")
public class ProductGroupController {
    private CommandProductGroupService command;
    private QueryProductGroupService query;

    public ProductGroupController(final CommandProductGroupService command, final QueryProductGroupService query) {
        this.command = command;
        this.query = query;
    }

    @GetMapping("/")
    @Operation(description = "Load all Product Group", responses = {
        @ApiResponse(content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = ProductListGroupRepresentation.class)))
        )
    })
    public ProductListGroupRepresentation findAll(
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize) {
        pageNo = pageNo == null ? 0 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        return query.findAll(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(description = "Load Product Group by id", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = ProductGroupRepresentation.class)))
    })
    public ProductGroupRepresentation findById(
            @PathVariable final Long id) {
        return query.findById(id);
    }

    @Operation(description = "Create a Product Group", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = ProductGroupRepresentation.class)))
    })
    @PostMapping("/")
    public ProductGroupRepresentation create(@RequestBody final ProductGroupInfo request) {
        return command.save(request);
    }

    @Operation(description = "Update a Product Group", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = ProductGroupRepresentation.class)))
    })
    @PutMapping("/")
    public ProductGroupRepresentation update(@RequestBody final ProductGroupInfo request) {
        return command.update(request);
    }

    @Operation(description = "Delete a Product Group by id", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = ProductGroupRepresentation.class)))
    })
    @DeleteMapping("/{id}")
    public ProductGroupRepresentation delete(@PathVariable final Long id) {
        return command.delete(id);
    }
}
