package labaks.sherlock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Choose extends ActionBarActivity {

    public final static String THIEF = "labaks.sherlock.THIEF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose, menu);
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

    public void onRadioClick(View view) {
        Intent answer = new Intent();

        switch (view.getId()) {
            case R.id.radioCrow:
                answer.putExtra(THIEF, "Ворона");
                break;
            case R.id.radioCat:
                answer.putExtra(THIEF, "Кот");
                break;
            case R.id.radioDog:
                answer.putExtra(THIEF, "Пёс");
                break;
            default:
                break;
        }

        setResult(RESULT_OK, answer);
        finish();
    }
}
