package Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import adapter.ShippingCartAdapter;
import pojo.Seller;

import static Fragments.StoreProductItemDetailsFragment.productid;
import static Fragments.StoreProductItemDetailsFragment.selectedvariations;
import static utils.AppConstant.cart_id;
import static utils.AppConstant.user_id;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingCartFragment extends Fragment {


    public List<String> storeList = new ArrayList<>();
    public RecyclerView recyclerView;
    public ShippingCartAdapter shippingCartAdapter;

    public static List<String> updatecartList = new ArrayList<>();

    ProgressDialog progress ;

    public static Handler handlerm;

    public View rootView;

    public static JSONArray cart_products          ;



    public ShoppingCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        cart_products =null;
        storeList.clear();

        sethandler();
        rootView = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        getcart();
        ((MainActivity)getActivity()).mTitle.setText("Shopping Cart");
        ((MainActivity)getActivity()).getSupportActionBar().hide();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 0);



        /*recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(getActivity(), String.valueOf(position) + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        Button btnCheckOut = (Button) rootView.findViewById(R.id.btnCheckOut);
        btnCheckOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(updatecartList.size()>0)
                {
                      updatecart();
                }
                else
                {
                    ((MainActivity)getActivity()).getSupportActionBar().show();
                    ((MainActivity) getActivity()).navigateToReplace(new CheckOutFragment(), null, true);
                }
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    public void getcart()
    {
        progress = new ProgressDialog(rootView.getContext());
        progress.setMessage("Loading Cart......");
        progress.show();
        Seller seller;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //String url = "http://streetshops.sg/dashboard/appapi/getCart.php?cartid=" +cart_id +
                      //      "&userid="+user_id;

                    String url = "http://streetshops.sg/dashboard/appapi/getCart.php?cartid=" +cart_id +
                                "&userid="+user_id;

                    StringBuilder body = new StringBuilder();
                    DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                    HttpPost httpGet = new HttpPost(url); // create new httpGet object
                    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");

                    System.out.println("Cool0 Product id"+cart_id);

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


    public void updatecart()
    {
        progress = new ProgressDialog(rootView.getContext());
        progress.setMessage("Updating cart.....");
        progress.show();
        Seller seller;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String products ="";
                    for(int i=0;i<ShoppingCartFragment.updatecartList.size();i++)
                    {
                        if(i==ShoppingCartFragment.updatecartList.size()-1)
                        {
                            products = products + ShoppingCartFragment.updatecartList.get(i) ;
                        }
                        else
                        {
                            products = products + ShoppingCartFragment.updatecartList.get(i) + "---";
                        }
                    }
                    System.out.println("Coolbb"+products);
                    //String url = "http://streetshops.sg/dashboard/appapi/getCart.php?cartid=" +cart_id +
                    //      "&userid="+user_id;

                    String url = "http://streetshops.sg/dashboard/appapi/updatecart.php?cartid=" +cart_id +
                            "&products="+products;

                    StringBuilder body = new StringBuilder();
                    DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                    HttpPost httpGet = new HttpPost(url); // create new httpGet object
                    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");

                    System.out.println("Cool0 Product id"+cart_id);

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

                    b.putInt("key", 200);
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

    public void updatecart1()
    {
        progress = new ProgressDialog(rootView.getContext());
        progress.setMessage("Updating cart.....");
        progress.show();
        Seller seller;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String products ="";
                    for(int i=0;i<ShoppingCartFragment.updatecartList.size();i++)
                    {
                        if(i==ShoppingCartFragment.updatecartList.size()-1)
                        {
                            products = products + ShoppingCartFragment.updatecartList.get(i) ;
                        }
                        else
                        {
                            products = products + ShoppingCartFragment.updatecartList.get(i) + "---";
                        }
                    }
                    System.out.println("Coolbb"+products);
                    //String url = "http://streetshops.sg/dashboard/appapi/getCart.php?cartid=" +cart_id +
                    //      "&userid="+user_id;

                    String url = "http://streetshops.sg/dashboard/appapi/updatecart.php?cartid=" +cart_id +
                            "&products="+products;

                    StringBuilder body = new StringBuilder();
                    DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                    HttpPost httpGet = new HttpPost(url); // create new httpGet object
                    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");

                    System.out.println("Cool0 Product id"+cart_id);

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

                    b.putInt("key", 201);
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
                    if (keyy == 200)
                    {
                        System.out.println("Cool" + px);
                        progress.dismiss();
                        Toast.makeText(getActivity(), "Cart Updated.", Toast.LENGTH_SHORT).show();
                        updatecartList.clear();
                        ((MainActivity)getActivity()).getSupportActionBar().show();
                        ((MainActivity) getActivity()).navigateToReplace(new CheckOutFragment(), null, true);
                    }

                    if (keyy == 201)
                    {
                        System.out.println("Cool" + px);
                        progress.dismiss();
                        Toast.makeText(getActivity(), "Cart Updated.", Toast.LENGTH_SHORT).show();
                        updatecartList.clear();
                        ((MainActivity)getActivity()).getSupportActionBar().show();
                        getActivity().onBackPressed();
                    }

                    if (keyy == 199)
                    {
                        System.out.println("Cool" + px);
                        progress.dismiss();
                        //cart_id  =
                        try
                        {
                            JSONObject json = new JSONObject(px);
                            String responsee = json.getString("status");
                            System.out.println("Coolo");
                            if(responsee.equals("1"))
                            {
                                JSONObject jsnobject = new JSONObject(json.getString("cart_products"));
                                JSONObject jsnobject1 = new JSONObject(json.getString("cart_total_summary"));
                                System.out.println("Coolox");
                                TextView shimpment_total    =   (TextView) rootView.findViewById (R.id. shimpment_total);
                                TextView total    =   (TextView) rootView.findViewById (R.id. total);
                                TextView products_count    =   (TextView) rootView.findViewById (R.id. products_count);
                                System.out.println("Cooloxx");
                                shimpment_total.setText ("$"+jsnobject1.getString("shimpment_total"));
                                total.setText ("$"+jsnobject1.getString("total"));
                                products_count.setText (jsnobject1.getString("products_count"));
                                System.out.println("Cooloxxx");

                                System.out.println("Coolo1"+jsnobject.getJSONArray("products"));
                                JSONArray jsonArray = jsnobject.getJSONArray("products");
                                System.out.println("Coolo2");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject explrObject = jsonArray.getJSONObject(i);
                                    System.out.println("Coolo22"+explrObject.getString("product_variation"));

                                    storeList.add(explrObject.getString("product_id")+"~~~"+
                                            explrObject.getString("product_quantity")+"~~~"+
                                            explrObject.getString("product_name")+"~~~"+
                                            explrObject.getString("product_img")+"~~~"+
                                            explrObject.getString("product_store")+"~~~"+
                                            explrObject.getString("product_price")+"~~~"+
                                            explrObject.getString("product_total")+"~~~"+
                                            explrObject.getString("product_variation")+"~~~"+
                                            explrObject.getString("product_variationID")+"~~~"
                                    );
                                    //storeList.add("2~~~rehan2~~~hell2");
                                    shippingCartAdapter = new ShippingCartAdapter(storeList,getActivity());
                                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                                    recyclerView.setLayoutManager(mLayoutManager);
                                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                                    recyclerView.setAdapter(shippingCartAdapter);

                                }
                                System.out.println("Coolo3");
                              /*  JSONObject json1xx  = new JSONObject(cart_products.getString(0));
                               // cart_products      =  json.getJSONArray("cart_products");
                               // JSONObject json1xx  = new JSONObject(cart_products.getString(0));
                               // JSONArray json1xx1 = json1xx.getJSONArray("products");
                                System.out.println("Coolk");
                                for(int i=0;i<=cart_products.length()-1;i++)
                                {
                                    //JSONArray json1xx2  = json1xx1.getJSONArray(i);
                                    System.out.println("Coolm"+cart_products.get(i).toString());
                                }

                               // System.out.println("Coolt"+json1xx.toString());

*/
                            }
                            else
                            {
                                Toast.makeText(getActivity(), "Failed to add product to cart Please contact admin.", Toast.LENGTH_SHORT).show();

                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println("Cooln"+e.getMessage());
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

    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    if(updatecartList.size()>0)
                    {
                        updatecart1();
                    }
                    else
                    {
                        ((MainActivity)getActivity()).getSupportActionBar().show();
                       getActivity().onBackPressed();

                    }

                    return true;

                }

                return false;
            }
        });
    }



}
