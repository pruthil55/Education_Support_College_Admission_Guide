package com.example.loginfbgooglenumber;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.File;
import java.io.IOException;

public class ViewRankingImage extends AppCompatActivity {

    FirebaseStorage storage=FirebaseStorage.getInstance();
    StorageReference storageReference;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ranking_image);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        Intent intent=getIntent();
        String collageFullName=intent.getStringExtra("COLLEGE_FULL_NAME");
        final PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);

        storageReference=storage.getReferenceFromUrl("gs://login-fb--number.appspot.com/").child(collageFullName+".jpg");

        try
        {
            final File file= File.createTempFile("image","jpg");
            storageReference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap= BitmapFactory.decodeFile(file.getAbsolutePath());
                    progressBar.setVisibility(View.GONE);

                    photoView.setImageURI(Uri.parse(file.getAbsolutePath()));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    FancyToast.makeText(ViewRankingImage.this,"Coming Soon! \uD83D\uDE0A",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                }
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}

