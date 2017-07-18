package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ResetPasswordValidity_ implements Parcelable
{

public final static Creator<ResetPasswordValidity_> CREATOR = new Creator<ResetPasswordValidity_>() {


@SuppressWarnings({
"unchecked"
})
public ResetPasswordValidity_ createFromParcel(Parcel in) {
ResetPasswordValidity_ instance = new ResetPasswordValidity_();
return instance;
}

public ResetPasswordValidity_[] newArray(int size) {
return (new ResetPasswordValidity_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
