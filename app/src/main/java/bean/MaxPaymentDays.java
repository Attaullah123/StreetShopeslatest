package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MaxPaymentDays implements Parcelable
{

public final static Creator<MaxPaymentDays> CREATOR = new Creator<MaxPaymentDays>() {


@SuppressWarnings({
"unchecked"
})
public MaxPaymentDays createFromParcel(Parcel in) {
MaxPaymentDays instance = new MaxPaymentDays();
return instance;
}

public MaxPaymentDays[] newArray(int size) {
return (new MaxPaymentDays[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
