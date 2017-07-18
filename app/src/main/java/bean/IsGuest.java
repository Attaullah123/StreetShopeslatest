package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class IsGuest implements Parcelable
{

public final static Creator<IsGuest> CREATOR = new Creator<IsGuest>() {


@SuppressWarnings({
"unchecked"
})
public IsGuest createFromParcel(Parcel in) {
IsGuest instance = new IsGuest();
return instance;
}

public IsGuest[] newArray(int size) {
return (new IsGuest[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
