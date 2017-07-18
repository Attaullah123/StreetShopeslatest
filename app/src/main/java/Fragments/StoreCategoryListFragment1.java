package Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.streetshopes.MainActivity;
import com.streetshopes.R;

import java.util.List;

import adapter.StoreCategoryListAdapter;
import pojo.Seller;
import pojo.SellerCat;
import pojo.SubCategory;
import recycleview.ClickListener;
import recycleview.RecyclerTouchListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreCategoryListFragment1 extends Fragment {

    SellerCat sellerCat;
    private List<SubCategory> subCategoryArrayList;
    private RecyclerView recyclerView;
    private StoreCategoryListAdapter storeCategoryListAdapter;
    Seller seller;


    public StoreCategoryListFragment1(List<SubCategory> sellerCat) {
        // Required empty public constructor
        this.subCategoryArrayList = sellerCat;
    }
/*
    public StoreCategoryListFragment() {
        // Required empty public constructor
    }


    public static StoreCategoryListFragment newInstance(SellerCat datum) {
        Bundle args = new Bundle();
        args.putParcelable("datum", datum);
        StoreCategoryListFragment profileFragment = new StoreCategoryListFragment();
        profileFragment.setArguments(args);
        return profileFragment;


    }
*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seller_store_list, container, false);

        ((MainActivity)getActivity()).mTitle.setText("Seller's Store");
        //sellerCat = (SellerCat) getArguments().get("datum");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        storeCategoryListAdapter = new StoreCategoryListAdapter(subCategoryArrayList,getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(storeCategoryListAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                //Toast.makeText(getActivity(), String.valueOf(position) + " is selected!", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).navigateToReplace(new StoreProductListFragment(), null, true);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        // Inflate the layout for this fragment
        return rootView;
    }

}
