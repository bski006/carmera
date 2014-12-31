package veme.cario.com.CARmera;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AppEventsLogger;

import veme.cario.com.CARmera.fragment.ActivityFragment.NearbyListingFragment;
import veme.cario.com.CARmera.fragment.ActivityFragment.NearbyTaggedFragment;

/**
 * Created by bski on 11/22/14.
 */
public class NearbyActivity extends BaseActivity
                            implements NearbyListingFragment.OnNearbyListingSelectedListener,
                                       NearbyTaggedFragment.OnNearbyTaggedSelectedListener{

    private final String TAG = "NEARBY_ACTIVITY";
    private ViewPager viewPager;
    private NeabyPagerAdapter nearbyPagerAdapter;

    @Override
    public void OnNearbyListingsSelected(int pos) {
    }

    @Override
    public void OnNearbyTaggedSelected(int pos) {
    }

    /* TODO:
        1. Query by geolocation
        2. Query by Edmund's API/other api
     */
    @Override
    public void onCreate (Bundle savedBundleinst) {
        super.onCreate(savedBundleinst);

        getLayoutInflater().inflate(R.layout.activity_nearby, frame_layout);
        drawer_listview.setItemChecked(drawer_pos, true);
        setTitle("Nearby");

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        Intent i = getIntent();
        Bundle args = i.getExtras();
        if (args != null) {
            Log.i(TAG, "Year: " + args.getString("vehicle_year"));
        }

        nearbyPagerAdapter = new NeabyPagerAdapter (getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.nearby_pager);
        viewPager.setAdapter(nearbyPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int pos) {
                actionBar.setSelectedNavigationItem(pos);
            }
        });
        /* 1. nearby tags */
        actionBar.addTab(actionBar.newTab()
                                  .setText("Tags")
                                  .setTabListener(new ActionBar.TabListener() {
                                      @Override
                                      public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                                          viewPager.setCurrentItem(tab.getPosition());
                                      }

                                      @Override
                                      public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

                                      }

                                      @Override
                                      public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

                                      }
                                  }));

        /* 2. nearby listings */
        actionBar.addTab((actionBar.newTab()
                                   .setText("Listings")
                                   .setTabListener(new ActionBar.TabListener() {
                                       @Override
                                       public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                                           viewPager.setCurrentItem(tab.getPosition());
                                       }

                                       @Override
                                       public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

                                       }

                                       @Override
                                       public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

                                       }
                                   })));
    }

    @Override
    public void onPause() {
        super.onPause();
        // AppEventsLogger.deactivateApp(this);
    }


    private static class NeabyPagerAdapter extends FragmentPagerAdapter {
        public NeabyPagerAdapter (FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            Fragment frag;
            switch(pos) {
                case 0:
                    frag = new NearbyTaggedFragment();
                    break;
                case 1:
                    frag = new NearbyListingFragment();
                    break;
                default:
                    return null;
            }
            return frag;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
