package com.example.loginfbgooglenumber;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity {

    private ImageView iv_google_login_button, iv_phone_login_button, iv_back_button;

    private GoogleSignInClient mGoogleSignInClient;
    private String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private int RC_SIGN_IN = 1;

    public static ProgressDialog progressDialog;

    DatabaseReference databaseReference;

    UserData userData;

    // added for popup dialog for successfully registered or not
    Dialog popup_dialog;
    Button btn_closePopupPositiveImg;
    ImageView iv_closePopupPositiveImg;
    TextView tv_verify_message;

    public static int login_status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (GiveCollegeRating.progressDialog == null) {
        }
        else {
            GiveCollegeRating.progressDialog.dismiss();
        }

        LoadData();

        iv_google_login_button = findViewById(R.id.iv_google_login_button);
        iv_phone_login_button = findViewById(R.id.iv_phone_login_button);
        iv_back_button = findViewById(R.id.iv_back_button);

        mAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserDataForStatus");

        userData = new UserData();

        popup_dialog = new Dialog(this);

        progressDialog = new ProgressDialog(LoginActivity.this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);

//        For sign out we can use following code
//        btnSignOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mGoogleSignInClient.signOut();
//                Toast.makeText(MainActivity.this,"You are Logged Out",Toast.LENGTH_SHORT).show();
//            }
//        });

        iv_google_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        iv_phone_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, LoginActivity2.class);
                startActivity(intent);
            }
        });

        iv_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, LogoutActivityDrawer.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN) {
            LoginActivity.showProgressBar();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{

            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            FirebaseGoogleAuth(acc);
        }
        catch (ApiException e){
            FancyToast.makeText(LoginActivity.this,""+e.getMessage(),FancyToast.LENGTH_LONG, FancyToast.ERROR,true).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        //check if the account is null
        if (acct != null) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FancyToast.makeText(LoginActivity.this,"Signed In Successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        FancyToast.makeText(LoginActivity.this,"Login Failed",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        updateUI(null);
                    }
                }
            });
        }
        else{
            FancyToast.makeText(LoginActivity.this,"Authentication Failed",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
        }
    }

    private void updateUI(FirebaseUser fUser){
//        btnSignOut.setVisibility(View.VISIBLE);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account !=  null) {
            final String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            final String personEmail = account.getEmail();
            final String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    LoginActivity.login_status = 1;
                    saveData();

                    if (dataSnapshot.child(personId).exists())
                    {
                        String status = dataSnapshot.child(personId).child("reviewGiven").getValue().toString();

                        if (status.equals("true")) {
                            try {
                                showPositivePopupDialog();
                            }
                            catch (WindowManager.BadTokenException e) {

                            }
                        }
                        else {
                            Toast.makeText(LoginActivity.this,personName + "  " + personEmail ,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, GiveCollegeRating.class);
                            intent.putExtra("personId", personId);
                            startActivity(intent);
                        }
                    }
                    else {
                        storeDataIntoDatabase(personId);
                        Intent intent = new Intent(LoginActivity.this, GiveCollegeRating.class);
                        intent.putExtra("personId", personId);
                        startActivity(intent);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

    public static void showProgressBar() {
        progressDialog.show();

        progressDialog.setContentView(R.layout.progress_dialog);

        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    public void showPositivePopupDialog()
    {
        popup_dialog.show();
        popup_dialog.setContentView(R.layout.popup_dialog_positive);

        iv_closePopupPositiveImg = (ImageView) popup_dialog.findViewById(R.id.iv_closePopupPositiveImg);
        btn_closePopupPositiveImg = (Button) popup_dialog.findViewById(R.id.btn_closePopupPositiveImg);
        tv_verify_message = (TextView) popup_dialog.findViewById(R.id.tv_verify_message);

//        tv_verify_message.setText("Verification email has been sent to you... Please verify your Email "+
//                et_charusatemailid.getText().toString().trim().toLowerCase());

        try {
            popup_dialog.getWindow().setBackgroundDrawableResource(
                    android.R.color.transparent
            );
        } catch (final IllegalArgumentException e) {
            LoginActivity.login_status = 2;
            saveData();

        } catch (final Exception e) {
            LoginActivity.login_status = 2;
            saveData();
        }


        iv_closePopupPositiveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup_dialog.dismiss();
                Intent intent = new Intent(LoginActivity.this, LogoutActivityDrawer.class);
                startActivity(intent);
            }
        });

        btn_closePopupPositiveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup_dialog.dismiss();
                Intent intent = new Intent(LoginActivity.this, LogoutActivityDrawer.class);
                startActivity(intent);
            }
        });

        popup_dialog.setCanceledOnTouchOutside(false);
        popup_dialog.setCancelable(false);

    }

    public void storeDataIntoDatabase(String personId)
    {
        userData.setReviewGiven(false);

        databaseReference.child(personId).setValue(userData);

    }




    public void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("savecounter",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("counterkey",LoginActivity.login_status);
        editor.apply();
    }
    public void LoadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("savecounter",MODE_PRIVATE);
        LoginActivity.login_status = sharedPreferences.getInt("counterkey",MODE_PRIVATE);
        // Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveData();
    }
}