package com.uofthacks.praxis;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ankitguglani.MeU;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class DownloadTask extends AsyncTask<String, Void, String> {

    public static String hello;
    protected String doInBackground(String... urls) {

        String result = "";
        InputStream is = null;
        HttpEntity entity ;
        JSONObject json_data = null;

//the year data to send
        //  ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        //  nameValuePairs.add(new BasicNameValuePair("year","1980"));

//http post
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://praxisonline.mybluemix.net/data.php");
            // httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            if(response == null){
                Log.d("fuck","Error responce");
            }
            entity = response.getEntity();
            is = entity.getContent();
        }catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
        }
//convert response to string
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();

            result=sb.toString();
        }catch(Exception e){
            Log.e("log_tag", "Error converting result " + e.toString());
        }

//parse json data
        try{
            JSONArray jArray = new JSONArray(result);

            for(int i=0;i<jArray.length();i++){
                json_data = jArray.getJSONObject(i);
                Log.i("log_tag","id: "+json_data.getInt("cid")+
                                ", name: "+json_data.getString("username")+
                                ", sex: "+json_data.getString("cname")+
                                ", birthyear: "+json_data.getString("ctext")

                );
                Log.d("text",json_data.getString("ctext"));
                hello = json_data.getString("ctext");
            }
       }
        catch(JSONException e){
            Log.e("log_tag", "Error parsing data "+e.toString());
        }


  return "";
    }


    //This Method is called when Network-Request finished
    protected void onPostExecute(String serverData) {

    }
}
public class DashBoard extends ActionBarActivity {

    MeU MeU = new MeU();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        MeU.setupBluetooth(getApplicationContext(), "00:06:66:67:51:4F");


        final Button startc = (Button) findViewById(R.id.button2);

        startc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MeU.sendText("MeU 30% Off");
        }
    });




}}
