package adapter;

/**
 * Created by hardik on 16/12/16.
 */

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;
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
import java.util.List;

import Fragments.ProfileFragment;

import static utils.AppConstant.user_id;

public class SellerReviewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    public static Handler handlerm;

    List<String> list;
    Context context;


    public SellerReviewsAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        System.out.println("Cool"+list.size());


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.row_reviews_header, parent, false);
            return new HeaderViewHolder (v);
        } else if(viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.row_reviews_footer, parent, false);
            return new FooterViewHolder (v);
        } else if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.row_reviews_generic, parent, false);
            return new GenericViewHolder (v);
        }
        return null;
    }

    private String getItem (int position) {
        return list .get (position);
    }


    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            /*headerHolder.txtTitleHeader.setText ("Header");
            headerHolder.txtTitleHeader.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick (View view) {
                    Toast.makeText (context, "Clicked Header", Toast.LENGTH_SHORT).show ();
                }
            });*/
        } else if(holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
            /*footerHolder.txtTitleFooter.setText ("Footer");
            footerHolder.txtTitleFooter.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick (View view) {
                    Toast.makeText (context, "Clicked Footer", Toast.LENGTH_SHORT).show ();
                }
            });*/
        } else if(holder instanceof GenericViewHolder) {
            String[] currentItem = getItem (position - 1).split("~~~");
            System.out.println("position"+String.valueOf(position - 1) +"ee"+currentItem[0]+currentItem[1]);
            GenericViewHolder genericViewHolder = (GenericViewHolder) holder;
            genericViewHolder.Username.setText (currentItem[0]);
            if(Math.round(Float.valueOf(currentItem[2]))==1)
            {
                genericViewHolder.Rating.setImageResource(R.drawable.onestar);
            }

            if(Math.round(Float.valueOf(currentItem[2]))==2)
            {
                genericViewHolder.Rating.setImageResource(R.drawable.twostar);
            }

            if(Math.round(Float.valueOf(currentItem[2]))==3)
            {
                genericViewHolder.Rating.setImageResource(R.drawable.threestar);
            }

            if(Math.round(Float.valueOf(currentItem[2]))==4)
            {
                genericViewHolder.Rating.setImageResource(R.drawable.fourstar);
            }

            if(Math.round(Float.valueOf(currentItem[2]))==5)
            {
                genericViewHolder.Rating.setImageResource(R.drawable.fivestar);
            }

            genericViewHolder.Comments.setText (currentItem[1]);
        }
    }

    //    need to override this method
    @Override
    public int getItemViewType (int position) {
        if(isPositionHeader (position)) {
            return TYPE_HEADER;
        } else if(isPositionFooter (position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader (int position) {
        return position == 0;
    }

    private boolean isPositionFooter (int position) {
        return position == list.size () + 1;
    }

    @Override
    public int getItemCount () {
        return list.size () + 2;
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public SimpleRatingBar Ratingggg;
        public EditText Usernameeeee;
        public EditText  Reviewwwww;
        public Button reviewitttt;
        ProgressDialog progress ;

        public FooterViewHolder (View itemView) {
            super (itemView);
            this.Ratingggg     = (SimpleRatingBar) itemView.findViewById (R.id.ratingggg);
            this.Usernameeeee  = (EditText) itemView.findViewById (R.id.usernameee);
            this.Reviewwwww    = (EditText) itemView.findViewById (R.id.reviewwww);
            this.reviewitttt   = (Button) itemView.findViewById (R.id.reviewittt);


            this.reviewitttt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    System.out.println("beteal"+String.valueOf(Math.round(Ratingggg.getRating())));
                    if(Usernameeeee.getText().toString().equals("")||Reviewwwww.getText().toString().equals(""))
                    {

                        new AlertDialog.Builder(view.getContext())
                                .setTitle("Failed to add seller review")
                                .setMessage("Please fill all fields.")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which)
                                    {

                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                    else
                    {
                        addReview(Usernameeeee.getText().toString(),Reviewwwww.getText().toString(),String.valueOf(Math.round(Ratingggg.getRating())));
                    }
                }
            });
        }

        public void addReview(final String name ,final String description,final String rate)
        {

            System.out.println("calm man calm"+name);
            sethandler();
            progress = new ProgressDialog(context);
            progress.setMessage("Loading Seller Reviews");
            progress.show();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String url = "http://streetshops.sg/dashboard/appapi/addSellerReview.php";
                        StringBuilder body = new StringBuilder();
                        DefaultHttpClient httpclient = new DefaultHttpClient(); // create new httpClient
                        HttpPost httpGet = new HttpPost(url); // create new httpGet object
                        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
                        httpGet.addHeader("customerid", user_id);
                        httpGet.addHeader("sellerid", ProfileFragment.sellersid);
                        httpGet.addHeader("name", name);
                        httpGet.addHeader("description", description);
                        httpGet.addHeader("rate", rate);


                        ArrayList KlientNameValuePairs = new ArrayList<NameValuePair>();
                        KlientNameValuePairs.add(new BasicNameValuePair("customerid",user_id));
                        KlientNameValuePairs.add(new BasicNameValuePair("sellerid",ProfileFragment.sellersid));
                        KlientNameValuePairs.add(new BasicNameValuePair("name",name));
                        KlientNameValuePairs.add(new BasicNameValuePair("description",description));
                        KlientNameValuePairs.add(new BasicNameValuePair("rate",rate));


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

                    if (b.isEmpty())
                    {
                        System.out.println("Empty");
                    }
                    else
                    {
                        int keyy = b.getInt("key");
                        String px = b.getString("pm");

                        //hideDialog();





                        if (keyy == 92)
                        {
                            System.out.println("Cool"+px);


                            try {
                                JSONObject json = new JSONObject(px);
                                String responsee = json.getString("status");
                                progress.dismiss();

                                if(responsee.equals("1"))
                                {
                                    new AlertDialog.Builder(context)
                                            .setTitle("Review added.")
                                            .setMessage("Review sucessfuly added")
                                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which)
                                                {

                                                }
                                            })
                                            .setIcon(android.R.drawable.ic_dialog_alert)
                                            .show();

                                }
                                else
                                {
                                    new AlertDialog.Builder(context)
                                            .setTitle("Fail to add review")
                                            .setMessage("You have already Reviewed this seller.")
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

    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        //TextView txtTitleHeader;



        public HeaderViewHolder (View itemView) {
            super (itemView);
            //this.txtTitleHeader = (TextView) itemView.findViewById (R.id.txtHeader);
        }
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        TextView Username;
        ImageView Rating;
        TextView Comments;
        public GenericViewHolder (View itemView)
        {
            super (itemView);

            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            this.Username    =   (TextView) itemView.findViewById (R.id.usernamee);
            this.Username.setText("Call");
            this.Rating      =   (ImageView) itemView.findViewById (R.id.ratingg);
            this.Comments    =   (TextView) itemView.findViewById (R.id.commentss);

        }


    }
}
