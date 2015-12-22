package carmera.io.carmera.fragments.search_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carmera.io.carmera.MakesSearchActivity;
import carmera.io.carmera.R;
import carmera.io.carmera.listeners.OnEditBodyTypes;
import carmera.io.carmera.listeners.OnEditCompressors;
import carmera.io.carmera.listeners.OnEditDriveTrain;
import carmera.io.carmera.listeners.OnEditHp;
import carmera.io.carmera.listeners.OnEditMakes;
import carmera.io.carmera.listeners.OnEditMpg;
import carmera.io.carmera.listeners.OnEditTags;
import carmera.io.carmera.listeners.OnEditTorque;
import carmera.io.carmera.models.ListingsQuery;
import carmera.io.carmera.utils.Constants;

/**
 * Created by bski on 12/18/15.
 */
public class SpecialSearchFragment extends Fragment {
    @Bind (R.id.incentivized_search) View incentivized_search;
    @Bind (R.id.low_depreciation) View low_depr_search;
    @Bind (R.id.low_insurance) View low_insurance_search;
    @Bind (R.id.low_repairs) View low_repairs_search;
    @Bind (R.id.european_search) View european_search;
    @Bind (R.id.decent_power) View decent_power;
    @Bind (R.id.super_sport) View super_sport;
    @Bind (R.id.green_search) View green_search;
    @Bind (R.id.four_wheel_drive_search) View four_wheel_drive_search;
    @Bind (R.id.boosted_search) View boosted_search;
    @Bind (R.id.reliable_search) View reliable_search;
    @Bind (R.id.top_safety_search) View top_safety_search;

    public String TAG = getClass().getCanonicalName();
    private Context cxt;
    private OnEditTags onEditTagsListener;
    private OnEditBodyTypes onEditBodyTypes;
    private OnEditDriveTrain onEditDriveTrain;
    private OnEditHp onEditHp;
    private OnEditMakes onEditMakes;
    private OnEditMpg onEditMpg;
    private OnEditCompressors onEditCompressors;

    @OnClick(R.id.add_incentives)
    void addIncentives () {
        onEditTagsListener.OnEditTagCallback("Has Incentives");
    }

    @OnClick(R.id.add_low_repairs)
    void addRepairs () {
        onEditTagsListener.OnEditTagsCallback(cxt.getResources().getStringArray(R.array.cheap_repairs_tags));
    }

    @OnClick(R.id.add_low_depreciation)
    void addDepreciation () {
        onEditTagsListener.OnEditTagsCallback(cxt.getResources().getStringArray(R.array.cheap_depreciation_tags));
    }

    @OnClick(R.id.add_low_insurance)
    void addInsurance () {
        onEditTagsListener.OnEditTagsCallback(cxt.getResources().getStringArray(R.array.cheap_insurance_tags));
    }

    @OnClick(R.id.add_european)
    void addEuropean () {
        String[] europeanMakes = cxt.getResources().getStringArray(R.array.european_makes);
        onEditMakes.OnEditMakesCallback(europeanMakes);
    }

    @OnClick(R.id.add_decent_power)
    void addDecentPower () {
        onEditHp.OnEditHpCallback(300);
    }

    @OnClick(R.id.add_super_sport)
    void addSuperSport () {
        onEditHp.OnEditHpCallback(500);
    }

    @OnClick(R.id.add_green)
    void addMpg () {
        onEditMpg.OnEditMpgCallback(40);
    }

    @OnClick(R.id.add_four_wheel_drive)
    void addAllWheel() {
        onEditDriveTrain.OnEditDriveTrainCallback("all wheel drive");
    }

    @OnClick(R.id.add_boosted)
    void addBoosted() {
        String [] compressors = cxt.getResources().getStringArray(R.array.force_induction);
        onEditCompressors.OnEditCompressorsCallback(compressors);
    }

