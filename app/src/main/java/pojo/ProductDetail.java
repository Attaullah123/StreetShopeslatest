package pojo;

import android.os.Parcelable;


import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetail implements Parcelable
{

@SerializedName("product_id")
@Expose
private String productId;
@SerializedName("productname")
@Expose
private String productname;
@SerializedName("productprice")
@Expose
private String productprice;
@SerializedName("productdesc")
@Expose
private String productdesc;
public final static Parcelable.Creator<ProductDetail> CREATOR = new Creator<ProductDetail>() {


@SuppressWarnings({
"unchecked"
})
public ProductDetail createFromParcel(Parcel in) {
ProductDetail instance = new ProductDetail();
instance.productId = ((String) in.readValue((String.class.getClassLoader())));
instance.productname = ((String) in.readValue((String.class.getClassLoader())));
instance.productprice = ((String) in.readValue((String.class.getClassLoader())));
instance.productdesc = ((String) in.readValue((String.class.getClassLoader())));
return instance;
}

public ProductDetail[] newArray(int size) {
return (new ProductDetail[size]);
}

}
;

public String getProductId() {
return productId;
}

public void setProductId(String productId) {
this.productId = productId;
}

public String getProductname() {
return productname;
}

public void setProductname(String productname) {
this.productname = productname;
}

public String getProductprice() {
return productprice;
}

public void setProductprice(String productprice) {
this.productprice = productprice;
}

public String getProductdesc() {
return productdesc;
}

public void setProductdesc(String productdesc) {
this.productdesc = productdesc;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(productId);
dest.writeValue(productname);
dest.writeValue(productprice);
dest.writeValue(productdesc);
}

public int describeContents() {
return 0;
}

}
