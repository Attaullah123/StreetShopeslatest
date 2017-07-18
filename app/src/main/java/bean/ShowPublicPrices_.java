package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ShowPublicPrices_ implements Parcelable
{

public final static Creator<ShowPublicPrices_> CREATOR = new Creator<ShowPublicPrices_>() {


@SuppressWarnings({
"unchecked"
})
public ShowPublicPrices_ createFromParcel(Parcel in) {
ShowPublicPrices_ instance = new ShowPublicPrices_();
return instance;
}

public ShowPublicPrices_[] newArray(int size) {
return (new ShowPublicPrices_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
