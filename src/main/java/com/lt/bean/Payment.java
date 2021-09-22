package com.lt.bean;

public class Payment {
    private long transactionId;
    private double billingAmount;
    private String billingMode;
    private String card_no;
    private String card_expiry;

    public Payment() {
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public Payment(double billingAmount, String billingMode, long transactionId) {
        this.billingAmount = billingAmount;
        this.billingMode = billingMode;
        this.transactionId = transactionId;
    }

    public Payment(long transactionId, double billingAmount, String billingMode, String card_no, String card_expiry) {
        this.transactionId = transactionId;
        this.billingAmount = billingAmount;
        this.billingMode = billingMode;
        this.card_no = card_no;
        this.card_expiry = card_expiry;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getCard_expiry() {
        return card_expiry;
    }

    public void setCard_expiry(String card_expiry) {
        this.card_expiry = card_expiry;
    }

    public double getBillingAmount() {
        return billingAmount;
    }

    public void setBillingAmount(double billingAmount) {
        this.billingAmount = billingAmount;
    }

    public String getBillingMode() {
        return billingMode;
    }

    public void setBillingMode(String billingMode) {
        this.billingMode = billingMode;
    }
}
