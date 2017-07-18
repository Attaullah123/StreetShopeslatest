package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable
{

public final static Creator<Note> CREATOR = new Creator<Note>() {


@SuppressWarnings({
"unchecked"
})
public Note createFromParcel(Parcel in) {
Note instance = new Note();
return instance;
}

public Note[] newArray(int size) {
return (new Note[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
