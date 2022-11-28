package com.betall.core.retail.productinfrastructure.utils;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.util.HtmlUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.productcontext.models.*;

import com.betall.core.retail.productinfrastructure.models.*;
import com.betall.core.retail.productcontext.responses.ProductDetailRepresentation;

public class EntityMapper {
    /**
     * Convert Page<Product> (infrastructure models) to Page<ProductDetailInfo> (context models)
     * @param products
     * @param page
     * @param total
     * @return Page<ProductDetailInfo>
     */
    public Page<ProductInfo> convertPage(final Page<Product> products, final Pageable page, final Long total) {
        if (products.hasContent()) {
            final List<ProductInfo> lstProduct = products.stream().map(p -> toProductInfo(p, null)).collect(Collectors.toList());
            return new PageImpl<>(lstProduct, page, total);
        }
        return new PageImpl<>(new ArrayList<>());
    }

    /**
     * Convert Product (infrastructure models) to ProductInfo (context models)
     * @param product
     * @return ProductInfo
     */
    public ProductInfo toProductInfo(final Product product, final ProductColor productColor) {
        final ProductInfo productInfo = ProductInfo.builder().build();
        BeanUtils.copyProperties(product, productInfo);
        if (!product.getImages().isEmpty()) {
            productInfo.setImageUrl(product.getImages().stream().iterator().next().getSrc());
        }
        if (productColor != null) {
            productInfo.setHaravanProductId(product.getProductId());
            productInfo.setColorId(productColor.getId());
            productInfo.setColorName(productColor.getColorName());
        }
        return productInfo;
    }


    /**
     * Convert Product (infrastructure models) to ProductDetailInfo (context models)
     * @param product
     * @return ProductDetailInfo
     */
    public ProductDetailInfo toProductDetailInfo(final Product product) {
        final ProductDetailInfo productDetailInfo = ProductDetailInfo.builder().build();
        BeanUtils.copyProperties(product, productDetailInfo);
        productDetailInfo.setDescription(HtmlUtils.htmlUnescape(product.getDescription()));
        productDetailInfo.setLstProductColor(toProductColorInfoList(product.getProductColors()));
        productDetailInfo.setLstProductAdvertisement(toProductAdvertisementInfoList(product.getProductAdvertisements()));
        productDetailInfo.setLstProductSpecification(toProductSpecificationInfoList(product.getProductSpecifications()));
        productDetailInfo.setImages(toImageList(product.getImages()));
        return productDetailInfo;
    }

    /**
     * Convert Set<ProductColor> (infrastructure models) to List<ProductColorInfo> (context models)
     * @param productColors
     * @return productColorInfoList
     */
    private List<ProductColorInfo> toProductColorInfoList(final Set<ProductColor> productColors) {
        return productColors.stream().map(productColor -> {
            final ProductColorInfo productColorInfo = ProductColorInfo.builder().build();
            BeanUtils.copyProperties(productColor, productColorInfo);
            productColorInfo.setProductId(productColor.getProduct().getProductId());
            productColorInfo.setUrlProductImage(toStringList(productColor.getUrlProductImage()));
            return productColorInfo;
        }).collect(Collectors.toList());
    }

    /**
     * Convert Set<String> to List<String>
     * @param strings
     * @return stringList
     */
    private List<String> toStringList(final Set<String> strings) {
        if (strings != null) {
            return new ArrayList<>(strings);
        }
        return Collections.emptyList();
    }

    /**
     * Convert Set<ProductAdvertisement> (infrastructure models) to List<ProductAdvertisementInfo> (context models)
     * @param productAdvertisements
     * @return productAdvertisementInfoList
     */
    private List<ProductAdvertisementInfo> toProductAdvertisementInfoList(final Set<ProductAdvertisement> productAdvertisements) {
        if (productAdvertisements == null || productAdvertisements.isEmpty()) {
            return Collections.emptyList();
        }
        return productAdvertisements.stream().map(productAdvertisement -> {
            final ProductAdvertisementInfo productAdvertisementInfo = ProductAdvertisementInfo.builder().build();
            BeanUtils.copyProperties(productAdvertisement, productAdvertisementInfo);
            productAdvertisementInfo.setProductId(productAdvertisement.getProduct().getProductId());
            return productAdvertisementInfo;
        }).collect(Collectors.toList());
    }

