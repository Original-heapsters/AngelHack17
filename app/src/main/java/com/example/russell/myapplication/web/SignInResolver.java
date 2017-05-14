package com.example.russell.myapplication.web;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by andrewbriare on 5/13/17.
 */

public class SignInResolver {

    String signInUrl = "https://www.calottery.com/sign-in?redir=http://www.calottery.com/my-account";
    String result;

    public SignInResolver()
    {
        try {
            // get the tables on this page, note I masked the phone number
            Document doc = Jsoup.connect("http://www.fonefinder.net/findome.php?npa=###&nxx=###&thoublock=####").get();
            Elements tables = doc.select("table");

            // get the second table, 0 indexed
            Element secondTable = tables.get(1);

            // get the columns
            Elements tds = secondTable.select("td");

            //get the 5th column, 0 indexed
            Element fifthTd = tds.get(4);

            //get the link in this column
            Element link = fifthTd.select("a").first();

            //print out the URL
            System.out.println(link.attr("href"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getResult()
    {
        return result;
    }
}
