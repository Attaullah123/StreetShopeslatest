package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ResetPasswordValidity implements Parcelable
{

public final static Creator<ResetPasswordValidity> CREATOR = new Creator<ResetPasswordValidity>() {


@SuppressWarnings({
"unchecked"
})
public ResetPasswordValidity createFromParcel(Parcel in) {
ResetPasswordValidity instance = new ResetPasswordValidity();
return instance;
}

public ResetPasswordValidity[] newArray(int size) {
return (new ResetPasswordValidity[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
