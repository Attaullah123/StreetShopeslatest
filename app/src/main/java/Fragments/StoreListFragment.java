package Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.streetshopes.LoginActivity;
import com.streetshopes.MainActivity;
import com.streetshopes.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.StoreListAdapter;
import bean.Datum;
import bean.StoreListResponce;
import pojo.Seller;
import pojo.SellertsList;
import recycleview.ClickListener;
import recycleview.RecyclerTouchListener;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.AppConstant;

import static com.streetshopes.R.id.edtPassword;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreListFragment extends Fragment {


    private List<Seller> sellerList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StoreListAdapter mStoreListAdapter;

    public StoreListFragment() {
        // Required empty public constructor
    }


    public static StoreListFragment newInstance(Seller datum) {
        Bundle args = new Bundle();
        args.putParcelable("datum", datum);
        StoreListFragment profileFragment = new StoreListFragment();
        profileFragment.setArguments(args);
        return profileFragment;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_list, container, false);

        ((MainActivity)getActivity()).mTitle.setText("Stores");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);


        getStoreList();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

               // Toast.makeText(getActivity(), String.valueOf(position) + " is selected!", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).navigateToReplace(ProfileFragment.newInstance(sellerList.get(position)), null, true);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        // Inflate the layout for this fragment
        return rootView;
    }

    private void getStoreList()
    {

        AppConstant.showProgressDialog(getActivity());
        ApiInterface apiService =  ApiClient.getClient().create(ApiInterface.class);
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("type", AppConstant.TYPE_STORE_LIST);
        Call<SellertsList> call = apiService.getSellerList(mapParam);
        call.enqueue(new Callback<SellertsList>() {
            @Override
            public void onResponse(Call<SellertsList> call, Response<SellertsList> response) {
               // SellertsList storeListResponce = response.body();
                SellertsList storeListResponce = null;
                try {
                     storeListResponce = response.body();
                    Log.d("msg", response.body().getMsg());
                }catch (NullPointerException e)
                {
                    System.out.println("Exception Message on StoreList Page : "+e.getMessage());
                    getStoreList();
                    return;
                }


                if(storeListResponce.getStatus() == 1) {
                    sellerList = storeListResponce.getSellers();
                    Log.d("storeList size",sellerList.size()+"");
                    mStoreListAdapter = new StoreListAdapter(storeListResponce.getSellers(),getActivity());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mStoreListAdapter);
                    AppConstant.dismissProgressDialog();

                } else {
                    Toast.makeText(getActivity(),"fail",Toast.LENGTH_SHORT).show();
                    AppConstant.dismissProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<SellertsList> call, Throwable t) {
                // Log error here since request failed
                Log.e("LoginActivity", t.toString());

            }
        });
    }

}
