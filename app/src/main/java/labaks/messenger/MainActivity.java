package labaks.messenger;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    private static final int NOTIFY_ID = 101;

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
        Context context = getApplicationContext();

        Intent notificationIntent = new Intent(context, MainActivity.class); //По клику переходит на это приложение
//        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com")); По клику перейдет на сайт
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP); // Чтобы по клику запускалась уже запущенная активность

        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.icon_mini)
                .setTicker(res.getString(R.string.last))
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(res.getString(R.string.mess))
                .setContentText(res.getString(R.string.do_not))
                .setDefaults(Notification.DEFAULT_ALL);
        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
    }

    public void sendActionNotification(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification builder = new Notification.Builder(this)
                .setTicker(getString(R.string.message_with_buttons))
                .setContentTitle(getString(R.string.mess))
                .setContentText(getString(R.string.important_message))
                .setSmallIcon(R.drawable.icon_mini)
                .setContentIntent(pIntent)
                .addAction(R.drawable.icon_mini, getString(R.string.open), pIntent)
                .addAction(R.drawable.icon_mini, getString(R.string.close), pIntent)
                .addAction(R.drawable.icon_mini, getString(R.string.other), pIntent)
                .build();

        builder.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, builder);
    }

    public void sendBigTextStyleNotification(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification.Builder builder = new Notification.Builder(this)
                .setTicker(getString(R.string.message_long))
                .setContentTitle(getString(R.string.mess))
                .setContentText(getString(R.string.long_message_content))
                .setSmallIcon(R.drawable.icon_mini)
                .addAction(R.drawable.icon_mini, getString(R.string.open), pIntent)
                .setAutoCancel(true);

        Notification notification = new Notification.BigTextStyle(builder)
                .bigText(getString(R.string.long_message_content))
                .build();

        notificationManager.notify(1, notification);
    }

    public void sendBigPictureStyleNotification(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification.Builder builder = new Notification.Builder(this)
                .setTicker(getString(R.string.message_big_image))
                .setContentTitle(getString(R.string.mess))
                .setContentText(getString(R.string.message_big_image))
                .setSmallIcon(R.drawable.icon_mini)
                .addAction(R.drawable.icon_mini, getString(R.string.open), pendingIntent);

        Notification notification = new Notification.BigPictureStyle(builder)
                .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.pict))
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(2, notification);
    }

    public void sendInboxStyleNotification(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification.Builder builder = new Notification.Builder(this)
                .setTicker(getString(R.string.message_inbox))
                .setContentTitle(getString(R.string.mess))
                .setContentText(getString(R.string.message_inbox))
                .setSmallIcon(R.drawable.icon_mini)
                .addAction(R.drawable.icon_mini, getString(R.string.open), pendingIntent);

        Notification notification = new Notification.InboxStyle(builder)
                .addLine(getString(R.string.first_mess))
                .addLine(getString(R.string.second_mess))
                .addLine(getString(R.string.third_mess))
                .addLine(getString(R.string.fourth_mess))
                .addLine(getString(R.string.fifth_mess))
                .setSummaryText(getString(R.string.more))
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(3, notification);

    }
}
