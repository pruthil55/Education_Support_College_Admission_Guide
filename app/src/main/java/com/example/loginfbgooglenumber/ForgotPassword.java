package com.example.loginfbgooglenumber;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText etForgotEmailId;
    Button btnChangePassword;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etForgotEmailId = findViewById(R.id.etForgotEmailId);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        mFirebaseAuth = FirebaseAuth.getInstance();

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etForgotEmailId.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(ForgotPassword.this, "Please enter Email Address.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    mFirebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgotPassword.this, "Please check your Email for reset password", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ForgotPassword.this , LoginActivity2.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(ForgotPassword.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }

            }
        });

    }
}
