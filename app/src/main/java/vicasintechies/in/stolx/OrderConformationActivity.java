package vicasintechies.in.stolx;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.HashMap;

public class OrderConformationActivity extends AppCompatActivity  {
    private RecyclerView mrecyclerView;
    private DatabaseReference mdatabase,morderbase,mnotification;
    private ProgressDialog prodialog;
    String src="abc";
    int total;
    Integer qty;
    String adminuid;
    String quu;
    DiscreteScrollView scrollView,scroll;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_conformation);
        prodialog = new ProgressDialog(this);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("products");
        morderbase= FirebaseDatabase.getInstance().getReference().child("orders");
        mnotification = FirebaseDatabase.getInstance().getReference().child("notifications");
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.products_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               src = parent.getItemAtPosition(position).toString();
               Log.d("spinn","banthu");
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        TextView textView = (TextView)findViewById(R.id.oplace);
        editText = (EditText)findViewById(R.id.orderqty);



        /*try{
            qty = Integer.parseInt(ss);
        }catch (NumberFormatException e){
            System.out.print(e.getMessage());
        }*/

        switch (src){
            case "Mini Drafter":textView.setText("₹200 to ₹220");
           /*     if(qty<5){
                    total = 220*qty;
                }else{
                    total= 200*qty;
                }
*/

                break;
            case "Scientific Calculator":textView.setText("₹530 to ₹550");
               /* if(qty<5){
                    total = 550*qty;
                }else{
                    total= 530*qty;
                }*/
                break;
            case "Drawing Board Pins":textView.setText("₹15"); /*total=qty*15*/;break;
            case "Drasheet Containers":textView.setText("₹ 100");/*total=total*100*/;break;
        }
        scrollView = (DiscreteScrollView)findViewById(R.id.picker);

        Button btn = (Button)findViewById(R.id.orderconfirm);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                quu = editText.getText().toString();
                new AlertDialog.Builder(OrderConformationActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Confirmation")
                        .setMessage("Do you want to place the order of "+quu +" of"+src)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                prodialog.setMessage("Confirming your Order...");
                                prodialog.show();
                                final  String rd= editText.getText().toString().trim();
                                DatabaseReference newpost = morderbase.push();
                                newpost.child("Product Name").setValue(src);
                                newpost.child("from_uid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());

                                newpost.child("qty").setValue(rd);
                                newpost.child("status").setValue("Requested").addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        prodialog.dismiss();
                                        new AlertDialog.Builder(OrderConformationActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Thank you")
                                                .setMessage("We will get back to you with total price...")
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                        startActivity(new Intent(OrderConformationActivity.this,NavigationActivity.class));
                                                        finish();
                                                    }
                                                }).show();
                                    }
                                });
                                HashMap<String,String> notifications= new HashMap<String, String>();
                                notifications.put("from", FirebaseAuth.getInstance().getCurrentUser().getUid());
                                notifications.put("type","Order request");
                                adminuid="Zb5bvUQ8DeXybGBcRr8vzchjqFy1";
                                mnotification.child(adminuid).push().setValue(notifications).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        Toast.makeText(getApplicationContext(),"Task Completed",Toast.LENGTH_LONG).show();
                                    }
                                });


                            }
                        }).setNegativeButton("No", null).show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Book,NotificationsFragment.ProductViewHolder> productViewHolderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Book, NotificationsFragment.ProductViewHolder>(
                Book.class,
                R.layout.rowoffer,
                NotificationsFragment.ProductViewHolder.class,
                mdatabase
        ) {
            @Override
            protected void populateViewHolder(NotificationsFragment.ProductViewHolder viewHolder, Book model, int position) {
                final String post_key = getRef(position).getKey().toString();
                //viewHolder.setName(model.getName());
                //viewHolder.setPrice("₹ "+model.getPrice());
                /*viewHolder.setPlace(model.getPlace());
                viewHolder.setCollege(model.getCollege());
                viewHolder.setBranch(model.getBranch());*/
                viewHolder.setImage(getApplicationContext(),model.getImage());


            }

            @Override
            public void onDataChanged() {

            }
        };
        scrollView.setAdapter(productViewHolderFirebaseRecyclerAdapter);
        //scroll.setAdapter(productViewHolderFirebaseRecyclerAdapter);
    }

   /* @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       switch (adapterView.getId()){
           case R.id.spinner: src = (String) adapterView.getItemAtPosition(i);
               Log.d("spin",src);break;

       }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}
