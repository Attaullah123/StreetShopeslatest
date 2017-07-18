package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Website implements Parcelable
{

public final static Creator<Website> CREATOR = new Creator<Website>() {


@SuppressWarnings({
"unchecked"
})
public Website createFromParcel(Parcel in) {
Website instance = new Website();
return instance;
}

public Website[] newArray(int size) {
return (new Website[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
