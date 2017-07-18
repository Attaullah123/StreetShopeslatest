package adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.streetshopes.R;

import java.util.List;

import bean.Datum;
import pojo.Seller;

/**
 * Created by hardik on 13/12/16.
 */

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.MyViewHolder> {

    private List<Seller> storList;
    Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtStoreName,txtLocationName;
        public ImageView imgStoreImage;

        public MyViewHolder(View view) {
            super(view);
            txtStoreName = (TextView) view.findViewById(R.id.txtStoreName);
            txtLocationName = (TextView) view.findViewById(R.id.txtLocationName);
            imgStoreImage = (ImageView) view.findViewById(R.id.imgStoreImage);
        }
    }


    public StoreListAdapter(List<Seller> storList, FragmentActivity activity) {
        this.storList = storList;
        mContext = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_store_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       // String storeName = storeList.get(position);
        Seller datum = storList.get(position);
        holder.txtStoreName.setText(datum.getTitle());
        holder.txtLocationName.setText(datum.getAddress());

        Glide.with(mContext)
                .load(datum.getBannerUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)

                .into(holder.imgStoreImage);


        //holder.imgStoreImage.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public int getItemCount() {
        return storList.size();
    }

    public Seller getItem(int position) {
        return storList.get(position);
    }
}