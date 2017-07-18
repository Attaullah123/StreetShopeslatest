package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class OutstandingAllowAmount implements Parcelable
{

public final static Creator<OutstandingAllowAmount> CREATOR = new Creator<OutstandingAllowAmount>() {


@SuppressWarnings({
"unchecked"
})
public OutstandingAllowAmount createFromParcel(Parcel in) {
OutstandingAllowAmount instance = new OutstandingAllowAmount();
return instance;
}

public OutstandingAllowAmount[] newArray(int size) {
return (new OutstandingAllowAmount[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
