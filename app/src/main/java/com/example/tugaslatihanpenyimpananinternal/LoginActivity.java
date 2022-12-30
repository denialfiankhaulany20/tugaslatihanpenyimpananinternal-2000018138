package com.example.tugaslatihanpenyimpananinternal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPref = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);
        boolean isUserLoggedIn = sharedPref.getBoolean("is_login", false);

        // if user already logged in jumo to main / home activity
        if (isUserLoggedIn) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        bindViews();
    }

    private void bindViews() {
        editTextUsername = findViewById(R.id.login_username);
        editTextPassword = findViewById(R.id.login_password);
    }

    public void onLoginButtonClicked(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Tolong isi setiap informasi!", Toast.LENGTH_LONG).show();
        } else {
            // everything fine!

            UserDatabase db = UserDatabase.getInstance(LoginActivity.this);

            User user = db.userDao().retrieveSingleUser(username);

            // username not found
            if (user == null) {
                Toast.makeText(LoginActivity.this, "Username yang anda masukan belum teregristrasi", Toast.LENGTH_LONG).show();

                // user goes to register activity to create an account!
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            } else {
                String userRegUsername = user.getNama();
                int userRegNim = user.getNim();
                String userRegPassword = user.getPassword();

                if (username.equals(userRegUsername) && password.equals(userRegPassword)) {
                    // login successful
                    // open main activity

                    // save user prefs
                    SharedPreferences sharedPref = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    editor.putString("username_", userRegUsername);
                    editor.putInt("nim_", userRegNim);

                    editor.apply();

                    startActivity(new Intent(this, MainActivity.class));
                    Toast.makeText(this, "Berhasil Login!!", Toast.LENGTH_LONG).show();

                    finish();
                } else {
                    Toast.makeText(this, "Password salah!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void onRegisterButtonClicked(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}