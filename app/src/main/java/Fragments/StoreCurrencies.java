package Fragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.CurrenciesAdapter;
import recycleview.ClickListener;
import recycleview.RecyclerTouchListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreCurrencies extends Fragment {


    public List<String> ReviewList= new ArrayList<>();
    public RecyclerView recyclerView;
    public CurrenciesAdapter reviewsAdapter;
    public ProgressDialog progress ;
    public View view;
    public static JSONArray currencies            ;
    public static Handler handlerm;

    public StoreCurrencies()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        currencies           = null ;
        sethandler();

        View rootView = inflater.inflate(R.layout.fragment_currencies, container, false);

        ((MainActivity)getActivity()).mTitle.setText("Currencies");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        //storeList.add(1,"you are good");

/*


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
        reviewsAdapter = new SellerReviewsAdapter(getActivity(),  ReviewList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(reviewsAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

              //  Toast.makeText(getActivity(), String.valueOf(position) + " is selected!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        // Inflate the layout for this fragment
        view = rootView;
        getSellerCurrencies();
        return rootView;
    }

    public void getSellerCurrencies()
    {
        progress = new ProgressDialog(view.getContext());
        progress.setMessage("Loading Store Details");
        progress.show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://streetshops.sg/dashboard/appapi/getcurrencylist.php";
                    StringBuilder body = new StringBuilder();
                    DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                    HttpPost httpGet = new HttpPost(url); // create new httpGet object
                    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
                   // httpGet.addHeader("customerID", user_id);
                    //httpGet.addHeader("sellerID", seller.getSellerId());

                    ArrayList KlientNameValuePairs = new ArrayList<NameValuePair>();
                    //KlientNameValuePairs.add(new BasicNameValuePair("customerID",user_id));
                    //KlientNameValuePairs.add(new BasicNameValuePair("sellerID",seller.getSellerId()));


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

                               // JSONArray venues = json.getJSONArray("currencies");
                               // JSONObject json1  = new JSONObject(venues.getString(0));
                             //   System.out.println("Rehan" + json1.getString("seller_id"));

                                String review = "";

                                JSONArray currencies = json.getJSONArray("currencies");

                                try {
                                    //System.out.println("Reviews"+ ProfileFragment.Reviews);


                                    for(int i=0;i<=currencies.length()-1;i++)
                                    {
                                        JSONObject json1  = new JSONObject(currencies.getString(i));
                                        System.out.println("Rehan"+i + json1.getString("name"));

                                        review ="";
                                        review=review+json1.getString("name")+"~~~";
                                        review=review+json1.getString("iso_code")+"~~~";
                                        review=review+"3";
                                       // review=review+json1.getString("conversion_rate");

                                        ReviewList.add(review);

                                        // this.ReviewList.clear();

                                    }

                                    reviewsAdapter = new CurrenciesAdapter(getActivity(),  ReviewList);
                                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                                    recyclerView.setLayoutManager(mLayoutManager);
                                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                                    recyclerView.setAdapter(reviewsAdapter);

                                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
                                        @Override
                                        public void onClick(View view, int position) {

                                            //  Toast.makeText(getActivity(), String.valueOf(position) + " is selected!", Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onLongClick(View view, int position) {

                                        }
                                    }));

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else
                            {
                                new AlertDialog.Builder(view.getContext())
                                        .setTitle("Failed to get Currencies")
                                        .setMessage("Failed to get Currencies.Please try again or check your internet connection")
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
        };


    };}

}
