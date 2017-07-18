package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Website_ implements Parcelable
{

public final static Creator<Website_> CREATOR = new Creator<Website_>() {


@SuppressWarnings({
"unchecked"
})
public Website_ createFromParcel(Parcel in) {
Website_ instance = new Website_();
return instance;
}

public Website_[] newArray(int size) {
return (new Website_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
