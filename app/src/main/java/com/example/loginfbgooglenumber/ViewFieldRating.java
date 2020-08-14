package com.example.loginfbgooglenumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class ViewFieldRating extends AppCompatActivity {

    TextView tvClgName;

    DatabaseReference databaseField;
    String fieldName;
    Float fieldRating;
    int noOfRaterForField;
    ListView listViewField;

    List<Field> fieldList;

    public static final String FIELD_NAME = "1";
    public static final String COLLEGE_NAME = "2";

    String clg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_field_rating);

        tvClgName = (TextView) findViewById(R.id.tvFieldName);
        listViewField = (ListView) findViewById(R.id.listViewField);
        fieldList = new ArrayList<>();

        Intent intent = getIntent();

        clg = intent.getStringExtra("COLLEGE_NAME");

        tvClgName.setText(clg.toUpperCase());

        databaseField = FirebaseDatabase.getInstance().getReference("college").child(clg);

        listViewField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Field field = fieldList.get(i);

                Intent intent = new Intent(ViewFieldRating.this, ViewDetailsRating.class);
                Bundle extras = new Bundle();
                extras.putString(COLLEGE_NAME,clg);
                extras.putString(FIELD_NAME,field.getFieldName());
                intent.putExtras(extras);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseField.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                fieldList.clear();

                for(DataSnapshot fieldSnapshot : dataSnapshot.getChildren())
                {
                    if(
                                    fieldSnapshot.getKey().equals("Mechanical Engineering") || fieldSnapshot.getKey().equals("Civil Engineering") || fieldSnapshot.getKey().equals("Electrical Engineering") || fieldSnapshot.getKey().equals("Computer Engineering") || fieldSnapshot.getKey().equals("Computer Science Engineering") ||
                                    fieldSnapshot.getKey().equals("Electronics And Communication Engineering") || fieldSnapshot.getKey().equals("Information Technology") || fieldSnapshot.getKey().equals("Chemical Engineering") || fieldSnapshot.getKey().equals("Automobile Engineering") || fieldSnapshot.getKey().equals("Instrumentation Engineering") ||
                                    fieldSnapshot.getKey().equals("Environmental Engineering") || fieldSnapshot.getKey().equals("Mechatronics Engineering") || fieldSnapshot.getKey().equals("Dairy Technology") || fieldSnapshot.getKey().equals("Instrumentation And Control") || fieldSnapshot.getKey().equals("Bio Chemical Engineering") ||
                                    fieldSnapshot.getKey().equals("Information And Communication Technology") || fieldSnapshot.getKey().equals("Electrical and Electronics Engineering") || fieldSnapshot.getKey().equals("Electronics Engineering") || fieldSnapshot.getKey().equals("Aeronautical Engineering") || fieldSnapshot.getKey().equals("Production Engineering") ||
                                    fieldSnapshot.getKey().equals("Agricultural Engineering") || fieldSnapshot.getKey().equals("Food Processing") || fieldSnapshot.getKey().equals("Textile Technology") || fieldSnapshot.getKey().equals("Biomedical Engineering") || fieldSnapshot.getKey().equals("Biotechnology") ||
                                    fieldSnapshot.getKey().equals("Mining Engineering") || fieldSnapshot.getKey().equals("Water Management") || fieldSnapshot.getKey().equals("Automation and Robotics") || fieldSnapshot.getKey().equals("Plastic Engineering") || fieldSnapshot.getKey().equals("Industrial Engineering") ||
                                    fieldSnapshot.getKey().equals("Marine Engineering") || fieldSnapshot.getKey().equals("Metallurgical Engineering") || fieldSnapshot.getKey().equals("Nano Technology Engineering") || fieldSnapshot.getKey().equals("Petrochemical Engineering") || fieldSnapshot.getKey().equals("Petroleum Engineering") ||
                                    fieldSnapshot.getKey().equals("Manufacturing Engineering") || fieldSnapshot.getKey().equals("Materials Science and Technology") || fieldSnapshot.getKey().equals("Mathematics and Computing Engineering") || fieldSnapshot.getKey().equals("Architecture Engineering") || fieldSnapshot.getKey().equals("Metallurgical and Materials Engineering") ||
                                    fieldSnapshot.getKey().equals("Naval Architecture") || fieldSnapshot.getKey().equals("Rubber Technology") || fieldSnapshot.getKey().equals("Safety and Fire Engineering") || fieldSnapshot.getKey().equals("Big Data Analytics") || fieldSnapshot.getKey().equals("Textile Engineering") ||
                                    fieldSnapshot.getKey().equals("Construction Technology") || fieldSnapshot.getKey().equals("Agricultural and Irrigation Engineering")
                    )
                    {
                        fieldName=fieldSnapshot.getKey();
                        fieldRating=Float.valueOf(dataSnapshot.child(fieldName).child("fieldtotal").getValue().toString().trim());
                        noOfRaterForField=Integer.valueOf(dataSnapshot.child(fieldName).child("noofuser").getValue().toString().trim());
                        Field field = new Field(fieldName,fieldRating,noOfRaterForField);
                        fieldList.add(field);
                    }


                }

                FieldList adapter = new FieldList(ViewFieldRating.this , fieldList);
                listViewField.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
