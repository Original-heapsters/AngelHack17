package com.example.russell.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.russell.myapplication.web.SignInResolver;

public class MainActivity extends AppCompatActivity {

    Button buttonSignInPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSignInPage = (Button)findViewById(R.id.button);

        buttonSignInPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestWebInfoViewer.class);
                startActivity(intent);
            }
        });
    }
}
