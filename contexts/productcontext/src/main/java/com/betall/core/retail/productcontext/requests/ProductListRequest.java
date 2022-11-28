package com.betall.core.retail.productcontext.requests;

import com.fasterxml.jackson.annotation.*;
import com.google.gson.Gson;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListRequest {
    @JsonProperty("groupId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long groupId;

    @JsonProperty("lstFilterId")
    private List<Long> lstFilterId;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("pageSize")
    private Integer pageSize;

    @JsonProperty("typeId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long typeId;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void checkSetUp() {
        if (this.lstFilterId == null) {
            this.lstFilterId = Collections.emptyList();
        }
        if (this.page == null) {
            this.page = 0;
        }
        if (this.pageSize == null) {
            this.pageSize = 10;
        }
    }
}
