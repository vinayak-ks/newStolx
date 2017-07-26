package vicasintechies.in.stolx;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
//import butterknife.BindView;
//import butterknife.ButterKnife;

public class MainActivity extends DrawerActivity {

    FloatingActionButton fab1;
    FloatingActionButton fab2;
    FloatingActionButton fab3;
    FloatingActionButton fab4;
    MaterialViewPager mViewPager;

    private static final int GALLERY_REQUEST = 1;
    private ImageButton imageButton;
    private EditText editName;
    private EditText editPrice;
    private EditText editCollege;
    private EditText editPlace;
    private EditText editBranch;

    private Button button;
    private Uri imageuri = null;
    private ProgressDialog prodialog;
    private String strtext;
    private String strimg;

    private StorageReference storageReference;
    private DatabaseReference databserefernce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
       /* mViewPager = (MaterialViewPager)findViewById(R.id.materialViewPager);*/

        storageReference = FirebaseStorage.getInstance().getReference();
        databserefernce = FirebaseDatabase.getInstance().getReference().child("book1");
        prodialog = new ProgressDialog(this);

        fab1 = (FloatingActionButton) findViewById(R.id.fabBook);
        fab2 = (FloatingActionButton) findViewById(R.id.fabXerox);
        fab3 = (FloatingActionButton) findViewById(R.id.fabInstruments);
        fab4 = (FloatingActionButton) findViewById(R.id.fabOthers);

        //alertdialog
        Fragment fragment = new BlankFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame,fragment,"fragment");
        fragmentTransaction.commit();


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //buttonClicked(view);
                Toast.makeText(getApplicationContext(),"this is adding ",Toast.LENGTH_LONG).show();
                AddFragment fragment = new AddFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.frame,fragment,"fragment");
                fragmentTransaction.commit();
            }
        });

        /*final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
*/
/*        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    case 0:
                        return BookFragment.newInstance();
                    //    return RecyclerViewFragment.newInstance();
                    //case 1:
                    //    return RecyclerViewFragment.newInstance();
                    //case 2:
                    //    return WebViewFragment.newInstance();
                    default:
                        return BookFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "Books";
                    case 1:
                        return "Drafters";
                    case 2:
                        return "REquests";
                    case 3:
                        return "Ads";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://www.hdiphonewallpapers.us/phone-wallpapers/540x960-1/540x960-mobile-wallpapers-hd-2218x5ox3.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        final View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }*/
    }

    public void buttonClicked(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.prompts, null);
        editName = (EditText) alertLayout.findViewById(R.id.edit_name);
        editPrice = (EditText) alertLayout.findViewById(R.id.edit_price);
        editPlace = (EditText) alertLayout.findViewById(R.id.edit_place);
        editCollege = (EditText) alertLayout.findViewById(R.id.edit_college);
        editBranch = (EditText) alertLayout.findViewById(R.id.edit_branch);


      /*  cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // to encode password in dots
                    etPassword.setTransformationMethod(null);
                } else {
                    // to display the password in normal text
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });*/

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Login");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("upload", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Toast.makeText(getBaseContext(), "Username: " + name + " Password: " + price, Toast.LENGTH_LONG).show();
                  //  startPosting(name,price,place,college,branch);


            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }


}
