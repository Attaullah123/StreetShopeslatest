package bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

@SerializedName("customer")
@Expose
private Customer customer;
public final static Creator<Data> CREATOR = new Creator<Data>() {


@SuppressWarnings({
"unchecked"
})
public Data createFromParcel(Parcel in) {
Data instance = new Data();
instance.customer = ((Customer) in.readValue((Customer.class.getClassLoader())));
return instance;
}

public Data[] newArray(int size) {
return (new Data[size]);
}

}
;

/**
* No args constructor for use in serialization
*
*/
public Data() {
}

/**
*
* @param customer
*/
public Data(Customer customer) {
super();
this.customer = customer;
}

public Customer getCustomer() {
return customer;
}

public void setCustomer(Customer customer) {
this.customer = customer;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(customer);
}

public int describeContents() {
return 0;
}

}
