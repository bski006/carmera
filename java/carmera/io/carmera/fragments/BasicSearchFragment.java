package carmera.io.carmera.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;
import carmera.io.carmera.R;

/**
 * Created by bski on 10/12/15.
 */
public class BasicSearchFragment extends SearchFragment {

    public static BasicSearchFragment newInstance() {
        return new BasicSearchFragment();
    }

    @Bind(R.id.basic_container) public ObservableScrollView basic_container;

    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.basic_search, container, false);
        ButterKnife.bind (this, v);
        return v;
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialViewPagerHelper.registerScrollView(getActivity(), basic_container, null);
    }
}
