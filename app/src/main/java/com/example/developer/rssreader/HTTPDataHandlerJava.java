package com.example.developer.rssreader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.System.in;

/**
 * Created by developer on 11.09.17.
 */

public class HTTPDataHandlerJava {
    static String stream="";
    public HTTPDataHandlerJava(){};
    public String GetHTTPHandler(String urlString){
        try{
            URL url=new URL(urlString);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            if(urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream inputStream=new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader r= new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder sb=new StringBuilder();
                String line="";
                while  ((line=r.readLine())!=null){
                    sb.append(line);
                    stream=sb.toString();
                    urlConnection.disconnect();
                }
            }

        }
        catch (Exception ex){ return  null;}
        return stream;
    }



}
