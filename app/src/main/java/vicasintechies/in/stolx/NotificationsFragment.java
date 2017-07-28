package vicasintechies.in.stolx;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

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
    DiscreteScrollView scrollView;
   // MoviesAdapter moviesAdapter;
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
        View rootview = inflater.inflate(R.layout.fragment_notifications, container, false);
        rootview.setBackgroundColor(Color.parseColor("#003c8f"));
        return rootview;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("products");

        mrecyclerView = (RecyclerView) view.findViewById(R.id.myoffers_recycle);
        Log.d("IT's Reaching","Notification");
        mrecyclerView.setHasFixedSize(true);
        //mrecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mrecyclerView.setLayoutManager(linearLayoutManager);



        //prodialog = new ProgressDialog(getContext());


    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Stsart", "gjjg");
        FirebaseRecyclerAdapter<Book,ProductViewHolder> productViewHolderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Book, ProductViewHolder>(
                Book.class,
                R.layout.rowoffer,
                ProductViewHolder.class,
                mdatabase
        ) {
            @Override
            protected void populateViewHolder(ProductViewHolder viewHolder, Book model, int position) {
                final String post_key = getRef(position).getKey().toString();
                //viewHolder.setName(model.getName());
                //viewHolder.setPrice("₹ "+model.getPrice());
                /*viewHolder.setPlace(model.getPlace());
                viewHolder.setCollege(model.getCollege());
                viewHolder.setBranch(model.getBranch());*/
                viewHolder.setImage(getActivity().getApplicationContext(),model.getImage());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle args = new Bundle();
                        //args.putString("fragment","CropsP");
                       /* args.putString("key",post_key);
                        args.putString("dtable","Book");*/
                        Intent i = new Intent(getActivity(),OrderConformationActivity.class);
                        i.putExtras(args);
                        startActivity(i);
                    }
                });

            }

            @Override
            public void onDataChanged() {
                if (prodialog != null && prodialog.isShowing()) {
                    prodialog.dismiss();
                }
            }
        };
        Log.d("Coming","data entererd");
        mrecyclerView.setAdapter(productViewHolderFirebaseRecyclerAdapter);

    }

    public  static class ProductViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public ProductViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setImage(Context ctx, String image) {
            ImageView imageView = (ImageView) mView.findViewById(R.id.offerimage);
           /* Picasso.with(ctx).load(image).into(imageView);*/
            Glide.with(ctx).load(image).into(imageView);
            Log.d("Image","inmahgeg");
        }



    }
}
