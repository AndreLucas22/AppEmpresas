package com.example.applicationteste;

import com.google.gson.annotations.SerializedName;

public class Investor {
    @SerializedName("id")
    private int id;
    @SerializedName("investor_name")
    private String investor_name;
    @SerializedName("email")
    private String email;
    @SerializedName("city")
    private String city;
    @SerializedName("country")
    private String country;
    @SerializedName("balance")
    private Double balance;
    @SerializedName("photo")
    private String photo;
    @SerializedName("portfolio")
    private Portfolio portfolio;
    @SerializedName("portfolio_value")
    private Double portfolio_value;
    @SerializedName("first_access")
    private Boolean first_access;
    @SerializedName("super_angel")
    private Boolean super_angel;
}
