package vicasintechies.in.stolx;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
com.github.clans.fab.FloatingActionButton fab1;
    com.github.clans.fab.FloatingActionButton fab2;
    com.github.clans.fab.FloatingActionButton fab3;
    com.github.clans.fab.FloatingActionButton fab4;

    FloatingActionMenu menu_labels;
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        menu_labels = (FloatingActionMenu) findViewById(R.id.menu_labels_right);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragment = new BlankFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame,fragment,"fragment");
        fragmentTransaction.commit();
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
                fragmentTransaction.add(R.id.frame,fragment,"fragment");
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
                fragmentTransaction.add(R.id.frame,fragment,"fragment");
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
                fragmentTransaction.add(R.id.frame,fragment,"fragment");
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
                fragmentTransaction.add(R.id.frame,fragment,"fragment");
                fragmentTransaction.commit();

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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

        if (id == R.id.nav_camera) {
            // Handle the camera action

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(NavigationActivity.this,LoginActivity.class));
            finish();
            
        } else if (id == R.id.nav_gallery) {
            Bundle args = new Bundle();
            args.putString("user","default");
            Intent i = new Intent(NavigationActivity.this,NoticationActivity.class);
            i.putExtras(args);
            startActivity(i);
                //finish();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
