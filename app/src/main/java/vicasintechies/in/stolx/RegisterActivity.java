package vicasintechies.in.stolx;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText rusr,rphn,rrclg,rcity,rbranch;
    private Button rsub;
    private FirebaseAuth mAuth;
    private DatabaseReference mref;
    private CoordinatorLayout coordinatorLayout;
    private ProgressDialog prodialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mref = FirebaseDatabase.getInstance().getReference().child("users");
        rusr = (EditText)findViewById(R.id.registerusername);
        rphn = (EditText)findViewById(R.id.registerphno);
        rrclg = (EditText)findViewById(R.id.registerclgname);
        rcity = (EditText)findViewById(R.id.registercity);
        rbranch=(EditText)findViewById(R.id.branch);
        rsub = (Button)findViewById(R.id.registerbutton);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

            prodialog = new ProgressDialog(this);
            rsub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
/*                    if(rusr!=null || rphn!=null || rrclg!=null || rcity!=null || rbranch!=null ){

                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout,"Enter all the Fields",Snackbar.LENGTH_LONG);
                        snackbar.show();
                        *//*Toast.makeText(getApplicationContext(),"Enter all the Fields",Toast.LENGTH_LONG).show();*//*
                    }
                    else{*/
                        prodialog.setMessage("Registering the user...");
                        prodialog.show();
                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        String tokenid = FirebaseInstanceId.getInstance().getToken();
                        HashMap<String,String> notifications= new HashMap<String, String>();
                        notifications.put("name",rusr.getText().toString().trim() );
                        notifications.put("email",rphn.getText().toString().trim());
                        notifications.put("college",rrclg.getText().toString().trim());
                        notifications.put("city",rcity.getText().toString().trim());
                        notifications.put("branch",rbranch.getText().toString().trim());
                        notifications.put("device_token",tokenid);
                        mref.child(uid).setValue(notifications).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                prodialog.dismiss();
                                startActivity(new Intent(RegisterActivity.this,NavigationActivity.class));
                            }
                        });
                   // }


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
