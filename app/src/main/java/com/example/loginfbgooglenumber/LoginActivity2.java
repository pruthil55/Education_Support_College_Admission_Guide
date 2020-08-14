package com.example.loginfbgooglenumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity2 extends AppCompatActivity {

    private EditText et_phone_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        et_phone_no = findViewById(R.id.et_phone_no);

        findViewById(R.id.btn_generate_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = "91";

                String number = et_phone_no.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    et_phone_no.setError("Valid number is required");
                    et_phone_no.requestFocus();
                    return;
                }

                String phoneNumber = "+" + code + number;

                Intent intent = new Intent(LoginActivity2.this, VerifyPhoneActivity.class);
                intent.putExtra("phonenumber", phoneNumber);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity2.this, LogoutActivityDrawer.class));
    }

}
