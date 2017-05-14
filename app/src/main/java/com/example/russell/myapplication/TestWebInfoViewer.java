package com.example.russell.myapplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TestWebInfoViewer extends Activity {

    // URL Address
    String url = "https://www.calottery.com/sign-in";
    ProgressDialog mProgressDialog;
    TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web_info_viewer);

        tv = (TextView)findViewById(R.id.textView);

        Button titlebutton = (Button) findViewById(R.id.button2);
        titlebutton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                new Title().execute();
            }
        });
    }

    // Title AsyncTask
    private class Title extends AsyncTask<Void, Void, Void> {
        String title = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(TestWebInfoViewer.this);
            mProgressDialog.setTitle("Getting the datas");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect(url).get();

                document.getElementById("objBody_content_0_leftcolumn_0_txtEmail").attr("value", "hellaandrew@gmail.com");
                
                /*
                //With this you login and a session is created
                Connection.Response res = Jsoup.connect(url)
                        .data("$Email", "hellaandrew@gmail.com",
                                "$Password", "Grenadier1!@")
                        .method(Connection.Method.POST)
                        .execute();

                int testIndex = res.body().indexOf("Welcome");
                String testText = res.body().substring(testIndex - 5, testIndex + 15);

                title +=
                        "Body:\n" + String.valueOf(testText) + "\n\n" +
                        "Content Type: " + res.contentType() + "\n\n" +
                        "Status Message: " + res.statusMessage() + "\n\n\n\n\n\n";

                //This will get you cookies
                Map<String, String> loginCookies = res.cookies();

                //Here you parse the page that you want. Put the url that you see when you have logged in
                Document doc = Jsoup.connect("https://www.calottery.com/play/second-chance/scratchers-second-chance?login=true")
                        .cookies(loginCookies)
                        .get();

                String submitUrl = "https://www.calottery.com/SecondChanceSubmit.ashx?entrycode=1010101010101&ticketid=0101010";

                Connection.Response submitCode = Jsoup.connect(submitUrl)
                        .method(Connection.Method.POST)
                        .execute();

                title +=
                        "Body: " + submitCode.body() + "\n\n" +
                        "Content Type: " + submitCode.contentType() + "\n\n" +
                        "Status Message: " + submitCode.statusMessage() + "\n\n";
                        */
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            tv.setText(title);
            mProgressDialog.dismiss();
        }
    }
}