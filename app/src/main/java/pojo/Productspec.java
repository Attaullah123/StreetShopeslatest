package pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Productspec implements Parcelable
{

@SerializedName("productmanufacture")
@Expose
private String productmanufacture;
@SerializedName("productavilable")
@Expose
private String productavilable;
@SerializedName("productWeight")
@Expose
private String productWeight;
@SerializedName("productproductCode")
@Expose
private String productproductCode;
@SerializedName("productspeclist")
@Expose
private String productspeclist;
public final static Creator<Productspec> CREATOR = new Creator<Productspec>() {


@SuppressWarnings({
"unchecked"
})
public Productspec createFromParcel(Parcel in) {
Productspec instance = new Productspec();
instance.productmanufacture = ((String) in.readValue((String.class.getClassLoader())));
instance.productavilable = ((String) in.readValue((String.class.getClassLoader())));
instance.productWeight = ((String) in.readValue((String.class.getClassLoader())));
instance.productproductCode = ((String) in.readValue((String.class.getClassLoader())));
instance.productspeclist = ((String) in.readValue((String.class.getClassLoader())));
return instance;
}

public Productspec[] newArray(int size) {
return (new Productspec[size]);
}

}
;

public String getProductmanufacture() {
return productmanufacture;
}

public void setProductmanufacture(String productmanufacture) {
this.productmanufacture = productmanufacture;
}

public String getProductavilable() {
return productavilable;
}

public void setProductavilable(String productavilable) {
this.productavilable = productavilable;
}

public String getProductWeight() {
return productWeight;
}

public void setProductWeight(String productWeight) {
this.productWeight = productWeight;
}

public String getProductproductCode() {
return productproductCode;
}

public void setProductproductCode(String productproductCode) {
this.productproductCode = productproductCode;
}

public String getProductspeclist() {
return productspeclist;
}

public void setProductspeclist(String productspeclist) {
this.productspeclist = productspeclist;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(productmanufacture);
dest.writeValue(productavilable);
dest.writeValue(productWeight);
dest.writeValue(productproductCode);
dest.writeValue(productspeclist);
}

public int describeContents() {
return 0;
}

}
