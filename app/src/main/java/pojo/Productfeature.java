package pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Productfeature implements Parcelable
{

@SerializedName("featuress")
@Expose
private String featuress;
public final static Creator<Productfeature> CREATOR = new Creator<Productfeature>() {


@SuppressWarnings({
"unchecked"
})
public Productfeature createFromParcel(Parcel in) {
Productfeature instance = new Productfeature();
instance.featuress = ((String) in.readValue((String.class.getClassLoader())));
return instance;
}

public Productfeature[] newArray(int size) {
return (new Productfeature[size]);
}

}
;

public String getFeaturess() {
return featuress;
}

public void setFeaturess(String featuress) {
this.featuress = featuress;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(featuress);
}

public int describeContents() {
return 0;
}

}
