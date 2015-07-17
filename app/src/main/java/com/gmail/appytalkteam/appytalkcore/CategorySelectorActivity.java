package com.gmail.appytalkteam.appytalkcore;

<<<<<<< HEAD
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class CategorySelectorActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selector);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_selector, menu);
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
=======

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.Locale;

public class CategorySelectorActivity extends AppCompatActivity implements OnTouchListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setButtons(getLocales());
    }

    // when activity restarts from back button, rerun setup so new preferred flag is default
    @Override
    protected void onRestart() {
        super.onRestart();
        setButtons(getLocales());
    }

    // implement an onTouch() function, we don't care about the event
    // but it's more kid-friendly than onClick
    @Override
    public boolean onTouch(View view, MotionEvent event){
        switch(event.getAction()){
            case
                    MotionEvent.ACTION_DOWN:
                String langRequested = view.getTag().toString();
                setLanguage(langRequested);

                startQuizActivity();

                return true;
        }
        return false;
    }

    public void setButtons(String locales[]){
        // See language_selector_activity.xml
        // Set the icons and tags for our buttons according to the locales[] Array
        // Also add an onTouchListener (see above) and start the Animation
        setContentView(R.layout.language_selector_activity);
        Context context;
        context = this.getApplicationContext();
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.language_select);

        ImageButton b;
        LinearLayout layout = (LinearLayout) findViewById(R.id.flag_frame);
        LinearLayout sublayout = (LinearLayout) findViewById(R.id.miniflag_frame);
        for(int i =0; i < locales.length; i++) {
            if(i==0){  // big flag
                b = (ImageButton) layout.getChildAt(i);
            } else {  // little flags
                b = (ImageButton) sublayout.getChildAt(i-1);
            }
            b.setImageResource(context.getResources().getIdentifier(locales[i], "drawable", context.getPackageName()));
            b.setTag(locales[i]);
            b.setOnTouchListener(this);
            b.startAnimation(zoomIn);
        }
    }

    public String[] getLocales(){
// Return a an array of all our supported locales with system or (preferably) last used first
        Resources res = getResources();
        String[] locales = res.getStringArray(R.array.locales_array);
        SharedPreferences settings = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        //noinspection
        String currentLocale = Locale.getDefault().getLanguage().substring(0, 2);
        String lang = settings.getString("language", currentLocale);

        // Swap so that last used or current locale is at top of the list
        // String locales[] = {"en", "ru", "es", "fr"};
        int j = 0;
        for (int i = 0; i < locales.length; i++) {
            //noinspection ConstantConditions
            if (lang.equalsIgnoreCase(locales[i])) j = i;
        }
        String temp = locales[0];
        locales[0] = locales[j];
        locales[j] = temp;

        return locales;
    }

    public void setLanguage(String langRequested){
        SharedPreferences settings = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("language", langRequested);

        // Commit the edits!
        editor.commit();
    }

    public void startQuizActivity(){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);

    }
}
>>>>>>> origin/master
