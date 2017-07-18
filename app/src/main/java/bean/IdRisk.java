package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class IdRisk implements Parcelable
{

public final static Creator<IdRisk> CREATOR = new Creator<IdRisk>() {


@SuppressWarnings({
"unchecked"
})
public IdRisk createFromParcel(Parcel in) {
IdRisk instance = new IdRisk();
return instance;
}

public IdRisk[] newArray(int size) {
return (new IdRisk[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
