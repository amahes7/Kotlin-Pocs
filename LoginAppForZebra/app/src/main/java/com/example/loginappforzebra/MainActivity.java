package com.example.loginappforzebra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginBtn;

    final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(flags);
        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            }
        });
        username = (EditText) findViewById(R.id.etName);
        password = (EditText) findViewById(R.id.etPassword);
        loginBtn = (Button) findViewById(R.id.btnLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateCredentials(username.getText().toString(), password.getText().toString());
            }
        });
    }


    private void validateCredentials(String inputUsername, String inputPassword) {
        if (inputUsername.equalsIgnoreCase("Admin")
                && inputPassword.equalsIgnoreCase("Password")) {
            Intent intent = new Intent(MainActivity.this, ListActivityPage.class);
            startActivity(intent);
        } else {
            showError();
        }
    }

    private void showError() {
        password.setError("Password and username didn't match");
    }
}