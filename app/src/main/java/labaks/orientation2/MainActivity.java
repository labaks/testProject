package labaks.orientation2;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    boolean mState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        TextView infoText = (TextView) findViewById(R.id.textView);
        switch (view.getId()) {
            case R.id.checkOrientation:
                infoText.setText(getScreenOrientation());
                break;
            case R.id.checkRotation:
                infoText.setText(getRotateOrientation());
                break;
            case R.id.switchOrientation:
                switchOrientation(view);
                break;
            default:
                infoText.setText("");
        }

    }

    private String getScreenOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return getString(R.string.landscape_orientation);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return getString(R.string.portrait_orientation);
        } else return "";
    }

    private String getRotateOrientation() {
        int rotate = getWindowManager().getDefaultDisplay().getRotation();
        switch (rotate) {
            case Surface.ROTATION_0:
                return getString(R.string.ROTATION_0);
            case Surface.ROTATION_180:
                return getString(R.string.ROTATION_180);
            case Surface.ROTATION_270:
                return getString(R.string.ROTATION_270);
            case Surface.ROTATION_90:
                return getString(R.string.ROTATION_90);
            default:
                return getString(R.string.sheet);
        }
    }

    private void switchOrientation(View view) {
        Button mButton = (Button) findViewById(view.getId());

        if (!mState) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            mButton.setText(getString(R.string.ORIENTATION_PORTRAIT));
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mButton.setText(getString(R.string.ORIENTATION_LANDSCAPE));
        }
        mState = !mState;
    }
}
