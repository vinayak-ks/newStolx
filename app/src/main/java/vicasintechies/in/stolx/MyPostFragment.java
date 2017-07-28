package vicasintechies.in.stolx;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyPostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPostFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseReference mdatabase;
    private Query query;
    private RecyclerView mRecycler;
    private OnFragmentInteractionListener mListener;

    public MyPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyPostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyPostFragment newInstance(String param1, String param2) {
        MyPostFragment fragment = new MyPostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
        String value = getArguments().getString("title");
        mdatabase = FirebaseDatabase.getInstance().getReference().child(value);
        String usr = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        query = mdatabase.orderByChild("Uid").equalTo(usr);
        View rootview = inflater.inflate(R.layout.fragment_my_post, container, false);
        rootview.setBackgroundColor(Color.parseColor("#003c8f"));
        mRecycler = (RecyclerView) rootview.findViewById(R.id.mypost_recycle);
        mRecycler.setHasFixedSize(true);
        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(linearLayoutManager);
    }
/*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Book,MyViewHolder> firebaseRecyclerAdapterMyFert = new FirebaseRecyclerAdapter<Book, MyViewHolder>(
                Book.class,
                R.layout.rowpost,
                MyViewHolder.class,
                query
        ) {
            @Override
            protected void populateViewHolder(MyViewHolder viewHolder, Book model, int position) {

                final String post_key = getRef(position).getKey();
                //viewHolder.setTitle(model.getTitle());
                viewHolder.setName(model.getName());
                viewHolder.setPrice(model.getSDate());
                //viewHolder.setPlace(model.getPlace());
                viewHolder.setImage(getContext(),model.getImage());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                        builder1.setMessage("Are you sure want to delete?");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        mdatabase.child(post_key).removeValue();
                                        dialog.cancel();
                                    }
                                });

                        builder1.setNegativeButton(
                                "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                });
               /* if(mypo.equals(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())){
                } else{
                    Toast.makeText(getContext(),"u Don't have any posts",Toast.LENGTH_LONG).show();
                }*/

                //viewHolder.setEmail(model.getUsrid());
            }
        };

        mRecycler.setAdapter(firebaseRecyclerAdapterMyFert);
    }
    

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setName(String name) {
            TextView namee = (TextView) mView.findViewById(R.id.myname);
            namee.setText(name);
        }

      /*  public void setPlace(String place) {
            TextView textView1 = (TextView) mView.findViewById(R.id.place);
            textView1.setText(place);
            Log.d("Coming1","name data entererd");
        }
*/


        public void setPrice(String price) {
            TextView textView2 = (TextView) mView.findViewById(R.id.mydate);
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
            ImageView imageView = (ImageView) mView.findViewById(R.id.myimage);
           /* Picasso.with(ctx).load(image).into(imageView);*/
            Glide.with(ctx).load(image).into(imageView);
            Log.d("Image","inmahgeg");
        }
    }


}
