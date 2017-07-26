package vicasintechies.in.stolx;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mrecyclerView;
    private DatabaseReference mdatabase;
    private ProgressDialog prodialog;

    public NotificationsFragment() {
        // Required empty public constructor
    }


    public static NotificationsFragment newInstance() {
        NotificationsFragment fragment = new NotificationsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("notifications").child(uid);

        mrecyclerView = (RecyclerView) view.findViewById(R.id.notifyrecycle);
        Log.d("IT's Reaching","Notification");
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mrecyclerView.setLayoutManager(linearLayoutManager);



        //prodialog = new ProgressDialog(getContext());
       /* floatingActionButton = (FloatingActionButton)view.findViewById(R.id.crop_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropAddFragment cropAddFragment = new CropAddFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,cropAddFragment,"fragment");
                fragmentTransaction.commit();
            }
        });*/

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Stsart","gjjg");
        List<String> li = new ArrayList<>();
        li.add("vinay");


        FirebaseRecyclerAdapter<Notification,NotificationViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Notification,NotificationViewHolder>(
                Notification.class,
                R.layout.row,
                NotificationViewHolder.class,
                mdatabase
        ){

            @Override
            protected void populateViewHolder(NotificationViewHolder viewHolder, Notification model, int position) {
                Log.d("From not","jkjkj");
                final String post_key = getRef(position).getKey().toString();
                Log.d("From not","jkjkj");
                viewHolder.setName("vinay");
                viewHolder.setPrice("₹ "+model.getType());
                /*viewHolder.setPlace(model.getPlace());
                viewHolder.setCollege(model.getCollege());
                viewHolder.setBranch(model.getBranch());*/
                //viewHolder.setImage(getActivity().getApplicationContext(),model.getImage());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle args = new Bundle();
                        //args.putString("fragment","CropsP");
                        args.putString("key",post_key);
                        Intent i = new Intent(getActivity(),SelectionActivity.class);
                        i.putExtras(args);
                        startActivity(i);
                    }
                });

            }

            @Override
            public void onDataChanged() {
                /*if (prodialog != null && prodialog.isShowing()) {
                    prodialog.dismiss();
                }*/
            }
        };
        Log.d("Noticastins ","Niyre entererd");
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public  static class NotificationViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public NotificationViewHolder(View itemView) {
            super(itemView);
            Log.d("notify","jkjkj");
            mView=itemView;
        }

        public void setName(String name) {
            TextView namee = (TextView) mView.findViewById(R.id.sname);
            namee.setText(name);
        }

      /*  public void setPlace(String place) {
            TextView textView1 = (TextView) mView.findViewById(R.id.place);
            textView1.setText(place);
            Log.d("Coming1","name data entererd");
        }
*/


        public void setPrice(String price) {
            TextView textView2 = (TextView) mView.findViewById(R.id.sprice);
            textView2.setText(price);
        }



      /*  public void setCollege(String college) {
            TextView textView3 = (TextView) mView.findViewById(R.id.college);
            textView3.setText(college);
        }



        public void setBranch(String branch) {
            TextView textView4 = (TextView) mView.findViewById(R.id.branch);
            textView4.setText(branch);
        }*/



        public void setImage(Context ctx, String image) {
            ImageView imageView = (ImageView) mView.findViewById(R.id.simage);
           /* Picasso.with(ctx).load(image).into(imageView);*/
            Glide.with(ctx).load(image).into(imageView);
            Log.d("Image","gfgf");
        }
    }

    class TestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<String> contents;

        static final int TYPE_HEADER = 0;
        static final int TYPE_CELL = 1;

        public TestRecyclerViewAdapter(List<String> contents) {
            this.contents = contents;
        }

        @Override
        public int getItemViewType(int position) {
            switch (position) {
                case 0:
                    return TYPE_HEADER;
                default:
                    return TYPE_CELL;
            }
        }

        @Override
        public int getItemCount() {
            return contents.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;

            switch (viewType) {
                case TYPE_HEADER: {
                    view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.row, parent, false);
                    return new RecyclerView.ViewHolder(view) {
                    };
                }
                case TYPE_CELL: {
                    view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.row, parent, false);
                    return new RecyclerView.ViewHolder(view) {
                    };
                }
            }
            return null;
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            switch (getItemViewType(position)) {
                case TYPE_HEADER:
                    break;
                case TYPE_CELL:
                    break;
            }
        }
    }
}
