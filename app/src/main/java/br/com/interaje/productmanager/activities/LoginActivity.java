package br.com.interaje.productmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import br.com.interaje.productmanager.R;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username_login);
        password = (EditText) findViewById(R.id.password_login);
    }

    public void login(View v) {
        String valueUsername = username.getText().toString();
        String valuePassword = password.getText().toString();

        if (!valuePassword.isEmpty() || !valueUsername.isEmpty()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
