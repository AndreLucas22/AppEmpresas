package com.example.applicationteste.domain;

import com.google.gson.annotations.SerializedName;

public class LoginCredenciado {
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;

    public LoginCredenciado() {
        email = "";
        password = "";
    }
}
