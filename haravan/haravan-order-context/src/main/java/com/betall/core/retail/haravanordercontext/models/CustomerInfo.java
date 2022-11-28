package com.betall.core.retail.haravanordercontext.models;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import com.google.gson.Gson;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfo {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("accepts_marketing")
    private Boolean acceptsMarketing;

    @JsonProperty("addresses")
    private List<AddressInfo> addressInfos;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("default_address")
    private AddressInfo defaultAddressInfo;

    @JsonProperty("email")
    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("last_order_id")
    private Long lastOrderId;

    @JsonProperty("last_order_name")
    private String lastOrderName;

    @JsonProperty("published")
    private Boolean published;

    @JsonProperty("multipass_identifier")
    private Boolean multipassIdentifier;

    @JsonProperty("note")
    private String note;

    @JsonProperty("state")
    private String state;

    @JsonProperty("orders_count")
    private Long ordersCount;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("tags")
    private String tags;

    @JsonProperty("total_spent")
    private Double totalSpent;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("birthday")
    private String birthday;

    @JsonProperty("gender")
    private Integer gender;

    @JsonProperty("last_order_date")
    private String lastOrderDate;

    @JsonProperty("verified_email")
    private Boolean verifiedEmail;

    @JsonProperty("send_email_invite")
    private Boolean sendEmailInvite;

    @JsonProperty("send_email_welcome")
    private Boolean sendEmailWelcome;

    public void validate() {
        if (id == null || id <= 0) {
            throw new DataViolationException("CustomerInfo id must be greater than 0.");
        }

        if (firstName == null || firstName.trim().length() == 0) {
            throw new DataViolationException("CustomerInfo firstName is null or empty.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
