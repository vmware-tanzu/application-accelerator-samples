package com.vmware.acme.payment;

public class PaymentResponse {

    private boolean success;
    private String message;
    private String amount;
    private String transactionID;
    private int status;

    public boolean getSuccess() {
        return success;
    }

    public PaymentResponse(boolean success, String message, String amount, String transactionID, int status) {
        this.success = success;
        this.message = message;
        this.amount = amount;
        this.transactionID = transactionID;
        this.status = status;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
