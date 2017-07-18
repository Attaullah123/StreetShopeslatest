package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Deleted implements Parcelable
{

public final static Creator<Deleted> CREATOR = new Creator<Deleted>() {


@SuppressWarnings({
"unchecked"
})
public Deleted createFromParcel(Parcel in) {
Deleted instance = new Deleted();
return instance;
}

public Deleted[] newArray(int size) {
return (new Deleted[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
