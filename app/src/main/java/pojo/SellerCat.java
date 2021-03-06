package pojo;

import android.os.Parcelable;


import java.util.List;
import android.os.Parcel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerCat implements Parcelable
{

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("sellerbanner")
@Expose
private String sellerbanner;
@SerializedName("sellerCategories")
@Expose
private List<SellerCategory> sellerCategories = null;
@SerializedName("msg")
@Expose
private String msg;
public final static Parcelable.Creator<SellerCat> CREATOR = new Creator<SellerCat>() {


@SuppressWarnings({
"unchecked"
})
public SellerCat createFromParcel(Parcel in) {
SellerCat instance = new SellerCat();
instance.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
instance.sellerbanner = ((String) in.readValue((String.class.getClassLoader())));
in.readList(instance.sellerCategories, (pojo.SellerCategory.class.getClassLoader()));
instance.msg = ((String) in.readValue((String.class.getClassLoader())));
return instance;
}

public SellerCat[] newArray(int size) {
return (new SellerCat[size]);
}

}
;

public Integer getStatus() {
return status;
}

public void setStatus(Integer status) {
this.status = status;
}

public String getSellerbanner() {
return sellerbanner;
}

public void setSellerbanner(String sellerbanner) {
this.sellerbanner = sellerbanner;
}

public List<SellerCategory> getSellerCategories() {
return sellerCategories;
}

public void setSellerCategories(List<SellerCategory> sellerCategories) {
this.sellerCategories = sellerCategories;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(status);
dest.writeValue(sellerbanner);
dest.writeList(sellerCategories);
dest.writeValue(msg);
}

public int describeContents() {
return 0;
}

}
