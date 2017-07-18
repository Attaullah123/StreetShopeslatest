package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ResetPasswordToken_ implements Parcelable
{

public final static Creator<ResetPasswordToken_> CREATOR = new Creator<ResetPasswordToken_>() {


@SuppressWarnings({
"unchecked"
})
public ResetPasswordToken_ createFromParcel(Parcel in) {
ResetPasswordToken_ instance = new ResetPasswordToken_();
return instance;
}

public ResetPasswordToken_[] newArray(int size) {
return (new ResetPasswordToken_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
