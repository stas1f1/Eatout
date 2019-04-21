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

public class SQLPlaceOrder extends Thread
{
    String ClientID;
    String CafeID;
    String Contents;
    String Timer;
    String OrderID = "-1";
    InputStream is = null;
    String result = null;
    String line = null;

    public void run()
    {
        // создаем лист для отправки запросов
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("ClientID", ClientID));
        nameValuePairs.add(new BasicNameValuePair("CafeID", CafeID));
        nameValuePairs.add(new BasicNameValuePair("Contents", Contents));
        nameValuePairs.add(new BasicNameValuePair("Timer", Timer));
        //  подключаемся к php запросу и отправляем
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://test.rightdown.info/db_place_order.php");
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
            OrderID = (json_data.getString("OrderID"));
            Log.e("pass i", "Order placed");
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

    public String getOrderID()
    {
        return OrderID;
    }

    // принемаем userID при запуске потока
    public void start(String ClientID, String CafeID, String Contents, String Timer)
    {
        this.ClientID = ClientID;
        this.CafeID = CafeID;
        this.Contents = Contents;
        this.Timer = Timer;
        this.start();
    }


}
