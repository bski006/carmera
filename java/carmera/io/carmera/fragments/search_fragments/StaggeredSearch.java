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
import android.widget.Toast;

import java.util.ArrayList;

import carmera.io.carmera.R;
import carmera.io.carmera.cards.StaggeredImageButtonCard;
import carmera.io.carmera.listeners.OnEditCompressors;
import carmera.io.carmera.listeners.OnEditCylinders;
import carmera.io.carmera.listeners.OnEditDriveTrain;
import carmera.io.carmera.listeners.OnEditHp;
import carmera.io.carmera.listeners.OnEditMakes;
import carmera.io.carmera.listeners.OnEditMpg;
import carmera.io.carmera.listeners.OnEditTags;
import carmera.io.carmera.listeners.OnEditTransmission;
import carmera.io.carmera.listeners.OnSearchFragmentVisible;
import it.gmariotti.cardslib.library.extra.staggeredgrid.internal.CardGridStaggeredArrayAdapter;
import it.gmariotti.cardslib.library.extra.staggeredgrid.view.CardGridStaggeredView;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by bski on 1/13/16.
 */
public class StaggeredSearch extends Fragment {

    private Context cxt;
    private OnSearchFragmentVisible onSearchFragmentVisible;

    private OnEditTags onEditTagsListener;
    private OnEditMakes onEditMakes;
    private OnEditDriveTrain onEditDriveTrain;
    private OnEditHp onEditHp;
    private OnEditMpg onEditMpg;
    private OnEditCompressors onEditCompressors;
    private OnEditTransmission onEditTransmission;
    private OnEditCylinders onEditCylinders;



    public static StaggeredSearch newInstance () {
        return new StaggeredSearch();
    }

