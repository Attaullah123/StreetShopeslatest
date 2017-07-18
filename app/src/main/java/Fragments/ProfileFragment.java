package Fragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import android.os.Handler;

import bean.Datum;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pojo.Seller;
import utils.CircleImageView;
import android.os.Message;


import static utils.AppConstant.user_id;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.textViewStats)
    TextView textViewStats;
    @BindView(R.id.textViewRefundPolicy)
    TextView textViewRefundPolicy;
    @BindView(R.id.textViewShipPolicy)
    TextView textViewShipPolicy;
    @BindView(R.id.textViewNext)
    TextView textViewNext;
    @BindView(R.id.favseller)
    ImageView favseller;
    @BindView(R.id.likeseller)
    ImageView likeseller;
    @BindView(R.id.user_profile_photo)
    CircleImageView userProfilePhoto;
    @BindView(R.id.textViewReviews)
    RelativeLayout textViewReviews;
    @BindView(R.id.favseller1)
    RelativeLayout favseller1;
    @BindView(R.id.likeseller1)
    RelativeLayout likeseller1;


    TextView user_profile_name,user_profile_short_bio;
    Seller seller;
    ProgressDialog progress ;

    public static String sellersid          =  "";
    public static String sellersname        =  "";
    public static String sellerslogourl     =  "";
    public static String sellertotallike    =  "";
    public static String sellermylike       =  "";
    public static String sellertotalfav     =  "";
    public static String sellermyfav        =  "";
    public static String sellersrating      =  "";
    public static JSONArray Reviews            ;
    public static String Refundpolicy       =  "";
    public static String Shipmentpolicy     =  "";
    public static String sellerreviewscount =  "";

    public static String sellerownername    =  "";
    public static String sellerscountry     =  "";
    public static String selleraddress      =  "";
    public static String sellerphone        =  "";
    public static String TotalSale          =  "";
    public static String TotalOrders        =  "";
    public static String TotalEarning       =  "";
    public static String Total              =  "";

    View view;
    public static Handler handlerm;

    public static ProfileFragment newInstance(Seller datum)
    {

        Bundle args = new Bundle();
        args.putParcelable("datum", datum);
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.setArguments(args);
        return profileFragment;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
          sellersid          =  "";
          sellersname        =  "";
          sellerslogourl     =  "";
          sellertotallike    =  "0";
          sellermylike       =  "No";
          sellertotalfav     =  "0";
          sellermyfav        =  "No";
          sellersrating      =  "1";
          Reviews           = null ;
          sellerreviewscount =  "0"  ;
          Refundpolicy       =  "";
          Shipmentpolicy     =  "";

          sellerownername    =  "";
          sellerscountry     =  "";
          selleraddress      =  "";
          sellerphone        =  "";
          TotalSale          =  "0.0";
          TotalOrders        =  "0.0";
          TotalEarning       =  "0.0";
          Total              =  "0.0";

        sethandler();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        ((MainActivity)getActivity()).mTitle.setText("Seller Details");
        try {
            seller = (Seller) getArguments().get("datum");
            user_profile_name = (TextView) view.findViewById(R.id.user_profile_name);
            user_profile_short_bio = (TextView) view.findViewById(R.id.user_profile_short_bio);

            user_profile_name.setText(seller.getTitle());
            user_profile_short_bio.setText(seller.getAddress());

            System.out.println("User Id ="+user_id+" Seller ID ="+seller.getSellerId());


            ButterKnife.bind(this, view);

           // Glide.with(getActivity())
             //       .load(seller.getBannerUrl())
         //           .into(userProfilePhoto);
        }
        catch(Exception e)
        {

        }


        favseller1 = (RelativeLayout) view.findViewById(R.id.favseller1);

        favseller1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                favit();
            }
        });


        likeseller1 = (RelativeLayout) view.findViewById(R.id.likeseller1);

        likeseller1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               likeit();
            }
        });

        textViewReviews = (RelativeLayout) view.findViewById(R.id.textViewReviews);

        textViewReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                System.out.println("Reviews");
                ((MainActivity)getActivity()).navigateToReplace(new SellerReviewsFragment(), null, true);
            }
        });


        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();

        float px = Math.round(32 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));

        textViewStats = (TextView) view.findViewById(R.id.textViewStats);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textViewStats.getLayoutParams();

        params.height = Math.round(((((width-32)/100)*25)));
        System.out.println("Cool"+width+"kk"+(((((width-32)/100)*25))));
        textViewStats.setLayoutParams(params);

        textViewStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ((MainActivity)getActivity()).navigateToReplace(new SellerStatsFragment(), null, true);
            }
        });

        textViewRefundPolicy = (TextView) view.findViewById(R.id.textViewRefundPolicy);

        params = (LinearLayout.LayoutParams) textViewRefundPolicy.getLayoutParams();

        params.height = Math.round(((((width-32)/100)*25)));
        System.out.println("Cool"+width+"kk"+(((((width-32)/100)*25))));
        textViewRefundPolicy.setLayoutParams(params);

        textViewRefundPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                        System.out.println("Refund Policy");
                ((MainActivity)getActivity()).navigateToReplace(new SellerRefundPolicyFragment(), null, true);

            }
        });



        textViewShipPolicy = (TextView) view.findViewById(R.id.textViewShipPolicy);

        params = (LinearLayout.LayoutParams) textViewShipPolicy.getLayoutParams();

        params.height = Math.round(((((width-32)/100)*25)));
        System.out.println("Cool"+width+"kk"+(((((width-32)/100)*25))));
        textViewShipPolicy.setLayoutParams(params);

        textViewShipPolicy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        System.out.println("Shipment Policy");
                        ((MainActivity)getActivity()).navigateToReplace(new SellerRefundShimpmentyFragment(), null, true);


                    }
        });

        textViewNext = (TextView) view.findViewById(R.id.textViewNext);

        params = (LinearLayout.LayoutParams) textViewNext.getLayoutParams();

        params.height = Math.round(((((width-32)/100)*25)));
        System.out.println("Cool"+width+"kk"+(((((width-32)/100)*25))));
        textViewNext.setLayoutParams(params);

        textViewNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).navigateToReplace(SellerStoreFragment.newInstance(seller), null, true);
                // ((MainActivity)getActivity()).navigateToReplace(new SellerStoreFragment(), null, true);
            }
        });


        getSellerDetails();

        return view;
    }


    public void getSellerDetails()
    {
        progress = new ProgressDialog(view.getContext());
        progress.setMessage("Loading Store Details");
        progress.show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://streetshops.sg/dashboard/appapi/getsellerdetails.php";
                    StringBuilder body = new StringBuilder();
                    DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                    HttpPost httpGet = new HttpPost(url); // create new httpGet object
                    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    httpGet.addHeader("customerID", user_id);
                    httpGet.addHeader("sellerID", seller.getSellerId());

                    ArrayList KlientNameValuePairs = new ArrayList<NameValuePair>();
                    KlientNameValuePairs.add(new BasicNameValuePair("customerID",user_id));
                    KlientNameValuePairs.add(new BasicNameValuePair("sellerID",seller.getSellerId()));


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


    @Override
   public void onPause() {
        super.onPause();
        sethandler();

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

                    if (keyy == 90)
                    {
                        System.out.println("Cool"+px);
                        progress.dismiss();

                        try {
                            JSONObject json = new JSONObject(px);
                            String responsee = json.getString("status");


                            if(responsee.equals("1"))
                            {

                                JSONArray venues = json.getJSONArray("sellerDetail");
                                JSONObject json1  = new JSONObject(venues.getString(0));
                                System.out.println("Rehan" + json1.getString("seller_id"));

                                //sellersid          =  json1.getString("seller_id");
                                sellersid          =  seller.getSellerId();
                                sellersname        =  json1.getString("sellersname");
                                sellerslogourl     =  json1.getString("sellersimageurls");
                                sellertotallike    =  json1.getString("sellertotallike");
                                sellermylike       =  json1.getString("sellermylike");
                                sellertotalfav     =  json1.getString("sellertotalfav");
                                sellermyfav        =  json1.getString("sellermyfav");
                                sellersrating      =  json1.getString("sellersrating");
                                TotalSale          =  json1.getString("TotalSale");
                                sellerreviewscount =  json1.getString("reviews_count");

                                ((MainActivity)getActivity()).mTitle.setText( sellersname);



                                Shipmentpolicy     =  json1.getString("shipping_policy");
                                Refundpolicy       =  json1.getString("returnpolicy");
                                sellerownername    =  json1.getString("sellerownername");
                                sellerscountry     =  json1.getString("sellerscountry");
                                selleraddress      =  json1.getString("selleraddress");
                                sellerphone        =  json1.getString("sellerphone");
                                TotalSale          =  json1.getString("TotalSale");
                                TotalOrders        =  json1.getString("TotalOrders");
                                TotalEarning       =  json1.getString("TotalEarning");
                                Total              =  json1.getString("Total");

                                if(sellersrating.equals(""))
                                {
                                    sellersrating = "1";
                                }

                                System.out.println(Shipmentpolicy);

                                TextView user_profile_name =(TextView)view.findViewById(R.id.user_profile_name);
                                user_profile_name.setText(sellersname);

                                ImageView user_logo   =    (ImageView)view.findViewById(R.id.sellerlogo);
                                user_logo.setImageResource(R.drawable.loginbg);
                                Glide.with(ProfileFragment.this)
                                        .load(sellerslogourl)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)

                                        .skipMemoryCache(true)
                                        .into(user_logo);

                                SimpleRatingBar user_rating =   (SimpleRatingBar)view.findViewById(R.id.user_rating);

                                user_rating.setEnabled(false);
                                user_rating.setClickable(false);
                                user_rating.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        System.out.println("Cliecked");
                                    }
                                });
                               // System.out.println("Bell"+Math.round(Float.valueOf(sellersrating)));

                                if(Math.round(Float.valueOf(sellersrating))==1)
                                {
                                    //user_rating.setImageResource(R.drawable.onestar);
                                    user_rating.setRating(1);
                                }

                                if(Math.round(Float.valueOf(sellersrating))==2)
                                {
                                   // user_rating.setImageResource(R.drawable.twostar);
                                    user_rating.setRating(2);
                                }

                                if(Math.round(Float.valueOf(sellersrating))==3)
                                {
                                    //user_rating.setImageResource(R.drawable.threestar);
                                    user_rating.setRating(3);
                                }

                                if(Math.round(Float.valueOf(sellersrating))==4)
                                {
                                    //user_rating.setImageResource(R.drawable.fourstar);
                                    user_rating.setRating(4);
                                }

                                if(Math.round(Float.valueOf(sellersrating))==5)
                                {
                                    //user_rating.setImageResource(R.drawable.fivestar);
                                    user_rating.setRating(5);
                                }

                                TextView sellertotalliketx =(TextView)view.findViewById(R.id.sellertotallike);
                                sellertotalliketx.setText(sellertotallike);

                                ImageView likeseller =(ImageView)view.findViewById(R.id.likeseller);

                                if(sellermylike.equals("Yes"))
                                {
                                    likeseller.setImageResource(R.drawable.likedark);
                                }
                                else
                                {
                                    likeseller.setImageResource(R.drawable.icn_likes);
                                }


                                TextView sellertotalfavtx =(TextView)view.findViewById(R.id.sellertotalfav);
                                sellertotalfavtx.setText(sellertotalfav);


                                ImageView favseller =(ImageView)view.findViewById(R.id.favseller);

                                if(sellermyfav.equals("Yes"))
                                {
                                    favseller.setImageResource(R.drawable.heartdark);
                                }
                                else
                                {
                                    favseller.setImageResource(R.drawable.ic_action_heart);
                                }

                                TextView sellreviewscountx =(TextView)view.findViewById(R.id.sellertotalreviews);
                                sellreviewscountx.setText(sellerreviewscount);

                                Reviews = json.getJSONArray("sellerReviews");

                            }
                            else
                            {
                                new AlertDialog.Builder(view.getContext())
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


                    if (keyy == 91)
                    {
                        System.out.println("Cool"+px);


                        try {
                            JSONObject json = new JSONObject(px);
                            String responsee = json.getString("status");
                            progress.dismiss();

                            if(responsee.equals("1"))
                            {
                                ImageView likeseller =(ImageView)view.findViewById(R.id.likeseller);
                                TextView sellertotalliketx =(TextView)view.findViewById(R.id.sellertotallike);

                                    likeseller.setImageResource(R.drawable.likedark);
                                    sellermylike="Yes";
                                    sellertotalliketx.setText(String.valueOf(Integer.valueOf(sellertotallike)+1));
                                    sellertotallike=String.valueOf(Integer.valueOf(sellertotallike)+1);


                            }
                            else
                            {
                                new AlertDialog.Builder(view.getContext())
                                        .setTitle("Failed to like Seller")
                                        .setMessage("Failed to like Seller.Please try again or check your internet connection")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which)
                                            {

                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
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


                    if (keyy == 92)
                    {
                        System.out.println("Cool"+px);


                        try {
                            JSONObject json = new JSONObject(px);
                            String responsee = json.getString("status");
                            progress.dismiss();

                            if(responsee.equals("1"))
                            {
                                ImageView likeseller =(ImageView)view.findViewById(R.id.likeseller);
                                TextView sellertotalliketx =(TextView)view.findViewById(R.id.sellertotallike);

                                likeseller.setImageResource(R.drawable.icn_likes);
                                sellermylike="No";
                                sellertotalliketx.setText(String.valueOf(Integer.valueOf(sellertotallike)-1));
                                sellertotallike=String.valueOf(Integer.valueOf(sellertotallike)-1);


                            }
                            else
                            {
                                new AlertDialog.Builder(view.getContext())
                                        .setTitle("Failed to unlike Seller")
                                        .setMessage("Failed to unlike Seller.Please try again or check your internet connection")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which)
                                            {

                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
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



                    if (keyy == 93)
                    {
                        System.out.println("Cool 93"+px);


                        try {
                            JSONObject json = new JSONObject(px);
                            String responsee = json.getString("status");
                            progress.dismiss();

                            if(responsee.equals("1"))
                            {
                                ImageView favseller =(ImageView)view.findViewById(R.id.favseller);
                                TextView sellertotalfavtx =(TextView)view.findViewById(R.id.sellertotalfav);

                                favseller.setImageResource(R.drawable.heartdark);
                                sellermyfav="Yes";
                                sellertotalfavtx.setText(String.valueOf(Integer.valueOf(sellertotalfav)+1));
                                sellertotalfav=String.valueOf(Integer.valueOf(sellertotalfav)+1);


                            }
                            else
                            {
                                new AlertDialog.Builder(view.getContext())
                                        .setTitle("Failed to favourite  Seller")
                                        .setMessage("Failed to favourite  Seller.Please try again or check your internet connection")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which)
                                            {

                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
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


                    if (keyy == 94)
                    {
                        System.out.println("Cool 94"+px);


                        try {
                            JSONObject json = new JSONObject(px);
                            String responsee = json.getString("status");
                            progress.dismiss();

                            if(responsee.equals("1"))
                            {
                                ImageView favseller =(ImageView)view.findViewById(R.id.favseller);
                                TextView sellertotalfavtx =(TextView)view.findViewById(R.id.sellertotalfav);

                                favseller.setImageResource(R.drawable.ic_action_heart);
                                sellermyfav="No";
                                sellertotalfavtx.setText(String.valueOf(Integer.valueOf(sellertotalfav)-1));
                                sellertotalfav=String.valueOf(Integer.valueOf(sellertotalfav)-1);


                            }
                            else
                            {
                                new AlertDialog.Builder(view.getContext())
                                        .setTitle("Failed to un favourite Seller")
                                        .setMessage("Failed to un favourite Seller.Please try again or check your internet connection")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which)
                                            {

                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
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


    public void favit()
    {
        System.out.print("i am here 1"+sellermyfav);
        if(sellermyfav.equals("Yes"))
        {
            System.out.print("i am here 2"+sellermyfav);
            deletefav();
        }
        else
        {
            System.out.print("i am here 3"+sellermyfav);
            addfav();
        }

    }

    public void likeit()
    {
        if(sellermylike.equals("Yes"))
        {
            deletelike();
        }
        else
        {
            addlike();
        }
    }

    public void addlike()
    {

        progress = new ProgressDialog(view.getContext());
        progress.setMessage("Loading Seller Details");
        progress.show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://streetshops.sg/dashboard/appapi/addlikes.php";
                    StringBuilder body = new StringBuilder();
                    DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                    HttpPost httpGet = new HttpPost(url); // create new httpGet object
                    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    httpGet.addHeader("customerid", user_id);
                    httpGet.addHeader("sellerid", seller.getSellerId());


                    ArrayList KlientNameValuePairs = new ArrayList<NameValuePair>();
                    KlientNameValuePairs.add(new BasicNameValuePair("customerid",user_id));
                    KlientNameValuePairs.add(new BasicNameValuePair("sellerid",seller.getSellerId()));


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

                    b.putInt("key", 91);
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

    public void deletelike()
    {

        progress = new ProgressDialog(view.getContext());
        progress.setMessage("Loading Seller Details");
        progress.show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://streetshops.sg/dashboard/appapi/deletelikes.php";
                    StringBuilder body = new StringBuilder();
                    DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                    HttpPost httpGet = new HttpPost(url); // create new httpGet object
                    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    httpGet.addHeader("customerid", user_id);
                    httpGet.addHeader("sellerid", seller.getSellerId());


                    ArrayList KlientNameValuePairs = new ArrayList<NameValuePair>();
                    KlientNameValuePairs.add(new BasicNameValuePair("customerid",user_id));
                    KlientNameValuePairs.add(new BasicNameValuePair("sellerid",seller.getSellerId()));


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

                    b.putInt("key", 92);
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


    public void addfav()
    {
        progress = new ProgressDialog(view.getContext());
        progress.setMessage("Adding favourite");
        progress.show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://streetshops.sg/dashboard/appapi/addfavourate.php";
                    StringBuilder body = new StringBuilder();
                    DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                    HttpPost httpGet = new HttpPost(url); // create new httpGet object
                    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    httpGet.addHeader("customerid", user_id);
                    httpGet.addHeader("sellerid", seller.getSellerId());


                    ArrayList KlientNameValuePairs = new ArrayList<NameValuePair>();
                    KlientNameValuePairs.add(new BasicNameValuePair("customerid",user_id));
                    KlientNameValuePairs.add(new BasicNameValuePair("sellerid",seller.getSellerId()));


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

                    b.putInt("key", 93);
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

    public void deletefav()
    {
        progress = new ProgressDialog(view.getContext());
        progress.setMessage("Removing favourite");
        progress.show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://streetshops.sg/dashboard/appapi/deletefavourate.php";
                    StringBuilder body = new StringBuilder();
                    DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                    HttpPost httpGet = new HttpPost(url); // create new httpGet object
                    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    httpGet.addHeader("customerid", user_id);
                    httpGet.addHeader("sellerid", seller.getSellerId());


                    ArrayList KlientNameValuePairs = new ArrayList<NameValuePair>();
                    KlientNameValuePairs.add(new BasicNameValuePair("customerid",user_id));
                    KlientNameValuePairs.add(new BasicNameValuePair("sellerid",seller.getSellerId()));


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

                    b.putInt("key", 94);
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


}
