package Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.streetshopes.MainActivity;
import com.streetshopes.R;

import java.util.ArrayList;
import java.util.List;

import adapter.StoreCategoryListAdapter;
import pojo.Seller;
import pojo.SellerCat;
import pojo.SellerCategory;
import pojo.SubCategory;
import recycleview.ClickListener;
import recycleview.RecyclerTouchListener;
import utils.AppConstant;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreCategoryListFragment extends Fragment {

    private ArrayList<SubCategory> subCategoryArrayList;
    private RecyclerView recyclerView;
    private StoreCategoryListAdapter storeCategoryListAdapter;
    //Seller seller;
    public StoreCategoryListFragment() {
        // Required empty public constructor
    }
    /*public StoreCategoryListFragment(List<SubCategory> sellerCat) {
        // Required empty public constructor
        this.subCategoryArrayList = sellerCat;
    }*/



    public static StoreCategoryListFragment newInstance(ArrayList<SubCategory> datum) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("datum", datum);
        StoreCategoryListFragment storeCategoryListFragment = new StoreCategoryListFragment();
        storeCategoryListFragment.setArguments(args);
        return storeCategoryListFragment;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seller_store_list, container, false);
        ((MainActivity)getActivity()).mTitle.setText(ProfileFragment.sellersname);
        subCategoryArrayList = (ArrayList<SubCategory>) getArguments().get("datum");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        storeCategoryListAdapter = new StoreCategoryListAdapter(subCategoryArrayList,getActivity());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(storeCategoryListAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                //Toast.makeText(getActivity(), String.valueOf(position) + " is selected!", Toast.LENGTH_SHORT).show();

                ((MainActivity)getActivity()).navigateToReplace(StoreProductListFragment.newInstance(subCategoryArrayList.get(position), null), null, true);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        // Inflate the layout for this fragment
        return rootView;
    }

}
