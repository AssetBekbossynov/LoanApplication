package com.example.user.newapplication;

import com.google.gson.annotations.SerializedName;

public class LoanInfo {

    @SerializedName("price")
    public String price;
    @SerializedName("id_product")
    public String id_product;
    @SerializedName("cashback")
    public String cashback;
    @SerializedName("id_category")
    public String id_category;
    @SerializedName("name")
    public String name;
    @SerializedName("credit_interest_rate")
    public String credit_interest_rate;
    @SerializedName("credit_max_sum")
    public String credit_max_sum;
    @SerializedName("credit_max_period")
    public String credit_max_period;
    @SerializedName("credit_required_documents")
    public String credit_required_documents;
    @SerializedName("credit_consideration_time")
    public String credit_consideration_time;
    @SerializedName("credit_age")
    public String credit_age;
    @SerializedName("name_category")
    public String name_category;
    @SerializedName("ads")
    public String ads;
    @SerializedName("photo")
    public String photo;
    @SerializedName("currency")
    public String currency;
    @SerializedName("count_clicks")
    public String count_clicks;
    @SerializedName("count_buys")
    public String count_buys;


    @Override
    public String toString() {
        return "LoanInfo{" +
                "price='" + price + '\'' +
                ", id_product='" + id_product + '\'' +
                ", cashback='" + cashback + '\'' +
                ", id_category='" + id_category + '\'' +
                ", name='" + name + '\'' +
                ", credit_interest_rate='" + credit_interest_rate + '\'' +
                ", credit_max_sum='" + credit_max_sum + '\'' +
                ", credit_max_period='" + credit_max_period + '\'' +
                ", credit_required_documents='" + credit_required_documents + '\'' +
                ", credit_consideration_time='" + credit_consideration_time + '\'' +
                ", credit_age='" + credit_age + '\'' +
                ", name_category='" + name_category + '\'' +
                ", ads='" + ads + '\'' +
                ", photo='" + photo + '\'' +
                ", currency='" + currency + '\'' +
                ", count_clicks='" + count_clicks + '\'' +
                ", count_buys='" + count_buys + '\'' +
                '}';
    }
}
