package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MaxPaymentDays_ implements Parcelable
{

public final static Creator<MaxPaymentDays_> CREATOR = new Creator<MaxPaymentDays_>() {


@SuppressWarnings({
"unchecked"
})
public MaxPaymentDays_ createFromParcel(Parcel in) {
MaxPaymentDays_ instance = new MaxPaymentDays_();
return instance;
}

public MaxPaymentDays_[] newArray(int size) {
return (new MaxPaymentDays_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
