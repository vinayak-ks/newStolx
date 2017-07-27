package vicasintechies.in.stolx;


import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //MaterialViewPager mViewPager;
    private Button button;
    private Uri imageuri = null;
    private ProgressDialog prodialog;
    private String strtext;
    private String strimg;
    SlidingTabLayout tabLayout;

    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    private StorageReference storageReference;
    private DatabaseReference databserefernce;
    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        storageReference = FirebaseStorage.getInstance().getReference();
        databserefernce = FirebaseDatabase.getInstance().getReference().child("book1");
        prodialog = new ProgressDialog(getContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);

        mViewPager = (ViewPager) v.findViewById(R.id.pagerCities);
        mSectionsPagerAdapter = new SectionsPagerAdapter(
                getChildFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (SlidingTabLayout) v.findViewById(R.id.tabanim_tabs);
        tabLayout.setViewPager(mViewPager);
/*        mViewPager = (MaterialViewPager)rootview.findViewById(R.id.materialViewPager);
        *//*final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        }*//*

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {

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

*//*        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
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
        });*//*
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        final View logo = rootview.findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }*/

        return v;
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<String> tabNames = new ArrayList<String>() {
            {
                add("Books");
                add("Xerox");
                add("Instruments");
                add("Others");
                add("notifications");
            }
        };


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return BookFragment.newInstance();
            } else if (position == 1) {
                return new XeroxFragment();
            } else if (position == 2) {
                return new InstrumentFragment();
            } else if (position == 3) {
                return new OthersFragment();
            } else {
                return new NotificationsFragment();
            }

        }

        @Override
        public int getCount() {
            return tabNames.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames.get(position);
        }

    }
}
