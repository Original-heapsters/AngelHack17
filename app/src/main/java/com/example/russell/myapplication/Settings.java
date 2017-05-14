package com.example.russell.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class Settings extends AppCompatActivity {
    private Button newAccButton;
    private Button loginButton;
    static String username;
    static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        newAccButton = (Button) findViewById(R.id.buttonCreateAcc);
        newAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToUrl("https://www.calottery.com/register");
            }
        });
        loginButton = (Button) findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText user = (EditText) findViewById(R.id.usernameText);
                username = user.getText().toString();

                EditText pass = (EditText) findViewById(R.id.passwordText);
                password = pass.getText().toString();
            }
        });
    }

    public void goToUrl(String url){
        Uri uri = Uri.parse(url);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
