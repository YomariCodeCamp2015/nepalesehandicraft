package com.example.ruchi.nepalihandicraftapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ShopDetails extends ActionBarActivity {
TextView shpdetailtap,additemtap,itemview;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        shpdetailtap=(TextView)findViewById(R.id.shpdetail);
        shpdetailtap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate();
                        }
        });
        additemtap=(TextView)findViewById(R.id.additem);
        additemtap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate_additem();
            }
        });
        itemview=(TextView)findViewById(R.id.viewdetail);
        itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate_view();
            }
        });

    }

    public void navigate(){
        intent=new Intent(this, ShopDetailEntry.class);
        startActivity(intent);
    }

    public void navigate_additem(){
        intent=new Intent(this, AddItem.class);
        startActivity(intent);
    }

    public void navigate_view(){
       // intent=new Intent(this, EditItemActivity.class);
       // startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop_details, menu);
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
