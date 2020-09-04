package com.example.applicationteste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.applicationteste.domain.LoginCredenciado;
import com.example.applicationteste.help.Preferencia;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    TextInputEditText eMail;
    TextInputLayout eMailLayout;
    TextInputEditText password;
    TextInputLayout passwordLayout;
    ConstraintLayout layoutLoading;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //descomentando essa parte nao precisa fazer sempre login porque os dados estao salvos locais
        //so precisaria de um metodo para renovar o token
//        if(Preferencia.getInstance(getApplicationContext()).getKeyLoggen()){
//            Intent intent = new Intent(getApplicationContext(), Home.class);
//            startActivity(intent);
//            finish();
//        }
        passwordLayout = (TextInputLayout) findViewById(R.id.input_layout_password);
        password = (TextInputEditText) findViewById(R.id.password);
        eMailLayout = (TextInputLayout) findViewById(R.id.input_layout_eMail);
        eMail = (TextInputEditText) findViewById(R.id.eMail);
        btnLogin = (Button) findViewById(R.id.login);
        layoutLoading = (ConstraintLayout) findViewById(R.id.containerLoad);
        error = (TextView) findViewById(R.id.errorText);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });

    }

    private void logIn() {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        if(!validate()){
            return;
        }

        layoutLoading.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);

        LoginCredenciado loginCredenciado = new LoginCredenciado();
        loginCredenciado.email = eMail.getText().toString();
        loginCredenciado.password = password.getText().toString();
        System.out.println("dados login "+loginCredenciado);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Login login = ApiClient.createServiceLogin(Login.class);
                    final Call<JsonObject> confirmation = login.Login(loginCredenciado);
//                    Response<JsonObject> response = confirmation.execute();
                    confirmation.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.isSuccessful() && response.code() == 200) {
                                if ((response.body() != null) && (response.body().size() > 0)) {
                                    Headers headers = response.headers();
                                    Preferencia.getInstance(getApplicationContext()).
                                            saveLogin(true, headers.get("access-token"), headers.get("client"), headers.get("uid"));
                                    btnLogin.setEnabled(true);
                                    layoutLoading.setVisibility(View.GONE);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
//                                error.setVisibility(View.VISIBLE);
                                btnLogin.setEnabled(true);
                                eMailLayout.setErrorIconDrawable(R.drawable.ic_error);
                                eMailLayout.setError(" ");
                                passwordLayout.setErrorIconDrawable(R.drawable.ic_error);
                                passwordLayout.setError(getString(R.string.errortext));
                                layoutLoading.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            System.out.println("error"+t);
                            layoutLoading.setVisibility(View.GONE);
                        }
                    });
//                    if(response.isSuccessful()){
//                        JsonObject responseConfirmation = response.body();
//                        if(response.code() == 200){
//                            System.out.println("responseConfirmation "+responseConfirmation);
//                            layoutLoading.setVisibility(View.GONE);
//                        }
//                    }
                } catch (Exception e) {
                    System.out.println("ERROR "+e);
                    layoutLoading.setVisibility(View.GONE);
                }
            }
        }).start();

    }

    private boolean validate() {
        if(eMail.getText().toString().isEmpty() ||
                !Patterns.EMAIL_ADDRESS.matcher(eMail.getText().toString()).matches()){
            eMailLayout.setError("Endereço de e-mail inválido");
            eMailLayout.setErrorIconDrawable(R.drawable.ic_error);
            if(password.getText().toString().isEmpty()){
                passwordLayout.setError("Senha inválida");
                passwordLayout.setErrorIconDrawable(R.drawable.ic_error);
            }else{
                passwordLayout.setError(null);
            }
            return false;
        } else {
            eMailLayout.setError(null);
        }
        if(password.getText().toString().isEmpty()){
            passwordLayout.setError("Senha inválida");
            return false;
        } else {
            passwordLayout.setError(null);
        }
        passwordLayout.setError(null);
        eMailLayout.setError(null);
        return true;
    }
}
