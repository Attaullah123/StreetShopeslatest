package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class IsGuest_ implements Parcelable
{

public final static Creator<IsGuest_> CREATOR = new Creator<IsGuest_>() {


@SuppressWarnings({
"unchecked"
})
public IsGuest_ createFromParcel(Parcel in) {
IsGuest_ instance = new IsGuest_();
return instance;
}

public IsGuest_[] newArray(int size) {
return (new IsGuest_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
