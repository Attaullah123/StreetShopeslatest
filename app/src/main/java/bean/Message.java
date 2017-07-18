package bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message implements Parcelable
{

@SerializedName("customer")
@Expose
private Customer_ customer;
public final static Creator<Message> CREATOR = new Creator<Message>() {


@SuppressWarnings({
"unchecked"
})
public Message createFromParcel(Parcel in) {
Message instance = new Message();
instance.customer = ((Customer_) in.readValue((Customer_.class.getClassLoader())));
return instance;
}

public Message[] newArray(int size) {
return (new Message[size]);
}

}
;

/**
* No args constructor for use in serialization
*
*/
public Message() {
}

/**
*
* @param customer
*/
public Message(Customer_ customer) {
super();
this.customer = customer;
}

public Customer_ getCustomer() {
return customer;
}

public void setCustomer(Customer_ customer) {
this.customer = customer;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(customer);
}

public int describeContents() {
return 0;
}

}
