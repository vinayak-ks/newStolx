package vicasintechies.in.stolx;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int GALLERY_REQUEST = 1;
    private ImageButton imageButton;
    private EditText editName;
    private EditText editPrice;
    private Spinner editCollege;
    private Spinner editPlace;
    private Spinner editBranch;
    private Button button;
    private Uri imageuri = null;
    private ProgressDialog prodialog;
    private String strtext;
    private String strimg;
    private String phoneno;
    private String clg,branch,place;

    String a,b,c;
    private StorageReference storageReference;
    private DatabaseReference databserefernce;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SellFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        phoneno = user.getPhoneNumber();


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_add, container, false);


        //ads
        NativeExpressAdView adView = (NativeExpressAdView) rootview.findViewById(R.id.adView);

        AdRequest request = new AdRequest.Builder()
                .addTestDevice("CEB43289F6636A1BC8ECD3D520DBB186").build();
        adView.loadAd(request);

        //
        strtext = getArguments().getString("table");
        strimg = getArguments().getString("imgtable");

        Log.d("Table",strtext);
        Log.d("Tableimg",strimg);
        rootview.setBackgroundColor(Color.parseColor("#003c8f"));
        // Inflate the layout for this fragment
        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        storageReference = FirebaseStorage.getInstance().getReference();
        databserefernce = FirebaseDatabase.getInstance().getReference().child(strtext);

        imageButton = (ImageButton)getView().findViewById(R.id.postimage);
        editName =(EditText)getView().findViewById(R.id.editname);
        editPrice =(EditText)getView().findViewById(R.id.editprice);
        editPlace =(Spinner)getView().findViewById(R.id.editplace);
        editCollege =(Spinner)getView().findViewById(R.id.editcollege);
        editBranch =(Spinner)getView().findViewById(R.id.editbranch);
        button =(Button)getView().findViewById(R.id.sell);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.college_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editCollege.setAdapter(adapter);
        editCollege.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            clg = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),R.array.branch_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editBranch.setAdapter(adapter1);
        editBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branch=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.city_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editPlace.setAdapter(adapter2);
        editPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        prodialog = new ProgressDialog(getContext());
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT<19){
                    Intent gallerintent = new Intent(Intent.ACTION_GET_CONTENT);
                    gallerintent.setType("image/*");
                    startActivityForResult(gallerintent,GALLERY_REQUEST);
                }else {
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    /*startActivityForResult(intent, GALLERY_REQUEST);*/
                    startActivityForResult(Intent.createChooser(intent,"Select Image"),GALLERY_REQUEST);
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });
    }

    private void startPosting() {

        prodialog.setMessage("Uploading");

        final String name= editName.getText().toString().trim();
        final String price= editPrice.getText().toString().trim();

        Date date = new Date();
        final Date newDate = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("dd-MMM-yyyy");
        final String stringdate = dt.format(newDate);



        if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(price)){
            prodialog.show();
            StorageReference filepath = storageReference.child(strimg).child(imageuri.getLastPathSegment());

            filepath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

                    DatabaseReference newpost = databserefernce.push();

                    newpost.child("Name").setValue(name);
                    newpost.child("Price").setValue(price);
                    newpost.child("Place").setValue(place);
                    newpost.child("College").setValue(clg);
                    newpost.child("Branch").setValue(branch);
                    newpost.child("Image").setValue(downloadUri.toString());
                    newpost.child("SDate").setValue(stringdate);
                    newpost.child("Phone").setValue(phoneno);
                    newpost.child("Uid").setValue(currentFirebaseUser.getUid());

                    /*newpost.child("Uid").setValue(FirebaseAuth.getCurrentUser)*/
                    prodialog.dismiss();

                   /* BookFragment bk = new BookFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.frame,bk,"fragment");
                    fragmentTransaction.commit();
*/
                   startActivity(new Intent(getActivity(),NavigationActivity.class));


                   /* if (strtext.equals("Book")){

                    } *//*/*else if (strtext.equals("Xerox")){
                        XeroxFragment bk = new XeroxFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,bk,"fragment");
                        fragmentTransaction.commit();
                    } else if (strtext.equals("Instruments")){
                        InstrumentFragment bk = new InstrumentFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,bk,"fragment");
                        fragmentTransaction.commit();
                    } else if (strtext.equals("Others")){
                        OthersFragment bk = new OthersFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,bk,"fragment");
                        fragmentTransaction.commit();
                    }
*/
                }
            });
        }
        else {
            Toast.makeText(getActivity(),"Enter all fields",Toast.LENGTH_LONG).show();
        }

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            if(data!=null){
                imageuri = data.getData();
                imageButton.setImageURI(imageuri);
            }
        }


    }
 /*   @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.edit_college:
                a = adapterView.getItemAtPosition(i).toString();
                Log.d("taag",a);
                break;
            case R.id.edit_branch: b = (String)adapterView.getItemAtPosition(i);
                Log.d("taag1",b);break;

            case R.id.edit_place: c=(String)adapterView.getItemAtPosition(i);break;

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getContext(),"Nothing selected",Toast.LENGTH_LONG).show();

    }*/
}
