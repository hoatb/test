package com.betall.core.retail.haravanretail.services;

import com.betall.core.retail.haravan_product_context.models.HaravanProductImageInfo;
import com.betall.core.retail.haravan_product_context.models.HaravanProductInfo;
import com.betall.core.retail.haravan_product_context.models.HaravanProductVariantInfo;
import com.betall.core.retail.haravan_product_context.services.QueryHaravanProductService;
import com.betall.core.retail.productcontext.models.ProductColorInfo;
import com.betall.core.retail.productcontext.models.ProductDetailList;
import com.betall.core.retail.productcontext.models.ProductImageInfo;
import com.betall.core.retail.productcontext.models.ProductDetailInfo;
import com.betall.core.retail.productcontext.services.CommandProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ProductService {
    private QueryHaravanProductService haravanService;
    private CommandProductService commandProduct;

    public ProductService(
            final QueryHaravanProductService haravanService,
            final CommandProductService commandProduct) {
        this.haravanService = haravanService;
        this.commandProduct = commandProduct;
    }

    public HttpStatus syncAll() {
        log.info("============> Starting sync all products....");
        List<ProductDetailInfo> products = new ArrayList<>();
        List<HaravanProductInfo> haravanProducts = haravanService.findAllProducts();
        for(HaravanProductInfo haravanProductInfo : haravanProducts) {
            log.info(String.format("Processing Product '%s - %s' from Haravan.", haravanProductInfo.getId(), haravanProductInfo.getTitle()));
            ProductDetailInfo productInfo = toProductInfo(haravanProductInfo);
            products.add(productInfo);
        }
        commandProduct.saveAll(ProductDetailList.builder().productsDetail(products).build());
        log.info("============> End sync all products.");
        return HttpStatus.OK;
    }

    private ProductDetailInfo toProductInfo(HaravanProductInfo haravanProductInfo) {
        return ProductDetailInfo.builder()
            .productId(haravanProductInfo.getId())
            .productName(haravanProductInfo.getTitle())
            .star(null)
            .numberReview(null)
            .priceDiscount(haravanProductInfo.getVariants().get(0).getPrice())
            .price(haravanProductInfo.getVariants().get(0).getCompareAtPrice())
            .percent(null)
            .content(null)
            .brand(haravanProductInfo.getVendor())
            .lstProductColor(toProductColorInfoList(haravanProductInfo.getVariants(), haravanProductInfo.getImages()))
            .lstProductAdvertisement(null)
            .lstProductSpecification(null)
            .description(HtmlUtils.htmlEscape(haravanProductInfo.getBodyHtml()))
            .repaymentAmountByMonth(null)
            .isAllowInstallment(null)
            .isActive(true)
            .images(toProductImageInfoList(haravanProductInfo.getImages()))
            .build();
    }

    private List<ProductColorInfo> toProductColorInfoList(List<HaravanProductVariantInfo> variants, List<HaravanProductImageInfo> images) {
        List<ProductColorInfo> productColorInfos = new ArrayList<>();
        for(HaravanProductVariantInfo variant : variants) {
            ProductColorInfo productColorInfo = ProductColorInfo.builder()
                .id(variant.getId())
                .productId(variant.getProductId())
                .colorName(variant.getOption1())
                .colorCode(null)
                .percent(null)
                .priceDiscount(variant.getPrice())
                .price(variant.getCompareAtPrice())
                .urlProductImage(toProductColorImageList(variant.getId(), images))
                .build();
            productColorInfos.add(productColorInfo);
        }
        return productColorInfos;
    }

    private List<String> toProductColorImageList(Long id, List<HaravanProductImageInfo> images) {
        List<String> productColorImageList = new ArrayList<>();
        for(HaravanProductImageInfo image : images) {
            if (image.getVariantIds().contains(id)) {
                productColorImageList.add(image.getSrc());
            }
        }
        return productColorImageList;
    }

    private ProductImageInfo toProductImageInfo(HaravanProductImageInfo haravanProductImageInfo) {
        ProductImageInfo productImageInfo = ProductImageInfo.builder().build();
        BeanUtils.copyProperties(haravanProductImageInfo, productImageInfo);
        return productImageInfo;
    }

    private List<ProductImageInfo> toProductImageInfoList(List<HaravanProductImageInfo> haravanProductImageInfos) {
        List<ProductImageInfo> productImageInfos = new ArrayList<>();
        for(HaravanProductImageInfo haravanProductImageInfo : haravanProductImageInfos) {
            productImageInfos.add(toProductImageInfo(haravanProductImageInfo));
        }
        return productImageInfos;
    }
}
