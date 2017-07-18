package bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Groups implements Parcelable
{

@SerializedName("@attributes")
@Expose
private Attributes attributes;
public final static Creator<Groups> CREATOR = new Creator<Groups>() {


@SuppressWarnings({
"unchecked"
})
public Groups createFromParcel(Parcel in) {
Groups instance = new Groups();
instance.attributes = ((Attributes) in.readValue((Attributes.class.getClassLoader())));
return instance;
}

public Groups[] newArray(int size) {
return (new Groups[size]);
}

}
;

/**
* No args constructor for use in serialization
*
*/
public Groups() {
}

/**
*
* @param attributes
*/
public Groups(Attributes attributes) {
super();
this.attributes = attributes;
}

public Attributes getAttributes() {
return attributes;
}

public void setAttributes(Attributes attributes) {
this.attributes = attributes;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(attributes);
}

public int describeContents() {
return 0;
}

}
