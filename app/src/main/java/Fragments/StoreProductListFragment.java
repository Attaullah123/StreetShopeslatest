package Fragments;


import android.os.Bundle;
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

import com.streetshopes.MainActivity;
import com.streetshopes.R;

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
import pojo.SellerCategory;
import pojo.SubCategory;
import recycleview.ClickListener;
import recycleview.RecyclerTouchListener;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.AppConstant;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreProductListFragment extends Fragment {


    @BindView(R.id.imgList)
    ImageView imgList;
    @BindView(R.id.imgGried)
    ImageView imgGried;
    @BindView(R.id.imgPriceFilter)
    ImageView imgPriceFilter;
    @BindView(R.id.subCategoryname)
    TextView subCategoryname;
    @BindView(R.id.txtPriceFilterSelectedValue)
    TextView txtPriceFilterSelectedValue;
    private List<SellerProduct> sellerProducts = new ArrayList<>();
    private RecyclerView recyclerView;
    private StoreProductGridAdapter storeProductGridAdapter;
    private StoreProductListAdapter storeProductListAdapter;
    private Unbinder unbinder;
    private SellerCategory sellerCategory;
    private SubCategory subCategory;

    public StoreProductListFragment() {
        // Required empty public constructor
    }

    public static StoreProductListFragment newInstance(SubCategory datum, String search) {
        Bundle args = new Bundle();
        args.putParcelable(AppConstant.EXTRA_SUB_CATEGORY, datum);
        args.putString(AppConstant.EXTRA_SEARCH, search);
        StoreProductListFragment storeProductListFragment = new StoreProductListFragment();
        storeProductListFragment.setArguments(args);
        return storeProductListFragment;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_product_grid, container, false);

       unbinder = ButterKnife.bind(this, rootView);

        ((MainActivity) getActivity()).mTitle.setText("Seller's Store");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        storeProductListAdapter = new StoreProductListAdapter(sellerProducts,getActivity().getApplicationContext());

        //storeProductGridAdapter = new StoreProductGridAdapter(sellerProducts, getActivity().getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(storeProductListAdapter);

        Bundle bundle = getArguments();
        if(bundle.getString(AppConstant.EXTRA_SEARCH) != null) {
            getSellerSearchProductList(bundle.getString(AppConstant.EXTRA_SEARCH));
        } else {
            subCategory = (SubCategory) bundle.getParcelable(AppConstant.EXTRA_SUB_CATEGORY);
            getSellerProductList();
        }

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                //Toast.makeText(getActivity(), String.valueOf(position) + " is selected!", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).navigateToReplace(StoreProductDetailsFragment.newInstance(sellerProducts.get(position)), null, true);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

            // Inflate the layout for this fragment
        //subCatname.setText(subCategory.getSubcatname());


        return rootView;

    }

    @OnClick({R.id.imgList, R.id.imgGried, R.id.imgPriceFilter})
    public void onClick(View view) {
        switch (view.getId()) {
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
            case R.id.imgPriceFilter:
               // AppConstant.hideKeyboard(SignUp1Activity.this);
                AppConstant.showListPopup(getActivity(), view, AppConstant.priceFilter, txtPriceFilterSelectedValue);
                getSellerProductList();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void getSellerProductList()
    {
        subCategoryname.setText(subCategory.getSubcatname());
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("categoryID", subCategory.getSubcatid());
        String price = "1";
        if(txtPriceFilterSelectedValue.getText().toString().trim().equals("Price: High to Low")){
            price = "1";
        }else {
            price = "0";
        }
        mapParam.put("price", price);

        Call<ProductList> call = apiService.getSellerProductList(mapParam);
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                ProductList productList = response.body();
                //Toast.makeText(getActivity(),productList.getMsg(),Toast.LENGTH_SHORT).show();
                if(productList.getStatus() == 1) {
                    sellerProducts.clear();
                    sellerProducts = productList.getSellerProducts();
                    Log.d("storeList size",sellerProducts.size()+"");
                   // storeProductGridAdapter = new StoreProductGridAdapter(sellerProducts,getActivity().getApplicationContext());
                    storeProductListAdapter = new StoreProductListAdapter(sellerProducts,getActivity().getApplicationContext());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(storeProductListAdapter);

                } else {
                    Toast.makeText(getActivity(),"No Product to display",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                // Log error here since request failed
                Log.e("LoginActivity", t.toString());

            }
        });
    }

    private void getSellerSearchProductList(String search) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("keyword", search);

        Call<ProductList> call = apiService.getSellerSearchProductList(mapParam);
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                ProductList productList = response.body();
                //Toast.makeText(getActivity(),productList.getMsg(),Toast.LENGTH_SHORT).show();
                if(productList.getStatus() == 1) {
                    sellerProducts = productList.getSellerProducts();
                    Log.d("storeList size",sellerProducts.size()+"");
                    storeProductGridAdapter = new StoreProductGridAdapter(sellerProducts,getActivity().getApplicationContext());
                    storeProductListAdapter = new StoreProductListAdapter(sellerProducts,getActivity().getApplicationContext());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(storeProductListAdapter);

                } else {
                    Toast.makeText(getActivity(),"fail",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                // Log error here since request failed
                Log.e("LoginActivity", t.toString());

            }
        });
    }
}
