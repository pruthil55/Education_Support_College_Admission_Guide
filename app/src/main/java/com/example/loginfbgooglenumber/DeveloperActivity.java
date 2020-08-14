package com.example.loginfbgooglenumber;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class DeveloperActivity extends AppCompatActivity {

    ImageView iv_close_developer;
    TextView tv_mobile_no_pratik, tv_mail_pratik, tv_linkedin_pratik, tv_github_pratik;

    TextView tv_mobile_no_dhruv, tv_mail_dhruv, tv_linkedin_dhruv, tv_github_dhruv;

    TextView tv_mobile_no_pruthil, tv_mail_pruthil, tv_linkedin_pruthil, tv_github_pruthil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        iv_close_developer = findViewById(R.id.iv_close_developer);

        tv_mobile_no_pratik = findViewById(R.id.tv_mobile_no_pratik);
        tv_mail_pratik = findViewById(R.id.tv_mail_pratik);
        tv_linkedin_pratik = findViewById(R.id.tv_linkedin_pratik);
        tv_github_pratik = findViewById(R.id.tv_github_pratik);

        tv_mobile_no_dhruv = findViewById(R.id.tv_mobile_no_dhruv);
        tv_mail_dhruv = findViewById(R.id.tv_mail_dhruv);
        tv_linkedin_dhruv = findViewById(R.id.tv_linkedin_dhruv);
        tv_github_dhruv = findViewById(R.id.tv_github_dhruv);

        tv_mobile_no_pruthil = findViewById(R.id.tv_mobile_no_pruthil);
        tv_mail_pruthil = findViewById(R.id.tv_mail_pruthil);
        tv_linkedin_pruthil = findViewById(R.id.tv_linkedin_pruthil);
        tv_github_pruthil = findViewById(R.id.tv_github_pruthil);


        iv_close_developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeveloperActivity.this, LogoutActivityDrawer.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        tv_mobile_no_pratik.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "6353905147"));// Initiates the Intent
                startActivity(intent);
            }
        });

        tv_mail_pratik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + "Regarding contact (Education Support)"+ "&body=" + "This mail is Regarding Developer Contact. Please write below.\n-----------------------------------------------------------------\n" + "&to=" + "pratikdhoriyani405@gmail.com");
                mailIntent.setData(data);
                startActivity(Intent.createChooser(mailIntent, "Send mail..."));

            }
        });

        tv_linkedin_pratik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/pratik-dhoriyani-68a18b179/?originalSubdomain=in"));
                startActivity(intent);
            }
        });

        tv_github_pratik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/pratikdhoriyani"));
                startActivity(intent);
            }
        });


        tv_mobile_no_dhruv.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "6353868832"));// Initiates the Intent
                startActivity(intent);
            }
        });

        tv_mail_dhruv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + "Regarding contact (Education Support)"+ "&body=" + "This mail is Regarding Developer Contact. Please write below.\n-----------------------------------------------------------------\n" + "&to=" + "www.startnew02@gmail.com");
                mailIntent.setData(data);
                startActivity(Intent.createChooser(mailIntent, "Send mail..."));

            }
        });

        tv_linkedin_dhruv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/dhruv-kachhadiya-766590178/?originalSubdomain=in"));
                startActivity(intent);
            }
        });

        tv_github_dhruv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/dhruv-kachhadiya"));
                startActivity(intent);
            }
        });



        tv_mobile_no_pruthil.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "6359261969"));// Initiates the Intent
                startActivity(intent);
            }
        });

        tv_mail_pruthil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + "Regarding contact (Education Support)"+ "&body=" + "This mail is Regarding Developer Contact. Please write below.\n-----------------------------------------------------------------\n" + "&to=" + "pruthilhirpara9909283353@gmail.com");
                mailIntent.setData(data);
                startActivity(Intent.createChooser(mailIntent, "Send mail..."));

            }
        });

        tv_linkedin_pruthil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/pruthil-hirpara-51646018b/?originalSubdomain=in"));
                startActivity(intent);
            }
        });

        tv_github_pruthil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/pruthil55"));
                startActivity(intent);
            }
        });

    }
}
