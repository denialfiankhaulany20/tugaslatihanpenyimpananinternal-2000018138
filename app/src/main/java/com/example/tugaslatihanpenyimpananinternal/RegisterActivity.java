package com.example.tugaslatihanpenyimpananinternal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editNIM;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bindViews();
    }

    private void bindViews() {
        editTextUsername = findViewById(R.id.register_username);
        editTextPassword = findViewById(R.id.register_password);
        editNIM = findViewById(R.id.register_nim);
        editTextConfirmPassword = findViewById(R.id.register_confirm_password);
    }

    public void onRegisterButtonClickedOnRegister(View view) {
        String username = editTextUsername.getText().toString();
        String nim = editNIM.getText().toString().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        // convert nim into integer
        int intNIM = Integer.parseInt(nim);

        if (username.isEmpty() || nim.isEmpty()  || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Tolong isi setiap informasi!", Toast.LENGTH_LONG).show();
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(RegisterActivity.this,  "Password dan Confirm Password tidak sama!", Toast.LENGTH_LONG).show();
        } else {
            // everything ok!

            // init database
            UserDatabase db = UserDatabase.getInstance(RegisterActivity.this);

            User user = new User(username, intNIM, password);

            // insert user into database
            db.userDao().insertNewUser(user);

            Toast.makeText(RegisterActivity.this, "Registrasi Sukses :D", Toast.LENGTH_SHORT).show();

            // after creating an account user goes back to login activity
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        }
    }
}