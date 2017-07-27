package vicasintechies.in.stolx;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    List<Notification> li = new ArrayList<>();
    MoviesAdapter moviesAdapter;
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
        moviesAdapter = new MoviesAdapter(li);
        mrecyclerView = (RecyclerView) view.findViewById(R.id.notifyrecycle);
        Log.d("IT's Reaching","Notification");
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mrecyclerView.setLayoutManager(linearLayoutManager);
        mrecyclerView.setAdapter(moviesAdapter);


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
        li = new ArrayList<>();
        //li.add("vinay");


        ValueEventListener roomsValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for ( DataSnapshot userDataSnapshot : dataSnapshot.getChildren() ) {
                    Log.d("notify ",userDataSnapshot.toString());
                         /*for ( DataSnapshot roomDataSnapshot : userDataSnapshot.getChildren() ) {*/
                            String s = userDataSnapshot.getValue().getClass().toString();
                            Log.d("nim",s);
                            Notification room = userDataSnapshot.getValue(Notification.class);

                            li.add(room);
                            moviesAdapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        Log.d("Noticastins ","Niyre entererd");
        mdatabase.addListenerForSingleValueEvent(roomsValueEventListener);
       // mrecyclerView.setAdapter(testRecyclerViewAdapter);
    }

    /*public  static class NotificationViewHolder extends RecyclerView.ViewHolder{
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

      *//*  public void setPlace(String place) {
            TextView textView1 = (TextView) mView.findViewById(R.id.place);
            textView1.setText(place);
            Log.d("Coming1","name data entererd");
        }
*//*


        public void setPrice(String price) {
            TextView textView2 = (TextView) mView.findViewById(R.id.sprice);
            textView2.setText(price);
        }



      *//*  public void setCollege(String college) {
            TextView textView3 = (TextView) mView.findViewById(R.id.college);
            textView3.setText(college);
        }



        public void setBranch(String branch) {
            TextView textView4 = (TextView) mView.findViewById(R.id.branch);
            textView4.setText(branch);
        }*//*



        public void setImage(Context ctx, String image) {
            ImageView imageView = (ImageView) mView.findViewById(R.id.simage);
           *//* Picasso.with(ctx).load(image).into(imageView);*//*
            Glide.with(ctx).load(image).into(imageView);
            Log.d("Image","gfgf");
        }
    }*/

    class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

        private List<Notification> moviesList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title, year, genre;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.sname);
                /*genre = (TextView) view.findViewById(R.id.genre);
                year = (TextView) view.findViewById(R.id.year);*/
            }
        }


        public MoviesAdapter(List<Notification> moviesList) {
            this.moviesList = moviesList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Notification movie = moviesList.get(position);
            holder.title.setText(movie.getFrom());
            //holder.genre.setText(movie.getGenre());
           // holder.year.setText(movie.getYear());
        }

        @Override
        public int getItemCount() {
            return moviesList.size();
        }
    }
}
