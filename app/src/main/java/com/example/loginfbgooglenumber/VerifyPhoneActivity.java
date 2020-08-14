package com.example.loginfbgooglenumber;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends AppCompatActivity {

    private String verificationId, phoneNumberCopy;
    private FirebaseAuth mAuth;
    private EditText et_otp;

    public static ProgressDialog progressDialog;

    DatabaseReference databaseReference;

    UserData userData;

    // added for popup dialog for successfully registered or not
    Dialog popup_dialog;
    Button btn_closePopupPositiveImg;
    ImageView iv_closePopupPositiveImg;
    TextView tv_verify_message;

    public static String phoneNumberCopyForDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        LoadData();

        mAuth = FirebaseAuth.getInstance();

        et_otp = findViewById(R.id.et_otp);

        progressDialog = new ProgressDialog(VerifyPhoneActivity.this);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserDataForStatus");

        userData = new UserData();

        popup_dialog = new Dialog(this);

        final String phonenumber = getIntent().getStringExtra("phonenumber");
        phoneNumberCopy = phonenumber;
        VerifyPhoneActivity.phoneNumberCopyForDrawer = phonenumber;



        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(phoneNumberCopy).exists())
                {
                    String status = dataSnapshot.child(phonenumber).child("reviewGiven").getValue().toString();

                    if (status.equals("true")) {
                        try {
                            showPositivePopupDialog();
                        }
                        catch (WindowManager.BadTokenException e) {

                        }
                    }
                    else {
                        sendVerificationCode(phonenumber);

                        findViewById(R.id.btn_verify_otp).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String code = et_otp.getText().toString().trim();

                                if (code.isEmpty() || code.length() < 6) {

                                    et_otp.setError("Please enter valid code...");
                                    et_otp.requestFocus();
                                    return;
                                }
                                VerifyPhoneActivity.showProgressBar();
                                verifyCode(code);
                            }
                        });
                    }

                }
                else {
                    sendVerificationCode(phonenumber);

                    findViewById(R.id.btn_verify_otp).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String code = et_otp.getText().toString().trim();

                            if (code.isEmpty() || code.length() < 6) {

                                et_otp.setError("Please enter valid code...");
                                et_otp.requestFocus();
                                return;
                            }
                            VerifyPhoneActivity.showProgressBar();
                            verifyCode(code);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            LoginActivity.login_status = 2;
                            saveData();
                            storeDataIntoDatabase();
                            Intent intent = new Intent(VerifyPhoneActivity.this, GiveCollegeRating.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("phonenumber",phoneNumberCopy);
                            startActivity(intent);

                        } else {
                            VerifyPhoneActivity.progressDialog.dismiss();
                            FancyToast.makeText(VerifyPhoneActivity.this,""+task.getException().getMessage(),FancyToast.LENGTH_LONG, FancyToast.ERROR,true).show();
                        }

                    }
                });
    }

    private void sendVerificationCode(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                VerifyPhoneActivity.showProgressBar();
                et_otp.setText(code);
                verifyCode(code);
            }
            else {
                // one solution found without OTP from: https://stackoverflow.com/questions/48740626/firebase-is-not-sending-otp-to-the-mobile-number
                VerifyPhoneActivity.showProgressBar();
                FancyToast.makeText(VerifyPhoneActivity.this,"Verifying code Automatically...",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                signInWithCredential(phoneAuthCredential);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            FancyToast.makeText(VerifyPhoneActivity.this,""+e.getMessage(),FancyToast.LENGTH_LONG, FancyToast.ERROR,true).show();
        }
    };

    public static void showProgressBar() {
        progressDialog.show();

        progressDialog.setContentView(R.layout.progress_dialog);

        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    public void storeDataIntoDatabase()
    {
        userData.setReviewGiven(false);

        databaseReference.child(phoneNumberCopy).setValue(userData);
    }

    public void showPositivePopupDialog()
    {
        popup_dialog.show();
        popup_dialog.setContentView(R.layout.popup_dialog_positive);

        iv_closePopupPositiveImg = (ImageView) popup_dialog.findViewById(R.id.iv_closePopupPositiveImg);
        btn_closePopupPositiveImg = (Button) popup_dialog.findViewById(R.id.btn_closePopupPositiveImg);
        tv_verify_message = (TextView) popup_dialog.findViewById(R.id.tv_verify_message);

        popup_dialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        iv_closePopupPositiveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup_dialog.dismiss();
                Intent intent = new Intent(VerifyPhoneActivity.this, LogoutActivityDrawer.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        btn_closePopupPositiveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup_dialog.dismiss();
                Intent intent = new Intent(VerifyPhoneActivity.this, LogoutActivityDrawer.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        popup_dialog.setCanceledOnTouchOutside(false);
        popup_dialog.setCancelable(false);

    }




    public void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("savecounter",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("counterkey",LoginActivity.login_status);
        editor.putString("phoneNumberCopyForDrawer",VerifyPhoneActivity.phoneNumberCopyForDrawer);
        editor.apply();
    }
    public void LoadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("savecounter",MODE_PRIVATE);
        LoginActivity.login_status = sharedPreferences.getInt("counterkey",MODE_PRIVATE);

        VerifyPhoneActivity.phoneNumberCopyForDrawer = sharedPreferences.getString("phoneNumberCopyForDrawer", "Logged in using Mobile No.");
        // Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveData();
    }

}
