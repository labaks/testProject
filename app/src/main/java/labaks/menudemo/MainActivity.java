package labaks.menudemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.textView);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        button.setOnClickListener(viewClickListener);
        textView.setOnClickListener(viewClickListener);
        imageView.setOnClickListener(showImageListener);
    }

    OnClickListener viewClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }
    };
    OnClickListener showImageListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            showToastImage(v);
        }
    };

    private void showToastImage(View v) {
        Toast imageToast = Toast.makeText(getApplicationContext(), R.string.android_in_pants, Toast.LENGTH_LONG);
        imageToast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastContainer = (LinearLayout) imageToast.getView();
        ImageView image = new ImageView(getApplicationContext());
        image.setImageResource(R.drawable.android);
        toastContainer.addView(image, 0);
        imageToast.show();
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
        TextView infoText = (TextView) findViewById(R.id.textView);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.menu1) {
            infoText.setText(getString(R.string.your_choose) + " " + getString(R.string.menu1));
            return true;
        } else if (id == R.id.menu2) {
            infoText.setText(getString(R.string.your_choose) + " " + getString(R.string.menu2));
            return true;
        } else if (id == R.id.menu3) {
            infoText.setText(getString(R.string.your_choose) + " " + getString(R.string.menu3));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSettingsClick(MenuItem item) {
        TextView infoText = (TextView) findViewById(R.id.textView);
        infoText.setText(getString(R.string.settings_chosed));
    }

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popupmenu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.menu1:
                        Toast toast1 = Toast.makeText(getApplicationContext(),
                                getString(R.string.your_choose) + " " + getString(R.string.popup_menu_item_1),
                                Toast.LENGTH_SHORT);
                        toast1.setGravity(Gravity.CENTER, -500, -500);
                        toast1.show();
                        return true;
                    case R.id.menu2:
                        Toast toast2 = Toast.makeText(getApplicationContext(),
                                getString(R.string.your_choose) + " " + getString(R.string.popup_menu_item_2),
                                Toast.LENGTH_SHORT);
                        toast2.setGravity(Gravity.CENTER, 500, -500);
                        toast2.show();
                        return true;
                    case R.id.menu3:
                        Toast toast3 = Toast.makeText(getApplicationContext(),
                                getString(R.string.your_choose) + " " + getString(R.string.popup_menu_item_3),
                                Toast.LENGTH_SHORT);
                        toast3.setGravity(Gravity.CENTER, -500, 500);
                        toast3.show();
                        return true;
                    case R.id.menu6:
                        showCustomToast();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {

            @Override
            public void onDismiss(PopupMenu menu) {
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.dismiss_dialog), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        popupMenu.show();
    }

    private void showCustomToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_layout, (ViewGroup) findViewById(R.id.toastLayout));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
