package com.example.russell.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button goToProcess;
    private Intent goToProcessIntent;
    private Button buttonSignInPage;
    private Button goToSettings;

    private SharedPreferences sharedPrefs;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem settings = menu.findItem(R.id.menu_item_settings);
        settings.setVisible(true);
        MenuItem newBarcode = menu.findItem(R.id.menu_item_new_barcode);
        newBarcode.setVisible(true);
        MenuItem goHome = menu.findItem(R.id.menu_item_home);
        goHome.setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_barcode:
                startActivity(new Intent(getApplicationContext(), OcrCaptureActivity.class));
                return true;
            case R.id.menu_item_settings:
                startActivity(new Intent(getApplicationContext(), Settings.class));
                return true;
            case R.id.menu_item_home:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToProcessIntent = new Intent(getApplicationContext(),OcrCaptureActivity.class);
        goToProcess = (Button) findViewById(R.id.GoProcess);
        goToProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToProcessIntent);
            }
        });

        buttonSignInPage = (Button)findViewById(R.id.button);

        buttonSignInPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CodeSubmission.class);
                startActivity(intent);
            }
        });

        goToSettings = (Button) findViewById(R.id.goToSettings);
        goToSettings.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v) {
               Intent goSettingsIntent = new Intent(getApplicationContext(), Settings.class);
               startActivity(goSettingsIntent);
           }
        });

        // Set the stored user credentials, if any
        sharedPrefs = getSharedPreferences("shichifu", Context.MODE_PRIVATE);

        String savedUsername = sharedPrefs.getString("username", "");
        String savedPassword = sharedPrefs.getString("password", "");

        if (savedUsername.length() > 0 && savedPassword.length() > 0) {
            TicketInfo.username = savedUsername;
            TicketInfo.password = savedPassword;
        }

        Log.i("Info", "Username: " + TicketInfo.username);
        Log.i("Info", "Password: " + TicketInfo.password);
    }
}