    /**
     * Convert Set<ProductSpecification> (infrastructure models) to List<ProductSpecificationInfo> (context models)
     * @param productSpecifications
     * @return productSpecificationInfoList
     */
    private List<ProductSpecificationInfo> toProductSpecificationInfoList(final Set<ProductSpecification> productSpecifications) {
        if (productSpecifications == null || productSpecifications.isEmpty()) {
            return Collections.emptyList();
        }
        return productSpecifications.stream().map(productSpecification -> {
            final ProductSpecificationInfo productSpecificationInfo = ProductSpecificationInfo.builder().build();
            BeanUtils.copyProperties(productSpecification, productSpecificationInfo);
            productSpecificationInfo.setProductId(productSpecification.getProduct().getProductId());
            return productSpecificationInfo;
        }).collect(Collectors.toList());
    }

    /**
     * Convert Set<ProductImage> (infrastructure models) to List<ProductImageInfo> (context models)
     * @param images
     * @return imageInfoList
     */
    private List<ProductImageInfo> toImageList(Set<ProductImage> images) {
        if (images == null || images.isEmpty()) {
            return Collections.emptyList();
        }
        return images.stream().map(image -> {
            final ProductImageInfo imageInfo = ProductImageInfo.builder().build();
            BeanUtils.copyProperties(image, imageInfo);
            imageInfo.setProductId(image.getProduct().getProductId());
            if (image.getVariantIds() != null && image.getVariantIds().isEmpty()) {
                imageInfo.setVariantIds(toLongList(image.getVariantIds()));
            }
            return imageInfo;
        }).collect(Collectors.toList());
    }

    /**
     * Convert Set<Long> to List<Long>
     * @param longs
     * @return longList
     */
    private List<Long> toLongList(final Set<Long> longs) {
        if (longs != null) {
            return new ArrayList<>(longs);
        }
        return Collections.emptyList();
    }

    /**
     * Convert ProductDetailInfo (context models) to Product (infrastructure models)
     * @param productDetailInfo
     * @return Product
     */
    public Product toProduct(final ProductDetailInfo productDetailInfo) {
        final Product product = Product.builder().build();
        BeanUtils.copyProperties(productDetailInfo, product);
        product.setProductColors(toProductColorSet(product, productDetailInfo.getLstProductColor()));
        product.setProductAdvertisements(toProductAdvertisementSet(product, productDetailInfo.getLstProductAdvertisement()));
        product.setProductSpecifications(toProductSpecificationSet(product, productDetailInfo.getLstProductSpecification()));
        product.setImages(toImageSet(product, productDetailInfo.getImages()));
        return product;
    }

    /**
     * Convert List<ProductColorInfo> (Context models) to Set<ProductColor> (Infrastructure models)
     * @param product
     * @param productColorInfoList
     * @return Set<ProductColor>
     */
    private Set<ProductColor> toProductColorSet(
            final Product product,
            final List<ProductColorInfo> productColorInfoList) {
        return productColorInfoList.stream().map(productColorInfo -> {
            final ProductColor productColor = ProductColor.builder().build();
            BeanUtils.copyProperties(productColorInfo, productColor);
            productColor.setProduct(product);
            productColor.setUrlProductImage(toStringSet(productColorInfo.getUrlProductImage()));
            return productColor;
        }).collect(Collectors.toSet());
    }

    /**
     * Product Color -> Convert List<String> to Set<String>
     * @param stringList
     * @return Set<String>
     */
    private Set<String> toStringSet(final List<String> stringList) {
        if (stringList != null) {
            return new HashSet<>(stringList);
        }

        return Collections.emptySet();
    }

