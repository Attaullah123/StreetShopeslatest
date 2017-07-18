package Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
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
import android.util.Log;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapter.ProductReviewsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pojo.ProductDetails;
import pojo.Productspec;
import pojo.Seller;
import pojo.SellerProduct;
import recycleview.ClickListener;
import recycleview.RecyclerTouchListener;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.AppConstant;

import static utils.AppConstant.cart_id;
import static utils.AppConstant.user_id;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreProductItemDetailsFragment extends Fragment {


    @BindView(R.id.imgProductDetailsLarge)
    ImageView imgProductDetailsLarge;
    @BindView(R.id.imgProductImage1)
    ImageView imgProductImage1;
    @BindView(R.id.imgProductImage2)
    ImageView imgProductImage2;
    @BindView(R.id.imgProductImage3)
    ImageView imgProductImage3;
    @BindView(R.id.txtProductName)
    TextView txtProductName;
    @BindView(R.id.txtProductDetails)
    TextView txtProductDetails;

    ProductDetails productDetails;
    //Productspec productspec;

    @BindView(R.id.txtManufacterValue)
    TextView txtManufacterValue;
    @BindView(R.id.txtWeightValue)
    TextView txtWeightValue;
    @BindView(R.id.txtSpecificationValue)
    TextView txtSpecificationValue;
    @BindView(R.id.txtAvailabilityValue)
    TextView txtAvailabilityValue;
    @BindView(R.id.txtProductCodeValue)
    TextView txtProductCodeValue;
    @BindView(R.id.txtSpecification2Value)
    TextView txtSpecification2Value;
    @BindView(R.id.txtProductFeatures)
    TextView txtProductFeatures;
    private SellerProduct sellerProduct;

    Seller seller;

    ProgressDialog progress ;

    View rootView;
    public static Handler handlerm;

    public static String sellerid           =  "";
    public static String productid          =  "";
    public static String productname        =  "";
    public static String productprice       =  "";
    public static String productdesc        =  "";
    public static String productmanufacture =  "";
    public static String productavilable    =  "";
    public static String productWeight      =  "";
    public static String productproductCode      =  "";
    public static String productspeclist       =  "";
    public static JSONArray Reviews            ;
    public static JSONArray images          ;
    public static JSONArray productspec ;
    public static JSONArray productfeatures          ;
    public static JSONArray productvariations          ;
    public static int totalVariiations          ;
    public static String selectedvariations          ;
    LinearLayout lx12;
    String[] radiotags;

    public SharedPreferences pref;
    public Resources res;
    public   SharedPreferences.Editor ed;

    public StoreProductItemDetailsFragment() {
        // Required empty public constructor
    }
    public static StoreProductItemDetailsFragment newInstance(SellerProduct sellerProduct) {
        Bundle args = new Bundle();
        args.putParcelable(AppConstant.EXTRA_PRODUCT, sellerProduct);
        StoreProductItemDetailsFragment storeProductItemDetailsFragment = new StoreProductItemDetailsFragment();
        storeProductItemDetailsFragment.setArguments(args);
        return storeProductItemDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        sethandler();
        sellerid             =  "";
        productid            =  "";
        productname          =  "";
        productprice         =  "0.0";
        productdesc          =  "";
        productmanufacture   =  "";
        productavilable      =  "1";
        productWeight        =  "0.0";
        productproductCode   =  "";
        productspeclist      =  "";
        Reviews              = null;
        images               = null;
        productspec          = null;
        productfeatures      = null;
        productvariations    = null;
        totalVariiations     = 0;
        selectedvariations   = "";

        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ed = pref.edit();

        System.out.println("Coool i am here ");
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_store_product_item_details, container, false);

        ((MainActivity)getActivity()).mTitle.setText("ITEM DETAILS");

        ButterKnife.bind(this, rootView);
        sellerProduct = (SellerProduct) getArguments().getParcelable(AppConstant.EXTRA_PRODUCT);

        RelativeLayout btnProductDetail1 = (RelativeLayout) rootView.findViewById(R.id.imgProductDescriptionImage1);
        btnProductDetail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).navigateToReplace(new StoreProductItemDetailSpecsFragment(), null, true);
            }
        });

        ImageView btnProductDetail = (ImageView) rootView.findViewById(R.id.imgProductDescriptionImage);
        btnProductDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).navigateToReplace(new StoreProductItemDetailSpecsFragment(), null, true);
            }
        });

