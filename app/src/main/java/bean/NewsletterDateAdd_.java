package bean;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsletterDateAdd_ implements Parcelable
{

public final static Creator<NewsletterDateAdd_> CREATOR = new Creator<NewsletterDateAdd_>() {


@SuppressWarnings({
"unchecked"
})
public NewsletterDateAdd_ createFromParcel(Parcel in) {
NewsletterDateAdd_ instance = new NewsletterDateAdd_();
return instance;
}

public NewsletterDateAdd_[] newArray(int size) {
return (new NewsletterDateAdd_[size]);
}

}
;

public void writeToParcel(Parcel dest, int flags) {
}

public int describeContents() {
return 0;
}

}
