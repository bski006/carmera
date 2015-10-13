package carmera.io.carmera;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.yalantis.guillotine.animation.GuillotineAnimation;
import butterknife.Bind;
import butterknife.ButterKnife;
import carmera.io.carmera.fragments.SearchContainer;
import carmera.io.carmera.fragments.CaptureFragment;
import carmera.io.carmera.fragments.ListingsV2Fragment;
import carmera.io.carmera.fragments.RecognitionResultsDisplay;

/**
 * Created by bski on 6/3/15.
 */
public class Base extends AppCompatActivity implements CaptureFragment.OnCameraResultListener,
                                                       SearchContainer.OnSearchVehiclesListener,
                                                       RecognitionResultsDisplay.RetakePhotoListener{

    private final String TAG = getClass().getCanonicalName();
    private static final long RIPPLE_DURATION = 250;
    private static GuillotineAnimation guillotineAnimation;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.root) FrameLayout root;
    @Bind(R.id.content_hamburger) View contentHamburger;
    private View search, carmera, saved;
    @Override
    public void retakePhoto() {
        CaptureFragment captureFragmentFragment = CaptureFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, captureFragmentFragment)
                .addToBackStack("CAPTURE")
                .commit();
    }

    @Override
    public void OnSearchListings (Parcelable query) {
        Bundle args = new Bundle();
        args.putParcelable(ListingsV2Fragment.EXTRA_LISTING_QUERY, query);
        ListingsV2Fragment listingsV2Fragment = ListingsV2Fragment.newInstance();
        listingsV2Fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, listingsV2Fragment)
                .addToBackStack("LISTINGS")
                .commit();
    }

    @Override
    public void OnCameraResult (byte[] image_data) {
        Bundle args = new Bundle();
        args.putByteArray("image_data", image_data);
        RecognitionResultsDisplay recognitionResultsFragment = new RecognitionResultsDisplay();
        recognitionResultsFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.content_frame, recognitionResultsFragment)
                                   .addToBackStack("RECOGNITION_RESULTS")
                                   .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        SearchContainer searchFragment = SearchContainer.newInstance();
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, searchFragment)
                .commit();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);
        search = guillotineMenu.findViewById(R.id.car_search);
        carmera = guillotineMenu.findViewById(R.id.carmera);
        saved = guillotineMenu.findViewById(R.id.saved_listings);


        guillotineAnimation = new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .build();

        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, SearchContainer.newInstance())
                        .commit();
                guillotineAnimation.close();
                return false;
            }
        });

        carmera.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, CaptureFragment.newInstance())
                        .commit();
                guillotineAnimation.close();
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.carmera_capture:
                CaptureFragment captureFragmentFragment = CaptureFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, captureFragmentFragment)
                        .addToBackStack("CAPTURE")
                        .commit();
        }
        return super.onOptionsItemSelected(item);
    }

}