//        RelativeLayout btnProductvari1 = (RelativeLayout) rootView.findViewById(R.id.btnVariations1);
//        btnProductvari1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ViewDialog alert = new ViewDialog();
//                alert.showDialog(getActivity(), "Error");
//            }
//        });
//
//        ImageView btnVariations = (ImageView) rootView.findViewById(R.id.btnVariations);
//        btnVariations.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ViewDialog alert = new ViewDialog();
//                alert.showDialog(getActivity(), "Error");
//            }
//        });

//        RelativeLayout btnAddCart1 = (RelativeLayout) rootView.findViewById(R.id.btnAddCart11);
//        btnAddCart1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addtocart();
//            }
//        });
//
//        ImageView btnAddCart = (ImageView) rootView.findViewById(R.id.btnAddCart);
//        btnAddCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addtocart();
//            }
//        });

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

        getProductDetails();

       // getSellerDetails();

        return rootView;
    }

    public void getProductDetails()
    {
        progress = new ProgressDialog(rootView.getContext());
        progress.setMessage("Loading Store Details");
        progress.show();
        Seller seller;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://streetshops.sg/dashboard/appapi/getprductdetail.php";
                    StringBuilder body = new StringBuilder();
                    DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                    HttpPost httpGet = new HttpPost(url); // create new httpGet object
                    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    httpGet.addHeader("productID", sellerProduct.getProductid());
                    System.out.println("productID"+sellerProduct.getProductid());
                   // httpGet.addHeader("sellerID", seller.getSellerId());

                    ArrayList KlientNameValuePairs = new ArrayList<NameValuePair>();
                    KlientNameValuePairs.add(new BasicNameValuePair("productID",sellerProduct.getProductid()));
                   // KlientNameValuePairs.add(new BasicNameValuePair("sellerID",seller.getSellerId()));


                    httpGet.setEntity(new UrlEncodedFormEntity(KlientNameValuePairs));


                    try {
                        HttpResponse response = httpclient.execute(httpGet); // execute httpGet
                        StatusLine statusLine = response.getStatusLine();
                        int statusCode = statusLine.getStatusCode();
                        if (statusCode == HttpStatus.SC_OK) {
                            // System.out.println(statusLine);
                            body.append(statusLine);
                            HttpEntity e = response.getEntity();
                            String entity = EntityUtils.toString(e);
                            body.append(entity);
                        } else {
                            body.append(statusLine);
                            // System.out.println(statusLine);
                        }
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        //	httpGet.; // stop connection
                    }

                    System.out.println(body.toString() + "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");

                    Bundle b = new Bundle();

                    b.putInt("key", 90);
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

    public  void sethandler()
    {

        handlerm = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // System.out.println(msg.getData().toString());

                Bundle b = msg.getData();

                if (b.isEmpty())
                {
                    System.out.println("Empty");
                }
                else
                {
                    int keyy = b.getInt("key");
                    String px = b.getString("pm");

                    //hideDialog();

                    if (keyy == 199)
                    {
                        System.out.println("Cool" + px);
                        progress.dismiss();
                        //cart_id  =
                        try
                        {
                            JSONObject json = new JSONObject(px);
                            String responsee = json.getString("status");
                            if(responsee.equals("1"))
                            {
                                cart_id = json.getString("cart_id");

                                ed.putString("cart_id", cart_id);
                                ed.commit();

                                RelativeLayout addCartswitch = (RelativeLayout) rootView.findViewById(R.id.addCartswitch);
                                addCartswitch.setVisibility(View.VISIBLE);


                            }
                            else
                            {
                                Toast.makeText(getActivity(), "Failed to add product to cart Please contact admin.", Toast.LENGTH_SHORT).show();

                            }
                        }
                        catch(Exception e)
                        {

                        }
                    }

                    if (keyy == 90)
                    {
                        System.out.println("Cool"+px);
                        progress.dismiss();

                        try
                        {
                            JSONObject json = new JSONObject(px);
                            String responsee = json.getString("status");

                            if(responsee.equals("1"))
                            {

                                JSONArray venues = json.getJSONArray("productDetails");
                                JSONObject json1  = new JSONObject(venues.getString(0));
                                System.out.println("Rehan" + json1.getString("seller_id"));

                                sellerid             =  json1.getString("seller_id");
                                productid            =  json1.getString("product_id");
                                productname          =  json1.getString("productname");
                                productprice         =  json1.getString("productprice");
                                productdesc          =  json1.getString("productdesc");

                                lx12 =(LinearLayout)rootView.findViewById(R.id.lx112);

                                System.out.println("sellerid  : "+  productid);

                                TextView productName = (TextView)rootView.findViewById(R.id.txtProductName);
                                productName.setText(productname);

                                TextView productPrice = (TextView)rootView.findViewById(R.id.txtProductPrice);
                                productPrice.setText("$"+productprice);

                                ((MainActivity)getActivity()).mTitle.setText("ITEM DETAILS");


                                try
                                {

                                    productspec = json.getJSONArray("productspecs");
                                    JSONObject jsonx     = new JSONObject(productspec.getString(0));
                                    productmanufacture   = jsonx.getString("productmanufacture");
                                    productavilable      = jsonx.getString("productavilable");
                                    productWeight        = jsonx.getString("productWeight");
                                    productproductCode   = jsonx.getString("productproductCode");
                                    productspeclist      = jsonx.getString("productspeclist");
                                }
                                catch(Exception e)
                                {
                                    System.out.println("Stage 1");
                                }



                                images               =  json.getJSONArray("productImages");

                                System.out.println(images.getString(0));

                                try {


                                    try
                                    {
                                        JSONObject jsonx = new JSONObject(images.getString(0));
                                        Glide.with(StoreProductItemDetailsFragment.this)
                                                .load(jsonx.getString("imageurl"))
                                                .into(imgProductImage1);
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println("Stage 11");
                                        imgProductImage1.setVisibility(View.GONE);
                                    }

                                    try {
                                        JSONObject jsonx = new JSONObject(images.getString(1));
                                        Glide.with(StoreProductItemDetailsFragment.this)
                                                .load(jsonx.getString("imageurl"))
                                                .into(imgProductImage2);
                                    } catch (Exception e) {
                                        imgProductImage2.setVisibility(View.GONE);
                                    }

                                    try {
                                        JSONObject jsonx = new JSONObject(images.getString(2));
                                        Glide.with(StoreProductItemDetailsFragment.this)
                                                .load(jsonx.getString("imageurl"))
                                                .into(imgProductImage3);
                                    } catch (Exception e) {
                                        imgProductImage3.setVisibility(View.GONE);
                                    }

                                    try {
                                        JSONObject jsonx = new JSONObject(images.getString(0));
                                        Glide.with(StoreProductItemDetailsFragment.this)
                                                .load(jsonx.getString("imageurl"))
                                                .into(imgProductDetailsLarge);
                                    } catch (Exception e) {
                                        imgProductDetailsLarge.setVisibility(View.GONE);
                                    }

                                }
                                catch (Exception e)
                                {
                                    System.out.println("Stage 2");
                                }

                                try
                                {
                                    StoreProductReviewsFragment.ReviewList.clear();
                                    Reviews              = json.getJSONArray("productReviews");
                                    String review = "";
                                    for(int i=0;i<=ProfileFragment.Reviews.length()-1;i++)
                                    {
                                        JSONObject json1xx  = new JSONObject(Reviews.getString(i));
                                        System.out.println("Rehan is cool"+i + json1xx.getString("name"));
                                        review ="";
                                        review=review+json1xx.getString("name")+"~~~";
                                        review=review+json1xx.getString("description")+"~~~";
                                        review=review+json1xx.getString("rate");

                                        StoreProductReviewsFragment.ReviewList.add(review);
                                        // this.ReviewList.clear();

                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                System.out.println("then i was there");
                                StoreProductReviewsFragment.sellerReviewsAdapter = new ProductReviewsAdapter(getActivity(),  StoreProductReviewsFragment.ReviewList);
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                                StoreProductReviewsFragment.recyclerView.setLayoutManager(mLayoutManager);
                                StoreProductReviewsFragment.recyclerView.setItemAnimator(new DefaultItemAnimator());
                                StoreProductReviewsFragment.recyclerView.setAdapter(StoreProductReviewsFragment.sellerReviewsAdapter);

                                StoreProductReviewsFragment. recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), StoreProductReviewsFragment.recyclerView, new ClickListener()
                                {
                                    @Override
                                    public void onClick(View view, int position) {


                                    }

                                    @Override
                                    public void onLongClick(View view, int position) {

                                    }
                                }));


                                try
                                {
                                    productfeatures      =  json.getJSONArray("productfeatures");
                                    System.out.println("feeee"+ productfeatures.toString());
                                }
                                catch(Exception e)
                                {
                                    System.out.println("Stage 4");
                                }

                                try
                                {
                                    productvariations      =  json.getJSONArray("productvariations");
                                    System.out.println("feeee1"+ productvariations.toString());
                                }
                                catch(Exception e)
                                {
                                    System.out.println("Stage 4");
                                }

                                //productattributes    =  json.getJSONArray("productspecs");

                            }
                            else
                            {
                                new AlertDialog.Builder(rootView.getContext())
                                        .setTitle("Failed to get Seller Details")
                                        .setMessage("Failed to get Seller Details.Please try again or check your internet connection")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which)
                                            {

                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }


                            try
                            {
                                JSONObject json1xx  = new JSONObject(StoreProductItemDetailsFragment.productvariations.getString(0));
                                System.out.println("StoreProductItemDetailSpecsFragmentVariation"+" "+json1xx.getString("variations").toString());
                                String[] first = json1xx.getString("variations").toString().split("---");
                                System.out.println("StoreProductItemDetailSpecsFragmentVariation: " +" "+first.length);
                                radiotags = new String[first.length];

                                StoreProductItemDetailsFragment.totalVariiations = first.length;

                                LinearLayout LL = new LinearLayout(rootView.getContext());
                                LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


                                TextView tx5 = new TextView(rootView.getContext());
                                tx5.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
                                tx5.setPadding(10,10,10,10);
                                tx5.setGravity(Gravity.RIGHT );
                                tx5.setTypeface(null, Typeface.BOLD);
                                tx5.setTextSize(16);
                                tx5.setTextColor(getResources().getColor(R.color.colorBlack));
                                tx5.setText("Price Impact");
                                LL.addView(tx5);

                                lx12.addView(LL);

                                for(int i=0;i<=first.length-1;i++)
                                {
                                    String[] Second = first[i].split("--");

                                    System.out.println("StoreProductItemDetailSpecsFragmentVariation: 1" );

                                    LL = new LinearLayout(rootView.getContext());
                                    LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    // LLParams.topMargin=10;
                                    LL.setPadding(10,10,10,10);
                                    LL.setOrientation(LinearLayout.HORIZONTAL);
                                    LL.setWeightSum(1);
                                    LL.setGravity(Gravity.CENTER_VERTICAL);
                                    LL.setLayoutParams(LLParams);

                                    System.out.println("StoreProductItemDetailSpecsFragmentVariation: 2" );

                                    RadioButton tx1 = new RadioButton(rootView.getContext());
                                    tx1.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT, 0.15f));tx1.setTextSize(12);
                                    tx1.setTag(Second[0]);
                                    radiotags[i]=Second[0];

                                    View.OnClickListener radio_listener = new View.OnClickListener(){
                                        public void onClick(View v)
                                        {
                                            String x = v.getTag().toString();
                                            selectedvariations =  x;
                                            System.out.println("CapCam" + selectedvariations);
                                            for(int i=0;i<=StoreProductItemDetailsFragment.totalVariiations-1;i++)
                                            {
                                                RadioButton txc = (RadioButton)rootView.findViewWithTag(radiotags[i]);
                                                if(!radiotags[i].equals(x)) {
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

                                    System.out.println("StoreProductItemDetailSpecsFragmentVariation: 3" );

                                    ImageView tx3 = new ImageView(rootView.getContext());
                                    // tx3.setLayoutParams(new LinearLayout.LayoutParams(0,100, 0.35f));
                                    DisplayMetrics displaymetrics = new DisplayMetrics();
                                    getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                                    DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
                                    int pxx = Math.round(20 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
                                    int stageWidth = displaymetrics.widthPixels;
                                    int stageHeight = displaymetrics.heightPixels;
                                    System.out.println("coooool"+((35*stageWidth)/100)+" pxx"+pxx);
                                    tx3.setLayoutParams(new LinearLayout.LayoutParams(0,((35*stageWidth)/100)-pxx, 0.35f));
                                    Glide.with(StoreProductItemDetailsFragment.this)
                                            .load(Second[3])
                                            .diskCacheStrategy(DiskCacheStrategy.NONE)

                                            .skipMemoryCache(true)
                                            .into(tx3);

                                    // tx3.setTextSize(14);
                                    // tx3.setTextColor(getResources().getColor(R.color.colorGrayText));
                                    // tx3.setText(Second[1]);
                                    LL.addView(tx3);

                                    System.out.println("StoreProductItemDetailSpecsFragmentVariation: 3" );

                                    TextView tx2 = new TextView(rootView.getContext());
                                    tx2.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT, 0.35f));
                                    tx2.setTextSize(14);
                                    tx2.setTextColor(getResources().getColor(R.color.colorBlack));
                                    tx2.setText(Second[2]);
                                    tx2.setPadding(10,0,10,10);
                                    LL.addView(tx2);

                                    TextView tx4 = new TextView(rootView.getContext());
                                    tx4.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT, 0.15f));
                                    tx4.setTextSize(14);
                                    tx4.setTextColor(getResources().getColor(R.color.colorBlack));
                                    tx4.setText("$"+Second[1]);
                                    LL.addView(tx4);

                                    //LL.addView(tx5);

                                    lx12.addView(LL);
                                    System.out.println("StoreProductItemDetailSpecsFragmentVariation : " +" "+Second[0]+" "+Second[1]);
                                }

                            }
                            catch(Exception e)
                            {
                                System.out.println(e.getMessage().toString());
                            }

                            progress.dismiss();

                        } catch (Exception e)
                        {
                            e.printStackTrace();
                            try
                            {
                                progress.dismiss();


                            }
                            catch (Exception x)
                            {

                            }
                        }
                    }


                }

            }
        };

        ;


    }

    public void addtocart()
    {
        progress = new ProgressDialog(rootView.getContext());
        progress.setMessage("Adding Product to cart");
        progress.show();
        Seller seller;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://streetshops.sg/dashboard/appapi/addtocart.php?cartid=" +cart_id +
                            "&userid="+user_id+"&productid="+ productid+"&variationid="+selectedvariations+"&quantity=1";
                    StringBuilder body = new StringBuilder();
                    DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                    HttpPost httpGet = new HttpPost(url); // create new httpGet object
                    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");

                    System.out.println("Cool0 Product id"+productid);

                 /*   httpGet.addHeader("userid", user_id);
                    httpGet.addHeader("cartid", cart_id);
                    httpGet.addHeader("variationid", selectedvariations);
                    httpGet.addHeader("quantity", "1");
                    httpGet.addHeader("productid", productid );*/

                    // System.out.println("productID"+sellerProduct.getProductid());
                    // httpGet.addHeader("sellerID", seller.getSellerId());

                    System.out.println("Cool1 selectedvariations="+selectedvariations + " cartid="+cart_id+ " userid="+ user_id
                            + " quantity=1"+ " productid="+ productid);

                    ArrayList KlientNameValuePairs = new ArrayList<NameValuePair>();

                  /*  KlientNameValuePairs.add(new BasicNameValuePair("userid", user_id));
                    KlientNameValuePairs.add(new BasicNameValuePair("cartid", cart_id));
                    KlientNameValuePairs.add(new BasicNameValuePair("variationid", selectedvariations));
                    KlientNameValuePairs.add(new BasicNameValuePair("quantity", "1"));
                    KlientNameValuePairs.add(new BasicNameValuePair("productid", productid ));*/

                    // KlientNameValuePairs.add(new BasicNameValuePair("sellerID",seller.getSellerId()));

                    System.out.println("Cool2 Product id"+productid);

                    httpGet.setEntity(new UrlEncodedFormEntity(KlientNameValuePairs));


                    try {
                        HttpResponse response = httpclient.execute(httpGet); // execute httpGet
                        StatusLine statusLine = response.getStatusLine();
                        int statusCode = statusLine.getStatusCode();
                        System.out.println("Cool3 Product id"+productid);
                        if (statusCode == HttpStatus.SC_OK) {
                            // System.out.println(statusLine);
                            body.append(statusLine);
                            HttpEntity e = response.getEntity();
                            String entity = EntityUtils.toString(e);
                            body.append(entity);
                            System.out.println("Cool4 Product id"+productid);
                        } else {
                            body.append(statusLine);
                            System.out.println("Cool5 Product id"+productid);
                            // System.out.println(statusLine);
                        }
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        //	httpGet.; // stop connection
                    }

                    System.out.println("Cool6 Product id"+productid);
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

    @Override
    public void onPause() {
        super.onPause();
        sethandler();

    }
/*
    private void getProductDetails()
    {
        AppConstant.showProgressDialog(getActivity());
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("productID", sellerProduct.getProductid());


        Call<ProductDetails> call = apiService.getProductDetails(mapParam);
        call.enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response)
            {
                AppConstant.dismissProgressDialog();
                if (response.body().getStatus() == 1)
                {
                    System.out.println(response.body()+"Cook book");
                    productDetails = response.body();
                    initUI();
                }
                else
                {
                    Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {
                // Log error here since request failed
                Log.e("LoginActivity", t.toString());

            }
        });
    }

    private void initUI() {

        try {
            Glide.with(this)
                    .load(productDetails.getProductImages().get(0).getImageurl())
                    .into(imgProductImage1);
        }
        catch(Exception e)
        {
            imgProductImage1.setVisibility(View.GONE);
        }

        try
        {
            Glide.with(this)
                    .load(productDetails.getProductImages().get(1).getImageurl())
                    .into(imgProductImage2);
        }
        catch(Exception e)
        {
            imgProductImage2.setVisibility(View.GONE);
        }

        try
        {
            Glide.with(this)
                    .load(productDetails.getProductImages().get(2).getImageurl())
                    .into(imgProductImage3);
        }
        catch(Exception e)
        {
            imgProductImage3.setVisibility(View.GONE);
        }

        try
        {
            Glide.with(this)
                    .load(productDetails.getProductImages().get(0).getImageurl())
                    .into(imgProductDetailsLarge);
        }
        catch(Exception e)
        {
            imgProductDetailsLarge.setVisibility(View.GONE);
        }


        txtProductDetails.setText(Html.fromHtml(productDetails.getProductDetails().get(0).getProductdesc()));

        txtManufacterValue.setText(productDetails.getProductspecs().get(0).getProductmanufacture());
        txtWeightValue.setText(productDetails.getProductspecs().get(0).getProductWeight());
        txtSpecificationValue.setText(productDetails.getProductspecs().get(0).getProductspeclist());
        txtAvailabilityValue.setText(productDetails.getProductspecs().get(0).getProductavilable());
        txtProductCodeValue.setText(productDetails.getProductspecs().get(0).getProductproductCode());
        txtProductFeatures.setText(productDetails.getProductfeatures().get(0).getFeaturess());

    }
    */

    @OnClick({R.id.imgProductDetailsLarge, R.id.imgProductImage1, R.id.imgProductImage2, R.id.imgProductImage3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgProductDetailsLarge:
                break;
            case R.id.imgProductImage1:
                try {
                    JSONObject jsonx = new JSONObject(images.getString(0));

                Glide.with(this)
                        .load(jsonx.getString("imageurl"))
                        .into(imgProductDetailsLarge);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.imgProductImage2:
                try {
                    JSONObject jsonx1 = new JSONObject(images.getString(1));

                Glide.with(this)
                        .load(jsonx1.getString("imageurl"))
                        .into(imgProductDetailsLarge);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.imgProductImage3:
                try {
                    JSONObject jsonx2 = new JSONObject(images.getString(2));

                Glide.with(this)
                        .load(jsonx2.getString("imageurl"))
                        .into(imgProductDetailsLarge);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public class ViewDialog {

        public void showDialog(Activity activity, String msg){
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

            LinearLayout lx12 =(LinearLayout)dialog.findViewById(R.id.lx112);

            final String[] radiotags;

            /*Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });*/


            try
            {
                JSONObject json1xx  = new JSONObject(StoreProductItemDetailsFragment.productvariations.getString(0));
                System.out.println("StoreProductItemDetailSpecsFragmentVariation"+" "+json1xx.getString("variations").toString());
                String[] first = json1xx.getString("variations").toString().split("---");
                System.out.println("StoreProductItemDetailSpecsFragmentVariation: " +" "+first.length);
                radiotags = new String[first.length];

                StoreProductItemDetailsFragment.totalVariiations = first.length;

                LinearLayout LL = new LinearLayout(dialog.getContext());
                LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


                TextView tx5 = new TextView(dialog.getContext());
                tx5.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
                tx5.setPadding(10,10,10,10);
                tx5.setGravity(Gravity.RIGHT );
                tx5.setTypeface(null, Typeface.BOLD);
                tx5.setTextSize(16);
                tx5.setTextColor(getResources().getColor(R.color.colorBlack));
                tx5.setText("Price Impact");
                LL.addView(tx5);

                lx12.addView(LL);

                for(int i=0;i<=first.length-1;i++)
                {
                    String[] Second = first[i].split("--");

                    System.out.println("StoreProductItemDetailSpecsFragmentVariation: 1" );

                    LL = new LinearLayout(dialog.getContext());
                    LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    // LLParams.topMargin=10;
                    LL.setPadding(10,10,10,10);
                    LL.setOrientation(LinearLayout.HORIZONTAL);
                    LL.setWeightSum(1);
                    LL.setGravity(Gravity.CENTER_VERTICAL);
                    LL.setLayoutParams(LLParams);

                    System.out.println("StoreProductItemDetailSpecsFragmentVariation: 2" );

                    RadioButton tx1 = new RadioButton(dialog.getContext());
                    tx1.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT, 0.15f));tx1.setTextSize(12);
                    tx1.setTag(Second[0]);
                    radiotags[i]=Second[0];
                    final View vc= tx1.getRootView();

                    View.OnClickListener radio_listener = new View.OnClickListener(){
                        public void onClick(View v)
                        {
                            String x = v.getTag().toString();
                            selectedvariations =  x;
                            System.out.println("CapCam" + selectedvariations);
                            for(int i=0;i<=StoreProductItemDetailsFragment.totalVariiations-1;i++)
                            {
                                RadioButton txc = (RadioButton)vc.findViewWithTag(radiotags[i]);
                                if(!radiotags[i].equals(x)) {
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

                    System.out.println("StoreProductItemDetailSpecsFragmentVariation: 3" );

                    ImageView tx3 = new ImageView(dialog.getContext());
                    // tx3.setLayoutParams(new LinearLayout.LayoutParams(0,100, 0.35f));
                    DisplayMetrics displaymetrics = new DisplayMetrics();
                    getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                    DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
                    int pxx = Math.round(20 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
                    int stageWidth = displaymetrics.widthPixels;
                    int stageHeight = displaymetrics.heightPixels;
                    System.out.println("coooool"+((35*stageWidth)/100)+" pxx"+pxx);
                    tx3.setLayoutParams(new LinearLayout.LayoutParams(0,((35*stageWidth)/100)-pxx, 0.35f));
                    Glide.with(dialog.getContext())
                            .load(Second[3])
                            .diskCacheStrategy(DiskCacheStrategy.NONE)

                            .skipMemoryCache(true)
                            .into(tx3);

                    // tx3.setTextSize(14);
                    // tx3.setTextColor(getResources().getColor(R.color.colorGrayText));
                    // tx3.setText(Second[1]);
                   // LL.addView(tx3);

                    System.out.println("StoreProductItemDetailSpecsFragmentVariation: 3" );

                    TextView tx2 = new TextView(dialog.getContext());
                    tx2.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f));
                    tx2.setTextSize(14);
                    tx2.setTextColor(getResources().getColor(R.color.colorBlack));
                    tx2.setText(Second[2]);
                    tx2.setPadding(10,0,10,10);
                    LL.addView(tx2);

                    TextView tx4 = new TextView(dialog.getContext());
                    tx4.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT, 0.15f));
                    tx4.setTextSize(14);
                    tx4.setTextColor(getResources().getColor(R.color.colorBlack));
                    tx4.setText("$"+Second[1]);
                    LL.addView(tx4);

                    //LL.addView(tx5);

                    lx12.addView(LL);
                    System.out.println("StoreProductItemDetailSpecsFragmentVariation : " +" "+Second[0]+" "+Second[1]);
                }

            }
            catch(Exception e)
            {
                System.out.println(e.getMessage().toString());
            }


            dialog.show();

        }
    }
}
