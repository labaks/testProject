package labaks.cowsaysmoo;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;
    private int mCatSound, mChickenSound, mCowSound, mDogSound, mDuckSound, mSheepSound;
    private int mCountLoadedSound;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        mAssetManager = getAssets();

        mCatSound = loadSound("cat.ogg");
        ImageButton catImageButton = (ImageButton) findViewById(R.id.btCat);
        catImageButton.setOnClickListener(onClickListener);

        mChickenSound = loadSound("chicken.ogg");
        ImageButton chickenImageButton = (ImageButton) findViewById(R.id.btChicken);
        chickenImageButton.setOnClickListener(onClickListener);

        mCowSound = loadSound("cow.ogg");
        ImageButton cowImageButton = (ImageButton) findViewById(R.id.btCow);
        cowImageButton.setOnClickListener(onClickListener);

        mDogSound = loadSound("dog.ogg");
        ImageButton dogImageButton = (ImageButton) findViewById(R.id.btDog);
        dogImageButton.setOnClickListener(onClickListener);

        mDuckSound = loadSound("duck.ogg");
        ImageButton duckImageButton = (ImageButton) findViewById(R.id.btDuck);
        duckImageButton.setOnClickListener(onClickListener);

        mSheepSound = loadSound("sheep.ogg");
        ImageButton sheepImageButton = (ImageButton) findViewById(R.id.btSheep);
        sheepImageButton.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btCat:
                    playSound(mCatSound);
                    break;
                case R.id.btChicken:
                    playSound(mChickenSound);
                    break;
                case R.id.btCow:
                    playSound(mCowSound);
                    break;
                case R.id.btDog:
                    playSound(mDogSound);
                    break;
                case R.id.btDuck:
                    playSound(mDuckSound);
                    break;
                case R.id.btSheep:
                    playSound(mSheepSound);
                    break;
            }
        }
    };

    private void playSound(int sound) {
        if (sound > 0)
            mSoundPool.play(sound, 1, 1, 1, 0, 1);
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor afd = null;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, getString(R.string.error) + " " + fileName, Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
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
