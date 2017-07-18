package pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetails implements Parcelable
{

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("productDetails")
@Expose
private List<ProductDetail> productDetails = null;
@SerializedName("productImages")
@Expose
private List<ProductImage> productImages = null;
@SerializedName("productspecs")
@Expose
private List<Productspec> productspecs = null;
@SerializedName("productfeatures")
@Expose
private List<Productfeature> productfeatures = null;
@SerializedName("msg")
@Expose
private String msg;
public final static Creator<ProductDetails> CREATOR = new Creator<ProductDetails>() {


@SuppressWarnings({
"unchecked"
})
public ProductDetails createFromParcel(Parcel in) {
ProductDetails instance = new ProductDetails();
instance.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
in.readList(instance.productDetails, (ProductDetail.class.getClassLoader()));
in.readList(instance.productImages, (ProductImage.class.getClassLoader()));
in.readList(instance.productspecs, (Productspec.class.getClassLoader()));
in.readList(instance.productfeatures, (Productfeature.class.getClassLoader()));
instance.msg = ((String) in.readValue((String.class.getClassLoader())));
return instance;
}

public ProductDetails[] newArray(int size) {
return (new ProductDetails[size]);
}

}
;

public Integer getStatus() {
return status;
}

public void setStatus(Integer status) {
this.status = status;
}

public List<ProductDetail> getProductDetails() {
return productDetails;
}

public void setProductDetails(List<ProductDetail> productDetails) {
this.productDetails = productDetails;
}

public List<ProductImage> getProductImages() {
return productImages;
}

public void setProductImages(List<ProductImage> productImages) {
this.productImages = productImages;
}

public List<Productspec> getProductspecs() {
return productspecs;
}

public void setProductspecs(List<Productspec> productspecs) {
this.productspecs = productspecs;
}

public List<Productfeature> getProductfeatures() {
return productfeatures;
}

public void setProductfeatures(List<Productfeature> productfeatures) {
this.productfeatures = productfeatures;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(status);
dest.writeList(productDetails);
dest.writeList(productImages);
dest.writeList(productspecs);
dest.writeList(productfeatures);
dest.writeValue(msg);
}

public int describeContents() {
return 0;
}

}
