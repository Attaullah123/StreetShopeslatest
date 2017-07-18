package bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupResponce implements Parcelable
{

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("data")
@Expose
private Data data;
@SerializedName("message")
@Expose
private Message message;
public final static Creator<SignupResponce> CREATOR = new Creator<SignupResponce>() {


@SuppressWarnings({
"unchecked"
})
public SignupResponce createFromParcel(Parcel in) {
SignupResponce instance = new SignupResponce();
instance.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
instance.data = ((Data) in.readValue((Data.class.getClassLoader())));
instance.message = ((Message) in.readValue((Message.class.getClassLoader())));
return instance;
}

public SignupResponce[] newArray(int size) {
return (new SignupResponce[size]);
}

}
;

/**
* No args constructor for use in serialization
*
*/
public SignupResponce() {
}

/**
*
* @param message
* @param status
* @param data
*/
public SignupResponce(Integer status, Data data, Message message) {
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

public Message getMessage() {
return message;
}

public void setMessage(Message message) {
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
