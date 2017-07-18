package Fragments;


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

import java.util.ArrayList;
import java.util.List;

import adapter.ConfirmOrderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmOrderFragment extends Fragment {


    private List<String> storeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ConfirmOrderAdapter reviewsAdapter;

    public ConfirmOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_confirm_order, container, false);

        ((MainActivity)getActivity()).mTitle.setText("Seller's Store");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        reviewsAdapter = new ConfirmOrderAdapter(getActivity(), storeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(reviewsAdapter);

        /*recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
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
