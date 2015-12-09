package labaks.converter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.editText);
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

    private float parrotToMeter(float parrots) {
        return (float) (parrots / 7.6);
    }

    private float meterToParrot(float meters) {
        return (float) (meters * 7.6);
    }

    public void btConvertClick(View view) {
        RadioButton meterRadio = (RadioButton) findViewById(R.id.radioInMeters);

        if (mEditText.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.nothing_to_convert), Toast.LENGTH_SHORT).show();
            return;
        }
        float inputValue = Float.parseFloat(mEditText.getText().toString());
        if (meterRadio.isChecked()) {
            mEditText.setText(String.valueOf(parrotToMeter(inputValue)));
        } else {
            mEditText.setText(String.valueOf(meterToParrot(inputValue)));
        }
    }
}
