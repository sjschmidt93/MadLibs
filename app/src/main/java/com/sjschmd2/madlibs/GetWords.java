package com.sjschmd2.madlibs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GetWords extends Activity{

    ArrayList<String> words,words1,words2,words3, inputs;
    Button b;
    EditText et;
    TextView tv, tv2;
    int index = 0;
    char encodedChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_words);
        init();
    }

    public void onClick(View v) {
        if (index > words.size()) {
            Intent i = new Intent(this,Display.class);
            Bundle b = new Bundle();
            b.putStringArrayList("words",inputs);
            b.putChar("char",encodedChar);
            i.putExtras(b);
            startActivity(i);
        }
        else {
            String input = et.getText().toString();
            if (input.equals("")) {
                tv.setText("ERROR: Field is empty.");
            } else {
                tv.setText("");
                index++;
                inputs.add(input);
                if(index==words.size()) {
                    b.setText("Click to see your madlib!");
                    tv2.setText("Done!");
                    et.setHint("You are done!");
                    et.setEnabled(false);
                    index = Integer.MAX_VALUE;
                }
                else {
                    b.setText(words.get(index));
                    et.setText("");
                    et.setHint("Enter appropiate text here and press the button below to submit.");
                    tv2.setText(index + 1 + "/" + words.size());
                }
            }

        }
    }

    private void init(){
        inputs = new ArrayList<String>();
        words1 = new ArrayList<String>(Arrays.asList("VERB", "NOUN", "NAME OF DRINK", "FAMOUS PERSON", "ANIMAL(PLURAL)", "NAME OF NUT", "NOUN", "VERB ENDING IN -ED", "VERB", "PLACE", "FAMOUS PERSON", "PHRASE"));
        words2 = new ArrayList<String>(Arrays.asList("NOUN","NOUN","NOUN","OCCUPATION","VERB","PLACE","VERB ENDING IN -ED","NOUN","VERB ENDING IN -ING","NOUN(PLURAL)","NOUN","PLACE","EMOTION"));
        words3 = new ArrayList<String>(Arrays.asList("ADJECTIVE","FEMALE NAME","NOUN(PLURAL)","PIECE OF CLOTHING","NOUN","ADJECTIVE","BODY PART","BODY PART"));
        b = (Button) findViewById(R.id.bGetWords);
        et = (EditText) findViewById(R.id.etGetWords);
        tv = (TextView) findViewById(R.id.tvGetWords);
        tv.setText("");
        tv.setTextColor(Color.RED);
        words = pickList();
        if(words!=null)
            b.setText(words.get(0));
        else
            b.setText("ERROR");
        tv2 = (TextView) findViewById(R.id.progress);
        tv2.setText(index+1+"/"+words.size());
    }

    private ArrayList<String> pickList(){
        Bundle box = getIntent().getExtras();
        if(box==null)
            return null;
        encodedChar = box.getChar("char");
        switch(encodedChar){
            case '1':
                return words1;
            case '2':
                return words2;
            case '3':
                return words3;
        }
        return null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_words, menu);
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
