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
    Button save;
    ArrayList<String> text, text1, text2, text3, inputs;
    String story = "";
    char encodedChar;
    boolean loading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        init();
    }

    private void init(){
        text1 = new ArrayList<String>(Arrays.asList(
                "Dirty Martinis are a good cocktail while you are on a date. Who can ",
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
                )
        );
        text2 = new ArrayList<String>(Arrays.asList(
                "It was during the battle of ",
                " when I was running through a ",
                " when a ",
                " went off right next to my platoon. Our ",
                " yelled for us to  ",
                " to the nearest ",
                " we could find. When we got to the",
                " "
            )
        );
        text3 = new ArrayList<String>(Arrays.asList(
                "As a male nursing professor at the local community college, I get my share of ",
                " young students. I try to keep my mind on my job, but it's not always easy. For example, the other day after class, ",
                ", a blonde with very large ",
                ", stayed to get help with her homework. As she leaned over my desk, I could see she wasn't wearing a ",
                ". \"Anatomy is so hard,\" she said, putting her hand on my ",
                " \"isn't there anything I could do to get some extra credit?\" Despite my best efforts, I could feel myself becoming ",
                ". \"Maybe I can do a lab report about how far I can fit a man's ",
                " into my ",
                "\" she asked."
            )
        );
        save = (Button) findViewById(R.id.save);
        Bundle b = getIntent().getExtras();
        tv = (TextView) findViewById(R.id.story);
        if(b==null)
            return;
        if(getIntent().hasExtra("char")) {
            inputs = b.getStringArrayList("words");
            encodedChar = b.getChar("char");
            text = pickText();
            story = getStory();
            tv.setText(story);
        }
        if(getIntent().hasExtra("load")){
            loading = true;
            String loadedStory = getIntent().getExtras().getString("load");
            tv.setText(loadedStory);
        }
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
                Intent back = new Intent(this,MainActivity.class);
                startActivity(back);
                break;
            case R.id.save:
                if(loading)
                    save.setText("This Madlib is already saved!");
                else {
                    Intent save = new Intent(this, Save.class);
                    save.putExtra("story", story);
                    startActivity(save);
                    break;
                }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
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
