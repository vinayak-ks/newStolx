package vicasintechies.in.stolx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.r0adkll.slidr.Slidr;

import java.util.HashMap;

public class SelectionActivity extends AppCompatActivity {
    MaterialViewPager materialViewPager;
    private DatabaseReference mAddReference;
    TextView txt,bookname,price,clg,branch,stime;
    ImageView imageView;
    private DatabaseReference mnotification;
    private StorageReference storageReference;
    String seller;
    Button btn,callbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        Bundle bdl = getIntent().getExtras();
        //CollapsingToolbarLayout collapsingToolbar =
                /*(CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);*/
        int primary = getResources().getColor(R.color.colorPrimary);
        int secondary = getResources().getColor(R.color.colorPrimaryDark);
        Slidr.attach(this);
        String com = bdl.getString("key");
        mAddReference= FirebaseDatabase.getInstance().getReference().child("Book1").child(com);
        mnotification = FirebaseDatabase.getInstance().getReference().child("notifications");
        bookname = (TextView)findViewById(R.id.selname);
        price = (TextView)findViewById(R.id.selprice);
        txt = (TextView)findViewById(R.id.selplace);
        clg = (TextView)findViewById(R.id.selcollege);
        imageView = (ImageView)findViewById(R.id.dimage);

        stime = (TextView)findViewById(R.id.dtime);
         btn = (Button)findViewById(R.id.sellbutton);
        callbtn =(Button)findViewById(R.id.scall);
        //txt.setText(com);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Book post = dataSnapshot.getValue(Book.class);
               // txt.setText(post.getUid());
                seller = post.getUid();
                bookname.setText(post.getName());
                price.setText(post.getPrice());
                txt.setText(post.getPlace());
                clg.setText(post.getCollege());

                Glide.with(getApplicationContext()).load(post.getImage()).into(imageView);




               // branch.setText(post.getBranch());
                stime.setText(post.getSDate());
                Log.d("time",post.getSDate());

/*
                callbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent im = new Intent(Intent.ACTION_CALL,)
                    }
                });
*/

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HashMap<String,String> notifications= new HashMap<String, String>();
                        notifications.put("from", FirebaseAuth.getInstance().getCurrentUser().getUid());
                        notifications.put("type","request");
                        mnotification.child(seller).push().setValue(notifications).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(getApplicationContext(),"Task Completed",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("nitp", "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(SelectionActivity.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mAddReference.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops

    }
}
