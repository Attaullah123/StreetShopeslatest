package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ResetPasswordToken implements Parcelable
{

public final static Creator<ResetPasswordToken> CREATOR = new Creator<ResetPasswordToken>() {


@SuppressWarnings({
"unchecked"
})
public ResetPasswordToken createFromParcel(Parcel in) {
ResetPasswordToken instance = new ResetPasswordToken();
return instance;
}

public ResetPasswordToken[] newArray(int size) {
return (new ResetPasswordToken[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
