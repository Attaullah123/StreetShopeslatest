package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.streetshopes.MainActivity;
import com.streetshopes.R;

import java.util.ArrayList;
import java.util.List;

import adapter.SellerReviewsAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellerStatsFragment extends Fragment {


    private List<String> storeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SellerReviewsAdapter sellerReviewsAdapter;

    public SellerStatsFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seller_stats, container, false);

        ((MainActivity)getActivity()).mTitle.setText(ProfileFragment.sellersname+ " Stats");

        TextView user_profile_companyName =  (TextView)rootView .findViewById(R.id.user_profile_companyName);
        user_profile_companyName.setText(ProfileFragment.sellersname);

        TextView user_profile_nameFull    =  (TextView)rootView .findViewById(R.id.user_profile_nameFull);
        user_profile_nameFull.setText(ProfileFragment.sellerownername);

        TextView user_profile_addressFull =  (TextView)rootView .findViewById(R.id.user_profile_addressFull);
        user_profile_addressFull.setText(ProfileFragment.selleraddress);

        TextView user_profile_countryName =  (TextView)rootView .findViewById(R.id.user_profile_countryName);
        user_profile_countryName.setText(ProfileFragment.sellerscountry);

        TextView user_profile_phoneNumber =  (TextView)rootView .findViewById(R.id.user_profile_phoneNumber);
        user_profile_phoneNumber.setText(ProfileFragment.sellerphone);


        TextView user_profile_totalSaleValue    =  (TextView)rootView .findViewById(R.id.user_profile_totalSaleValue);
        user_profile_totalSaleValue.setText(ProfileFragment.TotalSale);

        TextView user_profile_totalEarningValue =  (TextView)rootView .findViewById(R.id.user_profile_totalEarningValue);
        user_profile_totalEarningValue.setText(ProfileFragment.TotalEarning);

        TextView user_profile_totalOrdersValue =  (TextView)rootView .findViewById(R.id.user_profile_totalOrdersValue);
        user_profile_totalOrdersValue.setText(ProfileFragment.TotalOrders);

        TextView user_profile_totalValue =  (TextView)rootView .findViewById(R.id.user_profile_totalValue);
        user_profile_totalValue.setText(ProfileFragment.Total);

        ImageView user_iamge            =  (ImageView)rootView .findViewById(R.id.sellerlogo);
        Glide.with(SellerStatsFragment.this)
                .load(ProfileFragment.sellerslogourl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(user_iamge);

      /*
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        //storeList.add(1,"you are good");
        sellerReviewsAdapter = new SellerReviewsAdapter(getActivity(), storeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(sellerReviewsAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(getActivity(), String.valueOf(position) + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        // Inflate the layout for this fragment
        return rootView;
    }

}
