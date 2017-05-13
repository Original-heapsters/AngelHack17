package com.example.russell.myapplication.web;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by andrewbriare on 5/13/17.
 */

public class SignInResolver {

    String signInUrl = "https://www.calottery.com/sign-in?redir=http://www.calottery.com/my-account";
    String result;

    public SignInResolver()
    {
        Document doc = null;

        try {
            doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(new InputSource(new StringReader(signInUrl)));

            XPathExpression xpath = XPathFactory.newInstance()
                    .newXPath().compile("//td[text()=\"Description\"]/following-sibling::td[2]");

            String result = (String) xpath.evaluate(doc, XPathConstants.STRING);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public String getResult()
    {
        return result;
    }
}
