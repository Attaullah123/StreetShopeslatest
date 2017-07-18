package Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.streetshopes.LoginActivity;
import com.streetshopes.MainActivity;
import com.streetshopes.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.LoginResponce;
import pojo.Seller;
import pojo.SellerCat;
import pojo.SellerCategorie;
import pojo.SellerCategory;
import pojo.SubCategory;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.AppConstant;
import view.SlidingTabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellerStoreFragment extends Fragment {

    static final String LOG_TAG = "SlidingTabsBasicFragment";
    private ImageView imgBanner;
    Seller seller;
    SellerCat sellerCat;

   // private List<SubCategory> subCategories = new ArrayList<>();
    private ArrayList<SubCategory> subCategoryArrayList = new ArrayList<>();

    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;

    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    private ViewPager mViewPager;

    /**
     * Inflates the {@link View} which will be displayed by this {@link Fragment}, from the app's
     * resources.
     */



    public static SellerStoreFragment newInstance(Seller datum) {
        Bundle args = new Bundle();
        args.putParcelable("datum", datum);
        SellerStoreFragment profileFragment = new SellerStoreFragment();
        profileFragment.setArguments(args);
        return profileFragment;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).mTitle.setText(ProfileFragment.sellersname);

        View view = inflater.inflate(R.layout.fragment_seller_store, container, false);
        seller = (Seller) getArguments().get("datum");
        imgBanner = (ImageView) view.findViewById(R.id.imgBanner);

        processGetCategoryList();

        return view;
    }

    // BEGIN_INCLUDE (fragment_onviewcreated)
    /**
     * This is called after the {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has finished.
     * Here we can pick out the {@link View}s we need to configure from the content view.
     *
     * We set the {@link ViewPager}'s adapter to be an instance of {@link SamplePagerAdapter}. The
     * {@link SlidingTabLayout} is then given the {@link ViewPager} so that it can populate itself.
     *
     * @param view View created in {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setDividerColors(Color.parseColor("#00FFFFFF"));



    }
    // END_INCLUDE (fragment_onviewcreated){"status":1,"currencies":[{"currency_id":"1","name":"Singapore Dollar","iso_code":"SGD,","conversion_rate":"1.418692"},{"currency_id":"2","name":"US Dollar","iso_code":"USD,","conversion_rate":"1.000000"}],"msg":"Get Data Successfully."}

    /**
     * The {@link android.support.v4.view.PagerAdapter} used to display pages in this sample.
     * The individual pages are simple and just display two lines of text. The important section of
     * this class is the {@link #getPageTitle(int)} method which controls what is displayed in the
     * {@link SlidingTabLayout}.
     */
    class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
            return sellerCat.getSellerCategories().size();
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
         * same object as the {@link View} added to the {@link ViewPager}.
         */

        @Override
        public CharSequence getPageTitle(int position) {
            return sellerCat.getSellerCategories().get(position).getMaincatName();
           /* switch (position){
                case 0:
                    return "One Piece";
                case 1:
                    return "One Piece1";
                case 2:
                    return "One Piece2";
                default:
                     return "One Piece3";
            }*/
        }
        // END_INCLUDE (pageradapter_getpagetitle)

        @Override
        public Fragment getItem(int position) {
            //return new StoreCategoryListFragment();
            Log.d("Store : ", "Size : " +subCategoryArrayList.size());
            subCategoryArrayList = (ArrayList<SubCategory>) sellerCat.getSellerCategories().get(position).getSubCategory();
            return StoreCategoryListFragment.newInstance(subCategoryArrayList);


            /*switch (position){
                case 0:
                    return new StoreCategoryListFragment(subCategoryArrayList);
                case 1:
                    return new StoreCategoryListFragment(subCategoryArrayList);
                case 2:
                    return new StoreCategoryListFragment(subCategoryArrayList);
                default:
                    return new StoreCategoryListFragment(subCategoryArrayList);
            }*/

        }



    }

    private void processGetCategoryList() {
        AppConstant.showProgressDialog(getActivity());
        final ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("sellerID", seller.getSellerId());

        Call<SellerCat> call = apiService.getSellerCategoryList(mapParam);
        call.enqueue(new Callback<SellerCat>() {
            @Override
            public void onResponse(Call<SellerCat> call, Response<SellerCat> response) {
                sellerCat = response.body();
                System.out.println("SellerStoreFargment error 1");
                try {
                    Log.d("msg", response.body().getMsg());
                }catch (NullPointerException e){
                    processGetCategoryList();
                    return;
                }

                System.out.println("SellerStoreFargment error 2");
                AppConstant.dismissProgressDialog();
                if(sellerCat.getStatus() == 1) {
                    //subCategoryArrayList = (ArrayList<SubCategory>) sellerCat.getSellerCategories().get(0).getSubCategory();
                   // Toast.makeText(getActivity().getApplicationContext(),"hi",Toast.LENGTH_SHORT).show();


                    Glide.with(getActivity())
                            .load(sellerCat.getSellerbanner())
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)

                            .into(imgBanner);

                    mViewPager.setAdapter(new SamplePagerAdapter(getChildFragmentManager()));
                    mSlidingTabLayout.setViewPager(mViewPager);
                    System.out.println("SellerStoreFargment error3");
                } else {
                    AppConstant.showAlertDialog(getActivity(), response.message());
                }
            }

            @Override
            public void onFailure(Call<SellerCat> call, Throwable t) {
                // Log error here since request failed
              //  Log.e("SignUpActivity", t.toString());
                System.out.println("SellerStoreFargment error 0");
                AppConstant.showAlertDialog(getActivity(), getResources().getString(R.string.netrwork_error));
                AppConstant.dismissProgressDialog();
            }
        });
    }

}

