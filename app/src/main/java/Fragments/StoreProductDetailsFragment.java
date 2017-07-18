package Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.streetshopes.MainActivity;
import com.streetshopes.R;

import java.util.ArrayList;
import java.util.List;

import pojo.SellerCategory;
import pojo.SellerProduct;
import pojo.SubCategory;
import utils.AppConstant;
import view.SlidingTabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreProductDetailsFragment extends Fragment

{

    static final String LOG_TAG = "SlidingTabsBasicFragment";
    private SellerProduct sellerProduct;
    private List<SubCategory> subCategoryArrayList = new ArrayList<>();
    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;

    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    private ViewPager mViewPager;
    public SamplePagerAdapter adapter;

    public static StoreProductDetailsFragment newInstance(SellerProduct sellerProduct) {
        Bundle args = new Bundle();
        args.putParcelable(AppConstant.EXTRA_PRODUCT, sellerProduct);
        StoreProductDetailsFragment storeProductDetailsFragment = new StoreProductDetailsFragment();
        storeProductDetailsFragment.setArguments(args);
        System.out.println("main 2");
        return storeProductDetailsFragment;
    }

    /**
     * Inflates the {@link View} which will be displayed by this {@link Fragment}, from the app's
     * resources.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        ((MainActivity)getActivity()).mTitle.setText("Seller's Store");
        sellerProduct = (SellerProduct) getArguments().getParcelable(AppConstant.EXTRA_PRODUCT);
        System.out.println("main 1");
        return inflater.inflate(R.layout.fragment_store_product_details, container, false);
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
        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        adapter = new SamplePagerAdapter(getChildFragmentManager(),3);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(0);




        // END_INCLUDE (setup_viewpager)

        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setDividerColors(Color.parseColor("#00FFFFFF"));
        mSlidingTabLayout.setViewPager(mViewPager);
        System.out.println("main 3");

        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {
                mViewPager.setCurrentItem(position);
                adapter.notifyDataSetChanged();

                System.out.println("hmmrehan"+position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // END_INCLUDE (setup_slidingtablayout)
    }
    // END_INCLUDE (fragment_onviewcreated)

    /**
     * The {@link PagerAdapter} used to display pages in this sample.
     * The individual pages are simple and just display two lines of text. The important section of
     * this class is the {@link #getPageTitle(int)} method which controls what is displayed in the
     * {@link SlidingTabLayout}.
     */
    class SamplePagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        public SamplePagerAdapter(FragmentManager fm,int numTabs)
        {
            super(fm);
            this.mNumOfTabs = numTabs;
        }

        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
           // System.out.println("hmmxxx");
            return mNumOfTabs;
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
         * same object as the {@link View} added to the {@link ViewPager}.
         */

        @Override
        public CharSequence getPageTitle(int position)
        {
            System.out.println("hmmttttt"+position);
            switch (position){
                case 0:
                    System.out.println("hmm00");
                    return "ITEM DETAILS";
//                case 1:
//                    System.out.println("hmm11");
//                    return "REVIEWS";
//                case 2:
//                    System.out.println("hmm22");
//                    return "RELATED ITEMS";
                default:
                    return null;
            }
        }


        // END_INCLUDE (pageradapter_getpagetitle)

        @Override
        public Fragment getItem(int position)
        {
            System.out.println("hmmtttttx"+position);
            switch (position) {
                case 0:
                    System.out.println("hmm0");
                    return StoreProductItemDetailsFragment.newInstance(sellerProduct);
//                case 1:
//                     System.out.println("hmm1");
//                     return new StoreProductReviewsFragment();
//                case 2:
//                    System.out.println("hmm2");
//                    return new StoreRelatedItemsListFragment();
                default:
                    return null;
            }

        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }
}

