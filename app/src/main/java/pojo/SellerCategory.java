package pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SellerCategory implements Parcelable
{

@SerializedName("maincat_name")
@Expose
private String maincatName;
@SerializedName("subCategory")
@Expose
private List<SubCategory> subCategory = null;
public final static Creator<SellerCategory> CREATOR = new Creator<SellerCategory>() {


@SuppressWarnings({
"unchecked"
})
public SellerCategory createFromParcel(Parcel in) {
SellerCategory instance = new SellerCategory();
instance.maincatName = ((String) in.readValue((String.class.getClassLoader())));
in.readList(instance.subCategory, (SubCategory.class.getClassLoader()));
return instance;
}

public SellerCategory[] newArray(int size) {
return (new SellerCategory[size]);
}

}
;

public String getMaincatName() {
return maincatName;
}

public void setMaincatName(String maincatName) {
this.maincatName = maincatName;
}

public List<SubCategory> getSubCategory() {
return subCategory;
}

public void setSubCategory(List<SubCategory> subCategory) {
this.subCategory = subCategory;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(maincatName);
dest.writeList(subCategory);
}

public int describeContents() {
return 0;
}

}
