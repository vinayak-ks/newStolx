package vicasintechies.in.stolx;

import android.util.Log;

/**
 * Created by Chandan S R on 25-Jul-17.
 */

public class Notification {
    String from,type;

    public Notification() {
        Log.d("notifcation","jkjkj");
    }

    public Notification(String from, String type) {
        this.from = from;
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
