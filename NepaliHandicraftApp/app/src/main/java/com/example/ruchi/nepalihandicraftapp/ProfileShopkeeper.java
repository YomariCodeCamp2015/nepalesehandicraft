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


public class ProfileShopkeeper extends ActionBarActivity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText sname,address,contact,email,username,password;

    // url to create new product
    private static String url_create_ac = "http://mrcoolpage.byethost15.com/handicraftdb/create_account.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_shopkeeper);
        if (android.os.Build.VERSION.SDK_INT >= 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // Edit Text
        sname = (EditText) findViewById(R.id.inName);
        address = (EditText) findViewById(R.id.addr);
        contact = (EditText) findViewById(R.id.contact);
        email = (EditText) findViewById(R.id.eid);
        username= (EditText) findViewById(R.id.uname);
        password = (EditText) findViewById(R.id.pwd);

        // Create button
        Button btnCreateAc = (Button) findViewById(R.id.save);

        // button click event
        btnCreateAc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                new CreateNewAc().execute();
            }
        });
    }

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewAc extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ProfileShopkeeper.this);
            pDialog.setMessage("Creating Account..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String name = sname.getText().toString();
            String address1 = address.getText().toString();
            String contact1 = contact.getText().toString();
            String email1 = email.getText().toString();
            String username1 = username.getText().toString();
            String password1 = password.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("address",address1));
            params.add(new BasicNameValuePair("contact",contact1));
            params.add(new BasicNameValuePair("email",email1));
            params.add(new BasicNameValuePair("username",username1));
            params.add(new BasicNameValuePair("password",password1));

            // getting JSON Object
            // Note that create account url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_ac,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                    Intent i = new Intent(getApplicationContext(),LoginShopkeeper.class);
                    startActivity(i);

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

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            Toast toast=Toast.makeText(getApplicationContext(),"Success!!!", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    //UNUSED
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_shopkeeper, menu);
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
