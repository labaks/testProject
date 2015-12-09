package labaks.localeapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ListActivity implements AdapterView.OnItemLongClickListener {

    private ArrayAdapter<String> mMonthAdapter, mDayAdapter;
    private String mMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] mMonthArray = loadMonthResources();
        String[] mDayArray = loadDayResources();
        ArrayList<String> mMonthList = new ArrayList<>(Arrays.asList(mMonthArray));
        ArrayList<String> mDayList = new ArrayList<>(Arrays.asList(mDayArray));

        mMonthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mMonthList);
        mDayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDayList);
        setListAdapter(mMonthAdapter);
        getListView().setOnItemLongClickListener(this);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (getListAdapter() == mMonthAdapter) {
            mMonth = (String) l.getItemAtPosition(position);
            setListAdapter(mDayAdapter);
            Toast.makeText(getApplicationContext(), getString(R.string.your_choose) + " " + mMonth, Toast.LENGTH_SHORT).show();
            mDayAdapter.notifyDataSetChanged();
        } else {
            String mDay = (String) l.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),
                    getString(R.string.your_choose) + " " + mMonth + " " + getString(R.string.and) + " " + mDay,
                    Toast.LENGTH_SHORT).show();
            setListAdapter(mMonthAdapter);
            mMonthAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ArrayAdapter<String> mAdapter = (ArrayAdapter<String>) getListAdapter();
        String selectedItem = parent.getItemAtPosition(position).toString();
        mAdapter.remove(selectedItem);
        mAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), getString(R.string.deleted), Toast.LENGTH_SHORT).show();
        return true;
    }

    private String[] loadMonthResources() {
        return new String[]{
                getString(R.string.january), getString(R.string.february), getString(R.string.march), getString(R.string.april), getString(R.string.may),
                getString(R.string.june), getString(R.string.july), getString(R.string.august), getString(R.string.september), getString(R.string.october),
                getString(R.string.november), getString(R.string.december)
        };
    }

    private String[] loadDayResources() {
        return new String[]{
                getString(R.string.mon), getString(R.string.tues), getString(R.string.wedn), getString(R.string.thur),
                getString(R.string.fri), getString(R.string.sat), getString(R.string.sun)
        };
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
}
