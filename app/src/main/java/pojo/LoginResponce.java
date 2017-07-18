package pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponce implements Parcelable
{

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("data")
@Expose
private Data data;
@SerializedName("message")
@Expose
private String message;
public final static Creator<LoginResponce> CREATOR = new Creator<LoginResponce>() {


@SuppressWarnings({
"unchecked"
})
public LoginResponce createFromParcel(Parcel in) {
LoginResponce instance = new LoginResponce();
instance.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
instance.data = ((Data) in.readValue((Data.class.getClassLoader())));
instance.message = ((String) in.readValue((String.class.getClassLoader())));
return instance;
}

public LoginResponce[] newArray(int size) {
return (new LoginResponce[size]);
}

}
;

/**
* No args constructor for use in serialization
*
*/
public LoginResponce() {
}

/**
*
* @param message
* @param status
* @param data
*/
public LoginResponce(Integer status, Data data, String message) {
super();
this.status = status;
this.data = data;
this.message = message;
}

public Integer getStatus() {
return status;
}

public void setStatus(Integer status) {
this.status = status;
}

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(status);
dest.writeValue(data);
dest.writeValue(message);
}

public int describeContents() {
return 0;
}

}
