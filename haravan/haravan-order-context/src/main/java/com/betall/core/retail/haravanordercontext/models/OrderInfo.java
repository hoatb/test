package com.betall.core.retail.haravanordercontext.models;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.google.gson.Gson;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    @JsonProperty("billing_address")
    private AddressInfo billingAddressInfo;

    @JsonProperty("browser_ip")
    private String browserIp;

    @JsonProperty("buyer_accepts_marketing")
    private Boolean buyerAcceptsMarketing;

    @JsonProperty("cancel_reason")
    private String cancelReason;

    @JsonProperty("cancelled_at")
    private String cancelledAt;

    @JsonProperty("cart_token")
    private String cartToken;

    @JsonProperty("checkout_token")
    private String checkoutToken;

    @JsonProperty("client_details")
    private ClientDetails clientDetails;

    @JsonProperty("closed_at")
    private String closedAt;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("customer")
    private CustomerInfo customerInfo;

    @JsonProperty("discount_codes")
    private List<DiscountInfo> discountCodes;

    @JsonProperty("email")
    private String email;

    @JsonProperty("financial_status")
    private String financialStatus;

    @JsonProperty("fulfillments")
    private List<FulfillmentInfo> fulfillments;

    @JsonProperty("fulfillment_status")
    private String fulfillmentStatus;

    @JsonProperty("tags")
    private String tags;

    @JsonProperty("gateway")
    private String gateway;

    @JsonProperty("gateway_code")
    private String gatewayCode;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("landing_site")
    private String landingSite;

    @JsonProperty("landing_site_ref")
    private String landingSiteRef;

    @JsonProperty("source")
    private String source;

    @JsonProperty("line_items")
    private List<LineItemInfo> lineItems;

    @JsonProperty("name")
    private String name;

    @JsonProperty("note")
    private String note;

    @JsonProperty("number")
    private Long number;

    @JsonProperty("order_number")
    private String orderNumber;

    @JsonProperty("processing_method")
    private String processingMethod;

    @JsonProperty("referring_site")
    private String referringSite;

    @JsonProperty("refunds")
    private List<RefundInfo> refunds;

    @JsonProperty("shipping_address")
    private AddressInfo shippingAddress;

    @JsonProperty("shipping_lines")
    private List<ShippingLineInfo> shippingLines;

    @JsonProperty("source_name")
    private String sourceName;

    @JsonProperty("subtotal_price")
    private Double subtotalPrice;

    @JsonProperty("tax_lines")
    private String taxLines;

    @JsonProperty("taxes_included")
    private Boolean taxesIncluded;

    @JsonProperty("token")
    private String token;

    @JsonProperty("total_discounts")
    private Double totalDiscounts;

    @JsonProperty("total_line_items_price")
    private Double totalLineItemsPrice;

    @JsonProperty("total_price")
    private Double totalPrice;

    @JsonProperty("total_tax")
    private Double totalTax;

    @JsonProperty("total_weight")
    private Double totalWeight;

    @JsonProperty("updated_at")
    private String updated_at;

    @JsonProperty("transactions")
    private List<TransactionInfo> transactions;

    @JsonProperty("note_attributes")
    private List<NoteAttributeInfo> noteAttributes;

    @JsonProperty("confirmed_at")
    private String confirmedAt;

    @JsonProperty("closed_status")
    private String closedStatus;

    @JsonProperty("cancelled_status")
    private String cancelledStatus;

    @JsonProperty("confirmed_status")
    private String confirmedStatus;

    @JsonProperty("assigned_location_id")
    private Long assignedLocationId;

    @JsonProperty("assigned_location_name")
    private String assignedLocationName;

    @JsonProperty("assigned_location_at")
    private String assignedLocationAt;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("device_id")
    private Long deviceId;

    @JsonProperty("location_id")
    private Long locationId;

    @JsonProperty("location_name")
    private String locationName;

    @JsonProperty("ref_order_id")
    private Long refOrderId;

    @JsonProperty("contact_email")
    private String contactEmail;

    @JsonProperty("order_processing_status")
    private String orderProcessingStatus;

    @JsonProperty("prev_order_id")
    private Long prevOrderId;

    @JsonProperty("prev_order_number")
    private String prevOrderNumber;

    @JsonProperty("prev_order_date")
    private String prevOrderDate;

    @JsonProperty("confirm_user")
    private Long confirmUser;

    public void validate() {
        if (id == null || id <= 0) {
            throw new DataViolationException("OrderInfo id must be greater than 0.");
        }

        if (totalPrice == null || totalPrice < 0) {
            throw new DataViolationException("OrderInfo totalPrice must be greater than or equals 0.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
