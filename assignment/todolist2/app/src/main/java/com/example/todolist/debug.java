package com.example.todolist;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class debug extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug);
        setTitle("debug");

        final Button printbtn = findViewById(R.id.btn_print_path);
        final TextView text=findViewById(R.id.text_path);
        printbtn.setOnClickListener(new View.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                StringBuilder tt=new StringBuilder();
                tt.append("======Internal Private======\n");
                tt.append("cachedir:"+getCacheDir()+"\n");
                tt.append("filedir:"+getFilesDir()+"\n");
                tt.append("customdir:"+getDir("custom",MODE_PRIVATE)+"\n");
                tt.append("======External Private======\n");
                tt.append("cacheDir:"+getExternalCacheDirs()+"\n");
                tt.append("filesDir:"+getExternalFilesDir(null)+"\n");
                tt.append("pictureDir:"+getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"\n");
                tt.append("======External Public======\n");
                tt.append("rootDir:"+Environment.getExternalStorageDirectory()+"\n");
                tt.append("picturesDir:"+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"\n");
                text.setText(tt);
            }
        });

        final Button permi = findViewById(R.id.btn_request_permission);
        permi.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int state = ActivityCompat.checkSelfPermission(debug.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(state == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(debug.this,"already granted!",Toast.LENGTH_SHORT).show();
                    return ;
                }
                ActivityCompat.requestPermissions(debug.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(permissions.length==0 ||grantResults.length==0)
        {
            return ;
        }

        if(requestCode==1001)
        {
            int state = grantResults[0];
            if(state == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(debug.this, "permission granted",
                        Toast.LENGTH_SHORT).show();
            }
            else if(state == PackageManager.PERMISSION_DENIED)
            {
                Toast.makeText(debug.this, "permission denied",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
