package com.example.russell.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageProcessing extends AppCompatActivity {

    private ImageView userImg;
    private TextView textView;
    private Button takePic;

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
        }
    }
}
