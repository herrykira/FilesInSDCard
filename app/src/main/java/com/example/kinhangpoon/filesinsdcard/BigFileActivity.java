package com.example.kinhangpoon.filesinsdcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.kinhangpoon.filesinsdcard.adapter.BigAdapter;
import com.example.kinhangpoon.filesinsdcard.model.ScanFiles;

import java.util.LinkedList;

public class BigFileActivity extends AppCompatActivity {
    ListView listView;
    BigAdapter bigAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_file);

        listView = findViewById(R.id.listViewBig);
        ScanFiles scanFiles = new ScanFiles();
        scanFiles.initialize();
        LinkedList<String> fileNames = scanFiles.getBigName().fileNames;
        LinkedList<String> fileSizes = scanFiles.getBigName().sizes;
        bigAdapter = new BigAdapter(fileNames,fileSizes, BigFileActivity.this);
        listView.setAdapter(bigAdapter);

    }
}
