package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Company_ implements Parcelable
{

public final static Creator<Company_> CREATOR = new Creator<Company_>() {


@SuppressWarnings({
"unchecked"
})
public Company_ createFromParcel(Parcel in) {
Company_ instance = new Company_();
return instance;
}

public Company_[] newArray(int size) {
return (new Company_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
