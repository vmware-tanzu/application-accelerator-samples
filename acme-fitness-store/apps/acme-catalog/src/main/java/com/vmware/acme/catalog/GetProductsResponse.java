package com.vmware.acme.catalog;

import java.util.List;

public class GetProductsResponse {

    private List<ProductResponse> data;

    public GetProductsResponse(List<ProductResponse> data) {
        this.data = data;
    }

    public List<ProductResponse> getData() {
        return data;
    }

    public void setData(List<ProductResponse> data) {
        this.data = data;
    }
}
