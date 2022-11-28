package com.betall.core.retail.haravan_repositories;

import java.util.List;
import java.util.ArrayList;

import com.betall.core.retail.haravan_product_context.models.*;
import com.betall.core.retail.haravan_product_context.representations.HaravanProductRepresentation;
import com.betall.core.retail.haravan_product_context.representations.HaravanProductsRepresentation;

public class HaravanHttpProductDataFactory {
    public static HaravanProductsRepresentation initProductRepresentation(int size) {
        return HaravanProductsRepresentation.builder()
            .haravanProducts(initHaravanProductInfos(size))
            .build();
    }
    public static List<HaravanProductInfo> initHaravanProductInfos(int size) {
        final List<HaravanProductInfo> haravanProductInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final HaravanProductInfo haravanProductInfo = HaravanProductInfo.builder()
                .id(i + 1L)
                .bodyHtml("body html")
                .bodyPlain("body plain")
                .createdAt("2022-10-12T02:01:27.483Z")
                .handle(null)
                .images(initImages(2))
                .productType("Other")
                .publishedAt("2022-10-12T02:01:27.483Z")
                .publishedScope("global")
                .tags(null)
                .templateSuffix(null)
                .title("title")
                .updatedAt("2022-10-12T02:01:27.483Z")
                .variants(initVariants(3))
                .vendor("vendor")
                .options(initOptions(2))
                .onlyHideFromList(false)
                .notAllowPromotion(false)
                .build();
            haravanProductInfos.add(haravanProductInfo);
        }
        return haravanProductInfos;
    }

    public static List<HaravanProductImageInfo> initImages(int size) {
        final List<HaravanProductImageInfo> imageInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final HaravanProductImageInfo imageInfo = HaravanProductImageInfo.builder()
                .id(1L)
                .productId(i + 1L)
                .createdAt("2022-10-12T02:01:27.483Z")
                .updatedAt("2022-10-12T02:01:27.483Z")
                .fileName("file-product-1.png")
                .position(i+1)
                .src("https://google.com/images/file-product-1.png")
                .variantIds(new ArrayList<>())
                .build();
            imageInfos.add(imageInfo);
        }
        return imageInfos;
    }

    public static List<HaravanProductVariantInfo> initVariants(int size) {
        final List<HaravanProductVariantInfo> variantInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final HaravanProductVariantInfo variantInfo = HaravanProductVariantInfo.builder()
                .id(i + 1L)
                .barCode(null)
                .compareAtPrice(50000d)
                .createdAt("2022-10-12T02:01:27.483Z")
                .updatedAt("2022-10-12T02:01:27.483Z")
                .fulfillmentService(null)
                .grams(1d)
                .imageId(i + 1L)
                .productId(i + 1L)
                .inventoryAdvance(HaravanInventoryAdvanceInfo.builder()
                    .qtyAvailable(4)
                    .qtyCommited(2)
                    .qtyIncoming(4)
                    .build()
                )
                .option1("Black")
                .option2("M")
                .price(50000d)
                .inventoryPolicy(null)
                .inventoryQuantity(2)
                .position(1)
                .inventoryPolicy(null)
                .build();
            variantInfos.add(variantInfo);
        }

        return variantInfos;
    }

    public static List<HaravanProductOptionInfo> initOptions(int size) {
        final List<HaravanProductOptionInfo> optionInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final HaravanProductOptionInfo optionInfo = HaravanProductOptionInfo.builder()
                .id(i+ 1L)
                .name("Name")
                .productId(i + 1L)
                .position(i+1)
                .build();
            optionInfos.add(optionInfo);
        }

        return optionInfos;
    }

    public static HaravanProductInfo initProductInfo() {
        return HaravanProductInfo.builder()
            .id(1L)
            .bodyHtml("body html")
            .bodyPlain("body plain")
            .createdAt("2022-10-12T02:01:27.483Z")
            .handle(null)
            .images(initImages(2))
            .productType("Other")
            .publishedAt("2022-10-12T02:01:27.483Z")
            .publishedScope("global")
            .tags(null)
            .templateSuffix(null)
            .title("title")
            .updatedAt("2022-10-12T02:01:27.483Z")
            .variants(initVariants(3))
            .vendor("vendor")
            .options(initOptions(2))
            .onlyHideFromList(false)
            .notAllowPromotion(false)
            .build();
    }

    public static HaravanProductRepresentation initProductRepresentation() {
        return HaravanProductRepresentation.builder()
            .haravanProduct(initProductInfo())
            .build();
    }

    public static HaravanProductRepresentation initProductRepresentationNoData() {
        return HaravanProductRepresentation.builder()
            .haravanProduct(HaravanProductInfo.builder().build())
            .build();
    }
}
