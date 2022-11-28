package com.betall.core.retail.haravan_product_context;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import com.betall.core.retail.haravan_product_context.models.HaravanProductInfo;
import com.betall.core.retail.haravan_product_context.models.HaravanProductImageInfo;
import com.betall.core.retail.haravan_product_context.models.HaravanProductOptionInfo;
import com.betall.core.retail.haravan_product_context.models.HaravanProductVariantInfo;

public class HaravanProductContextDataFactory {
    public static List<HaravanProductInfo> initHaravanProducts(int size) {
        final List<HaravanProductInfo> haravanProductInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final HaravanProductInfo haravanProductInfo = initHaravanProduct();
            haravanProductInfos.add(haravanProductInfo);
        }
        return haravanProductInfos;
    }

    public static HaravanProductInfo initHaravanProduct() {
        return HaravanProductInfo.builder()
            .id(1028655526L)
            .bodyHtml("<p>body</p>")
            .bodyPlain("body")
            .createdAt("2020-10-22T03:37:00.905Z")
            .handle("ay-quay-da")
            .images(initProductImage(2))
            .productType("Other")
            .publishedScope("global")
            .tags(null)
            .templateSuffix("product")
            .title("Váy Quây dài")
            .updatedAt("2020-10-22T03:37:00.905Z")
            .variants(initVariants(2))
            .vendor("Khác")
            .options(initOptions(2))
            .onlyHideFromList(false)
            .notAllowPromotion(false)
            .build();
    }

    private static List<HaravanProductImageInfo> initProductImage(int size) {
        final List<HaravanProductImageInfo> images = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final HaravanProductImageInfo imageInfo = HaravanProductImageInfo.builder()
                    .id(1169154000L + i)
                    .position(i+1)
                    .productId(1028655526L)
                    .createdAt("2020-10-22T03:37:03.058")
                    .updatedAt("2020-10-22T03:37:03.058")
                    .src("https://product.hstatic.net/200000244883/product/95326454a3a8eb9d5b571d78b81.jpg")
                    .fileName("95326454a3a8eb9d5b571d78b81.jpg")
                    .variantIds(Arrays.asList(1063014645L))
                .build();
            images.add(imageInfo);
        }
        return images;
    }

    private static List<HaravanProductVariantInfo> initVariants(int size) {
        final List<HaravanProductVariantInfo> variants = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final HaravanProductVariantInfo variantInfo = HaravanProductVariantInfo.builder()
                .barCode(null)
                .id(1063014600L + i)
                .compareAtPrice(800000.0000)
                .createdAt("2020-10-22T03:37:03.058Z")
                .fulfillmentService(null)
                .grams(0.0000)
                .inventoryManagement(null)
                .inventoryPolicy("deny")
                .inventoryQuantity(1)
                .position(1)
                .price(600000.0000)
                .productId(1028655526L)
                .requiresShipping(true)
                .sku("AO551")
                .taxable(true)
                .title("Đen / S")
                .updatedAt("2020-10-22T03:37:03.121Z")
                .imageId(1169154560L)
                .option1("Đen")
                .option2("S")
                .option3(null)
                .inventoryAdvance(null)
                .build();
            variants.add(variantInfo);
        }
        return variants;
    }

    private static List<HaravanProductOptionInfo> initOptions(int size) {
        final List<HaravanProductOptionInfo> options = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final HaravanProductOptionInfo option = HaravanProductOptionInfo.builder()
                .id(1028650000L + i)
                .productId(1028655526L)
                .name("Test")
                .position(i+1)
                .build();
            options.add(option);
        }
        return options;
    }
}
