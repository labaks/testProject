package labaks.texteditor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends ActionBarActivity {
    private final static String FILENAME = "sample.txt";
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.actionOpen:
                openFile(FILENAME);
                return true;
            case R.id.actionSave:
                saveFile(FILENAME);
                return true;
            case R.id.actionSettings:
                Intent intent = new Intent();
                intent.setClass(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean(getString(R.string.pref_open), false)) {
            openFile(FILENAME);
        }

        float fSize = Float.parseFloat(prefs.getString(getString(R.string.pref_size), "20"));
        mEditText.setTextSize(fSize);

        String regular = prefs.getString(getString(R.string.pref_style), "");
        int typeface = Typeface.NORMAL;
        if (regular.contains("Bold")) {
            typeface += Typeface.BOLD;
        }
        if (regular.contains("Italic")) {
            typeface += Typeface.ITALIC;
        }

        mEditText.setTypeface(null, typeface);

        int color = Color.BLACK;
        if (prefs.getBoolean(getString(R.string.pref_color_green), false)) {
            color += Color.GREEN;
        }
        if (prefs.getBoolean(getString(R.string.pref_color_red), false)) {
            color += Color.RED;
        }
        if (prefs.getBoolean(getString(R.string.pref_color_blue), false)) {
            color += Color.BLUE;
        }
        mEditText.setTextColor(color);
    }

    private void openFile(String fileName) {
        try {
            InputStream inputStream = openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    builder.append(line + "\n");
                }
                inputStream.close();
                mEditText.setText(builder.toString());
            }
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(), getString(R.string.exception) + t.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveFile(String fileName) {
        try {
            OutputStream outputStream = openFileOutput(fileName, 0);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(mEditText.getText().toString());
            osw.close();
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(), getString(R.string.exception) + t.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
