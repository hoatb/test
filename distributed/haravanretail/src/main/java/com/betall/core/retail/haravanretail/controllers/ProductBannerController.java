package com.betall.core.retail.haravanretail.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.*;

import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;
import com.betall.core.retail.productbannercontext.responses.ProductBannerRepresentation;
import com.betall.core.retail.productbannercontext.responses.ProductListBannerRepresentation;

import com.betall.core.retail.productbannercontext.services.QueryProductBannerService;
import com.betall.core.retail.productbannercontext.services.CommandProductBannerService;

@RestController
@RequestMapping("/v1/retail/productbanner")
@Tag(name = "Product Banner Operations", description = "Operations for Product Banner")
public class ProductBannerController {
    private CommandProductBannerService command;
    private QueryProductBannerService query;

    public ProductBannerController(final CommandProductBannerService command, final QueryProductBannerService query) {
        this.command = command;
        this.query = query;
    }

    @GetMapping("/")
    @Operation(description = "Load all Product Banner", responses = {
        @ApiResponse(content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = ProductListBannerRepresentation.class)))
        )
    })
    public ProductListBannerRepresentation findAll(
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize) {
        pageNo = pageNo == null ? 0 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        return query.findAll(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(description = "Load Product Banner by id", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = ProductBannerRepresentation.class)))
    })
    public ProductBannerRepresentation findById(
            @PathVariable final Long id) {
        return query.findById(id);
    }

    @Operation(description = "Create a Product Banner", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = ProductBannerRepresentation.class)))
    })
    @PostMapping("/")
    public ProductBannerRepresentation create(@RequestBody final ProductBannerInfo request) {
        return command.save(request);
    }

    @Operation(description = "Update a Product Banner", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = ProductBannerRepresentation.class)))
    })
    @PutMapping("/")
    public ProductBannerRepresentation update(@RequestBody final ProductBannerInfo request) {
        return command.update(request);
    }

    @Operation(description = "Delete a Product Banner by id", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = ProductBannerRepresentation.class)))
    })
    @DeleteMapping("/{id}")
    public ProductBannerRepresentation delete(@PathVariable final Long id) {
        return command.delete(id);
    }
}