    @OnClick(R.id.add_reliable)
    void addReliable() {
        String [] reliableTags = cxt.getResources().getStringArray(R.array.reliable_tags);
        onEditTagsListener.OnEditTagsCallback(reliableTags);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.special_search, container, false);
        ButterKnife.bind(this, v);
        incentivized_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt, MakesSearchActivity.class);
                ListingsQuery listingsQuery = new ListingsQuery();
                listingsQuery.car.tags.add("Has Incentives");
                i.putExtra(Constants.EXTRA_LISTING_QUERY, Parcels.wrap(ListingsQuery.class, listingsQuery));
                startActivityForResult(i, 1);
            }
        });
        low_depr_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt, MakesSearchActivity.class);
                ListingsQuery listingsQuery = new ListingsQuery();
                listingsQuery.car.tags.addAll(Arrays.asList(cxt.getResources().getStringArray(R.array.cheap_depreciation_tags)));
                i.putExtra(Constants.EXTRA_LISTING_QUERY, Parcels.wrap(ListingsQuery.class, listingsQuery));
                startActivityForResult(i, 1);
            }
        });
        low_insurance_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt, MakesSearchActivity.class);
                ListingsQuery listingsQuery = new ListingsQuery();
                listingsQuery.car.tags.addAll(Arrays.asList(cxt.getResources().getStringArray(R.array.cheap_insurance_tags)));
                i.putExtra(Constants.EXTRA_LISTING_QUERY, Parcels.wrap(ListingsQuery.class, listingsQuery));
                startActivityForResult(i, 1);
            }
        });
        low_repairs_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt, MakesSearchActivity.class);
                ListingsQuery listingsQuery = new ListingsQuery();
                listingsQuery.car.tags.addAll(Arrays.asList(cxt.getResources().getStringArray(R.array.cheap_repairs_tags)));
                i.putExtra(Constants.EXTRA_LISTING_QUERY, Parcels.wrap(ListingsQuery.class, listingsQuery));
                startActivityForResult(i, 1);
            }
        });
        european_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt, MakesSearchActivity.class);
                ListingsQuery listingsQuery = new ListingsQuery();
                listingsQuery.car.makes.addAll(Arrays.asList(cxt.getResources().getStringArray(R.array.european_makes)));
                i.putExtra(Constants.EXTRA_LISTING_QUERY, Parcels.wrap(ListingsQuery.class, listingsQuery));
                startActivityForResult(i, 1);
            }
        });
        decent_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt, MakesSearchActivity.class);
                ListingsQuery listingsQuery = new ListingsQuery();
                listingsQuery.car.minHp = 300;
                listingsQuery.car.bodyTypes.addAll(Arrays.asList(cxt.getResources().getStringArray(R.array.sport_bodytypes)));
                i.putExtra(Constants.EXTRA_LISTING_QUERY, Parcels.wrap(ListingsQuery.class, listingsQuery));
                startActivityForResult(i, 1);
            }
        });
        super_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt, MakesSearchActivity.class);
                ListingsQuery listingsQuery = new ListingsQuery();
                listingsQuery.car.minHp = 500;
                listingsQuery.car.bodyTypes.addAll(Arrays.asList(cxt.getResources().getStringArray(R.array.sport_bodytypes)));
                i.putExtra(Constants.EXTRA_LISTING_QUERY, Parcels.wrap(ListingsQuery.class, listingsQuery));
                startActivityForResult(i, 1);
            }
        });
        green_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt, MakesSearchActivity.class);
                ListingsQuery listingsQuery = new ListingsQuery();
                listingsQuery.car.minMpg = 40;
                i.putExtra(Constants.EXTRA_LISTING_QUERY, Parcels.wrap(ListingsQuery.class, listingsQuery));
                startActivityForResult(i, 1);
            }
        });
        four_wheel_drive_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt, MakesSearchActivity.class);
                ListingsQuery listingsQuery = new ListingsQuery();
                listingsQuery.car.drivenWheels.add("all wheel drive");
                i.putExtra(Constants.EXTRA_LISTING_QUERY, Parcels.wrap(ListingsQuery.class, listingsQuery));
                startActivityForResult(i, 1);
            }
        });

        boosted_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt, MakesSearchActivity.class);
                ListingsQuery listingsQuery = new ListingsQuery();
                listingsQuery.car.compressors.addAll (Arrays.asList(cxt.getResources().getStringArray(R.array.force_induction)));
                i.putExtra(Constants.EXTRA_LISTING_QUERY, Parcels.wrap(ListingsQuery.class, listingsQuery));
                startActivityForResult(i, 1);
            }
        });

        reliable_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt, MakesSearchActivity.class);
                ListingsQuery listingsQuery = new ListingsQuery();
                listingsQuery.car.tags.addAll (Arrays.asList(cxt.getResources().getStringArray(R.array.reliable_tags)));
                i.putExtra(Constants.EXTRA_LISTING_QUERY, Parcels.wrap(ListingsQuery.class, listingsQuery));
                startActivityForResult(i, 1);
            }
        });


        top_safety_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt, MakesSearchActivity.class);
                ListingsQuery listingsQuery = new ListingsQuery();
                listingsQuery.car.tags.add("Top Safety");
                i.putExtra(Constants.EXTRA_LISTING_QUERY, Parcels.wrap(ListingsQuery.class, listingsQuery));
                startActivityForResult(i, 1);
            }
        });

        return v;
    }

    public static SpecialSearchFragment newInstance () {
        return new SpecialSearchFragment();
    }

    @Override
    public void onViewCreated (View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        try {
            cxt = getContext();
            onEditBodyTypes = (OnEditBodyTypes) activity;
            onEditDriveTrain = (OnEditDriveTrain) activity;
            onEditHp = (OnEditHp) activity;
            onEditMakes = (OnEditMakes) activity;
            onEditMpg = (OnEditMpg) activity;
            onEditTagsListener = (OnEditTags) activity;
            onEditCompressors = (OnEditCompressors) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    ": needs to implement CameraResultListener" );
        }
    }

}
