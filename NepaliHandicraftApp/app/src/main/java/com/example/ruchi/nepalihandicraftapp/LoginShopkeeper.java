package com.example.ruchi.nepalihandicraftapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class LoginShopkeeper extends ActionBarActivity {
    Intent intent;
    EditText uname;
    EditText pwd;
    Toast toast;
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    private static String url_verify_login = "http://mrcoolpage.byethost15.com/handicraftdb/verifylogin.php";
    private static final String TAG_SUCCESS = "success";
  //  private static final String TAG_VERIFY = "verify";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_shopkeeper);
        if (android.os.Build.VERSION.SDK_INT >= 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        TextView sup=(TextView)findViewById(R.id.signup);
        uname=(EditText)findViewById(R.id.usname);
        pwd=(EditText)findViewById(R.id.mpwd);

        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate();
            }
        });



    }

    public void signin(View view){
        //navigateto();
        smthn();
        //new VerifyLogin().execute();
    }

    public void navigate(){
        intent=new Intent(this,ProfileShopkeeper.class);
        startActivity(intent);
    }

/*    class VerifyLogin extends AsyncTask<String, String, String> {

        *//**
         * Before starting background thread Show Progress Dialog
         *//*
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginShopkeeper.this);
            pDialog.setMessage("Logging in....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            Toast toast=Toast.makeText(getApplicationContext(),"fghfghgf", Toast.LENGTH_LONG);
            toast.show();
            String name = uname.getText().toString();
            String pass1 = pwd.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", name));
            params.add(new BasicNameValuePair("password",pass1));

            JSONObject json = jsonParser.makeHttpRequest(url_verify_login,
                    "POST", params);

            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);
                toast=Toast.makeText(getApplicationContext(),"try", Toast.LENGTH_LONG);
                toast.show();
                if (success == 1) {
                    // successfully logged in
                   // if(json.getInt(TAG_VERIFY)==1) {
                        navigateto();
                    //}
                    // closing this screen
                    finish();
                } else {
                    // failed to log
                    toast=Toast.makeText(getApplicationContext(),"Invalid Username or Password", Toast.LENGTH_LONG);
                    toast.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        *//**
         * After completing background task Dismiss the progress dialog
         * **//*
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }
   }*/

    public void smthn(){

        String name = uname.getText().toString();
        String pass1 = pwd.getText().toString();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", name));
        params.add(new BasicNameValuePair("password",pass1));

        JSONObject json = jsonParser.makeHttpRequest(url_verify_login,
                "POST", params);

        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);
            toast=Toast.makeText(getApplicationContext(),""+success, Toast.LENGTH_LONG);
            toast.show();
            if (success == 1) {
                // successfully logged in
                // if(json.getInt(TAG_VERIFY)==1) {
                navigateto();
                //}
                // closing this screen
                finish();
            } else {
                // failed to log
                toast=Toast.makeText(getApplicationContext(),"Invalid Username or Password", Toast.LENGTH_LONG);
                toast.show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void navigateto(){
        Intent i = new Intent(this, ShopDetails.class);
        startActivity(i);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_shopkeeper, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
