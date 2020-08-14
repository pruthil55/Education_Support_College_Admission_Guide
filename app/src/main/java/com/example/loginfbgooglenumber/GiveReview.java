package com.example.loginfbgooglenumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class GiveReview extends AppCompatActivity {


    Details obj1 = new Details();
    Details obj2 = new Details();

    Button btnSubmit;
    RatingBar ratingBar1 , ratingBar2 , ratingBar3 , ratingBar4 , ratingBar5 , ratingBar6;

    DatabaseReference reference1;
    ArrayList<String> name=new ArrayList<String>();

    int fieldCounter = 0;

    public String personId;
    public String phonenumber;

    // databasereference for updating status of review given or not
    DatabaseReference reviewReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_review);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar1);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        ratingBar3 = (RatingBar) findViewById(R.id.ratingBar3);
        ratingBar4 = (RatingBar) findViewById(R.id.ratingBar4);
        ratingBar5 = (RatingBar) findViewById(R.id.ratingBar5);
        ratingBar6 = (RatingBar) findViewById(R.id.ratingBar6);

        Intent intent = getIntent();
        personId = intent.getStringExtra("personId");
        phonenumber = intent.getStringExtra("phonenumber");
        obj1.clgName = intent.getStringExtra("collegeName");
        obj1.fieldName = intent.getStringExtra("fieldName");


        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                obj1.teachingStaffRating = v;
//                Toast.makeText(GiveReview.this, ""+obj1.teachingStaffRating, Toast.LENGTH_SHORT).show();
            }
        });

        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                obj1.educationalEnvironmentRating = v;
//                Toast.makeText(GiveReview.this, ""+obj1.educationalEnvironentRating, Toast.LENGTH_SHORT).show();

            }
        });

        ratingBar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                obj1.educationalResourcesRating = v;
//                Toast.makeText(GiveReview.this, ""+obj1.educationalResourcesRating, Toast.LENGTH_SHORT).show();

            }
        });

        ratingBar4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                obj1.libraryServicesRating = v;
//                Toast.makeText(GiveReview.this, ""+obj1.librartServicesRating, Toast.LENGTH_SHORT).show();
            }
        });

        ratingBar5.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                obj1.collegeInfrastructureRating = v;
//                Toast.makeText(GiveReview.this, ""+obj1.collegeInfrastructureRating, Toast.LENGTH_SHORT).show();
            }
        });

        ratingBar6.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                obj1.placementReviewRating = v;
//                Toast.makeText(GiveReview.this, ""+obj1.collegeInfrastructureRating, Toast.LENGTH_SHORT).show();
            }
        });

        //complete logic for database
        reference1 = FirebaseDatabase.getInstance().getReference().child("college");


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                obj1.totalRatingField = (obj1.teachingStaffRating + obj1.educationalEnvironmentRating + obj1.educationalResourcesRating + obj1.libraryServicesRating + obj1.collegeInfrastructureRating + obj1.placementReviewRating)/6.0f;

                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            obj2.teachingStaffRating = Float.valueOf(dataSnapshot.child(GiveCollegeRating.tempClgName)
                                    .child(GiveCollegeRating.tempFieldName).child("teachingstaffrating").getValue().toString().trim());

                            obj2.educationalEnvironmentRating = Float.valueOf(dataSnapshot.child(GiveCollegeRating.tempClgName)
                                    .child(GiveCollegeRating.tempFieldName).child("educationalenvironmentrating").getValue().toString().trim());

                            obj2.educationalResourcesRating = Float.valueOf(dataSnapshot.child(GiveCollegeRating.tempClgName)
                                    .child(GiveCollegeRating.tempFieldName).child("educationalresourcesrating").getValue().toString().trim());

                            obj2.libraryServicesRating = Float.valueOf(dataSnapshot.child(GiveCollegeRating.tempClgName)
                                    .child(GiveCollegeRating.tempFieldName).child("libraryservicesrating").getValue().toString().trim());

                            obj2.collegeInfrastructureRating = Float.valueOf(dataSnapshot.child(GiveCollegeRating.tempClgName)
                                    .child(GiveCollegeRating.tempFieldName).child("collegeinfrastructurerating").getValue().toString().trim());

                            obj2.placementReviewRating = Float.valueOf(dataSnapshot.child(GiveCollegeRating.tempClgName)
                                    .child(GiveCollegeRating.tempFieldName).child("placementReviewRating").getValue().toString().trim());


                            obj2.noOfUserCollege = Integer.valueOf(dataSnapshot.child(GiveCollegeRating.tempClgName)
                                    .child("noofuser").getValue().toString().trim());

                            obj2.noOfUserField = Integer.valueOf(dataSnapshot.child(GiveCollegeRating.tempClgName)
                                    .child(GiveCollegeRating.tempFieldName).child("noofuser").getValue().toString().trim());

                            obj2.noOfUserCollege = obj2.noOfUserCollege + 1;
                            obj2.noOfUserField = obj2.noOfUserField + 1;

