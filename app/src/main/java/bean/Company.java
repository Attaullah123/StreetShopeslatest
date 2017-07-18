package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Company implements Parcelable
{

public final static Creator<Company> CREATOR = new Creator<Company>() {


@SuppressWarnings({
"unchecked"
})
public Company createFromParcel(Parcel in) {
Company instance = new Company();
return instance;
}

public Company[] newArray(int size) {
return (new Company[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
