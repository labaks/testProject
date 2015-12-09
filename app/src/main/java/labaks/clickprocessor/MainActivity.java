package labaks.clickprocessor;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private int mCountCrows = 0;
    private int mCountCats = 0;
    TextView mInfoTextView;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER_CATS = "counter_cats";
    public static final String APP_PREFERENCES_COUNTER_CROWS = "counter_crows";
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mInfoTextView = (TextView)findViewById(R.id.textView);
        Button mCrowsCounterButton = (Button) findViewById(R.id.buttonCrowsCounter);
        Button mCatCounterButton = (Button) findViewById(R.id.buttonCatCounter);
        mCrowsCounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInfoTextView.setText("Я насчитал " + ++mCountCrows + " ворон и " + mCountCats + " котов");
            }
        });
        mCatCounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInfoTextView.setText("Я насчитал " + mCountCrows + " ворон и " + ++mCountCats + " котов");
            }
        });
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

    public void onClick(View view) {
        mInfoTextView.setText("Hello body");
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_COUNTER_CATS, mCountCats);
        editor.putInt(APP_PREFERENCES_COUNTER_CROWS, mCountCrows);
        editor.apply();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(mSettings.contains(APP_PREFERENCES_COUNTER_CATS) && mSettings.contains(APP_PREFERENCES_COUNTER_CROWS)) {
            mCountCats = mSettings.getInt(APP_PREFERENCES_COUNTER_CATS, 0);
            mCountCrows = mSettings.getInt(APP_PREFERENCES_COUNTER_CROWS, 0);
            mInfoTextView.setText("Я насчитал " + mCountCrows + " ворон и " + mCountCats + " котов");
        }
    }
}
