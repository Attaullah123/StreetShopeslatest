package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Siret_ implements Parcelable
{

public final static Creator<Siret_> CREATOR = new Creator<Siret_>() {


@SuppressWarnings({
"unchecked"
})
public Siret_ createFromParcel(Parcel in) {
Siret_ instance = new Siret_();
return instance;
}

public Siret_[] newArray(int size) {
return (new Siret_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
