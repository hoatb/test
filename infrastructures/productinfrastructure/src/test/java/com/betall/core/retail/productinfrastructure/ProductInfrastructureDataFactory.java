package com.betall.core.retail.productinfrastructure;

import com.betall.core.retail.productcontext.models.ProductColorInfo;
import com.betall.core.retail.productcontext.models.ProductDetailInfo;
import com.betall.core.retail.productcontext.models.ProductDetailList;
import com.betall.core.retail.productcontext.requests.OrderProductInfo;
import com.betall.core.retail.productcontext.requests.OrderProductList;
import com.betall.core.retail.productinfrastructure.models.Product;
import com.betall.core.retail.productinfrastructure.models.ProductColor;
import com.betall.core.retail.productinfrastructure.models.ProductImage;

import java.util.*;

public class ProductInfrastructureDataFactory {
    public static List<Product> initProductList(long size) {
        List<Product> products = new ArrayList<>();
        for (long i = 0; i < size; i++) {
            Product product = initProduct(i);
            products.add(product);
        }
        return products;
    }

    private static Set<ProductColor> initProductColorSet(Product product) {
        Set<ProductColor> productColors = new HashSet<>();
        productColors.add(ProductColor.builder()
            .id(product.getProductId())
            .product(product)
            .colorName(String.format("Color %d", product.getProductId()))
            .colorCode(String.format("Color %d", product.getProductId()))
            .priceDiscount(1000D)
            .price(2000D)
            .percent(null)
            .urlProductImage(initUrlProductImageSet(product.getProductId()))
            .build()
        );
        return productColors;
    }

    private static Set<String> initUrlProductImageSet(long productId) {
        Set<String> urlProductImages = new HashSet<>();
        urlProductImages.add(String.format("Url %d", productId));
        return urlProductImages;
    }

    private static Set<ProductImage> initProductImageSet(Product product) {
        Set<ProductImage> productImageSet = new HashSet<>();
            ProductImage productImage = ProductImage.builder()
                .id(product.getProductId())
                .product(product)
                .fileName(String.format("file name %s", product.getProductId()))
                .position(1)
                .src(String.format("src %s", product.getProductId()))
                .variantIds(null)
                .build();
            productImageSet.add(productImage);
        return productImageSet;
    }

    public static Product initProduct(long i) {
        Product product = Product.builder()
            .productId(i)
            .productName(String.format("Product %d", i))
            .star(null)
            .numberReview(null)
            .priceDiscount(1000D)
            .price(2000D)
            .percent(null)
            .content(null)
            .brand("Brand")
            .productColors(null)
            .productAdvertisements(null)
            .productSpecifications(null)
            .description("hello 123")
            .repaymentAmountByMonth(null)
            .isAllowInstallment(null)
            .isActive(true)
            .images(null)
            .build();
        product.setProductColors(initProductColorSet(product));
        product.setImages(initProductImageSet(product));
        return product;
    }

    public static ProductDetailList initProductDetailList(long size) {
        List<ProductDetailInfo> productDetailList = new ArrayList<>();
        for (long i = 0; i < size; i++) {
            productDetailList.add(initProductDetailInfo(i));
        }
        return ProductDetailList.builder().productsDetail(productDetailList).build();
    }

    public static ProductDetailInfo initProductDetailInfo(long i)
    {
        return ProductDetailInfo.builder()
            .productId(i)
            .productName(String.format("Product %d", i))
            .star(null)
            .numberReview(null)
            .priceDiscount(1000D)
            .price(2000D)
            .percent(null)
            .content(null)
            .brand("brand")
            .lstProductColor(initProductColorList(i))
            .lstProductAdvertisement(null)
            .lstProductSpecification(null)
            .description("hello 123")
            .repaymentAmountByMonth(null)
            .isAllowInstallment(null)
            .isActive(true)
            .images(null)
            .build();
    }

    private static List<ProductColorInfo> initProductColorList(long productId) {
        List<ProductColorInfo> productColorInfos = new ArrayList<>();
        productColorInfos.add(ProductColorInfo.builder()
            .id(productId)
            .productId(productId)
            .colorName(String.format("Name color %d", productId))
            .colorCode(null)
            .priceDiscount(2000D)
            .price(1000D)
            .percent(null)
            .urlProductImage(List.of(String.format("url %d", productId)))
            .build()
        );
        return productColorInfos;
    }

    public static OrderProductList initOrderProductList(long size) {
        OrderProductList orderProductList = OrderProductList.builder().lstOrderProduct(new ArrayList<>()).build();
        for (long i = 0; i < size; i++) {
            orderProductList.getLstOrderProduct().add(OrderProductInfo.builder().productId(i).colorId(i).build());
        }
        return orderProductList;
    }
}
