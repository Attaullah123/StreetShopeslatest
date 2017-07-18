package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class OutstandingAllowAmount_ implements Parcelable
{

public final static Creator<OutstandingAllowAmount_> CREATOR = new Creator<OutstandingAllowAmount_>() {


@SuppressWarnings({
"unchecked"
})
public OutstandingAllowAmount_ createFromParcel(Parcel in) {
OutstandingAllowAmount_ instance = new OutstandingAllowAmount_();
return instance;
}

public OutstandingAllowAmount_[] newArray(int size) {
return (new OutstandingAllowAmount_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
