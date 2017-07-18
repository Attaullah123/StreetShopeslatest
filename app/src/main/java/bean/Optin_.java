package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Optin_ implements Parcelable
{

public final static Creator<Optin_> CREATOR = new Creator<Optin_>() {


@SuppressWarnings({
"unchecked"
})
public Optin_ createFromParcel(Parcel in) {
Optin_ instance = new Optin_();
return instance;
}

public Optin_[] newArray(int size) {
return (new Optin_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
