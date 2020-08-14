package com.example.loginfbgooglenumber;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LogoutActivityDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    Button btn_give_review_college;

    public int remember;

    CardView card1 , card2 , card3 , card4 , card5 , card6;

    DrawerLayout drawer;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LOGIN_STATUS = "loginStatus";

    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudienceNetworkAds.initialize(this);
        setContentView(R.layout.activity_logout_drawer);


        adView = new AdView(this, "250531626269097_251870982801828", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.loadAd();


        LoadData();


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        if (LoginActivity.login_status == 1) {
            updateNavHeaderForGoogle();
        }
        else if (LoginActivity.login_status == 2) {
            updateNavHeaderForPhone();
        }
        else {
            updateNavHeaderWithoutLogin();
        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        btn_give_review_college = (Button)findViewById(R.id.btn_give_review_college);
        card1 = (CardView)findViewById(R.id.card1);
        card2 = (CardView)findViewById(R.id.card2);
        card3 = (CardView)findViewById(R.id.card3);
        card4 = (CardView)findViewById(R.id.card4);
        card5 = (CardView)findViewById(R.id.card5);
        card6 = (CardView)findViewById(R.id.card6);

        LoadData();

        btn_give_review_college.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogoutActivityDrawer.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LogoutActivityDrawer.this, AcpcRankPredictor.class);
                startActivity(intent);

            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LogoutActivityDrawer.this, ViewCollegeRating.class);
                startActivity(intent);

            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LogoutActivityDrawer.this, ViewRanking.class);
                startActivity(intent);

            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LogoutActivityDrawer.this, ViewBooks.class);
                startActivity(intent);

            }
        });

        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LogoutActivityDrawer.this, ScholershipInformation.class);
                startActivity(intent);

            }
        });

        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LogoutActivityDrawer.this, ImportantWebsite.class);
                startActivity(intent);

            }
        });

        //handling floating action menu
        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });


    }


    // added for exit app when user click back button 1 time and give choice yes/no
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        setResult(RESULT_CANCELED);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setTitle("Confirm exit..!!");
            alertDialogBuilder.setIcon(R.drawable.ic_exit_app);
            alertDialogBuilder.setMessage("Are you sure want to exit?");
            alertDialogBuilder.setCancelable(false);

            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finishAffinity();
                }
            });

            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout_activity_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_home:
                Intent intent1 = new Intent(LogoutActivityDrawer.this, LogoutActivityDrawer.class);
                startActivity(intent1);
                break;
            case R.id.nav_terms_and_condition:
                Intent intent2 = new Intent(LogoutActivityDrawer.this, TermsAndConditionActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_developer:
                Intent intent3 = new Intent(LogoutActivityDrawer.this, DeveloperActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_logout:

                mAuth.signOut();
                AuthUI.getInstance()
                        .signOut(LogoutActivityDrawer.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                FancyToast.makeText(LogoutActivityDrawer.this,"Sign Out Successfull",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                                LoginActivity.login_status = 0;
                                Intent intent = new Intent(LogoutActivityDrawer.this, LogoutActivityDrawer.class);
                                startActivity(intent);
                            }
                        });
                break;
            case R.id.nav_share:

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,"Education Support : College Admission Guide "+"\nhttps://play.google.com/store/apps/details?id=education.support.loginfbgooglenumber");
                shareIntent.setType("text/plain");
                Intent.createChooser(shareIntent,"Share via");
                startActivity(shareIntent);

                break;
            case R.id.nav_feedback:
                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + "Regarding contact (Education Support)"+ "&body=" + "This mail is Regarding Feedback and Query. Please write below.\n-----------------------------------------------------------------\n" + "&to=" + "educationsapk@gmail.com");
                mailIntent.setData(data);
                startActivity(Intent.createChooser(mailIntent, "Send mail..."));
                break;
            case R.id.nav_rateThisApp:

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=education.support.loginfbgooglenumber"));
                startActivity(i);

                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void updateNavHeaderForGoogle()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);


        ImageView nav_user_photo = headerView.findViewById(R.id.nav_current_user_photo);
        TextView nav_user_name = headerView.findViewById(R.id.nav_current_user_name);
        TextView nav_user_id = headerView.findViewById(R.id.nav_current_user_id);

        nav_user_id.setText(currentUser.getEmail());
        nav_user_name.setText(currentUser.getDisplayName());

        Glide.with(this).load(currentUser.getPhotoUrl()).into(nav_user_photo);
    }

    public void updateNavHeaderForPhone()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);


        ImageView nav_user_photo = headerView.findViewById(R.id.nav_current_user_photo);
        TextView nav_user_name = headerView.findViewById(R.id.nav_current_user_name);
        TextView nav_user_id = headerView.findViewById(R.id.nav_current_user_id);

        nav_user_id.setText(VerifyPhoneActivity.phoneNumberCopyForDrawer);
        nav_user_name.setText("Education Support");
    }

    public void updateNavHeaderWithoutLogin()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);


        ImageView nav_user_photo = headerView.findViewById(R.id.nav_current_user_photo);
        TextView nav_user_name = headerView.findViewById(R.id.nav_current_user_name);
        TextView nav_user_id = headerView.findViewById(R.id.nav_current_user_id);

        nav_user_id.setText("Always try to help \uD83D\uDE0A");
        nav_user_name.setText("Education Support");
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
