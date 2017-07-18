package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.streetshopes.MainActivity;
import com.streetshopes.R;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdvancedSearchFragment extends Fragment {


    @BindView(R.id.rangeSeekbar)
    RangeSeekBar<Integer> rangeSeekbar;

    private Unbinder unbinder;

    public AdvancedSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_advanced_search, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        ((MainActivity) getActivity()).mTitle.setText("Seller's Store");

        rangeSeekbar.setRangeValues(0, 100);
        rangeSeekbar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                //Now you have the minValue and maxValue of your RangeSeekbar
                //Toast.makeText(getActivity(), minValue + "-" + maxValue, Toast.LENGTH_LONG).show();
            }
        });

    // Get noticed while dragging
        rangeSeekbar.setNotifyWhileDragging(true);

        return rootView;
    }

    @OnClick(R.id.btnSearchNow)
    public void onClick() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
