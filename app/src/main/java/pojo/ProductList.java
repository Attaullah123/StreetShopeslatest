package pojo;


import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductList implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("sellerProducts")
    @Expose
    private List<SellerProduct> sellerProducts = null;
    @SerializedName("msg")
    @Expose
    private String msg;
    public final static Parcelable.Creator<ProductList> CREATOR = new Creator<ProductList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductList createFromParcel(Parcel in) {
            ProductList instance = new ProductList();
            instance.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.sellerProducts, (pojo.SellerProduct.class.getClassLoader()));
            instance.msg = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ProductList[] newArray(int size) {
            return (new ProductList[size]);
        }

    };

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SellerProduct> getSellerProducts() {
        return sellerProducts;
    }

    public void setSellerProducts(List<SellerProduct> sellerProducts) {
        this.sellerProducts = sellerProducts;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeList(sellerProducts);
        dest.writeValue(msg);
    }

    public int describeContents() {
        return 0;
    }

}