//                        obj2.teachingStaffRating = Float.valueOf(new DecimalFormat("##.##").format(23));

                            obj2.teachingStaffRating = Float.valueOf(new DecimalFormat("##.##").format(Float.valueOf((obj2.teachingStaffRating * (obj2.noOfUserField - 1) + (obj1.teachingStaffRating)) / Float.valueOf(obj2.noOfUserField))));
                            obj2.educationalEnvironmentRating = Float.valueOf(new DecimalFormat("##.##").format(Float.valueOf((obj2.educationalEnvironmentRating * (obj2.noOfUserField - 1)) + obj1.educationalEnvironmentRating) / Float.valueOf(obj2.noOfUserField)));
                            obj2.educationalResourcesRating = Float.valueOf(new DecimalFormat("##.##").format(Float.valueOf((obj2.educationalResourcesRating * (obj2.noOfUserField - 1)) + obj1.educationalResourcesRating) / Float.valueOf(obj2.noOfUserField)));
                            obj2.libraryServicesRating = Float.valueOf(new DecimalFormat("##.##").format(Float.valueOf((obj2.libraryServicesRating * (obj2.noOfUserField - 1)) + obj1.libraryServicesRating) / Float.valueOf(obj2.noOfUserField)));
                            obj2.collegeInfrastructureRating = Float.valueOf(new DecimalFormat("##.##").format(Float.valueOf((obj2.collegeInfrastructureRating * (obj2.noOfUserField - 1)) + obj1.collegeInfrastructureRating) / Float.valueOf(obj2.noOfUserField)));
                            obj2.placementReviewRating = Float.valueOf(new DecimalFormat("##.##").format(Float.valueOf((obj2.placementReviewRating * (obj2.noOfUserField - 1)) + obj1.placementReviewRating) / Float.valueOf(obj2.noOfUserField)));


                            obj2.totalRatingField = Float.valueOf(new DecimalFormat("##.##").format(Float.valueOf((obj2.teachingStaffRating + obj2.educationalEnvironmentRating + obj2.educationalResourcesRating + obj2.libraryServicesRating + obj2.collegeInfrastructureRating)) / 5.0f));


