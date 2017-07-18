package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class IpRegistrationNewsletter implements Parcelable
{

public final static Creator<IpRegistrationNewsletter> CREATOR = new Creator<IpRegistrationNewsletter>() {


@SuppressWarnings({
"unchecked"
})
public IpRegistrationNewsletter createFromParcel(Parcel in) {
IpRegistrationNewsletter instance = new IpRegistrationNewsletter();
return instance;
}

public IpRegistrationNewsletter[] newArray(int size) {
return (new IpRegistrationNewsletter[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
