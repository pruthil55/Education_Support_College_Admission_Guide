package com.example.loginfbgooglenumber;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewDetailsRating extends AppCompatActivity {

    TextView tvFieldName;

    DatabaseReference databaseDetail;

    Float teachingStaffRating , educationalEnvironmentRating , educationalResourcesRating , libraryServicesRating , collegeInfrastructureRating, placementReviewRating;

    ListView listViewDetails;

    List<Details> detailsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details_rating);

        tvFieldName = (TextView) findViewById(R.id.tvFieldName);

        listViewDetails = (ListView) findViewById(R.id.listViewDetails);
        detailsList = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        String clg = extras.getString(ViewFieldRating.COLLEGE_NAME);
        String field = extras.getString(ViewFieldRating.FIELD_NAME);

        tvFieldName.setText(field.toUpperCase());

        databaseDetail = FirebaseDatabase.getInstance().getReference().child("college").child(clg).child(field);

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseDetail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                detailsList.clear();

                for(DataSnapshot detailSnapshot : dataSnapshot.getChildren())
                {
                    if(!(detailSnapshot.getKey().equals("noofuser")) || !(detailSnapshot.getKey().equals("fieldtotal")))
                    {
//                        fieldName=fieldSnapshot.getKey();
                        teachingStaffRating = Float.valueOf(dataSnapshot.child("teachingstaffrating").getValue().toString().trim());
                        educationalEnvironmentRating = Float.valueOf(dataSnapshot.child("educationalenvironmentrating").getValue().toString().trim());
                        educationalResourcesRating = Float.valueOf(dataSnapshot.child("educationalresourcesrating").getValue().toString().trim());
                        libraryServicesRating = Float.valueOf(dataSnapshot.child("libraryservicesrating").getValue().toString().trim());
                        collegeInfrastructureRating = Float.valueOf(dataSnapshot.child("collegeinfrastructurerating").getValue().toString().trim());
                        placementReviewRating = Float.valueOf(dataSnapshot.child("placementReviewRating").getValue().toString().trim());
                    }
                }
                Details details = new Details(teachingStaffRating,educationalEnvironmentRating,educationalResourcesRating,libraryServicesRating,collegeInfrastructureRating,placementReviewRating);
                detailsList.add(details);

                DetailList adapter = new DetailList(ViewDetailsRating.this , detailsList);
                listViewDetails.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}
