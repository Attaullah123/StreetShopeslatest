package bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoreListResponce implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<StoreListResponce> CREATOR = new Creator<StoreListResponce>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StoreListResponce createFromParcel(Parcel in) {
            StoreListResponce instance = new StoreListResponce();
            instance.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.data, (Datum.class.getClassLoader()));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public StoreListResponce[] newArray(int size) {
            return (new StoreListResponce[size]);
        }

    };

    /**
     * No args constructor for use in serialization
     */
    public StoreListResponce() {
    }

    /**
     * @param message
     * @param status
     * @param data
     */
    public StoreListResponce(Integer status, List<Datum> data, String message) {
        super();
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeList(data);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}
