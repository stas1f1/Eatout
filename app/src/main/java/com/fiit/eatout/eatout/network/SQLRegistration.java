package com.fiit.eatout.eatout.network;

import android.util.Log;

import com.fiit.eatout.eatout.globalValues.global;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by C on 17.02.2019.
 */

public class SQLRegistration extends Thread
{
    String name = "111";
    String surname = "111";
    String password = "111";
    String email = "111";
    InputStream is = null;

    public void run()
    {
        // создаем лист для отправки запросов
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        // один параметр, если нужно два и более просто добоовляем также
        nameValuePairs.add(new BasicNameValuePair("Name", name));
        nameValuePairs.add(new BasicNameValuePair("Surname", surname));
        nameValuePairs.add(new BasicNameValuePair("Password", password));
        nameValuePairs.add(new BasicNameValuePair("Email", email));
        //  подключаемся к php запросу и отправляем в него userID
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://test.rightdown.info/db_registration.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            //HttpEntity entity = response.getEntity();
            //is = entity.getContent();
            Log.i("pass 1", "connection success ");
            global.regSuccess = true;
        } catch (Exception e) {
            Log.e("Fail 1", e.toString());
        }
    }

    // принемаем userID при запуске потока
    public void start(String regname, String regsurname, String regemail, String regpassword)
    {
        this.name = regname;
        this.surname = regsurname;
        this.email = regemail;
        this.password = regpassword;
        this.start();
    }


}

