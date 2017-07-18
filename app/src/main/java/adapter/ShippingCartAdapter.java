package adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Fragments.CheckOutFragment;
import Fragments.ProfileFragment;
import Fragments.ShoppingCartFragment;

import static utils.AppConstant.cart_id;
import static utils.AppConstant.user_id;

/**
 * Created by hardik on 13/12/16.
 */

public class ShippingCartAdapter extends RecyclerView.Adapter<ShippingCartAdapter.MyViewHolder> {

    private List<String> list;
    public Context mContext;
    public static Handler handlerm;
    ProgressDialog progress ;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView  product_id;
        TextView  product_quantity;
        TextView  product_name;
        ImageView product_img;
        TextView  product_store;
        TextView  product_price;
        TextView  product_total;
        TextView  product_variation;
        TextView  product_variationID;

        ImageView product_remove;
        ImageView product_subtract;
        ImageView product_add;

        public MyViewHolder(View view) {
            super(view);
            this. product_id    =   (TextView) itemView.findViewById (R.id. product_id);
            this. product_quantity    =   (TextView) itemView.findViewById (R.id. product_quantity);
            this. product_name    =   (TextView) itemView.findViewById (R.id. product_name);
            this. product_img   =   (ImageView) itemView.findViewById (R.id.product_img);
            this. product_store    =   (TextView) itemView.findViewById (R.id. product_store);
            this. product_price    =   (TextView) itemView.findViewById (R.id. product_price);
            this. product_total    =   (TextView) itemView.findViewById (R.id. product_total);
            this. product_variation    =   (TextView) itemView.findViewById (R.id. product_variation);
            this. product_variationID    =   (TextView) itemView.findViewById (R.id. product_variationID);

            this. product_remove     =   (ImageView) itemView.findViewById (R.id.product_remove);
            this. product_subtract   =   (ImageView) itemView.findViewById (R.id.product_subtract);
            this. product_add        =   (ImageView) itemView.findViewById (R.id.product_add);


            //title = (TextView) view.findViewById(R.id.title);
        }
    }


    public ShippingCartAdapter(List<String> storeList, FragmentActivity activity) {
        this.list = storeList;
        mContext = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_shipping_cart, parent, false);

        return new MyViewHolder(itemView);
    }

    private String getItem (int position) {
        return list .get (position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        final String[] currentItem = getItem (position ).split("~~~");
        System.out.println("Cart "+currentItem[1]);

        final  MyViewHolder  MyViewHolderx = (ShippingCartAdapter.MyViewHolder) holder;


        MyViewHolderx.product_id.setText (currentItem[0]);
        MyViewHolderx.product_quantity.setText (currentItem[1]);
        MyViewHolderx.product_name.setText (currentItem[2]);
        MyViewHolderx.product_store.setText (currentItem[4]);
        MyViewHolderx.product_price.setText ("$"+currentItem[5]);
        MyViewHolderx.product_total.setText ("$"+currentItem[6]);
        MyViewHolderx.product_variation.setText (currentItem[7]);
        MyViewHolderx.product_variationID.setText (currentItem[8]);

        Glide.with(mContext)
                .load(currentItem[3])
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)

                .into(MyViewHolderx.product_img);



        MyViewHolderx.product_remove.setTag(getItem (position ).split("~~~"));
        MyViewHolderx.product_subtract.setTag(getItem (position ).split("~~~"));
        MyViewHolderx.product_add.setTag(getItem (position ).split("~~~"));

        MyViewHolderx.product_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                System.out.println("product_remove :"+view.getTag());

                String x = currentItem[0];

                removeproductfromcart(x);

            }
        });

        MyViewHolderx.product_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                System.out.println("product_remove :"+view.getTag());
                int qx =  Integer.valueOf(MyViewHolderx.product_quantity.getText().toString());
                if(qx>=2)
                {
                    qx=qx-1;
                    MyViewHolderx.product_quantity.setText(String.valueOf(qx));

                    String x = qx +"--"+currentItem[0]+"--"+currentItem[8];

                    for(int i=0;i<ShoppingCartFragment.updatecartList.size();i++)
                    {
                        String[] updateitem1 = ShoppingCartFragment.updatecartList.get(i).split("--");
                        if(updateitem1[1].equals(currentItem[0])&&updateitem1[2].equals(currentItem[8]))
                        {
                            ShoppingCartFragment.updatecartList.remove(i);
                            break;
                        }
                    }

                    ShoppingCartFragment.updatecartList.add(x);

                    for(int i=0;i<ShoppingCartFragment.updatecartList.size();i++)
                    {
                        System.out.println("Cool "+ShoppingCartFragment.updatecartList.get(i));

                    }
                }
                else
                {
                    Toast.makeText(mContext, "Product Quantity Cannot be less than 1.", Toast.LENGTH_SHORT).show();

                }



            }
        });

        MyViewHolderx.product_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                System.out.println("product_add :"+view.getTag());
                int qx =  Integer.valueOf(MyViewHolderx.product_quantity.getText().toString());

                qx=qx+1;
                MyViewHolderx.product_quantity.setText(String.valueOf(qx));

                String x = qx +"--"+currentItem[0]+"--"+currentItem[8];


                for(int i=0;i<ShoppingCartFragment.updatecartList.size();i++)
                {
                    String[] updateitem1 = ShoppingCartFragment.updatecartList.get(i).split("--");
                    if(updateitem1[1].equals(currentItem[0])&&updateitem1[2].equals(currentItem[8]))
                    {
                        ShoppingCartFragment.updatecartList.remove(i);
                        break;
                    }
                }

                ShoppingCartFragment.updatecartList.add(x);

                for(int i=0;i<ShoppingCartFragment.updatecartList.size();i++)
                {
                    System.out.println("Cool "+ShoppingCartFragment.updatecartList.get(i));

                }
            }
        });


    // String storeName = storeList.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size () ;
    }


    public void removeproductfromcart(final String pid )
    {

        System.out.println("Cool"+pid);
        sethandler();
        progress = new ProgressDialog(mContext);
        progress.setMessage("Updating Cart.....");
        progress.show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://streetshops.sg/dashboard/appapi/deletecartproduct.php?cartid=" +cart_id+"&productid="+pid;
                    StringBuilder body = new StringBuilder();
                    DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                    HttpPost httpGet = new HttpPost(url); // create new httpGet object
                    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");




                    ArrayList KlientNameValuePairs = new ArrayList<NameValuePair>();



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

                    System.out.println(body.toString());

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

                if (b.isEmpty()) {
                    System.out.println("Empty");
                } else {
                    int keyy = b.getInt("key");
                    String px = b.getString("pm");

                    //hideDialog();


                    if (keyy == 92)
                    {
                        System.out.println("Coolsss"+px);
                        Toast.makeText(mContext, "Product removed .", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        ((MainActivity) mContext).navigateToReplace(new ShoppingCartFragment(), null, true);


                    }
                }
                }
                };

            ;
        }

    }