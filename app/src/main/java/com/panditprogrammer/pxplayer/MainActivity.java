package com.panditprogrammer.pxplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();


        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {

                        File directory = new File("/storage/");
                        getFile(directory);

                        MyAdapter myAdapter = new MyAdapter(arrayList, MainActivity.this);
                        recyclerView.setAdapter(myAdapter);
                        Toast.makeText(MainActivity.this, String.valueOf(arrayList.size())+" Videos found ", Toast.LENGTH_SHORT).show();

                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(MainActivity.this, "Give Permission to Access the Storage", Toast.LENGTH_SHORT).show();

                        arrayList.add(" Permission not Granted !");
                        MyAdapter myAdapter = new MyAdapter(arrayList, MainActivity.this);
                        recyclerView.setAdapter(myAdapter);

                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();




    }


    // function for list all files in all directories
    public void getFile(File directory) {

        File listAllFile[] = directory.listFiles();

        // if listAllFile is not null
        if(listAllFile !=null && listAllFile.length >0){
            for (int i=0; i<listAllFile.length; i++){

                // if this is a directory
                if(listAllFile[i].isDirectory()){
                    getFile(listAllFile[i]);
                }
                else{
                    // check the file format
                    if(listAllFile[i].getName().endsWith(".mp4")){
                        arrayList.add(listAllFile[i].getName());
                    }
                }
            }
        }

    }





}