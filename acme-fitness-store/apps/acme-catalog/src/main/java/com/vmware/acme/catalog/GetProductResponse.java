package com.vmware.acme.catalog;

public class GetProductResponse {

    private ProductResponse data;
    private int status;

    public GetProductResponse(ProductResponse data, int status) {
        this.data = data;
        this.status = status;
    }

    public ProductResponse getData() {
        return data;
    }

    public void setData(ProductResponse data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
