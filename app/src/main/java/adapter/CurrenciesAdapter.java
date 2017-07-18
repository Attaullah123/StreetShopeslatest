package adapter;

/**
 * Created by hardik on 16/12/16.
 */

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Fragments.ProfileFragment;

import static utils.AppConstant.user_id;

public class CurrenciesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    public static Handler handlerm;

    List<String> list;
    Context context;


    public CurrenciesAdapter(Context context, List<String> list)
    {
        this.context = context;
        this.list = list;
        System.out.println("Cool"+list.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.row_currencies_header, parent, false);
            return new HeaderViewHolder (v);
        } else if(viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.row_currencies_footer, parent, false);
            return new FooterViewHolder (v);
        } else if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.row_currencies_generic, parent, false);
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
        }
        else
        if(holder instanceof GenericViewHolder)
        {
            String[] currentItem = getItem (position - 1).split("~~~");
            System.out.println("position"+String.valueOf(position - 1) +"ee"+currentItem[0]+currentItem[1]);
            GenericViewHolder genericViewHolder = (GenericViewHolder) holder;
            genericViewHolder.Username.setText (currentItem[0]);
            genericViewHolder.Rating.setText(currentItem[2]);
            genericViewHolder.Comments.setText (currentItem[1].replace(",",""));
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

        public FooterViewHolder (View itemView) {
            super (itemView);
            //this.txtTitleHeader = (TextView) itemView.findViewById (R.id.txtHeader);
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
        TextView Rating;
        TextView Comments;
        public GenericViewHolder (View itemView)
        {
            super (itemView);

            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            this.Username    =   (TextView) itemView.findViewById (R.id.usernamee);
            this.Username.setText("Call");
            this.Rating      =   (TextView) itemView.findViewById (R.id.ratingg);
            this.Comments    =   (TextView) itemView.findViewById (R.id.commentss);

        }


    }
}
