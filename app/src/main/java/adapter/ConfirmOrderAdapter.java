package adapter;

/**
 * Created by hardik on 16/12/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.streetshopes.R;

import java.util.List;

public class ConfirmOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    List<String> list;
    Context context;

    public ConfirmOrderAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        this.list.add("aaa");
        this.list.add("aaa");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.row_confirm_order_header, parent, false);
            return new HeaderViewHolder (v);
        } else if(viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.row_confirm_order_footer, parent, false);
            return new FooterViewHolder (v);
        } else if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.row_confirm_order_generic, parent, false);
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
            /*String currentItem = getItem (position - 1);
            GenericViewHolder genericViewHolder = (GenericViewHolder) holder;
            genericViewHolder.txtName.setText (currentItem);*/
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
        //TextView txtTitleFooter;

        public FooterViewHolder (View itemView) {
            super (itemView);
            //this.txtTitleFooter = (TextView) itemView.findViewById (R.id.txtFooter);
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
        //TextView txtName;

        public GenericViewHolder (View itemView) {
            super (itemView);
            //this.txtName = (TextView) itemView.findViewById (R.id.txtName);
        }
    }
}
