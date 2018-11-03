package com.dell.example.elecricitybillcalculatingsystem;



import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;




public class MainActivity extends AppCompatActivity {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    EditText B_IDEt;
    EditText PasswordEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        B_IDEt = (EditText)findViewById(R.id.etB_ID);
        PasswordEt = (EditText)findViewById(R.id.etPassword);


    }

public void OnLogin(View view) {
        String bid = B_IDEt.getText().toString();
        String password = PasswordEt.getText().toString();
        new BackgroundWorker().execute(bid, password);


}

    public class BackgroundWorker extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
        HttpURLConnection conn;
        URL url = null;


        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String ... params) {

            try {

                url = new URL("http://192.168.1.10/login.php");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "exception";
            }

            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder().appendQueryParameter("b_id", params[0]).appendQueryParameter("password", params[1]);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK) {

                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);

                    }

                    return (result.toString());


                } else {
                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }

        }


        @Override
        protected void onPostExecute(String result) {

            pdLoading.dismiss();

            if(result.equalsIgnoreCase("true")){
                Intent intent = new Intent(MainActivity.this,HomePage.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
            
            else if (result.equalsIgnoreCase("false")){
                Toast.makeText(MainActivity.this, "INVALID B_ID OR PASSWORD", Toast.LENGTH_LONG).show();
            }
            
            else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")){
                Toast.makeText(MainActivity.this, "SOMETHING WENT WRONG. CONNECTION PROBLEM", Toast.LENGTH_LONG).show();
            }

        }


    }

}

