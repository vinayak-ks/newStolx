package vicasintechies.in.stolx;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
com.github.clans.fab.FloatingActionButton fab1;
    com.github.clans.fab.FloatingActionButton fab2;
    com.github.clans.fab.FloatingActionButton fab3;
    com.github.clans.fab.FloatingActionButton fab4;
   /* com.github.clans.fab.FloatingActionButton fab5;*/
    ProgressDialog progressDialog;
    FloatingActionMenu menu_labels;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Stolx");
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


*/

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("CEB43289F6636A1BC8ECD3D520DBB186").build();
        mAdView.loadAd(adRequest);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6744256522448589/8441382795");
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("CEB43289F6636A1BC8ECD3D520DBB186").build());
        menu_labels = (FloatingActionMenu) findViewById(R.id.menu_labels_right);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragment = new BlankFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment,"fragment");
        fragmentTransaction.commit();

        progressDialog = new ProgressDialog(this);
        /*fab5 =  (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabOffer);*/
        fab1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabBook);
        fab2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabXerox);
        fab3 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabInstruments);
        fab4 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabOthers);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //buttonClicked(view);

                menu_labels.close(true);

                Bundle bundle = new Bundle();
                bundle.putString("table", "Book");
                bundle.putString("imgtable","BookImages");

                Toast.makeText(getApplicationContext(),"this is adding ",Toast.LENGTH_LONG).show();
                AddFragment fragment = new AddFragment();
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,fragment,"fragment").addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                menu_labels.close(true);
                Bundle bundle = new Bundle();
                bundle.putString("table", "Xerox");
                bundle.putString("imgtable","XeroxImages");

                AddFragment fragment = new AddFragment();
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,fragment,"fragment").addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menu_labels.close(true);
                Bundle bundle = new Bundle();
                bundle.putString("table", "Instruments");
                bundle.putString("imgtable","InstrumentImages");

                AddFragment fragment = new AddFragment();
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,fragment,"fragment").addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menu_labels.close(true);
                Bundle bundle = new Bundle();
                bundle.putString("table", "Others");
                bundle.putString("imgtable","OtherImages");

                AddFragment fragment = new AddFragment();
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,fragment,"fragment").addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
      /*  fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_labels.close(true);
                Bundle bundle = new Bundle();
                bundle.putString("table", "products");
                bundle.putString("imgtable","ProductImages");

                AddFragment fragment = new AddFragment();
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,fragment,"fragment").addToBackStack(null);
                fragmentTransaction.commit();
            }
        });*/
    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //finish();
                            if (mInterstitialAd.isLoaded()) {
                                Log.d("Inters","inrt");
                                mInterstitialAd.show();
                            }
                            doubleBackToExitPressedOnce = false;
                            System.exit(0);
                        }
                    }).setNegativeButton("No", null).show();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            // Handle the camera action
            progressDialog.setMessage("Signing out ...");
            progressDialog.show();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(NavigationActivity.this,LoginActivity.class));
            progressDialog.dismiss();
            finish();
            
        } else if (id == R.id.nav_home) {
            Fragment fragment = new BlankFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame,fragment,"fragment");
            fragmentTransaction.commit();
                //finish();
        } else if (id == R.id.nav_orders) {
            Fragment fragment = new MyOrderFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame,fragment,"fragment").addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_post) {
            progressDialog.setMessage("Please wait ...");
            progressDialog.show();
            Fragment fragment = new MyPostSelectorFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame,fragment,"fragment").addToBackStack(null);
            fragmentTransaction.commit();
            progressDialog.dismiss();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //boolean doubleBackToExitPressedOnce = false;

}
