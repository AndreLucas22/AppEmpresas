package com.example.applicationteste;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Portfolio {
    @SerializedName("enterprises_number")
    private Integer enterprises_number;
    @SerializedName("enterprises")
    private ArrayList enterprises;
}
