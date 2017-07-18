package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Newsletter implements Parcelable
{

public final static Creator<Newsletter> CREATOR = new Creator<Newsletter>() {


@SuppressWarnings({
"unchecked"
})
public Newsletter createFromParcel(Parcel in) {
Newsletter instance = new Newsletter();
return instance;
}

public Newsletter[] newArray(int size) {
return (new Newsletter[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
