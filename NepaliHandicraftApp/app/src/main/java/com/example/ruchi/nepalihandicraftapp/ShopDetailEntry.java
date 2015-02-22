package com.example.ruchi.nepalihandicraftapp;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ShopDetailEntry extends ActionBarActivity {

    Intent intent;
    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText panno,sname,address,contact;

    // url to create new product
    private static String url_upload_ac = "http://mrcoolpage.byethost15.com/handicraftdb/add_shop_details.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    public Double lat;
    public Double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail_entry);
        if (android.os.Build.VERSION.SDK_INT >= 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // Edit Text
        panno= (EditText) findViewById(R.id.panno);
        sname = (EditText) findViewById(R.id.shpname);
        address = (EditText) findViewById(R.id.shpaddr);
        contact = (EditText) findViewById(R.id.shpcontact);

        // Create button
        Button btnuploadAc = (Button) findViewById(R.id.shp_save);

        // button click event
        btnuploadAc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
               // new UploadAc().execute();
                smthn();
            }
        });
    }

    public void smthn(){
        String pno = panno.getText().toString();
        String name = sname.getText().toString();
        String address1 = address.getText().toString();
        String contact1 = contact.getText().toString();
        String l = lat.toString();
        String ln = lng.toString();


        Toast.makeText(getApplicationContext(), l+" "+ln,Toast.LENGTH_LONG).show();



 // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("panno", pno));
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("address",address1));
            params.add(new BasicNameValuePair("contact",contact1));
            params.add(new BasicNameValuePair("latitude",l ));
            params.add(new BasicNameValuePair("longitude",ln ));


            // getting JSON Object
            // Note that create account url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_upload_ac,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);
                Toast toast=Toast.makeText(getApplicationContext()," "+success, Toast.LENGTH_LONG);
                toast.show();
                if (success == 1) {
                    // successfully created product


                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                    toast=Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_LONG);
                    toast.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }


/**
     * Background Async Task to Create new product
     * *//*

    class UploadAc extends AsyncTask<String, String, String> {

        */
/**
         * Before starting background thread Show Progress Dialog
         * *//*

        @Override
        protected void onPreExecute() {
           super.onPreExecute();
            pDialog = new ProgressDialog(ShopDetailEntry.this);
            pDialog.setMessage("Uploading Details..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        */
/**
         * Creating product
         * *//*

        protected String doInBackground(String... args) {
            String pno = panno.getText().toString();
            String name = sname.getText().toString();
            String address1 = address.getText().toString();
            String contact1 = contact.getText().toString();
            String l = lat.toString();
            String ln = lng.toString();


            Toast.makeText(getApplicationContext(), l+" "+ln,Toast.LENGTH_SHORT).show();


           */
/* // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("panno", pno));
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("address",address1));
            params.add(new BasicNameValuePair("contact",contact1));
            params.add(new BasicNameValuePair("latitude",l ));
            params.add(new BasicNameValuePair("longitude",ln ));


            // getting JSON Object
            // Note that create account url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_upload_ac,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product


                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                    Toast toast=Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_LONG);
                    toast.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
*//*

            return null;
        }

        */
/**
         * After completing background task Dismiss the progress dialog
         * **//*

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
           // Toast toast=Toast.makeText(getApplicationContext(),"Success!!!", Toast.LENGTH_LONG);
            //toast.show();
        }

    }
*/



    public void setloc(View view){
        intent=new Intent(this, GoogleMapActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
           lat=data.getDoubleExtra("lat",0);
          lng=data.getDoubleExtra("long",0);

        Toast.makeText(getApplicationContext(), lat+" "+lng,Toast.LENGTH_SHORT).show();
    }

    public void shpchange(View view){

    }

    public void shpedit(View view){

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop_detail_entry, menu);
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
