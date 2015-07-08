package com.sjschmd2.madlibs;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Save extends ActionBarActivity {

    Button b;
    EditText et;
    TextView tv;
    SharedPreferences data;
    final String FILENAME = "MY_FILENAME";
    String toSave;
    boolean back = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        b = (Button) findViewById(R.id.save_button);
        et = (EditText) findViewById(R.id.save_name);
        tv = (TextView) findViewById(R.id.error_text);
        data = getSharedPreferences(FILENAME,0);
        toSave = getIntent().getExtras().getString("story");
    }

    public void onClick(View v){
        if(back){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
        else {
            String saveName = et.getText().toString();
            if (saveName.equals(""))
                tv.setText("ERROR: INPUT IS EMPTY");
            else {
                tv.setText("");
                SharedPreferences.Editor editor = data.edit();
                editor.putString(et.getText().toString(), toSave);
                editor.commit();
                new AlertDialog.Builder(this)
                        .setTitle("Success")
                        .setMessage("Your madlib was saved!")
                        .show();
                back = true;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);
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
