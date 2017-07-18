package bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Associations implements Parcelable
{

@SerializedName("groups")
@Expose
private Groups groups;
public final static Creator<Associations> CREATOR = new Creator<Associations>() {


@SuppressWarnings({
"unchecked"
})
public Associations createFromParcel(Parcel in) {
Associations instance = new Associations();
instance.groups = ((Groups) in.readValue((Groups.class.getClassLoader())));
return instance;
}

public Associations[] newArray(int size) {
return (new Associations[size]);
}

}
;

/**
* No args constructor for use in serialization
*
*/
public Associations() {
}

/**
*
* @param groups
*/
public Associations(Groups groups) {
super();
this.groups = groups;
}

public Groups getGroups() {
return groups;
}

public void setGroups(Groups groups) {
this.groups = groups;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(groups);
}

public int describeContents() {
return 0;
}

}
