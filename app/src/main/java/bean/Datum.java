package bean;

import com.google.gson.annotations.SerializedName;

/*-----------------------------------bean.Datum.java-----------------------------------*/


import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_country")
    @Expose
    private String idCountry;
    @SerializedName("id_state")
    @Expose
    private String idState;
    @SerializedName("hours")
    @Expose
    private String hours;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("postcode")
    @Expose
    private String postcode;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("date_add")
    @Expose
    private String dateAdd;
    @SerializedName("date_upd")
    @Expose
    private String dateUpd;
    public final static Parcelable.Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            Datum instance = new Datum();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.idCountry = ((String) in.readValue((String.class.getClassLoader())));
            instance.idState = ((String) in.readValue((String.class.getClassLoader())));
            instance.hours = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.address1 = ((String) in.readValue((String.class.getClassLoader())));
            instance.address2 = ((String) in.readValue((String.class.getClassLoader())));
            instance.postcode = ((String) in.readValue((String.class.getClassLoader())));
            instance.city = ((String) in.readValue((String.class.getClassLoader())));
            instance.latitude = ((String) in.readValue((String.class.getClassLoader())));
            instance.longitude = ((String) in.readValue((String.class.getClassLoader())));
            instance.phone = ((String) in.readValue((String.class.getClassLoader())));
            instance.fax = ((String) in.readValue((String.class.getClassLoader())));
            instance.note = ((String) in.readValue((String.class.getClassLoader())));
            instance.email = ((String) in.readValue((String.class.getClassLoader())));
            instance.active = ((String) in.readValue((String.class.getClassLoader())));
            instance.dateAdd = ((String) in.readValue((String.class.getClassLoader())));
            instance.dateUpd = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    };

    /**
     * No args constructor for use in serialization
     */
    public Datum() {
    }

    /**
     * @param phone
     * @param fax
     * @param hours
     * @param idState
     * @param idCountry
     * @param address1
     * @param address2
     * @param postcode
     * @param city
     * @param dateAdd
     * @param id
     * @param dateUpd
     * @param email
     * @param name
     * @param active
     * @param longitude
     * @param latitude
     * @param note
     */
    public Datum(String id, String idCountry, String idState, String hours, String name, String address1, String address2, String postcode, String city, String latitude, String longitude, String phone, String fax, String note, String email, String active, String dateAdd, String dateUpd) {
        super();
        this.id = id;
        this.idCountry = idCountry;
        this.idState = idState;
        this.hours = hours;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.postcode = postcode;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.fax = fax;
        this.note = note;
        this.email = email;
        this.active = active;
        this.dateAdd = dateAdd;
        this.dateUpd = dateUpd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(String idCountry) {
        this.idCountry = idCountry;
    }

    public String getIdState() {
        return idState;
    }

    public void setIdState(String idState) {
        this.idState = idState;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(String dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(String dateUpd) {
        this.dateUpd = dateUpd;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(idCountry);
        dest.writeValue(idState);
        dest.writeValue(hours);
        dest.writeValue(name);
        dest.writeValue(address1);
        dest.writeValue(address2);
        dest.writeValue(postcode);
        dest.writeValue(city);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(phone);
        dest.writeValue(fax);
        dest.writeValue(note);
        dest.writeValue(email);
        dest.writeValue(active);
        dest.writeValue(dateAdd);
        dest.writeValue(dateUpd);
    }

    public int describeContents() {
        return 0;
    }

}
/*-----------------------------------bean.StoreListResponce.java-----------------------------------*/


