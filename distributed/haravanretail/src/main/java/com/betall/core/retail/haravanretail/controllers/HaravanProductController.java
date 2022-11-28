package com.betall.core.retail.haravanretail.controllers;

import com.betall.core.retail.haravanretail.services.ProductService;
import com.betall.core.retail.productcontext.models.ProductDetailInfo;
import com.betall.core.retail.productcontext.requests.OrderProductList;
import com.betall.core.retail.productcontext.requests.ProductListRequest;
import com.betall.core.retail.productcontext.responses.ProductDetailRepresentation;
import com.betall.core.retail.productcontext.responses.ProductInfoRepresentation;
import com.betall.core.retail.productcontext.services.CommandProductService;
import com.betall.core.retail.productcontext.services.QueryProductColorService;
import com.betall.core.retail.productcontext.services.QueryProductService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.betall.core.retail.haravan_product_context.models.HaravanProductInfo;
import com.betall.core.retail.haravan_product_context.services.QueryHaravanProductService;

@RestController
@RequestMapping("/v1/retail/products")
@Tag(name = "Product Operations", description = "Operations for Product")
public class HaravanProductController {
    @Autowired
    private ProductService service;

    private QueryHaravanProductService haravanQuery;
    private QueryProductService query;
    private CommandProductService command;
    private QueryProductColorService queryProductColor;

    public HaravanProductController(
            QueryHaravanProductService haravanQuery,
            QueryProductService query,
            CommandProductService command,
            QueryProductColorService queryProductColor) {
        this.haravanQuery = haravanQuery;
        this.query = query;
        this.command = command;
        this.queryProductColor = queryProductColor;
    }

    /**
     * Sync all product from Haravan
     * @return HttpStatus
     */
    @PostMapping("/haravan/")
    @Operation(description = "Sync all product from Haravan", responses = {
        @ApiResponse(content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = HttpStatus.class)))
        )
    })
    public HttpStatus sync() {
        return service.syncAll();
    }

    /**
     * Get all products from Haravan
     * @return List of products
     */
    @GetMapping("/haravan/")
    @Operation(description = "Load all Haravan Product", responses = {
        @ApiResponse(content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = List.class)))
        )
    })
    public List<HaravanProductInfo> getHaravanProducts() {
        return haravanQuery.findAllProducts();
    }

    /**
     * Get product by id from Haravan
     * @param id (Product id)
     * @return Product
     */
    @GetMapping("/haravan/{id}")
    @Operation(description = "Load Haravan Product by id", responses = {
        @ApiResponse(content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = HaravanProductInfo.class))))
    })
    public HaravanProductInfo getHaravanProductById(@PathVariable Long id) {
        return haravanQuery.findProductById(id);
    }


    /**
     * Get all products from Database local
     * @return List of products
     */
    @PostMapping(value = "/product-list", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @Operation(
        description = "Load all Product (api old: product-list)",
        requestBody = @RequestBody(content = @Content(
                mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = ProductListRequest.class))
        )),
        responses = {
            @ApiResponse(content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProductInfoRepresentation.class)))
            )
        }
    )
    public ProductInfoRepresentation findAll(final ProductListRequest request) {
        request.checkSetUp();
        return query.findAll(request);
    }

    /**
     * Get product by OrderProductList
     * @param orderProductList
     * @return ProductInfoRepresentation
     */
    @PostMapping(value = "/list-by-id", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @Operation(
        description = "Get product by OrderProductList (api old: list-by-id)",
        requestBody = @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)),
        responses = {
            @ApiResponse(content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProductInfoRepresentation.class)))
            )
        }
    )
    public ProductInfoRepresentation findProductListById(@org.springframework.web.bind.annotation.RequestBody final OrderProductList orderProductList) {
        return queryProductColor.findProductListById(orderProductList);
    }

    /**
     * Get product by id from Database local
     * @param productId
     * @return Product
     */
    @PostMapping(value = "/product-detail", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @Operation(
        description = "Load Product by id (api old: product-detail)",
        requestBody = @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)),
        responses = {
            @ApiResponse(content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProductDetailRepresentation.class)))
            )
        }
    )
    public ProductDetailRepresentation findById(final Long productId) {
        return query.findById(productId);
    }

    /**
     * Create new product from Database local
     * @param request (Product info)
     * @return Product
     */
    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @Operation(
        description = "Create a Product",
        requestBody = @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)),
        responses = {
            @ApiResponse(content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProductDetailRepresentation.class)))
            )
        }
    )
    public ProductDetailRepresentation create(final ProductDetailInfo request) {
        return command.save(request);
    }

    /**
     * Update product from Database local
     * @param request (Product info)
     * @return Product
     */
    @PutMapping(value = "/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @Operation(
        description = "Update a Product",
        requestBody = @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)),
        responses = {
            @ApiResponse(content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProductDetailRepresentation.class)))
            )
        }
    )
    public ProductDetailRepresentation update(final ProductDetailInfo request) {
        return command.update(request);
    }

    /**
     * Delete product from Database local
     * @param id (Product id)
     * @return HttpStatus
     */
    @DeleteMapping("/{id}")
    @Operation(description = "Delete a Product", responses = {
        @ApiResponse(content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = ProductDetailRepresentation.class))))
    })
    public ProductDetailRepresentation delete(@PathVariable final Long id) {
        return command.delete(id);
    }
}
