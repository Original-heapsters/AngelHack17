package com.example.russell.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
                TicketInfo.username = user.getText().toString();
                EditText pass = (EditText) findViewById(R.id.passwordText);
                TicketInfo.password = pass.getText().toString();

                if (TicketInfo.setFull && TicketInfo.setID)
                {
                    startActivity(new Intent(getApplicationContext(),TestWebInfoViewer.class));
                }
                else
                {
                    TicketInfo.setID  = false;
                    TicketInfo.setFull = false;
                    startActivity(new Intent(getApplicationContext(),OcrCaptureActivity.class));
                }
            }
        });
    }

    public void goToUrl(String url){
        Uri uri = Uri.parse(url);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
