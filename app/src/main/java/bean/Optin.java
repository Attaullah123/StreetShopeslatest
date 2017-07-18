package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Optin implements Parcelable
{

public final static Creator<Optin> CREATOR = new Creator<Optin>() {


@SuppressWarnings({
"unchecked"
})
public Optin createFromParcel(Parcel in) {
Optin instance = new Optin();
return instance;
}

public Optin[] newArray(int size) {
return (new Optin[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
