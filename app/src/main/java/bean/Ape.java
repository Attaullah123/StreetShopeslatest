package bean;

/*-----------------------------------com.penniesmart.bean.Ape.java-----------------------------------*/


import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ape implements Parcelable
{

public final static Parcelable.Creator<Ape> CREATOR = new Creator<Ape>() {


@SuppressWarnings({
"unchecked"
})
public Ape createFromParcel(Parcel in) {
Ape instance = new Ape();
return instance;
}

public Ape[] newArray(int size) {
return (new Ape[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
