package com.fiit.eatout.eatout.network;

import android.util.Log;

import com.fiit.eatout.eatout.globalValues.global;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SQLLogin extends Thread
{
    String password = "111";
    String email = "111";
    String id;
    InputStream is = null;
    String result = null;
    String line = null;

    public void run()
    {
        // создаем лист для отправки запросов
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        // один параметр, если нужно два и более просто добоовляем также
        nameValuePairs.add(new BasicNameValuePair("Password", password));
        nameValuePairs.add(new BasicNameValuePair("Email", email));
        //  подключаемся к php запросу и отправляем
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://test.rightdown.info/db_login.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.i("pass 1", "connection success ");
        } catch (Exception e) {
            Log.e("Fail 1", e.toString());
        }

        // получаем ответ от php запроса в формате json
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.i("pass 2", "connection success" + result);
        } catch (Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        // обрабатываем полученный json
        try
        {
            JSONObject json_data = new JSONObject(result);
            id = (json_data.getString("ID"));
            if (id != null) {
                global.id = id;
                global.Name = (json_data.getString("Name"));
                //global.Surname = (json_data.getString("Surname"));
                global.isLogged = true;
                Log.e("pass i", global.id);
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

    // принемаем id при запуске потока
    public void start(String regemail, String regpassword)
    {
        global.id = "-1";
        global.isLogged = false;
        this.password = regpassword;
        this.email = regemail;
        this.start();
    }


}
