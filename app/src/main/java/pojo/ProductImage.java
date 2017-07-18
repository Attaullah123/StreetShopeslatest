package pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductImage implements Parcelable
{

@SerializedName("imageurl")
@Expose
private String imageurl;
public final static Creator<ProductImage> CREATOR = new Creator<ProductImage>() {


@SuppressWarnings({
"unchecked"
})
public ProductImage createFromParcel(Parcel in) {
ProductImage instance = new ProductImage();
instance.imageurl = ((String) in.readValue((String.class.getClassLoader())));
return instance;
}

public ProductImage[] newArray(int size) {
return (new ProductImage[size]);
}

}
;

public String getImageurl() {
return imageurl;
}

public void setImageurl(String imageurl) {
this.imageurl = imageurl;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(imageurl);
}

public int describeContents() {
return 0;
}

}
