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

public class ViewRanking extends AppCompatActivity {

    DatabaseReference databasecollageRanking;
    ArrayList<CollageRanking> list;
    RecyclerView recyclerViewCollegeRanking;
    SearchView searchViewRanking;

    String collageFullName;

    public static ProgressDialog progressDialog;

    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudienceNetworkAds.initialize(this);
        setContentView(R.layout.activity_view_ranking);


        adView = new AdView(this, "250531626269097_251873062801620", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.loadAd();




        databasecollageRanking = FirebaseDatabase.getInstance().getReference("college");
        recyclerViewCollegeRanking = findViewById(R.id.recyclerViewCollegeRanking);
        searchViewRanking = findViewById(R.id.searchViewRanking);

        progressDialog = new ProgressDialog(ViewRanking.this);

        showProgressBar();

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (databasecollageRanking != null) {

            databasecollageRanking.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists())
                    {
                        list = new ArrayList<>();


                        for (DataSnapshot collegeSnapshot : dataSnapshot.getChildren())
                        {
                            collageFullName = collegeSnapshot.getKey();

                            CollageRanking collageRanking=new CollageRanking(collageFullName);
                            list.add(collageRanking);
                        }

                        ViewRanking.progressDialog.dismiss();

                        AdapterClassCollageRankingList adapterClassCollageRankingList = new AdapterClassCollageRankingList(list, ViewRanking.this);
                        recyclerViewCollegeRanking.setLayoutManager(new LinearLayoutManager(ViewRanking.this));
                        recyclerViewCollegeRanking.setAdapter(adapterClassCollageRankingList);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ViewRanking.this, databaseError.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        if (searchViewRanking != null)
        {
            searchViewRanking.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        ArrayList<CollageRanking> myList = new ArrayList<>();
        for (CollageRanking object : list)
        {
            if (object.getClgFullName().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);

            }
        }

        AdapterClassCollageRankingList adapterClassCollageRankingList = new AdapterClassCollageRankingList(myList, ViewRanking.this);
        recyclerViewCollegeRanking.setLayoutManager(new LinearLayoutManager(ViewRanking.this));
        recyclerViewCollegeRanking.setAdapter(adapterClassCollageRankingList);

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
