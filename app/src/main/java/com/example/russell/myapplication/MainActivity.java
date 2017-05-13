package com.example.russell.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button goToProcess;
    private Intent goToProcessIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToProcessIntent = new Intent(getApplicationContext(),ImageProcessing.class);
        goToProcess = (Button) findViewById(R.id.GoProcess);
        goToProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToProcessIntent);
            }
        });

    }
}
