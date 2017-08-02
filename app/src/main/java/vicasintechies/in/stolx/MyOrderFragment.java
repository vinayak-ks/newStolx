package vicasintechies.in.stolx;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyOrderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyOrderFragment extends Fragment {
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


    public MyOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyOrderFragment newInstance(String param1, String param2) {
        MyOrderFragment fragment = new MyOrderFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_my_order, container, false);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("orders");
        String usr = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        query = mdatabase.orderByChild("from_uid").equalTo(usr);

        rootView.setBackgroundColor(Color.parseColor("#003c8f"));
        mRecycler = (RecyclerView) rootView.findViewById(R.id.myorder_recycle);
        mRecycler.setHasFixedSize(true);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Orders,MyOrderHolder> firebaseRecyclerAdapterMyFert = new FirebaseRecyclerAdapter<Orders,MyOrderHolder>(
                Orders.class,
                R.layout.row_order,
                MyOrderHolder.class,
                query
        ) {
            @Override
            protected void populateViewHolder(MyOrderHolder viewHolder, Orders model, int position) {

                final String post_key = getRef(position).getKey();
                //viewHolder.setTitle(model.getTitle());
                //Log.d("proudct ",model.getProduct_name());

                viewHolder.setName(model.getProduct());
                viewHolder.setPrice("₹"+model.getPrice());
                viewHolder.setQuantity(model.getQty());
                viewHolder.setStatus(model.getStatus());
                //viewHolder.setImage(getContext(),model.getProduct());
                //viewHolder.setPlace(model.getPlace());
                //viewHolder.setImage(getContext(),model.getImage());


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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(linearLayoutManager);
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
    public  static class MyOrderHolder extends RecyclerView.ViewHolder{
        View mView;
        public MyOrderHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setName(String name) {
            TextView namee = (TextView) mView.findViewById(R.id.myordeename);
            namee.setText(name);
        }

      /*  public void setPlace(String place) {
            TextView textView1 = (TextView) mView.findViewById(R.id.place);
            textView1.setText(place);
            Log.d("Coming1","name data entererd");
        }
*/
        public  void setStatus(String s){
            TextView textView2 = (TextView) mView.findViewById(R.id.prostatus);
            textView2.setText(s);
        }
        public  void setQuantity(String s){
            TextView textView2 = (TextView) mView.findViewById(R.id.proqty);
            textView2.setText(s);
        }

        public void setPrice(String price) {
            TextView textView2 = (TextView) mView.findViewById(R.id.sporice);
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
            //ImageView imageView = (ImageView) mView.findViewById(R.id.myoimage);
           /* Picasso.with(ctx).load(image).into(imageView);*/
           // Glide.with(ctx).load(image).into(imageView);
            /*switch (image){
                case "Scientific Calculator": imageView.setImageResource(R.drawable.mybook);break;
                default:imageView.setImageResource(R.drawable.myinstru);break;
            }*/

        }
    }

}
