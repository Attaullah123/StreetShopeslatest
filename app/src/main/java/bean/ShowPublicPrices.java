package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ShowPublicPrices implements Parcelable
{

public final static Creator<ShowPublicPrices> CREATOR = new Creator<ShowPublicPrices>() {


@SuppressWarnings({
"unchecked"
})
public ShowPublicPrices createFromParcel(Parcel in) {
ShowPublicPrices instance = new ShowPublicPrices();
return instance;
}

public ShowPublicPrices[] newArray(int size) {
return (new ShowPublicPrices[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
