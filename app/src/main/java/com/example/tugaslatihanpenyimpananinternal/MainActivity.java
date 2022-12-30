package com.example.tugaslatihanpenyimpananinternal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView usernameTextView;
    private TextView nimTextView;

    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean("is_login", true);
        editor.commit();

        String usernameLoggedIn = sharedPref.getString("username_", "");
        int nimLoggedIn = sharedPref.getInt("nim_", 0);

        bindViews();

        usernameTextView.setText("Username : " + usernameLoggedIn);
        nimTextView.setText("NIM : " +nimLoggedIn);
    }

    private void bindViews() {
        usernameTextView = findViewById(R.id.main_username);
        nimTextView = findViewById(R.id.main_nim);
        buttonLogout = findViewById(R.id.main_logout_button);
    }

    public void onClickLogoutButtonMain(View view) {
        SharedPreferences sharedPref = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean("is_login", false);
        editor.apply();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}