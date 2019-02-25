package com.laksita.ramapradana.workshop2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private String name = "Rama Pradana";
    private String usernameUser = "ramap";
    private String passwordUser = "tecart";
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private String username, password;
    private SharedPreferences profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean anyError = false;

                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                if(username.equals("")){
                    etUsername.setError("Username harus diisi");
                    anyError = true;
                }

                if(password.equals("")){
                    etPassword.setError("Password harus diisi");
                    anyError = true;
                }

                if(!anyError){
                    if(username.equals(usernameUser) && password.equals(passwordUser)){
                        profile = getSharedPreferences("profile", Context.MODE_PRIVATE);
                        SharedPreferences.Editor profileEditor = profile.edit();
                        profileEditor.putString("username", usernameUser);
                        profileEditor.putString("name", name);
                        profileEditor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        etPassword.setText("");
                        etUsername.requestFocus();
                        etUsername.setError("Username atau password salah!");
                    }
                }
            }
        });
    }
}
