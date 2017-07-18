package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Siret implements Parcelable
{

public final static Creator<Siret> CREATOR = new Creator<Siret>() {


@SuppressWarnings({
"unchecked"
})
public Siret createFromParcel(Parcel in) {
Siret instance = new Siret();
return instance;
}

public Siret[] newArray(int size) {
return (new Siret[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
