package vicasintechies.in.stolx;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Chandan S R on 26-Jul-17.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title = remoteMessage.getNotification().getTitle();
        String click = remoteMessage.getNotification().getClickAction();
        String buyer = remoteMessage.getData().get("from_user_id");
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(remoteMessage.getNotification().getBody());
        Intent resultIntent = new Intent(click);
        Bundle args = new Bundle();
        args.putString("user",buyer);
        //args.putString("key","Kpz1tHouDS0nfBv6SBk");

        //args.putString("dtable","Book");
        resultIntent.putExtras(args);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);


        int mNotificationId = (int)System.currentTimeMillis();
// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
