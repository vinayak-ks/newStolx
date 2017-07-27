package vicasintechies.in.stolx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NoticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notication);
        Bundle b = getIntent().getExtras();
        String s = b.getString("user");
        TextView txt = (TextView)findViewById(R.id.textView2);
        if(s.equals("default")){
            txt.setText("ass hole no notifiacations");
        }



    }
}