    /**
     * Convert List<ProductAdvertisementInfo> (Context models) to Set<ProductAdvertisement> (Infrastructure models)
     * @param product
     * @param productAdvertisementInfoList
     * @return Set<ProductAdvertisement>
     */
    private Set<ProductAdvertisement> toProductAdvertisementSet(
            final Product product,
            final List<ProductAdvertisementInfo> productAdvertisementInfoList) {
        if (productAdvertisementInfoList == null || productAdvertisementInfoList.isEmpty()) {
            return Collections.emptySet();
        }
        return productAdvertisementInfoList.stream().map(productAdvertisementInfo -> {
            final ProductAdvertisement productAdvertisement = ProductAdvertisement.builder().build();
            BeanUtils.copyProperties(productAdvertisementInfo, productAdvertisement);
            productAdvertisement.setProduct(product);
            return productAdvertisement;
        }).collect(Collectors.toSet());
    }

    /**
     * Convert List<ProductSpecificationInfo> (Context models) to Set<ProductSpecification> (Infrastructure models)
     * @param product
     * @param productSpecificationInfoList
     * @return Set<ProductSpecification>
     */
    private Set<ProductSpecification> toProductSpecificationSet(
            final Product product,
            final List<ProductSpecificationInfo> productSpecificationInfoList) {
        if (productSpecificationInfoList == null || productSpecificationInfoList.isEmpty()) {
            return Collections.emptySet();
        }
        return productSpecificationInfoList.stream().map(productSpecificationInfo -> {
            final ProductSpecification productSpecification = ProductSpecification.builder().build();
            BeanUtils.copyProperties(productSpecificationInfo, productSpecification);
            productSpecification.setProduct(product);
            return productSpecification;
        }).collect(Collectors.toSet());
    }

    /**
     * Convert List<ProductImageInfo> (Context models) to Set<ProductImage> (Infrastructure models)
     * @param product
     * @param imageInfoList
     * @return Set<ProductImage>
     */
    private Set<ProductImage> toImageSet(final Product product, final List<ProductImageInfo> imageInfoList) {
        if (imageInfoList == null || imageInfoList.isEmpty()) {
            return Collections.emptySet();
        }
        return imageInfoList.stream().map(imageInfo -> {
            final ProductImage image = ProductImage.builder().build();
            BeanUtils.copyProperties(imageInfo, image);
            image.setProduct(product);
            image.setVariantIds(toLongSet(imageInfo.getVariantIds()));
            return image;
        }).collect(Collectors.toSet());
    }

    /**
     * Convert List<Long> to Set<Long>
     * @param longList
     * @return Set<Long>
     */
    private Set<Long> toLongSet(List<Long> longList) {
        if (longList != null) {
            return new HashSet<>(longList);
        }
        return Collections.emptySet();
    }

    /**
     * Convert Product (infrastructure models) to ProductDetailRepresentation (context responses)
     * @param product
     * @return ProductDetailInfo
     */
    public ProductDetailRepresentation toProductDetailRepresentation(Product product) {
        ProductDetailInfo productDetailInfo = toProductDetailInfo(product);
        return ProductDetailRepresentation.builder()
            .status(0)
            .message(null)
            .data(
                ProductDetail.builder()
                    .productDetailInfo(productDetailInfo)
                    .build()
            )
            .build();
    }

    /**
     * Update Product (infrastructure models) from ProductDetailInfo (context models)
     * @param product
     * @param productDetailInfo
     */
    public void update(final Product product, final ProductDetailInfo productDetailInfo) {
        BeanUtils.copyProperties(productDetailInfo, product);
        product.setProductColors(toProductColorSet(product, productDetailInfo.getLstProductColor()));
        product.setProductAdvertisements(toProductAdvertisementSet(product, productDetailInfo.getLstProductAdvertisement()));
        product.setProductSpecifications(toProductSpecificationSet(product, productDetailInfo.getLstProductSpecification()));
        product.setImages(toImageSet(product, productDetailInfo.getImages()));
    }
}
