package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Ape_ implements Parcelable
{

public final static Creator<Ape_> CREATOR = new Creator<Ape_>() {


@SuppressWarnings({
"unchecked"
})
public Ape_ createFromParcel(Parcel in) {
Ape_ instance = new Ape_();
return instance;
}

public Ape_[] newArray(int size) {
return (new Ape_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
