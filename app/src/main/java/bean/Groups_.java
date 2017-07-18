package bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Groups_ implements Parcelable
{

@SerializedName("@attributes")
@Expose
private Attributes_ attributes;
public final static Creator<Groups_> CREATOR = new Creator<Groups_>() {


@SuppressWarnings({
"unchecked"
})
public Groups_ createFromParcel(Parcel in) {
Groups_ instance = new Groups_();
instance.attributes = ((Attributes_) in.readValue((Attributes_.class.getClassLoader())));
return instance;
}

public Groups_[] newArray(int size) {
return (new Groups_[size]);
}

}
;

/**
* No args constructor for use in serialization
*
*/
public Groups_() {
}

/**
*
* @param attributes
*/
public Groups_(Attributes_ attributes) {
super();
this.attributes = attributes;
}

public Attributes_ getAttributes() {
return attributes;
}

public void setAttributes(Attributes_ attributes) {
this.attributes = attributes;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(attributes);
}

public int describeContents() {
return 0;
}

}
