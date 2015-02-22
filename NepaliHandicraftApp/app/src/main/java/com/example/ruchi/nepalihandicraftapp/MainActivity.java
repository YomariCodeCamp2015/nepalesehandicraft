package com.example.ruchi.nepalihandicraftapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        TextView menu_shopkeeper=(TextView)findViewById(R.id.shopkeeper);
        menu_shopkeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate();

            }
        });

        TextView menu_viewer=(TextView)findViewById(R.id.viewer);
        menu_viewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate1();

            }
        });


    }

    public void navigate(){
        intent=new Intent(this,LoginShopkeeper.class);
        startActivity(intent);
    }

    public void navigate1(){
        intent=new Intent(this,ForViewer.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
