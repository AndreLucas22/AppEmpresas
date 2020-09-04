package com.example.applicationteste;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("investor")
    private Investor investor;
    @SerializedName("enterprise")
    private String enterprise;
    @SerializedName("success")
    private String success;

}
