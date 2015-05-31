package com.sjschmd2.madlibs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;


public class MainActivity extends Activity {


    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_main);
    }

    private void init(){
         b1 = (Button) findViewById(R.id.b1);
         b2 = (Button) findViewById(R.id.b2);
         b3 = (Button) findViewById(R.id.b3);
    }

    public void onClick(View v) {
        char encodedChar = '0';
        switch(v.getId()) {
            case R.id.b1:
                encodedChar = '1';
                break;
            case R.id.b2:
                encodedChar = '2';
                break;
            case R.id.b3:
                encodedChar = '3';
                break;
        }
        Intent i = new Intent(this,GetWords.class);
        i.putExtra("char",encodedChar);
        startActivity(i);
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
