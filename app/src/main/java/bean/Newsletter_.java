package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Newsletter_ implements Parcelable
{

public final static Creator<Newsletter_> CREATOR = new Creator<Newsletter_>() {


@SuppressWarnings({
"unchecked"
})
public Newsletter_ createFromParcel(Parcel in) {
Newsletter_ instance = new Newsletter_();
return instance;
}

public Newsletter_[] newArray(int size) {
return (new Newsletter_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