    private void showDialog () {
        Toast.makeText(getActivity(), "New Search Criteria Added!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_initial_search_grid, container, false);
        cxt = getActivity();
        ArrayList<Card> cards = new ArrayList<>();

        /* incentives */
        StaggeredImageButtonCard staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.incentivized_search), R.drawable.incentives);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                onEditTagsListener.OnEditTagCallback("has incentives");
            }
        });
        cards.add(staggeredImageButtonCard);

        /* 40+ mpg */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.efficient), R.drawable.efficient);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                onEditMpg.OnEditMpgCallback(40);
            }
        });
        cards.add(staggeredImageButtonCard);






        /* low insurance */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.low_insurance), R.drawable.low_insurance);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                String[] tags = cxt.getResources().getStringArray(R.array.cheap_insurance_tags);
                onEditTagsListener.OnEditTagsCallback(tags);
            }
        });
        cards.add(staggeredImageButtonCard);


        /* electric */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.electric_search), R.drawable.electric);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                onEditTagsListener.OnEditTagCallback("electric");
            }
        });
        cards.add(staggeredImageButtonCard);


        /* low repairs */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.low_repairs_search), R.drawable.low_repairs);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                String [] tags = getResources().getStringArray(R.array.cheap_repairs_tags);
                onEditTagsListener.OnEditTagsCallback(tags);
            }
        });
        cards.add(staggeredImageButtonCard);

        /* hybrid */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.hybrid), R.drawable.hybrid);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                onEditTagsListener.OnEditTagCallback("hybrid");
            }
        });
        cards.add(staggeredImageButtonCard);







        /* low depreciation */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.low_depreciation), R.drawable.equipment_search_image);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                String[] tags = cxt.getResources().getStringArray(R.array.cheap_depreciation_tags);
                onEditTagsListener.OnEditTagsCallback(tags);
            }
        });
        cards.add(staggeredImageButtonCard);

        /* awd */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.four_wheel_drive_search), R.drawable.all_wheel_drive);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                onEditDriveTrain.OnEditDriveTrainCallback("all wheel drive");
            }
        });
        cards.add(staggeredImageButtonCard);




        /* no recalls */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.no_recalls_search), R.drawable.recalls_free);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                String [] reliableTags = cxt.getResources().getStringArray(R.array.no_recalls_tags);
                onEditTagsListener.OnEditTagsCallback(reliableTags);
            }
        });
        cards.add(staggeredImageButtonCard);


        /* rear wheel drive */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.rear_wheel_drive_search), R.drawable.wagon);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                onEditDriveTrain.OnEditDriveTrainCallback("rear wheel drive");
            }
        });
        cards.add(staggeredImageButtonCard);




        /* 300+ hp */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.good_hp), R.drawable.mechanical_search_image);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                onEditHp.OnEditHpCallback(300);
            }
        });
        cards.add(staggeredImageButtonCard);

        /* 500+ hp */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.super_hp), R.drawable.super_sport);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                onEditHp.OnEditHpCallback(500);
            }
        });
        cards.add(staggeredImageButtonCard);




        /* top safety */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.top_safety_search), R.drawable.safety);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                onEditTagsListener.OnEditTagCallback("top safety");
            }
        });
        cards.add(staggeredImageButtonCard);

        /* turbo */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.turbo_search), R.drawable.turbo);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                String[] compressors = cxt.getResources().getStringArray(R.array.turbo);
                onEditCompressors.OnEditCompressorCallback("Turbo");
            }
        });
        cards.add(staggeredImageButtonCard);




        /* british */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.british_search), R.drawable.convertible);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                String[] europeanMakes = cxt.getResources().getStringArray(R.array.british_makes);
                onEditMakes.OnEditMakesCallback(europeanMakes);
            }
        });
        cards.add(staggeredImageButtonCard);

        /* supercharger */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.supercharger_search), R.drawable.supercharged);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                onEditCompressors.OnEditCompressorCallback("supercharger");
            }
        });
        cards.add(staggeredImageButtonCard);




        /* german */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.german_search), R.drawable.coupe);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                String[] europeanMakes = cxt.getResources().getStringArray(R.array.german_makes);
                onEditMakes.OnEditMakesCallback(europeanMakes);
            }
        });
        cards.add(staggeredImageButtonCard);

        /* manual */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.manual), R.drawable.manual);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                onEditTransmission.addTransmissionType("manual");
            }
        });
        cards.add(staggeredImageButtonCard);





        /* italian */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.italian_search), R.drawable.low_depreciation);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                String[] europeanMakes = cxt.getResources().getStringArray(R.array.italian_makes);
                onEditMakes.OnEditMakesCallback(europeanMakes);
            }
        });
        cards.add(staggeredImageButtonCard);

        /* cylinders */
        staggeredImageButtonCard = new StaggeredImageButtonCard(cxt, cxt.getResources().getString(R.string.many_cylinders), R.drawable.v10);
        staggeredImageButtonCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                showDialog();
                onEditCylinders.onSetMinCylinders(8);
            }
        });
        cards.add(staggeredImageButtonCard);




        CardGridStaggeredArrayAdapter cardGridStaggeredArrayAdapter = new CardGridStaggeredArrayAdapter(getActivity(), cards);
        CardGridStaggeredView cardGridStaggeredView = (CardGridStaggeredView) v.findViewById(R.id.data_staggered_grid_view);
        cardGridStaggeredArrayAdapter.notifyDataSetChanged();

        if (cardGridStaggeredView != null) {
            cardGridStaggeredView.setAdapter(cardGridStaggeredArrayAdapter);
        }
        return v;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onSearchFragmentVisible.SetFabVisible();
        }

    }

    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        try {
            cxt = getContext();
            onSearchFragmentVisible = (OnSearchFragmentVisible) activity;
            onEditMakes = (OnEditMakes) activity;
            onEditTagsListener = (OnEditTags) activity;
            onEditDriveTrain = (OnEditDriveTrain) activity;
            onEditHp = (OnEditHp) activity;
            onEditMpg = (OnEditMpg) activity;
            onEditCompressors = (OnEditCompressors) activity;
            onEditTransmission = (OnEditTransmission) activity;
            onEditCylinders = (OnEditCylinders) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    ": needs to implement CameraResultListener" );
        }
    }

}
