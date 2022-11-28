package com.betall.core.retail.haravan_product_context.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataValidationException;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HaravanProductInfo {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("body_html")
    private String bodyHtml;
    @JsonProperty("body_plain")
    private String bodyPlain;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("handle")
    private String handle;
    @JsonProperty("images")
    private List<HaravanProductImageInfo> images;
    @JsonProperty("product_type")
    private String productType;
    @JsonProperty("published_at")
    private String publishedAt;
    @JsonProperty("published_scope")
    private String publishedScope;
    @JsonProperty("tags")
    private String tags;
    @JsonProperty("template_suffix")
    private String templateSuffix;
    @JsonProperty("title")
    private String title;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("variants")
    private List<HaravanProductVariantInfo> variants;
    @JsonProperty("vendor")
    private String vendor;
    @JsonProperty("options")
    private List<HaravanProductOptionInfo> options;
    @JsonProperty("only_hide_from_list")
    private boolean onlyHideFromList;
    @JsonProperty("not_allow_promotion")
    private boolean notAllowPromotion;

    private void validateImages() {
        if (images == null) {
            throw new DataValidationException("HaravanProductInfo images is must not be null.");
        }

        for(HaravanProductImageInfo image : images) {
            image.validate();
        }
    }

    private void validateVariants() {
        if (variants == null) {
            throw new DataValidationException("HaravanProductInfo variants must not be null.");
        }

        for(HaravanProductVariantInfo variant : variants) {
            variant.validate();
        }
    }

    private void validateOptions() {
        if (options == null) {
            throw new DataValidationException("HaravanProductInfo options must not be null.");
        }

        for(HaravanProductOptionInfo option : options) {
            option.validate();
        }
    }

    public void validate() {
        if (id == null) {
            throw new DataValidationException("HaravanProductInfo must not be null.");
        }

        validateImages();
        validateVariants();
        validateOptions();
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