//                        obj2.teachingStaffRating = Float.valueOf((obj2.teachingStaffRating*(obj2.noOfUserField-1) + (obj1.teachingStaffRating))/Float.valueOf(obj2.noOfUserField));
//                        obj2.educationalEnvironmentRating = Float.valueOf((obj2.educationalEnvironmentRating*(obj2.noOfUserField-1)) + obj1.educationalEnvironmentRating)/Float.valueOf(obj2.noOfUserField);
//                        obj2.educationalResourcesRating = Float.valueOf((obj2.educationalResourcesRating*(obj2.noOfUserField-1)) + obj1.educationalResourcesRating)/Float.valueOf(obj2.noOfUserField);
//                        obj2.libraryServicesRating = Float.valueOf((obj2.libraryServicesRating*(obj2.noOfUserField-1)) + obj1.libraryServicesRating)/Float.valueOf(obj2.noOfUserField);
//                        obj2.collegeInfrastructureRating =  Float.valueOf((obj2.collegeInfrastructureRating*(obj2.noOfUserField-1)) + obj1.collegeInfrastructureRating)/Float.valueOf(obj2.noOfUserField);
//                        obj2.totalRatingField = Float.valueOf((obj2.teachingStaffRating + obj2.educationalEnvironmentRating + obj2.educationalResourcesRating + obj2.libraryServicesRating + obj2.collegeInfrastructureRating))/5.0f;


                            DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("college");

                            reference2.child(GiveCollegeRating.tempClgName).child(GiveCollegeRating.tempFieldName).child("teachingstaffrating").setValue(obj2.teachingStaffRating);
                            reference2.child(GiveCollegeRating.tempClgName).child(GiveCollegeRating.tempFieldName).child("educationalenvironmentrating").setValue(obj2.educationalEnvironmentRating);
                            reference2.child(GiveCollegeRating.tempClgName).child(GiveCollegeRating.tempFieldName).child("educationalresourcesrating").setValue(obj2.educationalResourcesRating);
                            reference2.child(GiveCollegeRating.tempClgName).child(GiveCollegeRating.tempFieldName).child("libraryservicesrating").setValue(obj2.libraryServicesRating);
                            reference2.child(GiveCollegeRating.tempClgName).child(GiveCollegeRating.tempFieldName).child("collegeinfrastructurerating").setValue(obj2.collegeInfrastructureRating);
                            reference2.child(GiveCollegeRating.tempClgName).child(GiveCollegeRating.tempFieldName).child("placementReviewRating").setValue(obj2.placementReviewRating);

                            reference2.child(GiveCollegeRating.tempClgName).child(GiveCollegeRating.tempFieldName).child("noofuser").setValue(obj2.noOfUserField);
                            reference2.child(GiveCollegeRating.tempClgName).child(GiveCollegeRating.tempFieldName).child("fieldtotal").setValue(obj2.totalRatingField);
                            reference2.child(GiveCollegeRating.tempClgName).child("noofuser").setValue(obj2.noOfUserCollege);


                            DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference().child("college").child(GiveCollegeRating.tempClgName);

                            itemsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        name.add(ds.getKey());
                                    }
                                    for (int j = 0; j < name.size(); j++) {
                                        if ((name.get(j)).equals("Mechanical Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
//                                        obj2.totalRatingCollege = obj2.totalRatingCollege + Float.valueOf(dataSnapshot
//                                                .child(name.get(j)).child("fieldtotal").getValue().toString().trim());
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Civil Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
//                                        obj2.totalRatingCollege = obj2.totalRatingCollege + Float.valueOf(dataSnapshot
//                                                .child(name.get(j)).child("fieldtotal").getValue().toString().trim());
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Electrical Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
//                                        obj2.totalRatingCollege = obj2.totalRatingCollege + Float.valueOf(dataSnapshot
//                                                .child(name.get(j)).child("fieldtotal").getValue().toString().trim());
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Computer Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Computer Science Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Electronics And Communication Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Information Technology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Chemical Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Automobile Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Instrumentation Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Environmental Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Mechatronics Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Dairy Technology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Instrumentation And Control")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Bio Chemical Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Information And Communication Technology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Electrical and Electronics Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Electronics Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Aeronautical Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Production Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Agricultural Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Food Processing")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Textile Technology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Biomedical Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Biotechnology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Mining Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Water Management")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Automation and Robotics")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Plastic Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Industrial Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Marine Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Metallurgical Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Nano Technology Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Petrochemical Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Petroleum Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Manufacturing Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Materials Science and Technology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Mathematics and Computing Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Architecture Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Metallurgical and Materials Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Naval Architecture")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Rubber Technology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Safety and Fire Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Big Data Analytics")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Textile Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Construction Technology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Agricultural and Irrigation Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Agriculture Information Technology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Artificial Intelligence")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Bachelor of Construction Technology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Big Data and Analytics")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Chemical Technology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Chemical and Biochemical Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Civil Engineering Integrated")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Civil Engineering Lateral")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Civil Engineering Lateral Entry")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Civil Engineering Part Time")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Civil Irrigation and Water Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Cloud Based Applications")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Computer Engineering Artificial Intelligence")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Computer Science Engineering Cloud Technology and Information Security")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Computer Science Engineering Data Science")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Computer Science Engineering Integrated")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Computer Science Engineering Lateral Entry")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Computer Science Lateral")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Computer Science and Engineering Big Data and Analysis")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Cyber Security")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Dairy Science")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Dairy and Food Technology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Electrical Engineering Part Time")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Electrical and Electronics Engineering Integrated")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Electrical and Electronics Engineering Lateral")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Electrical and Electronics Engineering Lateral Entry")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Electronics And Communication Engineering Part Time")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Electronics And Telecommunication Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Electronics and Communication Engineering Lateral Entry")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Electronics and Instrumentation")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Environmental Science and Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Environmental Science and Technology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Food Technology")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Industrial Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Information Technology Lateral")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Information and Communication Technology with Minor Computational Science Hons")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Materials Science and Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Mathematics and Computing Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Mechanical Engineering Integrated")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Mechanical Engineering Lateral")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Mechanical Engineering Lateral Entry")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Mechanical Engineering Part Time")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Mechanical Engineering Self Finance")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Mechatronics Engineering Integrated")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Mechatronics Engineering Lateral Entry")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Power Electronics Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Production Engineering Self Finance")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Renewable Energy and Environmental Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Textile Processing")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Energy Systems Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Mechanical Engineering - Design and Manufacturing")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        } else if ((name.get(j)).equals("Civil and Infrastructure Engineering")) {
                                            obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(obj2.totalRatingCollege + Float.valueOf(dataSnapshot
                                                    .child(name.get(j)).child("fieldtotal").getValue().toString().trim())));
                                            fieldCounter++;
                                        }
                                    }

//                                obj2.totalRatingCollege = Float.valueOf(obj2.totalRatingCollege)/(fieldCounter);
                                    obj2.totalRatingCollege = Float.valueOf(new DecimalFormat("##.##").format(Float.valueOf(obj2.totalRatingCollege) / (fieldCounter)));

                                    reference1.child(GiveCollegeRating.tempClgName).child("collegetotal").setValue(obj2.totalRatingCollege);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                HashMap hashMap = new HashMap();
                hashMap.put("reviewGiven", true);

                // databasereference for updating status of review given or not
                reviewReference = FirebaseDatabase.getInstance().getReference().child("UserDataForStatus");

                if (personId != null) {
                    reviewReference.child(personId).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            FancyToast.makeText(GiveReview.this,"Your consideration is very much appreciated", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }
                    });
                }
                else {
                    reviewReference.child(phonenumber).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            FancyToast.makeText(GiveReview.this,"Your consideration is very much appreciated",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }
                    });
                }

                Intent intent = new Intent(GiveReview.this , LogoutActivityDrawer.class);
                startActivity(intent);

            }
        });

    }


}
