package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsletterDateAdd implements Parcelable
{

public final static Creator<NewsletterDateAdd> CREATOR = new Creator<NewsletterDateAdd>() {


@SuppressWarnings({
"unchecked"
})
public NewsletterDateAdd createFromParcel(Parcel in) {
NewsletterDateAdd instance = new NewsletterDateAdd();
return instance;
}

public NewsletterDateAdd[] newArray(int size) {
return (new NewsletterDateAdd[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
