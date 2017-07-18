package pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerProduct implements Parcelable {

    @SerializedName("productid")
    @Expose
    private String productid;
    @SerializedName("productprice")
    @Expose
    private String productprice;
    @SerializedName("productname")
    @Expose
    private String productname;
    @SerializedName("productdesc")
    @Expose
    private String productdesc;
    @SerializedName("product_image_url")
    @Expose


    private String productImageUrl;
    public final static Creator<SellerProduct> CREATOR = new Creator<SellerProduct>() {

        @SuppressWarnings({
                "unchecked"
        })
        public SellerProduct createFromParcel(Parcel in) {
            SellerProduct instance = new SellerProduct();
            instance.productid = ((String) in.readValue((String.class.getClassLoader())));
            instance.productprice = ((String) in.readValue((String.class.getClassLoader())));
            instance.productname = ((String) in.readValue((String.class.getClassLoader())));
            instance.productdesc = ((String) in.readValue((String.class.getClassLoader())));
            instance.productImageUrl = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public SellerProduct[] newArray(int size) {
            return (new SellerProduct[size]);
        }

    }
            ;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productid);
        dest.writeValue(productprice);
        dest.writeValue(productname);
        dest.writeValue(productdesc);
        dest.writeValue(productImageUrl);
    }

    public int describeContents() {
        return 0;
    }

}