package pojo;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedItem implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("sellerProducts")
    @Expose
    private List<SellerProduct> sellerProducts = null;
    @SerializedName("msg")
    @Expose
    private String msg;
    public final static Parcelable.Creator<RelatedItem> CREATOR = new Creator<RelatedItem>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RelatedItem createFromParcel(Parcel in) {
            RelatedItem instance = new RelatedItem();
            instance.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.sellerProducts, (pojo.SellerProduct.class.getClassLoader()));
            instance.msg = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public RelatedItem[] newArray(int size) {
            return (new RelatedItem[size]);
        }

    }
            ;

    /**
     * No args constructor for use in serialization
     *
     */
    public RelatedItem() {
    }

    /**
     *
     * @param status
     * @param msg
     * @param sellerProducts
     */
    public RelatedItem(Integer status, List<SellerProduct> sellerProducts, String msg) {
        super();
        this.status = status;
        this.sellerProducts = sellerProducts;
        this.msg = msg;
    }

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