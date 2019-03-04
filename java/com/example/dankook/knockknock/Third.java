package com.example.dankook.knockknock;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Third extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_third);

        Button button3 = (Button) findViewById((R.id.button3));
        button3.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Third_information.class);
                        startActivity(intent);
                    }
                }
        );

        FirebaseStorage storage = FirebaseStorage.getInstance("gs://knock-7b4fc.appspot.com");
        StorageReference storageRef = storage.getReference();
        StorageReference spaceRef = storageRef.child("recent.jpg");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        GlideApp.with(this /* context */)
                .load(spaceRef)
                .into(imageView);
    }}
