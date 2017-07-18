package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Note_ implements Parcelable
{

public final static Creator<Note_> CREATOR = new Creator<Note_>() {


@SuppressWarnings({
"unchecked"
})
public Note_ createFromParcel(Parcel in) {
Note_ instance = new Note_();
return instance;
}

public Note_[] newArray(int size) {
return (new Note_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
