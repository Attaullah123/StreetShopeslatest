package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.streetshopes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class BillingShippingFragment extends Fragment {


    @BindView(R.id.imgBillingInfoMinus)
    ImageView imgBillingInfoMinus;
    @BindView(R.id.imgBillingInfoPlus)
    ImageView imgBillingInfoPlus;
    @BindView(R.id.expandableLayoutBillingInfo)
    ExpandableRelativeLayout expandableLayoutBillingInfo;
    @BindView(R.id.imgSippingInfoMinus)
    ImageView imgSippingInfoMinus;
    @BindView(R.id.imgSippingInfoPlus)
    ImageView imgSippingInfoPlus;
    @BindView(R.id.expandableLayoutShippingInfo)
    ExpandableRelativeLayout expandableLayoutShippingInfo;

    private Unbinder unbinder;

    public BillingShippingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_billing_shipping, container, false);
        unbinder = ButterKnife.bind(this, rootView);


        expandableLayoutShippingInfo.collapse();
        //expandableLayoutBillingInfo.expand();

        expandableLayoutBillingInfo.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                imgBillingInfoMinus.setVisibility(View.VISIBLE);
                imgBillingInfoPlus.setVisibility(View.GONE);
            }

            @Override
            public void onPreClose() {
                imgBillingInfoMinus.setVisibility(View.GONE);
                imgBillingInfoPlus.setVisibility(View.VISIBLE);
            }
        });

        expandableLayoutShippingInfo.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                imgSippingInfoMinus.setVisibility(View.VISIBLE);
                imgSippingInfoPlus.setVisibility(View.GONE);
            }

            @Override
            public void onPreClose() {
                imgSippingInfoMinus.setVisibility(View.GONE);
                imgSippingInfoPlus.setVisibility(View.VISIBLE);
            }
        });

        return rootView;
    }


    @OnClick({R.id.imgBillingInfoMinus, R.id.imgBillingInfoPlus, R.id.imgSippingInfoMinus, R.id.imgSippingInfoPlus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBillingInfoMinus:
                expandableLayoutBillingInfo.collapse();
                break;
            case R.id.imgBillingInfoPlus:
                expandableLayoutBillingInfo.expand();
                break;
            case R.id.imgSippingInfoMinus:
                expandableLayoutShippingInfo.collapse();
                break;
            case R.id.imgSippingInfoPlus:
                expandableLayoutShippingInfo.expand();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
