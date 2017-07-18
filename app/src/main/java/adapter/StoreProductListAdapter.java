package adapter;

import android.content.Context;
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

import pojo.SellerProduct;

/**
 * Created by hardik on 13/12/16.
 */

public class StoreProductListAdapter extends RecyclerView.Adapter<StoreProductListAdapter.MyViewHolder> {

    private List<SellerProduct> storeList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProductName,txtProductDetails,txtProductPrice;
        public ImageView imgItem;

        public MyViewHolder(View view) {
            super(view);
            txtProductName = (TextView) view.findViewById(R.id.txtProductName);
            txtProductDetails = (TextView) view.findViewById(R.id.txtProductDetails);
            txtProductPrice = (TextView) view.findViewById(R.id.txtProductPrice);
            imgItem = (ImageView) view.findViewById(R.id.imgItem);
        }
    }


    public StoreProductListAdapter(List<SellerProduct> storeList, Context applicationContext) {
        this.storeList = storeList;
        this.mContext = applicationContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_store_product_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SellerProduct sellerProduct = storeList.get(position);
        holder.txtProductName.setText(sellerProduct.getProductname());
        holder.txtProductDetails.setText(Html.fromHtml(sellerProduct.getProductdesc()));
        float f = Float.valueOf(sellerProduct.getProductprice());
        String test = String.format("%.02f", f);
        holder.txtProductPrice.setText("Price : $"+test);

        Glide.with(mContext)
                .load(sellerProduct.getProductImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)

                .into(holder.imgItem);
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }
}