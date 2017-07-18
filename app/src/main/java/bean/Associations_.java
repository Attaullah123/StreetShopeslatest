package bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Associations_ implements Parcelable
{

@SerializedName("groups")
@Expose
private Groups_ groups;
public final static Creator<Associations_> CREATOR = new Creator<Associations_>() {


@SuppressWarnings({
"unchecked"
})
public Associations_ createFromParcel(Parcel in) {
Associations_ instance = new Associations_();
instance.groups = ((Groups_) in.readValue((Groups_.class.getClassLoader())));
return instance;
}

public Associations_[] newArray(int size) {
return (new Associations_[size]);
}

}
;

/**
* No args constructor for use in serialization
*
*/
public Associations_() {
}

/**
*
* @param groups
*/
public Associations_(Groups_ groups) {
super();
this.groups = groups;
}

public Groups_ getGroups() {
return groups;
}

public void setGroups(Groups_ groups) {
this.groups = groups;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(groups);
}

public int describeContents() {
return 0;
}

}
