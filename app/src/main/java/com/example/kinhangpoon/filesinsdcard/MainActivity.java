package com.example.kinhangpoon.filesinsdcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kinhangpoon.filesinsdcard.model.ScanFiles;
import com.example.kinhangpoon.filesinsdcard.BigFileActivity;
import com.example.kinhangpoon.filesinsdcard.FrequentActivity;
import com.example.kinhangpoon.filesinsdcard.SaveFileActivity;

public class MainActivity extends AppCompatActivity {
    Button saveButton,bigButton,averageButton,frequentButton;
    ScanFiles scanFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveButton = findViewById(R.id.buttonSave);
        bigButton = findViewById(R.id.buttonBig);
        averageButton = findViewById(R.id.buttonAverage);
        frequentButton = findViewById(R.id.buttonFrequent);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SaveFileActivity.class);
                startActivity(i);
            }
        });
        bigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,BigFileActivity.class);
                startActivity(i);

            }
        });

        averageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanFiles = new ScanFiles();
                scanFiles.initialize();
                double size = scanFiles.average();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Data");
                builder.setMessage("Average file size: "+size+" KB");
                builder.show();
            }
        });

        frequentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,FrequentActivity.class);
                startActivity(i);
            }
        });
    }
}
