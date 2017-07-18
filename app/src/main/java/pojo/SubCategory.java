package pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategory implements Parcelable
{

@SerializedName("subcatid")
@Expose
private String subcatid;
@SerializedName("subcatname")
@Expose
private String subcatname;
@SerializedName("subcatdesc")
@Expose
private String subcatdesc;
@SerializedName("subcat_image_url")
@Expose
private String subcatImageUrl;
public final static Creator<SubCategory> CREATOR = new Creator<SubCategory>() {


@SuppressWarnings({
"unchecked"
})
public SubCategory createFromParcel(Parcel in) {
SubCategory instance = new SubCategory();
instance.subcatid = ((String) in.readValue((String.class.getClassLoader())));
instance.subcatname = ((String) in.readValue((String.class.getClassLoader())));
instance.subcatdesc = ((String) in.readValue((String.class.getClassLoader())));
instance.subcatImageUrl = ((String) in.readValue((String.class.getClassLoader())));
return instance;
}

public SubCategory[] newArray(int size) {
return (new SubCategory[size]);
}

}
;

public String getSubcatid() {
return subcatid;
}

public void setSubcatid(String subcatid) {
this.subcatid = subcatid;
}

public String getSubcatname() {
return subcatname;
}

public void setSubcatname(String subcatname) {
this.subcatname = subcatname;
}

public String getSubcatdesc() {
return subcatdesc;
}

public void setSubcatdesc(String subcatdesc) {
this.subcatdesc = subcatdesc;
}

public String getSubcatImageUrl() {
return subcatImageUrl;
}

public void setSubcatImageUrl(String subcatImageUrl) {
this.subcatImageUrl = subcatImageUrl;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(subcatid);
dest.writeValue(subcatname);
dest.writeValue(subcatdesc);
dest.writeValue(subcatImageUrl);
}

public int describeContents() {
return 0;
}

}
