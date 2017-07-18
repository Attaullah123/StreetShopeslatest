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

import adapter.ProductReviewsAdapter;
import adapter.SellerReviewsAdapter;
import recycleview.ClickListener;
import recycleview.RecyclerTouchListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreProductReviewsFragment extends Fragment {


    public  static List<String> ReviewList= new ArrayList<>();
    public  static RecyclerView recyclerView;
    public  static ProductReviewsAdapter sellerReviewsAdapter;

    public StoreProductReviewsFragment()
    {
        System.out.println("Hmm on Load");
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_product_reviews, container, false);

        ((MainActivity)getActivity()).mTitle.setText("REVIEWS");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        String review = "";

        System.out.println("i was here");
      /*  try
        {

            for(int i=0;i<=ProfileFragment.Reviews.length()-1;i++)
            {
                JSONObject json1  = new JSONObject(StoreProductItemDetailsFragment.Reviews.getString(i));
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
        System.out.println("then i was there");
        sellerReviewsAdapter = new ProductReviewsAdapter(getActivity(),  ReviewList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(sellerReviewsAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener()
        {
            @Override
            public void onClick(View view, int position) {


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        // Inflate the layout for this fragment*/
        return rootView;
    }

}
