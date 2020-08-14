package com.example.loginfbgooglenumber;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class GiveCollegeRating extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private static final String LOG_TAG = "Text API";
    private static final int PHOTO_REQUEST = 10;
    private TextView scanResults;
    private Uri imageUri;
    private TextRecognizer detector;
    private static final int REQUEST_WRITE_PERMISSION = 20;
    private static final String SAVED_INSTANCE_URI = "uri";
    private static final String SAVED_INSTANCE_RESULT = "result";

    EditText etname;
    TextView  tvbirthdate;
    TextView tvField;
    String name,clgname,birthdate,field;
    String splitname[];

    ArrayList<String> itemsClg=new ArrayList<>();
    SpinnerDialog spinnerDialogClg;

    ArrayList<String> itemsField=new ArrayList<>();
    SpinnerDialog spinnerDialogField;

    TextView tvclgname;
    Button btnScan;

    public static String tempClgName;
    public static String tempFieldName;

    public static final String CLG_TEMP = "";
    public static final String FIELD_TEMP = "";


    //for date
    private static final String TAB = "GiveCollegeRating";
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    // added for sending current user personId or phonenumber to another activity
    public String personId;
    public String phonenumber;


    public static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_college_rating);

        progressDialog = new ProgressDialog(GiveCollegeRating.this);

        if (LoginActivity.progressDialog == null) {
        }
        else {
            LoginActivity.progressDialog.dismiss();
        }
        if (VerifyPhoneActivity.progressDialog == null) {
        }
        else {
            VerifyPhoneActivity.progressDialog.dismiss();
        }

        btnScan = (Button) findViewById(R.id.btnScan);
        scanResults = (TextView) findViewById(R.id.scanResults);
        etname=(EditText) findViewById(R.id.etname);
        tvField=(TextView)findViewById(R.id.tvField);
        tvclgname = findViewById(R.id.tvclgname);

        // added for sending current user personId or phonenumber to another activity
        personId = getIntent().getStringExtra("personId");
        phonenumber = getIntent().getStringExtra("phonenumber");

        if (savedInstanceState != null) {
            imageUri = Uri.parse(savedInstanceState.getString(SAVED_INSTANCE_URI));
            scanResults.setText(savedInstanceState.getString(SAVED_INSTANCE_RESULT));
        }


        detector = new TextRecognizer.Builder(getApplicationContext()).build();

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etname.toString().isEmpty() || tvField.getText().toString().isEmpty() || tvclgname.getText().toString().isEmpty())
                {
                    Toast.makeText(GiveCollegeRating.this, "Please enter all the mandatory fields", Toast.LENGTH_LONG).show();
                    return;
                }

                showProgressBar();

                tempClgName = tvclgname.getText().toString().trim();
                tempFieldName = tvField.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("college");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.child(GiveCollegeRating.tempClgName).child(GiveCollegeRating.tempFieldName).exists()) {
                            FancyToast.makeText(GiveCollegeRating.this,"Please select valid combination of college and field!",FancyToast.LENGTH_LONG, FancyToast.ERROR,true).show();
                            startActivity(new Intent(GiveCollegeRating.this, LoginActivity.class));
                        }
                        else {
                            GiveCollegeRating.progressDialog.dismiss();
                            ActivityCompat.requestPermissions(GiveCollegeRating.this ,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
                            name=etname.getText().toString().trim().toUpperCase();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

//                clgname=etclgname.getText().toString().trim();
//                field=tvField.getText().toString().trim();

//                tempClgName = tvclgname.getText().toString().trim();
//                tempFieldName = tvField.getText().toString().trim();



//                Intent intent=new Intent(GiveCollegeRating.this, GiveReview.class);
//                intent.putExtra(CLG_TEMP,tempClgName);
//                intent.putExtra(FIELD_TEMP,tempFielgName);
//                startActivity(intent);


            }
        });


        itemsClg.add("AD Patel Institute of Technology, Anand");
        itemsClg.add("Aadishwar College of Technology, Gandhinagar");
        itemsClg.add("Aarya-Veer College of Engineering and Technology, Rajkot");
        itemsClg.add("Adani Institute of Infrastructure Engineering, Ahmedabad");
        itemsClg.add("Aditya College of Engineering and Advanced Studies, Ahmedabad");
        itemsClg.add("Aditya Silver Oak Institute of Technology, Ahmedabad");
        itemsClg.add("Ahmedabad Institute of Technology, Ahmedabad");
        itemsClg.add("Ahmedabad University, Ahmedabad");
        itemsClg.add("Alpha College of Engineering and Technology, Kalol");
        itemsClg.add("Amiraj College of Engineering and Technology, Ahmedabad");
        itemsClg.add("Anand Agricultural University, Anand");
        itemsClg.add("Apollo Institute of Engineering and Technology, Ahmedabad");
        itemsClg.add("Arrdekta Institute of Technology, Sabarkantha");
        itemsClg.add("Arun Muchhala Engineering College, Amreli");
        itemsClg.add("Aspee Shakilam Agricultural Biotechnology Institute, Surat");
        itemsClg.add("Atmiya Institute of Technology and Science, Rajkot");
        itemsClg.add("Atmiya University, Rajkot");
        itemsClg.add("Aurum Institute of Technology, Rajkot");
        itemsClg.add("BH Gardi College of Engineering and Technology, Rajkot");
        itemsClg.add("Babaria Institute of Technology, Vadodara");
        itemsClg.add("Balaji Engineering College, Junagadh");
        itemsClg.add("Bhagwan Arihant Institute of Technology, Surat");
        itemsClg.add("Bhagwan Mahavir College of Engineering and Technology, Surat");
        itemsClg.add("Birla Vishvakarma Mahavidyalaya, Anand");
        itemsClg.add("CEPT University, Ahmedabad");
        itemsClg.add("CK Pithawala College of Engineering and Technology, Surat");
        itemsClg.add("CU Shah College of Engineering and Technology, Surendranagar");
        itemsClg.add("CU Shah University, Surendranagar");
        itemsClg.add("Calrox Teachers University, Ahmedabad");
        itemsClg.add("Central Institute of Plastics Engineering and Technology, Ahmedabad");
        itemsClg.add("Chandubhai S Patel Institute of Technology, Anand");
        itemsClg.add("Chhotubhai Gopalbhai Patel Institute of Technology, Bardoli");
        itemsClg.add("College of Renewable Energy and Environmental Engineering, Gujarat");
        itemsClg.add("DA Degree Engineering and Technology, Mahemdabad");
        itemsClg.add("Darshan Institute of Engineering and Technology, Rajkot");
        itemsClg.add("Devang Patel Institute of Advance Technology and Research, Petlad");
        itemsClg.add("Dharmsinh Desai University, Nadiad");
        itemsClg.add("Dhirubhai Ambani Institute of Information and Communication Technology, Gandhinagar");
        itemsClg.add("Dr Jivraj Mehta Institute of Technology, Anand");
        itemsClg.add("Dr S and SS Ghandhy Government Engineering College, Surat");
        itemsClg.add("Dr Subhash Technical Campus, Junagadh");
        itemsClg.add("Dr VR Godhania College of Engineering and Technology, Porbandar");
        itemsClg.add("Engineering College, Tuwa");
        itemsClg.add("FD Mubin Degree College of Engineering, Bahiyal");
        itemsClg.add("GH Patel College of Engineering and Technology, Anand");
        itemsClg.add("GIDC Degree Engineering College, Navsari");
        itemsClg.add("GK Bharad Institute of Engineering, Rajkot");
        itemsClg.add("GSFC University, Vadodara");
        itemsClg.add("Gandhinagar Institute of Technology, Gandhinagar");
        itemsClg.add("Ganpat University Institute of Computer Technology, Mehsana");
        itemsClg.add("Government Engineering College, Bharuch");
        itemsClg.add("Government Engineering College, Bhavnagar");
        itemsClg.add("Government Engineering College, Bhuj");
        itemsClg.add("Government Engineering College, Dahod");
        itemsClg.add("Government Engineering College, Gandhinagar");
        itemsClg.add("Government Engineering College, Godhra");
        itemsClg.add("Government Engineering College, Modasa");
        itemsClg.add("Government Engineering College, Palanpur");
        itemsClg.add("Government Engineering College, Patan");
        itemsClg.add("Government Engineering College, Rajkot");
        itemsClg.add("Government Engineering College, Valsad");
        itemsClg.add("Grow More Foundations Group of Institutions Faculty of Engineering, Sabarkantha");
        itemsClg.add("Gujarat Institute of Technical Studies, Sabarkantha");
        itemsClg.add("Gujarat Power Engineering and Research Institute, Mehsana");
        itemsClg.add("Gyanmanjari Institute of Technology, Bhavnagar");
        itemsClg.add("HJD Institute of Technical Education and Research, Kutch");
        itemsClg.add("Hansaba College of Engineering and Technology, Sidhpur");
        itemsClg.add("Hasmukh Goswami College of Engineering, Ahmedabad");
        itemsClg.add("ITM Vocational University, Vadodara");
        itemsClg.add("Indian Institute of Information Technology, Surat");
        itemsClg.add("Indian Institute of Information Technology, Vadodara");
        itemsClg.add("Indian Institute of Technology Gandhinagar");
        itemsClg.add("Indrashil Institute of Science and Technology, Rajpur");
        itemsClg.add("Indrashil University, Kadi");
        itemsClg.add("Indus Institute of Technology and Engineering, Ahmedabad");
        itemsClg.add("Institute of Advanced Research, Gandhinagar");
        itemsClg.add("Institute of Engineering and Technology, Ahmedabad");
        itemsClg.add("Institute of Infrastructure Technology Research and Management, Ahmedabad");
        itemsClg.add("Institute of Technology and Management Universe, Vadodara");
        itemsClg.add("Institute of Technology, Ganpat University, Mehsana");
        itemsClg.add("Ipcowala Institute of Engineering and Technology, Anand");
        itemsClg.add("Junagadh Agricultural University, Junagadh");
        itemsClg.add("K J Faculty of Engineering and Technology, Shri Satsangi Saketdham Ram Ashram Group of Institutions, Mehsana");
        itemsClg.add("KJ Institute of Engineering and Technology, Vadodara");
        itemsClg.add("Kalol Institute of Technology and Research Centre, Kalol");
        itemsClg.add("Kamdhenu University, Gandhinagar");
        itemsClg.add("Knowledge Institute of Technology and Engineering, Anand");
        itemsClg.add("LD College of Engineering, Ahmedabad");
        itemsClg.add("LDRP Institute of Technology and Research, Gandhinagar");
        itemsClg.add("LJ Institute of Engineering and Technology, Ahmedabad");
        itemsClg.add("Laljibhai Chaturbhai Institute of Technology, Mehsana");
        itemsClg.add("Laxmi Institute of Technology, Sarigam");
        itemsClg.add("Leads Institute of Technology and Engineering, Bharuch");
        itemsClg.add("Lukhdhirji Engineering College, Morbi");
        itemsClg.add("MK College of Engineering and Technological Research, Patan");
        itemsClg.add("Madhuben and Bhanubhai Patel Institute of Technology, Anand");
        itemsClg.add("Maharaja Sayajirao University of Baroda, Vadodara");
        itemsClg.add("Mahatma Gandhi Institute of Technical Education and Research Centre, Navsari");
        itemsClg.add("Mahavir Swami College of Engineering and Technology, Surat");
        itemsClg.add("Mansinhbhai Institute of Dairy and Food Technology, Mehsana");
        itemsClg.add("Marwadi Education Foundation Group of Institutions, Rajkot");
        itemsClg.add("Marwadi University, Rajkot");
        itemsClg.add("Merchant Engineering College, Basna");
        itemsClg.add("Merchant Institute of Technology, Mehsana");
        itemsClg.add("Narnarayan Shastri Institute of Technology, Ahmedabad");
        itemsClg.add("Navrachana University, Vadodara");
        itemsClg.add("Neotech Institute of Technology, Vadodara");
        itemsClg.add("Nirma University, Ahmedabad");
        itemsClg.add("Noble Group of Institutions, Junagadh");
        itemsClg.add("Om Engineering College, Junagadh");
        itemsClg.add("Om Institute of Technology, Shahera");
        itemsClg.add("Om Shanti Engineering College, Rajkot");
        itemsClg.add("PP Savani University, Surat");
        itemsClg.add("Pacific School of Engineering, Surat");
        itemsClg.add("Parul Institute of Architecture and Research, Vadodara");
        itemsClg.add("Parul Institute of Design, Vadodara");
        itemsClg.add("Parul Institute of Engineering and Technology, Vadodara");
        itemsClg.add("Parul Institute of Technology, Vadodara");
        itemsClg.add("Plastindia International University, Vapi");
        itemsClg.add("Prime Institute of Engineering and Technology, Navsari");
        itemsClg.add("RK University, Rajkot");
        itemsClg.add("Rai University, Ahmedabad");
        itemsClg.add("Raksha Shakti University, Ahmedabad");
        itemsClg.add("S S Agrawal Institute of Engineering and Technology, Navsari");
        itemsClg.add("SAL College of Engineering, Ahmedabad");
        itemsClg.add("SAL Engineering and Technical Institute, Ahmedabad");
        itemsClg.add("SAL Institute of Engineering, Ahmedabad");
        itemsClg.add("SPB Patel Engineering College, Mehsana");
        itemsClg.add("SS Agrawal Colleges, Navsari");
        itemsClg.add("SVPES Faculty of Engineering, Technology and Research, Bardoli");
        itemsClg.add("Sabar Institute of Technology for Girls, Sabarkantha");
        itemsClg.add("Saffrony Institute of Technology, Linch");
        itemsClg.add("Sal Institute of Technology and Engineering Research, Ahmedabad");
        itemsClg.add("Samarth College of Engineering and Technology, Sabarkantha");
        itemsClg.add("Sanjaybhai Rajguru College of Engineering, Rajkot");
        itemsClg.add("Sankalchand Patel College of Engineering, Visnagar");
        itemsClg.add("Sardar Patel College Of Engineering, Bakrol");
        itemsClg.add("Sardar Vallabhbhai National Institute of Technology Surat");
        itemsClg.add("Sardar Vallabhbhai Patel Institute of Technology, Anand");
        itemsClg.add("Sardarkrushinagar Dantiwada Agricultural University, Sardarkrushinagar");
        itemsClg.add("Sarvajanik College of Engineering and Technology, Surat");
        itemsClg.add("School Technology, Pandit Deendayal Petroleum University, Gandhinagar");
        itemsClg.add("School of Petroleum Technology, Pandit Deendayal Petroleum University, Gandhinagar");
        itemsClg.add("Shankersinh Vaghela Bapu Institute of Technology, Gandhinagar");
        itemsClg.add("Shantilal Shah Engineering College, Bhavnagar");
        itemsClg.add("Sheth MC College of Dairy Science, Anand");
        itemsClg.add("Shree Kankeshwari Deviji Institute of Technology, Jamnagar");
        itemsClg.add("Shree Pandit Nathulalji Vyas Technical Campus, Surendranagar");
        itemsClg.add("Shree Saraswati Education Sansthan’s Group of Institutions- Dept of Engineering, Mehsana");
        itemsClg.add("Shree Swami Atmanand Saraswati Institute of Technology, Surat");
        itemsClg.add("Shree Swaminarayan Institute of Technology, Bhat");
        itemsClg.add("Shri Galbabhai Nanjibhai Patel Dairy Science and Food Technology College, Dantiwada");
        itemsClg.add("Shri JM Sabva Institute of Engineering and Technology, Botad");
        itemsClg.add("Shri Labhubhai Trivedi Institute of Engineering and Technology, Rajkot");
        itemsClg.add("Shri S'ad Vidya Mandal Institute of Technology, Bharuch");
        itemsClg.add("Shroff SR Rotary Institute of Chemical Technology, Bharuch");
        itemsClg.add("Sigma Engineering College, Matar");
        itemsClg.add("Sigma Institute of Engineering, Vadodara");
        itemsClg.add("Silver Oak College of Engineering and Technology, Ahmedabad");
        itemsClg.add("Sitarambhai Naranji Patel Institute of Technology and Research Centre, Surat");
        itemsClg.add("Smt Dharini Anilkumar Shukla Education Campus, Mahemdabad");
        itemsClg.add("Smt SR Patel Engineering College, Unjha");
        itemsClg.add("Smt Shantaben Haribhai Gajera Engineering College, Amreli");
        itemsClg.add("Swaminarayan College of Engineering and Technology, Kalol");
        itemsClg.add("Swarrnim Startup and Innovation University, Gandhinagar");
        itemsClg.add("Tatva Institute of Technological Studies, Modasa");
        itemsClg.add("UKA Tarsadia University, Bardoli");
        itemsClg.add("UV Patel College of Engineering, Mehsana");
        itemsClg.add("Universal College of Engineering and Technology, Gandhinagar");
        itemsClg.add("VVP Engineering College, Rajkot");
        itemsClg.add("Vadodara Institute of Engineering, Vadodara");
        itemsClg.add("Valia Institute of Technology, Valia");
        itemsClg.add("Veerayatan Institute of Engineering, Haripar");
        itemsClg.add("Veerayatn Group of Institutions- Faculty of Engineering and Management, Kutch");
        itemsClg.add("Vidhyadeep Institute of Engineering and Technology, Surat");
        itemsClg.add("Vidyabharti Trust Institute of Technology and Research Centre, Surat");
        itemsClg.add("Vishwakarma Government Engineering College, Ahmedabad");


        itemsField.add("Aeronautical Engineering");
        itemsField.add("Agricultural Engineering");
        itemsField.add("Agricultural and Irrigation Engineering");
        itemsField.add("Agriculture Information Technology");
        itemsField.add("Architecture Engineering");
        itemsField.add("Artificial Intelligence");
        itemsField.add("Automation and Robotics");
        itemsField.add("Automobile Engineering");
        itemsField.add("Bachelor of Construction Technology");
        itemsField.add("Big Data Analytics");
        itemsField.add("Big Data and Analytics");
        itemsField.add("Bio Chemical Engineering");
        itemsField.add("Biomedical Engineering");
        itemsField.add("Biotechnology");
        itemsField.add("Chemical Engineering");
        itemsField.add("Chemical Technology");
        itemsField.add("Chemical and Biochemical Engineering");
        itemsField.add("Civil Engineering Integrated");
        itemsField.add("Civil Engineering Lateral Entry");
        itemsField.add("Civil Engineering Lateral");
        itemsField.add("Civil Engineering Part Time");
        itemsField.add("Civil Engineering");
        itemsField.add("Civil Irrigation and Water Engineering");
        itemsField.add("Civil and Infrastructure Engineering");
        itemsField.add("Cloud Based Applications");
        itemsField.add("Computer Engineering Artificial Intelligence");
        itemsField.add("Computer Engineering");
        itemsField.add("Computer Science Engineering Cloud Technology and Information Security");
        itemsField.add("Computer Science Engineering Data Science");
        itemsField.add("Computer Science Engineering Integrated");
        itemsField.add("Computer Science Engineering Lateral Entry");
        itemsField.add("Computer Science Engineering");
        itemsField.add("Computer Science Lateral");
        itemsField.add("Computer Science and Engineering Big Data and Analysis");
        itemsField.add("Construction Technology");
        itemsField.add("Cyber Security");
        itemsField.add("Dairy Science");
        itemsField.add("Dairy Technology");
        itemsField.add("Dairy and Food Technology");
        itemsField.add("Electrical Engineering Part Time");
        itemsField.add("Electrical Engineering");
        itemsField.add("Electrical and Electronics Engineering Integrated");
        itemsField.add("Electrical and Electronics Engineering Lateral Entry");
        itemsField.add("Electrical and Electronics Engineering Lateral");
        itemsField.add("Electrical and Electronics Engineering");
        itemsField.add("Electronics And Communication Engineering Part Time");
        itemsField.add("Electronics And Communication Engineering");
        itemsField.add("Electronics And Telecommunication Engineering");
        itemsField.add("Electronics Engineering");
        itemsField.add("Electronics and Communication Engineering Lateral Entry");
        itemsField.add("Electronics and Instrumentation");
        itemsField.add("Energy Systems Engineering");
        itemsField.add("Environmental Engineering");
        itemsField.add("Environmental Science and Engineering");
        itemsField.add("Environmental Science and Technology");
        itemsField.add("Food Processing");
        itemsField.add("Food Technology");
        itemsField.add("Industrial Engineering");
        itemsField.add("Industrial Engineering");
        itemsField.add("Information And Communication Technology");
        itemsField.add("Information Technology Lateral");
        itemsField.add("Information Technology");
        itemsField.add("Information and Communication Technology with Minor Computational Science Hons");
        itemsField.add("Instrumentation And Control");
        itemsField.add("Instrumentation Engineering");
        itemsField.add("Manufacturing Engineering");
        itemsField.add("Marine Engineering");
        itemsField.add("Materials Science and Engineering");
        itemsField.add("Materials Science and Technology");
        itemsField.add("Mathematics and Computing Engineering");
        itemsField.add("Mathematics and Computing Engineering");
        itemsField.add("Mechanical Engineering - Design and Manufacturing");
        itemsField.add("Mechanical Engineering Integrated");
        itemsField.add("Mechanical Engineering Lateral Entry");
        itemsField.add("Mechanical Engineering Lateral");
        itemsField.add("Mechanical Engineering Part Time");
        itemsField.add("Mechanical Engineering Self Finance");
        itemsField.add("Mechatronics Engineering Integrated");
        itemsField.add("Mechatronics Engineering Lateral Entry");
        itemsField.add("Mechatronics Engineering");
        itemsField.add("Metallurgical Engineering");
        itemsField.add("Metallurgical and Materials Engineering");
        itemsField.add("Mining Engineering");
        itemsField.add("Nano Technology Engineering");
        itemsField.add("Naval Architecture");
        itemsField.add("Petrochemical Engineering");
        itemsField.add("Petroleum Engineering");
        itemsField.add("Plastic Engineering");
        itemsField.add("Power Electronics Engineering");
        itemsField.add("Production Engineering Self Finance");
        itemsField.add("Production Engineering");
        itemsField.add("Renewable Energy and Environmental Engineering");
        itemsField.add("Rubber Technology");
        itemsField.add("Safety and Fire Engineering");
        itemsField.add("Textile Engineering");
        itemsField.add("Textile Processing");
        itemsField.add("Textile Technology");
        itemsField.add("Water Management");
        itemsField.add("Mechanical Engineering");


        Collections.sort(itemsField);

        spinnerDialogClg=new SpinnerDialog(GiveCollegeRating.this,itemsClg,"Select or Search College","Close");// With No Animation
        spinnerDialogClg=new SpinnerDialog(GiveCollegeRating.this,itemsClg,"Select or Search College",R.style.DialogAnimations_SmileWindow,"Close");// With 	Animation

        spinnerDialogClg.setCancellable(true); // for cancellable
        spinnerDialogClg.setShowKeyboard(false);// for open keyboard by default


        spinnerDialogClg.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
