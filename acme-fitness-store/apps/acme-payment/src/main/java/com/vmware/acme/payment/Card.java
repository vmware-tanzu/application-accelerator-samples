package com.vmware.acme.payment;

import java.time.LocalDate;

import org.springframework.util.StringUtils;

public class Card {

    private String number;
    private String expYear;
    private String expMonth;
    private String ccv;

    public Card(String number, String expYear, String expMonth, String ccv) {
        this.number = number;
        this.expYear = expYear;
        this.expMonth = expMonth;
        this.ccv = ccv;
    }

    public Card() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public boolean containsMissingInfo() {
        return !StringUtils.hasText(number) || !StringUtils.hasText(expYear) || !StringUtils.hasText(ccv) || !StringUtils.hasText(expMonth);
    }

    public boolean isExpired() {
        LocalDate expDate = LocalDate.of(Integer.parseInt(expYear), Integer.parseInt(expMonth), 1);
        return expDate.isBefore(LocalDate.now());
    }

    public boolean isValidCardNumber() {
        return number.length() % 4 == 0;
    }
}
