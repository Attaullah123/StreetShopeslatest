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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.SellerReviewsAdapter;
import recycleview.ClickListener;
import recycleview.RecyclerTouchListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellerReviewsFragment extends Fragment {


    private List<String> ReviewList= new ArrayList<>();
    private RecyclerView recyclerView;
    private SellerReviewsAdapter sellerReviewsAdapter;

    public SellerReviewsFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seller_reviews, container, false);

        ((MainActivity)getActivity()).mTitle.setText("Seller's Reviews");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        //storeList.add(1,"you are good");




        String review = "";
        try {
            //System.out.println("Reviews"+ ProfileFragment.Reviews);


            for(int i=0;i<=ProfileFragment.Reviews.length()-1;i++)
            {
                JSONObject json1  = new JSONObject(ProfileFragment.Reviews.getString(i));
                System.out.println("Rehan"+i + json1.getString("name"));
                 review ="";
                review=review+json1.getString("name")+"~~~";
                review=review+json1.getString("description")+"~~~";
                review=review+json1.getString("rate");

                this.ReviewList.add(review);
               // this.ReviewList.clear();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        sellerReviewsAdapter = new SellerReviewsAdapter(getActivity(),  ReviewList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(sellerReviewsAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

              //  Toast.makeText(getActivity(), String.valueOf(position) + " is selected!", Toast.LENGTH_SHORT).show();
            /*try
            {
             String clickeditem = view.getTag().toString();
                if(clickeditem.equals("reviewit"))
                {
                    //addreview();
                }
            }
            catch(Exception e)
            {

            }*/
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        // Inflate the layout for this fragment
        return rootView;
    }

}
