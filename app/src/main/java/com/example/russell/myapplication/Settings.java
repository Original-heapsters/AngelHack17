package com.example.russell.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;


public class Settings extends AppCompatActivity {

    private Button newAccButton;
    private Button loginButton;

    private EditText editTextUsername;
    private EditText editTextPassword;

    private Switch switchRememberMe;

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
        setContentView(R.layout.activity_settings);

        sharedPrefs = getPreferences(Context.MODE_PRIVATE);

        editTextUsername = (EditText) findViewById(R.id.usernameText);
        editTextPassword = (EditText) findViewById(R.id.passwordText);
        switchRememberMe = (Switch) findViewById(R.id.toggle);

        editTextUsername.setText(sharedPrefs.getString("username", ""));
        editTextPassword.setText(sharedPrefs.getString("password", ""));
        switchRememberMe.setChecked(sharedPrefs.getBoolean("rememberCredentials", false));

        newAccButton = (Button) findViewById(R.id.buttonCreateAcc);
        loginButton = (Button) findViewById(R.id.buttonLogin);

        newAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToUrl("https://www.calottery.com/register");
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = editTextUsername.getText().toString();
                String enteredPassword = editTextPassword.getText().toString();

                SharedPreferences.Editor editor = sharedPrefs.edit();

                if (switchRememberMe.isChecked()) {
                    editor.putString("username", enteredUsername);
                    editor.putString("password", enteredPassword);
                }

                editor.putBoolean("rememberCredentials", switchRememberMe.isChecked());

                editor.apply();

                TicketInfo.username = enteredUsername;
                TicketInfo.password = enteredPassword;

                if (TicketInfo.barcodeFull.length() > 0 && TicketInfo.barcodeID.length() > 0) {
                    Intent barcodeScannerScreen = new Intent(getApplicationContext(), ImageProcessing.class);
                    startActivity(barcodeScannerScreen);
                } else {
                    Intent codeSubmissionScreen = new Intent(getApplicationContext(), TestWebInfoViewer.class);
                    startActivity(codeSubmissionScreen);
                }
            }
        });
    }

    public void goToUrl(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
