package pojo;

import android.os.Parcelable;


import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Seller implements Parcelable {

    @SerializedName("seller_id")
    @Expose
    private String sellerId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("banner_url")
    @Expose
    private String bannerUrl;
    public final static Parcelable.Creator<Seller> CREATOR = new Creator<Seller>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Seller createFromParcel(Parcel in) {
            Seller instance = new Seller();
            instance.sellerId = ((String) in.readValue((String.class.getClassLoader())));
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            instance.address = ((String) in.readValue((String.class.getClassLoader())));
            instance.bannerUrl = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Seller[] newArray(int size) {
            return (new Seller[size]);
        }

    };

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sellerId);
        dest.writeValue(title);
        dest.writeValue(address);
        dest.writeValue(bannerUrl);
    }

    public int describeContents() {
        return 0;
    }

}
