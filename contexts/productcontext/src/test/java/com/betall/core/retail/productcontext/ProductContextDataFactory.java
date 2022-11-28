package com.betall.core.retail.productcontext;

import com.betall.core.retail.productcontext.models.*;
import com.betall.core.retail.productcontext.requests.OrderProductList;
import com.betall.core.retail.productcontext.requests.OrderProductInfo;
import com.betall.core.retail.productcontext.requests.ProductListRequest;
import com.betall.core.retail.productcontext.responses.ProductDetailRepresentation;

import java.util.ArrayList;
import java.util.List;

public class ProductContextDataFactory {
    public static ProductInfoList initProductInfoList(long l) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (long j = 0; j < l; j++) {
            ProductInfo productInfo = ProductInfo.builder()
                .productId(j)
                .productName(String.format("Product name %s", j))
                .star(null)
                .numberReview(null)
                .content(null)
                .percent(null)
                .brand(String.format("Brand %s", j))
                .priceDiscount(1000D)
                .price(2000D)
                .imageUrl(String.format("url image %s", j))
                .colorId(j)
                .colorName(String.format("color name %s", j))
                .portalId(null)
                .number(null)
                .haravanProductId(j)
                .productColorActive(1)
                .build();
            productInfoList.add(productInfo);
        }
        return ProductInfoList.builder().total(productInfoList.size()).lstProduct(productInfoList).build();
    }

    public static ProductDetailInfo initProductDetailInfo(long i) {
        return ProductDetailInfo.builder()
            .productId(i)
            .productName(String.format("Product %s", i))
            .star(null)
            .numberReview(null)
            .priceDiscount(100000.0)
            .price(200000.0)
            .percent(null)
            .content(null)
            .brand("brand")
            .lstProductColor(initProductColorInfoList(i))
            .lstProductAdvertisement(initProductAdvertisementInfoList(i))
            .lstProductSpecification(initProductSpecificationInfoList(i))
            .description("description")
            .repaymentAmountByMonth(null)
            .isAllowInstallment(null)
            .isActive(true)
            .images(initProductImageInfoList(i))
            .build();
    }

    private static List<ProductColorInfo> initProductColorInfoList(Long productId) {
        List<ProductColorInfo> productColorInfos = new ArrayList<>();
        productColorInfos.add(ProductColorInfo.builder()
            .id(productId)
            .productId(productId)
            .colorName(String.format("colorName %d", productId))
            .colorCode(String.format("colorCode %d", productId))
            .priceDiscount(100000.0)
            .price(200000.0)
            .percent(null)
            .urlProductImage(null)
            .build());
        return productColorInfos;
    }

    private static List<ProductAdvertisementInfo> initProductAdvertisementInfoList(Long productId) {
        List<ProductAdvertisementInfo> productAdvertisementInfos = new ArrayList<>();
        productAdvertisementInfos.add(ProductAdvertisementInfo.builder()
            .id(productId)
            .productId(productId)
            .content(String.format("content %d", productId))
            .title(String.format("title %d", productId))
            .build());
        return productAdvertisementInfos;
    }

    private static List<ProductSpecificationInfo> initProductSpecificationInfoList(Long productId) {
        List<ProductSpecificationInfo> productSpecificationInfos = new ArrayList<>();
        productSpecificationInfos.add(ProductSpecificationInfo.builder()
            .id(productId)
            .productId(productId)
            .content(String.format("content %d", productId))
            .title(String.format("title %d", productId))
            .build());
        return productSpecificationInfos;
    }

    private static List<ProductImageInfo> initProductImageInfoList(Long productId) {
        List<ProductImageInfo> productImageInfos = new ArrayList<>();
        productImageInfos.add(ProductImageInfo.builder()
            .id(productId)
            .productId(productId)
            .fileName(String.format("file name %s", productId))
            .position(1)
            .src(String.format("src %s", productId))
            .variantIds(null)
            .build());
        return productImageInfos;
    }

    public static OrderProductList initLstOrderProduct(Long l) {
        List<OrderProductInfo> productInfoList = new ArrayList<>();
        for (long j = 0; j < l; j++) {
            OrderProductInfo orderProductInfo = OrderProductInfo.builder()
                .productId(j)
                .colorId(j)
                .build();
            productInfoList.add(orderProductInfo);
        }
        return OrderProductList.builder().lstOrderProduct(productInfoList).build();
    }

    public static ProductDetailRepresentation initProductDetailRepresentation() {
        return ProductDetailRepresentation.builder()
            .data(
                ProductDetail.builder()
                    .productDetailInfo(initProductDetailInfo(1L))
                    .build()
            )
            .build();
    }

    public static ProductDetailRepresentation initProductDetailRepresentationDelete() {
        ProductDetailInfo productInfo = initProductDetailInfo(1L);
        productInfo.setIsActive(false);
        return ProductDetailRepresentation.builder()
            .data(
                ProductDetail.builder()
                    .productDetailInfo(productInfo)
                    .build()
            )
            .build();
    }

    public static ProductDetailList initProductDetailList(long l) {
        List<ProductDetailInfo> productDetailInfoList = new ArrayList<>();
        for (long i = 0; i < l; i++) {
            ProductDetailInfo productDetailInfo = initProductDetailInfo(i);
            productDetailInfoList.add(productDetailInfo);
        }
        return ProductDetailList.builder().productsDetail(productDetailInfoList).build();
    }

    public static ProductListRequest initProductListRequest() {
        return ProductListRequest.builder()
            .groupId(null)
            .lstFilterId(new ArrayList<>())
            .page(0)
            .pageSize(10)
            .typeId(null)
            .build();
    }
}
