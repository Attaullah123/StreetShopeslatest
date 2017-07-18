package pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SellertsList implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("sellers")
    @Expose
    private List<Seller> sellers = null;
    @SerializedName("msg")
    @Expose
    private String msg;
    public final static Creator<SellertsList> CREATOR = new Creator<SellertsList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SellertsList createFromParcel(Parcel in) {
            SellertsList instance = new SellertsList();
            instance.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.sellers, (Seller.class.getClassLoader()));
            instance.msg = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public SellertsList[] newArray(int size) {
            return (new SellertsList[size]);
        }

    };

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeList(sellers);
        dest.writeValue(msg);
    }

    public int describeContents() {
        return 0;
    }

}
