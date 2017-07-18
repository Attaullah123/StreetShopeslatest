package bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes_ implements Parcelable
{

@SerializedName("nodeType")
@Expose
private String nodeType;
@SerializedName("api")
@Expose
private String api;
public final static Creator<Attributes_> CREATOR = new Creator<Attributes_>() {


@SuppressWarnings({
"unchecked"
})
public Attributes_ createFromParcel(Parcel in) {
Attributes_ instance = new Attributes_();
instance.nodeType = ((String) in.readValue((String.class.getClassLoader())));
instance.api = ((String) in.readValue((String.class.getClassLoader())));
return instance;
}

public Attributes_[] newArray(int size) {
return (new Attributes_[size]);
}

}
;

/**
* No args constructor for use in serialization
*
*/
public Attributes_() {
}

/**
*
* @param api
* @param nodeType
*/
public Attributes_(String nodeType, String api) {
super();
this.nodeType = nodeType;
this.api = api;
}

public String getNodeType() {
return nodeType;
}

public void setNodeType(String nodeType) {
this.nodeType = nodeType;
}

public String getApi() {
return api;
}

public void setApi(String api) {
this.api = api;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(nodeType);
dest.writeValue(api);
}

public int describeContents() {
return 0;
}

}
