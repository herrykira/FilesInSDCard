package com.example.kinhangpoon.filesinsdcard;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveFileActivity extends AppCompatActivity {
    EditText FileNameEditText,ContentEditText;
    Button SaveFileButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_file);

        FileNameEditText = findViewById(R.id.editTextFilename);
        ContentEditText = findViewById(R.id.editTextContent);
        SaveFileButton = findViewById(R.id.buttonSave2);

        SaveFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExternalStorageWritable() && checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    File file = new File(Environment.getExternalStorageDirectory(),FileNameEditText.getText().toString());

                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(ContentEditText.getText().toString().getBytes());
                        fos.close();
                        Toast.makeText(SaveFileActivity.this,FileNameEditText.getText().toString()+" is saved",Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(SaveFileActivity.this,"Can not write to external storage",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isExternalStorageWritable(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            Log.i("State","Yes, it is writable");
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this,permission);
        return check == PackageManager.PERMISSION_GRANTED;
    }
}
