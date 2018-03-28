package com.example.bvchopda.fragmentsdemo;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;

import com.fragments.EventBusFragment;
import com.fragments.ImportFragment;
import com.fragments.SlideShowFragment;
import com.fragments.ToolsFragment;
import com.notification.NotificationManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    HashMap<String, Fragment> fragmentHashMap;
    ArrayList<String> fragmentsList;
    private final int KEY_IMPORT = 1;
    private final int KEY_GALLERY = 2;
    private final int KEY_SLIDESHOW = 3;
    private final int KEY_TOOLS = 4;
    View mainLayout;
    private float lastTranslate = 0.0f;
    private NavigationView navigationView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainLayout = findViewById(R.id.mainLayout);

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null && getIntent().getExtras().keySet().contains(NotificationManager.NotificationConstants.notificationId))
        {
            Map<String, String> data = new HashMap<>();
            for (String key : getIntent().getExtras().keySet())
            {
                Object value = getIntent().getExtras().get(key);
                data.put(key, String.valueOf(value));
                Log.e(TAG, "Key: " + key + " Value: " + value);
            }
            NotificationManager.getInstance(this).handleExtras(data, true);
            finish();
        }

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        final Snackbar[] aSnackBar = new Snackbar[1];
        final Snackbar[] bSnackBar = new Snackbar[1];

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                aSnackBar[0] = Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_INDEFINITE).setAction("Action", null);
                aSnackBar[0].show();

                bSnackBar[0] = Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_INDEFINITE).setAction("Action", null);
                bSnackBar[0].show();

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (aSnackBar[0] != null && aSnackBar[0].isShownOrQueued())
                        {
                            aSnackBar[0].dismiss();
                            aSnackBar[0] = null;
                        }
                    }
                }, 2000);

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (bSnackBar[0] != null && bSnackBar[0].isShownOrQueued())
                        {
                            bSnackBar[0].dismiss();
                            bSnackBar[0] = null;
                        }
                    }
                }, 10000);
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                super.onDrawerSlide(drawerView, slideOffset);
                float moveFactor = (navigationView.getWidth() * slideOffset);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                {
                    mainLayout.setTranslationX(moveFactor);
                }
                else
                {
                    TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                    anim.setDuration(0);
                    anim.setFillAfter(true);
                    mainLayout.startAnimation(anim);

                    lastTranslate = moveFactor;
                }
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        fragmentHashMap = new HashMap<>();
        fragmentsList = new ArrayList<>();
        if (savedInstanceState != null) {
            Log.e(getClass().getSimpleName(), "savedInstanceState not null");
            fragmentHashMap = (HashMap<String, Fragment>) savedInstanceState.getSerializable("keymap");
            fragmentsList = (ArrayList<String>) savedInstanceState.getSerializable("keylist");
        } else {
            Log.e(getClass().getSimpleName(), "savedInstanceState null");
            openFragment(KEY_IMPORT);
        }
        Log.e(getClass().getSimpleName(), "onCreate()");
    }

    private void openFragment(int key) {
        Fragment fragment = null;
        switch (key) {
            case KEY_IMPORT:
                fragment = fragmentHashMap.get(ImportFragment.class.getSimpleName());
                if (fragment == null) {
                    fragment = new ImportFragment();
                    fragmentHashMap.put(ImportFragment.class.getSimpleName(), fragment);
                }
                fragmentsList.add(ImportFragment.class.getSimpleName());
                /*fragment = fragmentHashMap.get(VectorFragment.class.getSimpleName());
                if (fragment == null) {
                    fragment = new VectorFragment();
                    fragmentHashMap.put(VectorFragment.class.getSimpleName(), fragment);
                }
                fragmentsList.add(VectorFragment.class.getSimpleName());*/
                break;
            case KEY_GALLERY:
                /*fragment = fragmentHashMap.get(GalleryFragment.class.getSimpleName());
                if (fragment == null) {
                    fragment = new GalleryFragment();
                    fragmentHashMap.put(GalleryFragment.class.getSimpleName(), fragment);
                }
                fragmentsList.add(GalleryFragment.class.getSimpleName());*/

                fragment = fragmentHashMap.get(EventBusFragment.class.getSimpleName());
                if (fragment == null) {
                    fragment = new EventBusFragment();
                    fragmentHashMap.put(EventBusFragment.class.getSimpleName(), fragment);
                }
                fragmentsList.add(EventBusFragment.class.getSimpleName());

                break;
            case KEY_SLIDESHOW:
                fragment = fragmentHashMap.get(SlideShowFragment.class.getSimpleName());
                if (fragment == null) {
                    fragment = new SlideShowFragment();
                    fragmentHashMap.put(SlideShowFragment.class.getSimpleName(), fragment);
                }
                fragmentsList.add(SlideShowFragment.class.getSimpleName());
                break;
            case KEY_TOOLS:
                fragment = fragmentHashMap.get(ToolsFragment.class.getSimpleName());
                if (fragment == null) {
                    fragment = new ToolsFragment();
                    fragmentHashMap.put(ToolsFragment.class.getSimpleName(), fragment);
                }
                fragmentsList.add(ToolsFragment.class.getSimpleName());
                break;
        }
        replaceFragment(fragment, null);
    }

    public void replaceFragment(Fragment currentFragment, Fragment fragment, View viewById, String aaa) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.setOrdering(TransitionSet.ORDERING_TOGETHER);
            transitionSet.addTransition(new ChangeBounds()).
                    addTransition(new ChangeTransform()).
                    addTransition(new ChangeImageTransform());
            fragment.setSharedElementEnterTransition(transitionSet);
            fragment.setEnterTransition(new Fade());
            currentFragment.setExitTransition(new Fade());
            fragment.setSharedElementReturnTransition(transitionSet);
        }
        String aTag = fragment.getClass().getSimpleName();
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.addSharedElement(viewById, aaa);
        transaction.replace(R.id.frame, fragment, aTag);
        transaction.addToBackStack(aTag);
        // Commit the transaction
        transaction.commit();
    }

    public void replaceFragment(Fragment fragment, Bundle bundle) {
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        // Create new fragment and transaction
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.frame, fragment);
//        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        /*else if (fragmentsList.size() > 1) {
            fragmentsList.remove(fragmentsList.size() - 1);
            String tag = fragmentsList.get(fragmentsList.size() - 1);
            Fragment fragment = fragmentHashMap.get(tag);
            replaceFragment(fragment, null);
        }*/
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            openFragment(KEY_IMPORT);
        } else if (id == R.id.nav_gallery) {
            openFragment(KEY_GALLERY);
        } else if (id == R.id.nav_slideshow) {
            openFragment(KEY_SLIDESHOW);
        } else if (id == R.id.nav_manage) {
            openFragment(KEY_TOOLS);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("keymap", fragmentHashMap);
        outState.putSerializable("keylist", fragmentsList);
        super.onSaveInstanceState(outState);
    }
}