//                Toast.makeText(test.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                tvclgname.setText(item);
            }
        });
        tvclgname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialogClg.showSpinerDialog();
            }
        });





        spinnerDialogField=new SpinnerDialog(GiveCollegeRating.this,itemsField,"Select or Search Field","Close");// With No Animation
        spinnerDialogField=new SpinnerDialog(GiveCollegeRating.this,itemsField,"Select or Search Field",R.style.DialogAnimations_SmileWindow,"Close");// With 	Animation

        spinnerDialogField.setCancellable(true); // for cancellable
        spinnerDialogField.setShowKeyboard(false);// for open keyboard by default


        spinnerDialogField.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
//                Toast.makeText(test.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                tvField.setText(item);
            }
        });
        tvField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialogField.showSpinerDialog();
            }
        });

    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString= DateFormat.getDateInstance().format(c.getTime());
        tvbirthdate.setText(currentDateString);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    Toast.makeText(GiveCollegeRating.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST && resultCode == RESULT_OK) {
            launchMediaScanIntent();
            try {
                splitname=name.split(" ",3);
                splitname[0]= splitname[0].toUpperCase();
                splitname[1]=splitname[1].toUpperCase();
                Bitmap bitmap = decodeBitmapUri(this, imageUri);
                if (detector.isOperational() && bitmap != null) {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> textBlocks = detector.detect(frame);
                    String blocks = "";
                    String lines = "";
                    String words = "";
                    int count=0;
                    for (int index = 0; index < textBlocks.size(); index++) {
                        //extract scanned text blocks here
                        TextBlock tBlock = textBlocks.valueAt(index);
                        blocks = blocks + tBlock.getValue() + "\n" + "\n";
                        for (Text line : tBlock.getComponents()) {
                            //extract scanned text lines here

                            lines = lines + line.getValue() + "\n";
                            for (Text element : line.getComponents()) {
                                //extract scanned text words here

                                String ele=element.getValue().toUpperCase();
//                                words = words + element.getValue() + ", ";
//                                if(splitname[0].equals(ele) || splitname[1].equals(ele)){
//                                    count++;
//                                }

                                if(lock_match(splitname[0],ele)>50 || lock_match(splitname[1],ele)>50){
                                    count++;
                                    if (count == 2) {
                                        break;
                                    }
                                }

                                if (count == 2) {
                                    break;
                                }


                            }
//                            break;

                            if (count == 2) {
                                break;
                            }


                        }
//                        break;

                        if (count == 2) {
                            break;
                        }

                    }

                    if(count==2)
                    {
                        Intent intent=new Intent(GiveCollegeRating.this, GiveReview.class);
                        intent.putExtra("personId", personId);
                        intent.putExtra("phonenumber", phonenumber);
                        intent.putExtra("collegeName",tempClgName);
                        intent.putExtra("fieldName",tempFieldName);
                        startActivity(intent);
//                                    break;
                    }
                    else
                    {
                        //Toast.makeText(this, name, Toast.LENGTH_LONG).show();
                        //Toast.makeText(this, splitname[0], Toast.LENGTH_LONG).show();
                        Toast.makeText(this, "Try again! Entered Detail is not same as Id card....", Toast.LENGTH_SHORT).show();
                    }


                    if (textBlocks.size() == 0) {
                        scanResults.setText("Scan Failed: Found nothing to scan");
                    } else {
                        scanResults.setText(scanResults.getText() + "Blocks: " + "\n");
                        scanResults.setText(scanResults.getText() + blocks + "\n");
                        scanResults.setText(scanResults.getText() + "---------" + "\n");
                        scanResults.setText(scanResults.getText() + "Lines: " + "\n");
                        scanResults.setText(scanResults.getText() + lines + "\n");
                        scanResults.setText(scanResults.getText() + "---------" + "\n");
                        scanResults.setText(scanResults.getText() + "Words: " + "\n");
                        scanResults.setText(scanResults.getText() + words + "\n");
                        scanResults.setText(scanResults.getText() + "---------" + "\n");
                    }
                } else {
                    scanResults.setText("Could not set up the detector!");
                }
            } catch (Exception e) {
                Toast.makeText(this, "Please Enter Name Same As Per Your Id Card!", Toast.LENGTH_LONG)
                        .show();
                //  Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(LOG_TAG, e.toString());
            }
        }
    }


    public static int lock_match(String s, String t) {



        int totalw = word_count(s);
        int total = 100;
        int perw = total / totalw;
        int gotperw = 0;

        if (!s.equals(t)) {

            for (int i = 1; i <= totalw; i++) {
                if (simple_match(split_string(s, i), t) == 1) {
                    gotperw = ((perw * (total - 10)) / total) + gotperw;
                } else if (front_full_match(split_string(s, i), t) == 1) {
                    gotperw = ((perw * (total - 20)) / total) + gotperw;
                } else if (anywhere_match(split_string(s, i), t) == 1) {
                    gotperw = ((perw * (total - 30)) / total) + gotperw;
                } else {
                    gotperw = ((perw * smart_match(split_string(s, i), t)) / total) + gotperw;
                }
            }
        } else {
            gotperw = 100;
        }
        return gotperw;
    }

    public static int anywhere_match(String s, String t) {
        int x = 0;
        if (t.contains(s)) {
            x = 1;
        }
        return x;
    }

    public static int front_full_match(String s, String t) {
        int x = 0;
        String tempt;
        int len = s.length();

        //----------Work Body----------//
        for (int i = 1; i <= word_count(t); i++) {
            tempt = split_string(t, i);
            if (tempt.length() >= s.length()) {
                tempt = tempt.substring(0, len);
                if (s.contains(tempt)) {
                    x = 1;
                    break;
                }
            }
        }
        //---------END---------------//
        if (len == 0) {
            x = 0;
        }
        return x;
    }

    public static int simple_match(String s, String t) {
        int x = 0;
        String tempt;
        int len = s.length();


        //----------Work Body----------//
        for (int i = 1; i <= word_count(t); i++) {
            tempt = split_string(t, i);
            if (tempt.length() == s.length()) {
                if (s.contains(tempt)) {
                    x = 1;
                    break;
                }
            }
        }
        //---------END---------------//
        if (len == 0) {
            x = 0;
        }
        return x;
    }

    public static int smart_match(String ts, String tt) {

        char[] s = new char[ts.length()];
        s = ts.toCharArray();
        char[] t = new char[tt.length()];
        t = tt.toCharArray();


        int slen = s.length;
        //number of 3 combinations per word//
        int combs = (slen - 3) + 1;
        //percentage per combination of 3 characters//
        int ppc = 0;
        if (slen >= 3) {
            ppc = 100 / combs;
        }
        //initialising an integer to store the total % this class genrate//
        int x = 0;
        //declaring a temporary new source char array
        char[] ns = new char[3];
        //check if source char array has more then 3 characters//
        if (slen < 3) {
        } else {
            for (int i = 0; i < combs; i++) {
                for (int j = 0; j < 3; j++) {
                    ns[j] = s[j + i];
                }
                if (cross_full_match(ns, t) == 1) {
                    x = x + 1;
                }
            }
        }
        x = ppc * x;
        return x;
    }


    public static int  cross_full_match(char[] s, char[] t) {
        int z = t.length - s.length;
        int x = 0;
        if (s.length > t.length) {
            return x;
        } else {
            for (int i = 0; i <= z; i++) {
                for (int j = 0; j <= (s.length - 1); j++) {
                    if (s[j] == t[j + i]) {
                        // x=1 if any charecer matches
                        x = 1;
                    } else {
                        // if x=0 mean an character do not matches and loop break out
                        x = 0;
                        break;
                    }
                }
                if (x == 1) {
                    break;
                }
            }
        }
        return x;
    }

    public static String split_string(String s, int n) {

        int index;
        String temp;
        temp = s;
        String temp2 = null;

        int temp3 = 0;

        for (int i = 0; i < n; i++) {
            int strlen = temp.length();
            index = temp.indexOf(" ");
            if (index < 0) {
                index = strlen;
            }
            temp2 = temp.substring(temp3, index);
            temp = temp.substring(index, strlen);
            temp = temp.trim();

        }
        return temp2;
    }

    public static int word_count(String s) {
        int x = 1;
        int c;
        s = s.trim();
        if (s.isEmpty()) {
            x = 0;
        } else {
            if (s.contains(" ")) {
                for (;;) {
                    x++;
                    c = s.indexOf(" ");
                    s = s.substring(c);
                    s = s.trim();
                    if (s.contains(" ")) {
                    } else {
                        break;
                    }
                }
            }
        }
        return x;
    }


    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "picture.jpg");
        imageUri = FileProvider.getUriForFile(GiveCollegeRating.this,
                BuildConfig.APPLICATION_ID + ".provider", photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, PHOTO_REQUEST);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (imageUri != null) {
            outState.putString(SAVED_INSTANCE_URI, imageUri.toString());
            outState.putString(SAVED_INSTANCE_RESULT, scanResults.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    private void launchMediaScanIntent() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(imageUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private Bitmap decodeBitmapUri(Context ctx, Uri uri) throws FileNotFoundException {
        int targetW = 600;
        int targetH = 600;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeStream(ctx.getContentResolver()
                .openInputStream(uri), null, bmOptions);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(GiveCollegeRating.this, LogoutActivityDrawer.class));
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
