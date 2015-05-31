package com.sjschmd2.madlibs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;


public class Display extends Activity {

    TextView tv;
    Button b;
    ArrayList<String> text, text1, text2, text3, inputs;
    char encodedChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        init();
    }

    private void init(){
        text1 = new ArrayList<String>(Arrays.asList("Dirty Martinis are a good cocktail while you are on a date. Who can ",
                " a dirty martini? first, get a martini ",
                ". Add ",
                " or ",
                ". Next, add some ",
                " and some ",
                ". Put it in a ",
                " and drink. You can also have it ",
                ". Now ",
                ". You can also go to a ",
                " and get one already made. Many celebrities like this drink including "
                ));
        text2 = null;
        text3 = null;
        Bundle b = getIntent().getExtras();
        if(b==null)
            return;
        inputs = b.getStringArrayList("words");
        encodedChar = b.getChar("char");
        text = pickText();
        String story = getStory();
        tv = (TextView) findViewById(R.id.story);
        tv.setText(story);
    }

    private String getStory(){
        String ret = "";
        int biggerLen = Math.max(text.size(), inputs.size());
        for(int i = 0; i < biggerLen; i++){
            if(i < text.size())
                ret+=text.get(i);
            if(i<inputs.size()){
                ret+=inputs.get(i).toUpperCase();
            }
        }
        return ret;
    }

    private ArrayList<String> pickText(){
        switch(encodedChar){
            case '1':
                return text1;
            case '2':
                return text2;
            case '3':
                return text3;
            default:
                return null;
        }
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.back:
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                break;
            case R.id.save:

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display, menu);
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