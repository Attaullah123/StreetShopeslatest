package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.streetshopes.MainActivity;
import com.streetshopes.R;

import java.util.ArrayList;
import java.util.List;

import adapter.SellerReviewsAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellerRefundShimpmentyFragment extends Fragment {


    private List<String> storeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SellerReviewsAdapter sellerReviewsAdapter;

    public SellerRefundShimpmentyFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seller_shipment_policy, container, false);

        ((MainActivity)getActivity()).mTitle.setText(ProfileFragment.sellersname+ " Shipment Policy");

        System.out.println(ProfileFragment.Shipmentpolicy);

        TextView user_profile_Spoicy    =  (TextView)rootView .findViewById(R.id.user_profile_companyName);
        user_profile_Spoicy.setText(Html.fromHtml(ProfileFragment.Shipmentpolicy));
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
