package com.example.kinhangpoon.filesinsdcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.kinhangpoon.filesinsdcard.adapter.FrequentAdapter;
import com.example.kinhangpoon.filesinsdcard.model.ScanFiles;

import java.util.LinkedList;

public class FrequentActivity extends AppCompatActivity {
    ListView listView;
    FrequentAdapter frequentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequent);

        listView = findViewById(R.id.listviewFrequent);
        ScanFiles scanFiles = new ScanFiles();
        scanFiles.initialize();
        LinkedList<String> fileExtension = scanFiles.getFrequent().fileExtension;
        LinkedList<Integer> frequency = scanFiles.getFrequent().frequency;
        frequentAdapter = new FrequentAdapter(fileExtension,frequency, FrequentActivity.this);
        listView.setAdapter(frequentAdapter);
    }
}
