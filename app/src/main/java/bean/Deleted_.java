package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Deleted_ implements Parcelable
{

public final static Creator<Deleted_> CREATOR = new Creator<Deleted_>() {


@SuppressWarnings({
"unchecked"
})
public Deleted_ createFromParcel(Parcel in) {
Deleted_ instance = new Deleted_();
return instance;
}

public Deleted_[] newArray(int size) {
return (new Deleted_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
