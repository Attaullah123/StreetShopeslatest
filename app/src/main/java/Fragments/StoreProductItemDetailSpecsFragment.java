package Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
import java.util.List;

import adapter.ProductReviewsAdapter;
import adapter.SellerReviewsAdapter;
import pojo.Seller;
import recycleview.ClickListener;
import recycleview.RecyclerTouchListener;

import static Fragments.StoreProductItemDetailsFragment.productid;
import static Fragments.StoreProductItemDetailsFragment.selectedvariations;

import static utils.AppConstant.cart_id;
import static utils.AppConstant.user_id;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreProductItemDetailSpecsFragment extends Fragment {


    private List<String> storeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SellerReviewsAdapter sellerReviewsAdapter;
    LinearLayout lx11;
    LinearLayout lx12;
    ProgressDialog progress;
    View rootView;
    public static Handler handlerm;

    String[] radiotags;

    public static String productname = "";
    public SharedPreferences pref;
    public Resources res;
    public SharedPreferences.Editor ed;
    public static String selectedvariations;

    public StoreProductItemDetailSpecsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ed = pref.edit();
        sethandler();
        rootView = inflater.inflate(R.layout.fragment_product_details, container, false);


        ((MainActivity) getActivity()).mTitle.setText("Product Details");
        lx11 = (LinearLayout) rootView.findViewById(R.id.lx11);
        lx12 = (LinearLayout) rootView.findViewById(R.id.lx112);


        Button btnAddCart = (Button) rootView.findViewById(R.id.btnAddCart);
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RelativeLayout addCartswitch = (RelativeLayout) rootView.findViewById(R.id.addCartswitch);
                //addCartswitch.setVisibility(View.VISIBLE);
                ViewDialog alert = new ViewDialog();
                alert.showDialog(getActivity(), "Error");
                addtocart();
            }
        });

        TextView txtProductDetails = (TextView) rootView.findViewById(R.id.txtProductDetails);
        txtProductDetails.setText(Html.fromHtml(StoreProductItemDetailsFragment.productdesc));

        Button btnCartShow = (Button) rootView.findViewById(R.id.btnShowCart);
        btnCartShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).navigateToReplace(new ShoppingCartFragment(), null, true);
            }
        });

        Button btnAddCartContinue = (Button) rootView.findViewById(R.id.btnAddCartContinue);
        btnAddCartContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout addCartswitch = (RelativeLayout) rootView.findViewById(R.id.addCartswitch);
                addCartswitch.setVisibility(View.GONE);
                ((MainActivity) getActivity()).navigateToReplace(new StoreListFragment(), null, true);
            }
        });

        try {
            JSONObject json1xx = new JSONObject(StoreProductItemDetailsFragment.productfeatures.getString(0));
            System.out.println("StoreProductItemDetailSpecsFragment" + " " + json1xx.getString("featuress").toString());
            String[] first = json1xx.getString("featuress").toString().split("---");
            System.out.println("StoreProductItemDetailSpecsFragment : " + " " + first.length);


            for (int i = 0; i <= first.length - 1; i++) {
                String[] Second = first[i].split("--");

                LinearLayout LL = new LinearLayout(this.getContext());
                LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LLParams.topMargin = 10;
                LL.setPadding(10, 10, 10, 10);
                LL.setOrientation(LinearLayout.HORIZONTAL);
                LL.setWeightSum(1);
                LL.setLayoutParams(LLParams);

                TextView tx1 = new TextView(this.getContext());
                tx1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));
                tx1.setTextSize(12);
                tx1.setTextSize(12);
                tx1.setTypeface(Typeface.DEFAULT_BOLD);
                tx1.setTextColor(getResources().getColor(R.color.colorBlack));
                tx1.setText(Second[0]);
                LL.addView(tx1);

                TextView tx2 = new TextView(this.getContext());
                tx2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));
                tx2.setTextSize(12);
                tx2.setTextColor(getResources().getColor(R.color.colorBlack));
                tx2.setText(Second[1]);
                LL.addView(tx2);

                lx11.addView(LL);
                System.out.println("StoreProductItemDetailSpecsFragment : " + " " + Second[0] + " " + Second[1]);
            }

        } catch (Exception e) {
            System.out.println("StoreProductItemDetailSpecsFragmen" + e.getMessage().toString());


        }


        try {
            JSONObject json1xx = new JSONObject(StoreProductItemDetailsFragment.productvariations.getString(0));
            System.out.println("StoreProductItemDetailSpecsFragmentVariation" + " " + json1xx.getString("variations").toString());
            String[] first = json1xx.getString("variations").toString().split("---");
            System.out.println("StoreProductItemDetailSpecsFragmentVariation: " + " " + first.length);
            radiotags = new String[first.length];

            StoreProductItemDetailsFragment.totalVariiations = first.length;

            LinearLayout LL = new LinearLayout(this.getContext());
            LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            TextView tx5 = new TextView(this.getContext());
            tx5.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            tx5.setPadding(10, 10, 10, 10);
            tx5.setGravity(Gravity.RIGHT);
            tx5.setTypeface(null, Typeface.BOLD);
            tx5.setTextSize(16);
            tx5.setTextColor(getResources().getColor(R.color.colorBlack));
            tx5.setText("Price Impact");
            LL.addView(tx5);

            lx12.addView(LL);

            for (int i = 0; i <= first.length - 1; i++) {
                String[] Second = first[i].split("--");

                System.out.println("StoreProductItemDetailSpecsFragmentVariation: 1");

                LL = new LinearLayout(this.getContext());
                LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                // LLParams.topMargin=10;
                LL.setPadding(10, 10, 10, 10);
                LL.setOrientation(LinearLayout.HORIZONTAL);
                LL.setWeightSum(1);
                LL.setGravity(Gravity.CENTER_VERTICAL);
                LL.setLayoutParams(LLParams);

                System.out.println("StoreProductItemDetailSpecsFragmentVariation: 2");

                RadioButton tx1 = new RadioButton(this.getContext());
                tx1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.15f));
                tx1.setTextSize(12);
                tx1.setTag(Second[0]);
                radiotags[i] = Second[0];

                View.OnClickListener radio_listener = new View.OnClickListener() {
                    public void onClick(View v) {
                        String x = v.getTag().toString();
                        selectedvariations = x;
                        System.out.println("CapCam" + selectedvariations);
                        for (int i = 0; i <= StoreProductItemDetailsFragment.totalVariiations - 1; i++) {
                            RadioButton txc = (RadioButton) rootView.findViewWithTag(radiotags[i]);
                            if (!radiotags[i].equals(x)) {
                                txc.setChecked(false);
                            }
                        }
                    }
                };
                tx1.setOnClickListener(radio_listener);
                //tx1.setTextSize(14);
                //tx1.setTypeface(Typeface.DEFAULT_BOLD);
                //tx1.setTextColor(getResources().getColor(R.color.colorGrayText));
                //tx1.setText(Second[0]);
                LL.addView(tx1);

                System.out.println("StoreProductItemDetailSpecsFragmentVariation: 3");

                ImageView tx3 = new ImageView(this.getContext());
                // tx3.setLayoutParams(new LinearLayout.LayoutParams(0,100, 0.35f));
                DisplayMetrics displaymetrics = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
                int pxx = Math.round(20 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
                int stageWidth = displaymetrics.widthPixels;
                int stageHeight = displaymetrics.heightPixels;
                System.out.println("coooool" + ((35 * stageWidth) / 100) + " pxx" + pxx);
                tx3.setLayoutParams(new LinearLayout.LayoutParams(0, ((35 * stageWidth) / 100) - pxx, 0.35f));
                Glide.with(StoreProductItemDetailSpecsFragment.this)
                        .load(Second[3])
                        .diskCacheStrategy(DiskCacheStrategy.NONE)

                        .skipMemoryCache(true)
                        .into(tx3);

                // tx3.setTextSize(14);
                // tx3.setTextColor(getResources().getColor(R.color.colorGrayText));
                // tx3.setText(Second[1]);
                LL.addView(tx3);

                System.out.println("StoreProductItemDetailSpecsFragmentVariation: 3");

                TextView tx2 = new TextView(this.getContext());
                tx2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.35f));
                tx2.setTextSize(14);
                tx2.setTextColor(getResources().getColor(R.color.colorBlack));
                tx2.setText(Second[2]);
                tx2.setPadding(10, 0, 10, 10);
                LL.addView(tx2);

                TextView tx4 = new TextView(this.getContext());
                tx4.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.15f));
                tx4.setTextSize(14);
                tx4.setTextColor(getResources().getColor(R.color.colorBlack));
                tx4.setText("$" + Second[1]);
                LL.addView(tx4);

                //LL.addView(tx5);

                lx12.addView(LL);
                System.out.println("StoreProductItemDetailSpecsFragmentVariation : " + " " + Second[0] + " " + Second[1]);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }

        /*
        TextView user_profile_companyName =  (TextView)rootView .findViewById(R.id.user_profile_companyName);
        user_profile_companyName.setText(ProfileFragment.sellersname);

        TextView user_profile_nameFull    =  (TextView)rootView .findViewById(R.id.user_profile_nameFull);
        user_profile_nameFull.setText(ProfileFragment.sellerownername);

        TextView user_profile_addressFull =  (TextView)rootView .findViewById(R.id.user_profile_addressFull);
        user_profile_addressFull.setText(ProfileFragment.selleraddress);

        TextView user_profile_countryName =  (TextView)rootView .findViewById(R.id.user_profile_countryName);
        user_profile_countryName.setText(ProfileFragment.sellerscountry);

        TextView user_profile_phoneNumber =  (TextView)rootView .findViewById(R.id.user_profile_phoneNumber);
        user_profile_phoneNumber.setText(ProfileFragment.sellerphone);


        TextView user_profile_totalSaleValue    =  (TextView)rootView .findViewById(R.id.user_profile_totalSaleValue);
        user_profile_totalSaleValue.setText(ProfileFragment.TotalSale);

        TextView user_profile_totalEarningValue =  (TextView)rootView .findViewById(R.id.user_profile_totalEarningValue);
        user_profile_totalEarningValue.setText(ProfileFragment.TotalEarning);

        TextView user_profile_totalOrdersValue =  (TextView)rootView .findViewById(R.id.user_profile_totalOrdersValue);
        user_profile_totalOrdersValue.setText(ProfileFragment.TotalOrders);

        TextView user_profile_totalValue =  (TextView)rootView .findViewById(R.id.user_profile_totalValue);
        user_profile_totalValue.setText(ProfileFragment.Total);

        ImageView user_iamge            =  (ImageView)rootView .findViewById(R.id.sellerlogo);
        Glide.with(StoreProductItemDetailSpecsFragment.this)
                .load(ProfileFragment.sellerslogourl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.loading)
                .skipMemoryCache(true)
                .into(user_iamge);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        //storeList.add(1,"you are good");
        sellerReviewsAdapter = new SellerReviewsAdapter(getActivity(), storeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(sellerReviewsAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
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


    public void addtocart() {
        progress = new ProgressDialog(rootView.getContext());
        progress.setMessage("Adding Product to cart");
        progress.show();
        Seller seller;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://streetshops.sg/dashboard/appapi/addtocart.php?cartid=" + cart_id +
                            "&userid=" + user_id + "&productid=" + productid + "&variationid=" + selectedvariations + "&quantity=1";
                    StringBuilder body = new StringBuilder();
                    DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                    HttpPost httpGet = new HttpPost(url); // create new httpGet object
                    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");

                    System.out.println("Cool0 Product id" + productid);

                 /*   httpGet.addHeader("userid", user_id);
                    httpGet.addHeader("cartid", cart_id);
                    httpGet.addHeader("variationid", selectedvariations);
                    httpGet.addHeader("quantity", "1");
                    httpGet.addHeader("productid", productid );*/

                    // System.out.println("productID"+sellerProduct.getProductid());
                    // httpGet.addHeader("sellerID", seller.getSellerId());

                    System.out.println("Cool1 selectedvariations=" + selectedvariations + " cartid=" + cart_id + " userid=" + user_id
                            + " quantity=1" + " productid=" + productid);

                    ArrayList KlientNameValuePairs = new ArrayList<NameValuePair>();

                  /*  KlientNameValuePairs.add(new BasicNameValuePair("userid", user_id));
                    KlientNameValuePairs.add(new BasicNameValuePair("cartid", cart_id));
                    KlientNameValuePairs.add(new BasicNameValuePair("variationid", selectedvariations));
                    KlientNameValuePairs.add(new BasicNameValuePair("quantity", "1"));
                    KlientNameValuePairs.add(new BasicNameValuePair("productid", productid ));*/

                    // KlientNameValuePairs.add(new BasicNameValuePair("sellerID",seller.getSellerId()));

                    System.out.println("Cool2 Product id" + productid);

                    httpGet.setEntity(new UrlEncodedFormEntity(KlientNameValuePairs));


                    try {
                        HttpResponse response = httpclient.execute(httpGet); // execute httpGet
                        StatusLine statusLine = response.getStatusLine();
                        int statusCode = statusLine.getStatusCode();
                        System.out.println("Cool3 Product id" + productid);
                        if (statusCode == HttpStatus.SC_OK) {
                            // System.out.println(statusLine);
                            body.append(statusLine);
                            HttpEntity e = response.getEntity();
                            String entity = EntityUtils.toString(e);
                            body.append(entity);
                            System.out.println("Cool4 Product id" + productid);
                        } else {
                            body.append(statusLine);
                            System.out.println("Cool5 Product id" + productid);
                            // System.out.println(statusLine);
                        }
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        //	httpGet.; // stop connection
                    }

                    System.out.println("Cool6 Product id" + productid);
                    System.out.println(body.toString() + "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");

                    Bundle b = new Bundle();

                    b.putInt("key", 199);
                    b.putString("pm", body.toString().replace("HTTP/1.1 200 OK", ""));

                    Message gn = new Message();
                    gn.setData(b);

                    handlerm.sendMessage(gn);
                    ; // return the String
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }


    public void sethandler() {

        handlerm = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // System.out.println(msg.getData().toString());

                Bundle b = msg.getData();

                if (b.isEmpty()) {
                    System.out.println("Empty");
                } else {
                    int keyy = b.getInt("key");
                    String px = b.getString("pm");

                    //hideDialog();

                    if (keyy == 199) {
                        System.out.println("Cool" + px);
                        progress.dismiss();
                        //cart_id  =
                        try {
                            JSONObject json = new JSONObject(px);
                            String responsee = json.getString("status");
                            if (responsee.equals("1")) {
                                cart_id = json.getString("cart_id");

                                ed.putString("cart_id", cart_id);
                                ed.commit();

                                RelativeLayout addCartswitch = (RelativeLayout) rootView.findViewById(R.id.addCartswitch);
                                addCartswitch.setVisibility(View.VISIBLE);


                            } else {
                                Toast.makeText(getActivity(), "Failed to add product to cart Please contact admin.", Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {

                        }
                    }


                }

            }
        };

        ;
    }

    @Override
    public void onPause() {
        super.onPause();
        sethandler();

    }

    public class ViewDialog {

        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.choose_variations);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;

            dialog.getWindow().setAttributes(lp);

            TextView text = (TextView) dialog.findViewById(R.id.prdnamee);
            text.setText(productname);

            LinearLayout lx12 = (LinearLayout) dialog.findViewById(R.id.lx112);

            final String[] radiotags;

            /*Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });*/


            try {
                JSONObject json1xx = new JSONObject(StoreProductItemDetailsFragment.productvariations.getString(0));
                System.out.println("StoreProductItemDetailSpecsFragmentVariation" + " " + json1xx.getString("variations").toString());
                String[] first = json1xx.getString("variations").toString().split("---");
                System.out.println("StoreProductItemDetailSpecsFragmentVariation: " + " " + first.length);
                radiotags = new String[first.length];

                StoreProductItemDetailsFragment.totalVariiations = first.length;

                LinearLayout LL = new LinearLayout(dialog.getContext());
                LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


                TextView tx5 = new TextView(dialog.getContext());
                tx5.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
                tx5.setPadding(10, 10, 10, 10);
                tx5.setGravity(Gravity.RIGHT);
                tx5.setTypeface(null, Typeface.BOLD);
                tx5.setTextSize(16);
                tx5.setTextColor(getResources().getColor(R.color.colorBlack));
                tx5.setText("Price Impact");
                LL.addView(tx5);

                lx12.addView(LL);

                for (int i = 0; i <= first.length - 1; i++) {
                    String[] Second = first[i].split("--");

                    System.out.println("StoreProductItemDetailSpecsFragmentVariation: 1");

                    LL = new LinearLayout(dialog.getContext());
                    LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    // LLParams.topMargin=10;
                    LL.setPadding(10, 10, 10, 10);
                    LL.setOrientation(LinearLayout.HORIZONTAL);
                    LL.setWeightSum(1);
                    LL.setGravity(Gravity.CENTER_VERTICAL);
                    LL.setLayoutParams(LLParams);

                    System.out.println("StoreProductItemDetailSpecsFragmentVariation: 2");

                    RadioButton tx1 = new RadioButton(dialog.getContext());
                    tx1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.15f));
                    tx1.setTextSize(12);
                    tx1.setTag(Second[0]);
                    radiotags[i] = Second[0];
                    final View vc = tx1.getRootView();

                    View.OnClickListener radio_listener = new View.OnClickListener() {
                        public void onClick(View v) {
                            String x = v.getTag().toString();
                            selectedvariations = x;
                            System.out.println("CapCam" + selectedvariations);
                            for (int i = 0; i <= StoreProductItemDetailsFragment.totalVariiations - 1; i++) {
                                RadioButton txc = (RadioButton) vc.findViewWithTag(radiotags[i]);
                                if (!radiotags[i].equals(x)) {
                                    txc.setChecked(false);
                                }
                            }
                        }
                    };
                    tx1.setOnClickListener(radio_listener);
                    //tx1.setTextSize(14);
                    //tx1.setTypeface(Typeface.DEFAULT_BOLD);
                    //tx1.setTextColor(getResources().getColor(R.color.colorGrayText));
                    //tx1.setText(Second[0]);
                    LL.addView(tx1);

                    System.out.println("StoreProductItemDetailSpecsFragmentVariation: 3");

                    ImageView tx3 = new ImageView(dialog.getContext());
                    // tx3.setLayoutParams(new LinearLayout.LayoutParams(0,100, 0.35f));
                    DisplayMetrics displaymetrics = new DisplayMetrics();
                    getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                    DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
                    int pxx = Math.round(20 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
                    int stageWidth = displaymetrics.widthPixels;
                    int stageHeight = displaymetrics.heightPixels;
                    System.out.println("coooool" + ((35 * stageWidth) / 100) + " pxx" + pxx);
                    tx3.setLayoutParams(new LinearLayout.LayoutParams(0, ((35 * stageWidth) / 100) - pxx, 0.35f));
                    Glide.with(dialog.getContext())
                            .load(Second[3])
                            .diskCacheStrategy(DiskCacheStrategy.NONE)

                            .skipMemoryCache(true)
                            .into(tx3);

                    // tx3.setTextSize(14);
                    // tx3.setTextColor(getResources().getColor(R.color.colorGrayText));
                    // tx3.setText(Second[1]);
                    // LL.addView(tx3);

                    System.out.println("StoreProductItemDetailSpecsFragmentVariation: 3");

                    TextView tx2 = new TextView(dialog.getContext());
                    tx2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f));
                    tx2.setTextSize(14);
                    tx2.setTextColor(getResources().getColor(R.color.colorBlack));
                    tx2.setText(Second[2]);
                    tx2.setPadding(10, 0, 10, 10);
                    LL.addView(tx2);

                    TextView tx4 = new TextView(dialog.getContext());
                    tx4.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.15f));
                    tx4.setTextSize(14);
                    tx4.setTextColor(getResources().getColor(R.color.colorBlack));
                    tx4.setText("$" + Second[1]);
                    LL.addView(tx4);

                    //LL.addView(tx5);

                    lx12.addView(LL);
                    System.out.println("StoreProductItemDetailSpecsFragmentVariation : " + " " + Second[0] + " " + Second[1]);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage().toString());
            }


            dialog.show();

        }

    }
}
