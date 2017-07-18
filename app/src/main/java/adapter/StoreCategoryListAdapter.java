package adapter;

import android.content.Context;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.streetshopes.R;

import java.util.List;

import pojo.SellerCat;
import pojo.SubCategory;

/**
 * Created by hardik on 13/12/16.
 */

public class StoreCategoryListAdapter extends RecyclerView.Adapter<StoreCategoryListAdapter.MyViewHolder> {

    private List<SubCategory> subCategories;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProductName, txtProductDetails;
        public ImageView imgItem;

        public MyViewHolder(View view) {
            super(view);
            txtProductName = (TextView) view.findViewById(R.id.txtProductName);
            txtProductDetails = (TextView) view.findViewById(R.id.txtProductDetails);
            imgItem = (ImageView) view.findViewById(R.id.imgItem);
        }
    }


    public StoreCategoryListAdapter(List<SubCategory> storeList, FragmentActivity activity) {
        this.subCategories = storeList;
        context = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_seller_store_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       SubCategory subCategory = subCategories.get(position);
        holder.txtProductName.setText(subCategory.getSubcatname());
        holder.txtProductDetails.setText(Html.fromHtml(subCategory.getSubcatdesc()));

        Glide.with(context)
                .load(subCategory.getSubcatImageUrl())
                .into(holder.imgItem);
        Glide.with(context)
                .load(subCategory.getSubcatImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)

                .skipMemoryCache(true)
                .into(holder.imgItem);
    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }
}