package Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.streetshopes.MainActivity;
import com.streetshopes.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.StoreProductGridAdapter;
import adapter.StoreProductListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pojo.ProductList;
import pojo.SellerProduct;
import recycleview.ClickListener;
import recycleview.RecyclerTouchListener;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static utils.AppConstant.user_id;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreRelatedItemsListFragment extends Fragment {


    @BindView(R.id.imgList)
    ImageView imgList;
    @BindView(R.id.imgGried)
    ImageView imgGried;
    private List<SellerProduct> sellerProducts = new ArrayList<>();
    private RecyclerView recyclerView;
    private StoreProductGridAdapter storeProductGridAdapter;
    private StoreProductListAdapter storeProductListAdapter;
    private Unbinder unbinder;
    public static Handler handlerm;

    public StoreRelatedItemsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_related_item, container, false);

       unbinder = ButterKnife.bind(this, rootView);



        ((MainActivity) getActivity()).mTitle.setText("RELATED ITEMS");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        storeProductGridAdapter = new StoreProductGridAdapter(sellerProducts, getActivity().getApplicationContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(storeProductListAdapter);
        System.out.println("StoreRelatedItemsListFragment 1");
        getSellerProductList();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position)
            {
                //Toast.makeText(getActivity(), String.valueOf(position) + " is selected!", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).navigateToReplace(StoreProductDetailsFragment.newInstance(sellerProducts.get(position)), null, true);
            }

            @Override
            public void onLongClick(View view, int position)
            {

            }
        }));

        // Inflate the layout for this fragment

        return rootView;
    }

    @OnClick({R.id.imgList, R.id.imgGried})
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.imgList:
                storeProductListAdapter = new StoreProductListAdapter(sellerProducts, getActivity().getApplicationContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(storeProductListAdapter);

                break;
            case R.id.imgGried:
                //storeProductListAdapter = new StoreProductListAdapter(sellerProducts);
                RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getActivity(), 2);
                recyclerView.setLayoutManager(mLayoutManager1);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(storeProductGridAdapter);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void getSellerProductList() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("productID", StoreProductItemDetailsFragment.productid);
        mapParam.put("price", "1");

        Call<ProductList> call = apiService.getSellerRelatedProductList(mapParam);
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                ProductList productList = response.body();
               // Toast.makeText(getActivity(),productList.getMsg(),Toast.LENGTH_SHORT).show();
                if(productList.getStatus() == 1) {
                   try
                   {
                       sellerProducts.clear();
                       sellerProducts = productList.getSellerProducts();
                       Log.d("storeList size", sellerProducts.size() + "");
                       System.out.println("StoreRelatedItemsListFragment 1" + " " + "storeList size " + sellerProducts.size() + "");
//                    storeProductGridAdapter = new StoreProductGridAdapter(sellerProducts,getActivity().getApplicationContext());
                       storeProductListAdapter = new StoreProductListAdapter(sellerProducts, getActivity().getApplicationContext());
                       RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                       recyclerView.setLayoutManager(mLayoutManager);
                       recyclerView.setItemAnimator(new DefaultItemAnimator());
                       recyclerView.setAdapter(storeProductListAdapter);
                   }
                   catch(Exception e)
                    {

                    }

                } else {
                    Toast.makeText(getActivity(),"fail",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t)
            {
                // Log error here since request failed
                Log.e("LoginActivity", t.toString());

            }
        });
    }
}
