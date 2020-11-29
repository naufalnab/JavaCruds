package com.example.javacrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button read,readAll,insert,delete,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        read=(Button)findViewById(R.id.read_btn);
        readAll=(Button)findViewById(R.id.read_all_btn);
//        readAll.setVisibility(View.GONE);
        insert=(Button)findViewById(R.id.insert_btn);
        update=(Button)findViewById(R.id.update_btn);
        delete=(Button)findViewById(R.id.delete_btn);

        readAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent=new Intent(getApplicationContext(),ReadAllActivity.class);
                    startActivity(intent);
                    
                } else {
                    Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent=new Intent(getApplicationContext(),InsertDataActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent=new Intent(getApplicationContext(),UpdateDataActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });


        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent=new Intent(getApplicationContext(),ReadSingleDataActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent=new Intent(getApplicationContext(),DeleteDataActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}