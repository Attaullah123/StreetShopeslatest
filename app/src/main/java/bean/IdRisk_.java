package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class IdRisk_ implements Parcelable
{

public final static Creator<IdRisk_> CREATOR = new Creator<IdRisk_>() {


@SuppressWarnings({
"unchecked"
})
public IdRisk_ createFromParcel(Parcel in) {
IdRisk_ instance = new IdRisk_();
return instance;
}

public IdRisk_[] newArray(int size) {
return (new IdRisk_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
