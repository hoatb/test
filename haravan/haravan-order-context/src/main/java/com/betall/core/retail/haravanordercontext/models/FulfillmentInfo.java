package com.betall.core.retail.haravanordercontext.models;

import com.google.gson.Gson;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FulfillmentInfo {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("receipt")
    private String receipt;

    @JsonProperty("status")
    private String status;

    @JsonProperty("tracking_company")
    private String trackingCompany;

    @JsonProperty("tracking_company_code")
    private String trackingCompanyCode;

    @JsonProperty("tracking_numbers")
    private List<String> trackingNumbers;

    @JsonProperty("tracking_number")
    private String trackingNumber;

    @JsonProperty("tracking_url")
    private String trackingUrl;

    @JsonProperty("tracking_urls")
    private List<String> trackingUrls;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("line_items")
    private List<LineItemInfo> lineItems;

    @JsonProperty("province")
    private String province;

    @JsonProperty("province_code")
    private String provinceCode;

    @JsonProperty("district")
    private String district;

    @JsonProperty("district_code")
    private String districtCode;

    @JsonProperty("ward")
    private String ward;

    @JsonProperty("ward_code")
    private String wardCode;

    @JsonProperty("cod_amount")
    private Double codAmount;

    @JsonProperty("carrier_status_name")
    private String carrierStatusName;

    @JsonProperty("carrier_cod_status_name")
    private String carrierCodStatusName;

    @JsonProperty("carrier_status_code")
    private String carrierStatusCode;

    @JsonProperty("carrier_cod_status_code")
    private String carrierCodStatusCode;

    @JsonProperty("location_id")
    private Integer locationId;

    @JsonProperty("location_name")
    private String locationName;

    @JsonProperty("note")
    private String note;

    @JsonProperty("carrier_service_package_name")
    private String carrierServicePackageName;

    @JsonProperty("coupon_code")
    private String couponCode;

    @JsonProperty("ready_to_pick_date")
    private String readyToPickDate;

    @JsonProperty("picking_date")
    private String pickingDate;

    @JsonProperty("delivering_date")
    private String deliveringDate;

    @JsonProperty("delivered_date")
    private String deliveredDate;

    @JsonProperty("return_date")
    private String returnDate;

    @JsonProperty("not_meet_customer_date")
    private String notMeetCustomerDate;

    @JsonProperty("waiting_for_return_date")
    private String waitingForReturnDate;

    @JsonProperty("cod_paid_date")
    private String codPaidDate;

    @JsonProperty("cod_receipt_date")
    private String codReceiptDate;

    @JsonProperty("cod_pending_date")
    private String codPendingDate;

    @JsonProperty("cod_not_receipt_date")
    private String codNotReceiptDate;

    @JsonProperty("cancel_date")
    private String cancelDate;

    @JsonProperty("is_view_before")
    private Boolean isViewBefore;

    @JsonProperty("country")
    private String country;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("zip_code")
    private String zipCode;

    @JsonProperty("city")
    private String city;

    @JsonProperty("real_shipping_fee")
    private Double realShippingFee;

    @JsonProperty("shipping_notes")
    private String shippingNotes;

    @JsonProperty("total_weight")
    private Double totalWeight;

    @JsonProperty("package_length")
    private Double packageLength;

    @JsonProperty("package_width")
    private Double packageWidth;

    @JsonProperty("package_height")
    private Double packageHeight;

    @JsonProperty("transport_type")
    private Integer transportType;

    @JsonProperty("address")
    private String address;

    @JsonProperty("sender_phone")
    private String senderPhone;

    @JsonProperty("sender_name")
    private String senderName;

    @JsonProperty("carrier_service_code")
    private String carrierServiceCode;

    @JsonProperty("from_longtitude")
    private Double fromLongtitude;

    @JsonProperty("from_latitude")
    private Double fromLatitude;

    @JsonProperty("to_longtitude")
    private Double toLongtitude;

    @JsonProperty("to_latitude")
    private Double toLatitude;

    @JsonProperty("is_drop_off")
    private Boolean isDropOff;

    @JsonProperty("is_insurance")
    private Boolean isInsurance;

    @JsonProperty("insurance_price")
    private Double insurancePrice;

    @JsonProperty("is_open_box")
    private Boolean isOpenBox;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("shipping_phone")
    private String shippingPhone;

    public void validate() {
        if (id == null || id <= 0) {
            throw new DataViolationException("FulfillmentInfo id must be greater than 0.");
        }

        if (orderId == null || orderId <= 0) {
            throw new DataViolationException("FulfillmentInfo orderId must be greater than 0.");
        }

        if (firstName == null || firstName.trim().length() == 0) {
            throw new DataViolationException("FulfillmentInfo firstName is null or empty.");
        }

        if (countryCode == null || countryCode.trim().length() == 0) {
            throw new DataViolationException("FulfillmentInfo countryCode is null or empty.");
        }

        if (provinceCode == null || provinceCode.trim().length() == 0) {
            throw new DataViolationException("FulfillmentInfo provinceCode is null or empty.");
        }

        if (districtCode == null || districtCode.trim().length() == 0) {
            throw new DataViolationException("FulfillmentInfo districtCode is null or empty.");
        }

        if (wardCode == null || wardCode.trim().length() == 0) {
            throw new DataViolationException("FulfillmentInfo wardCode is null or empty.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
