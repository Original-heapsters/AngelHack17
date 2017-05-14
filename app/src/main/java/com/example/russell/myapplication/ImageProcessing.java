package com.example.russell.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageProcessing extends AppCompatActivity {

    private ImageView userImg;
    private TextView textView;
    private Button takePic;

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
        setContentView(R.layout.activity_image_processing);
        takePic = (Button) findViewById(R.id.TakePic);
        textView = (TextView) findViewById(R.id.ImageText);
        userImg = (ImageView) findViewById(R.id.UserImage);
        textView = (TextView) findViewById(R.id.ImageText);

        takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OcrCaptureActivity.class));
            }
        });


        if(TicketInfo.setFull && TicketInfo.setID) {
            textView.setText("Ticket full" + TicketInfo.barcodeFull + " \n Ticket ID " + TicketInfo.barcodeID);
            TicketInfo.setFull = false;
            TicketInfo.setID = false;
        }
    }
}
