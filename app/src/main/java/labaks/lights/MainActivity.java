package labaks.lights;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    RelativeLayout mRelativeLayout;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        mTextView = (TextView) findViewById(R.id.textView);

        Button yellowButton = (Button) findViewById(R.id.buttonYellow);
        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setText(R.string.Yellow);
                mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.yellowColor));
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

    public void onRedButtonClick(View view) {
        mTextView.setText(R.string.Red);
        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.redColor));
    }

    public void onGreenButtonClick(View view) {
        mTextView.setText(R.string.Green);
        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.greenColor));
    }
}
