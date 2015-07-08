package com.sjschmd2.madlibs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Load extends ActionBarActivity {

    Button b;
    EditText et;
    TextView tv, tv2;
    SharedPreferences data;
    final String FILENAME = "MY_FILENAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        b = (Button) findViewById(R.id.load_button);
        et = (EditText) findViewById(R.id.load_name);
        tv = (TextView) findViewById(R.id.load_error);
        tv2 = (TextView) findViewById(R.id.load_error_text);
        data = getSharedPreferences(FILENAME,0);
    }

    public void onClick(View v){
        String val = data.getString(et.getText().toString(), "Error loading.");
        if (!val.equals("Error loading.")){
            Intent i = new Intent(this,Display.class);
            i.putExtra("load",val);
            startActivity(i);
        }
        else {
            tv2.setTextColor(Color.RED);
            tv2.setText("ERROR: Could not load madlib.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_load, menu);
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
