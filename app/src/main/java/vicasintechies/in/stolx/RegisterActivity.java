package vicasintechies.in.stolx;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText rusr,rphn;
    Spinner rrclg,rcity,rbranch;
    private Button rsub;
    private FirebaseAuth mAuth;
    private DatabaseReference mref;
    private CoordinatorLayout coordinatorLayout;
    private ProgressDialog prodialog;
    private String clg,city,sbranch;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mref = FirebaseDatabase.getInstance().getReference().child("users");
        rusr = (EditText)findViewById(R.id.registerusername);
        rphn = (EditText)findViewById(R.id.registerphno);
        rrclg = (Spinner)findViewById(R.id.registerclgname);
        rcity = (Spinner) findViewById(R.id.registercity);
        rbranch=(Spinner) findViewById(R.id.branch);
        rsub = (Button)findViewById(R.id.registerbutton);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);



        //spinners

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.college_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rrclg.setAdapter(adapter);
        rrclg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clg = parent.getItemAtPosition(position).toString();
                Log.d("spinner","ola "+ clg);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.branch_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rcity.setAdapter(adapter1);
        rcity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.city_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rbranch.setAdapter(adapter2);
        rbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sbranch = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("CEB43289F6636A1BC8ECD3D520DBB186").build();
        mAdView.loadAd(adRequest);




            prodialog = new ProgressDialog(this);
            rsub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(TextUtils.isEmpty(rusr.getText().toString().trim()) || TextUtils.isEmpty(rphn.getText().toString().trim()) ){

                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout,"Enter all the Fields",Snackbar.LENGTH_LONG);

                        snackbar.show();
                        //*Toast.makeText(getApplicationContext(),"Enter all the Fields",Toast.LENGTH_LONG).show();*//*
                    }
                    else{

                        prodialog.setMessage("Registering the user...");
                        prodialog.show();
                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        String tokenid = FirebaseInstanceId.getInstance().getToken();
                        HashMap<String,String> notifications= new HashMap<String, String>();
                        notifications.put("name",rusr.getText().toString().trim() );
                        notifications.put("email",rphn.getText().toString().trim());
                        notifications.put("college",clg);
                        notifications.put("city",city);
                        notifications.put("branch",sbranch);
                        notifications.put("device_token",tokenid);
                        mref.child(uid).setValue(notifications).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                prodialog.dismiss();
                                startActivity(new Intent(RegisterActivity.this,NavigationActivity.class));
                            }
                        });
                    }


               /* newpost.child("device_token").setValue(tokenid);
                newpost.child("Uid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                newpost.child("name").setValue(rusr.getText().toString().trim());
                newpost.child("phno").setValue(rphn.getText().toString().trim());
                newpost.child("college").setValue(rrclg.getText().toString().trim());
                newpost.child("city").setValue(rcity.getText().toString().trim());
                */

                }
            });

    }

}
