package com.example.loginfbgooglenumber;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewCollegeRating extends AppCompatActivity {

    DatabaseReference databaseCollege;
    ArrayList<CollegeList> list;
    RecyclerView recyclerViewCollege;
    SearchView searchView;

    String collageName;
    float clgRating;
    int noOfRaterForCollege;

    public static ProgressDialog progressDialog;

    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudienceNetworkAds.initialize(this);
        setContentView(R.layout.activity_view_college_rating);


        adView = new AdView(this, "250531626269097_251874919468101", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.loadAd();



        progressDialog = new ProgressDialog(ViewCollegeRating.this);

        showProgressBar();

        databaseCollege = FirebaseDatabase.getInstance().getReference("college");
        recyclerViewCollege = findViewById(R.id.recyclerViewCollege);
        searchView = findViewById(R.id.searchView);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (databaseCollege != null) {

            databaseCollege.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists())
                    {
                        list = new ArrayList<>();

                        for (DataSnapshot collegeSnapshot : dataSnapshot.getChildren())
                        {
                            collageName=collegeSnapshot.getKey();
                            clgRating=Float.valueOf(dataSnapshot.child(collageName).child("collegetotal").getValue().toString().trim());
                            noOfRaterForCollege=Integer.valueOf(dataSnapshot.child(collageName).child("noofuser").getValue().toString().trim());

                            CollegeList collegeList = new CollegeList(collageName,clgRating,noOfRaterForCollege);
                            list.add(collegeList);
                        }

                        ViewCollegeRating.progressDialog.dismiss();

                        AdapterClass adapterClass = new AdapterClass(list, ViewCollegeRating.this);
                        recyclerViewCollege.setLayoutManager(new LinearLayoutManager(ViewCollegeRating.this));
                        recyclerViewCollege.setAdapter(adapterClass);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ViewCollegeRating.this, databaseError.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        if (searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {

                    search(s);

                    return true;
                }
            });
        }

    }

    private void search(String str)
    {

        ArrayList<CollegeList> myList = new ArrayList<>();
        for (CollegeList object : list)
        {
            if (object.getCollegeName().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);

            }
        }

        AdapterClass adapterClass = new AdapterClass(myList, ViewCollegeRating.this);
        recyclerViewCollege.setLayoutManager(new LinearLayoutManager(ViewCollegeRating.this));
        recyclerViewCollege.setAdapter(adapterClass);

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
}
