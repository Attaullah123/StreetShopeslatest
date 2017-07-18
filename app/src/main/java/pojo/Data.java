package pojo;



import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

@SerializedName("id")
@Expose
private String id;
@SerializedName("id_default_group")
@Expose
private String idDefaultGroup;
@SerializedName("passwd")
@Expose
private String passwd;
@SerializedName("lastname")
@Expose
private String lastname;
@SerializedName("firstname")
@Expose
private String firstname;
@SerializedName("email")
@Expose
private String email;
@SerializedName("id_gender")
@Expose
private String idGender;
@SerializedName("birthday")
@Expose
private String birthday;
@SerializedName("optin")
@Expose
private String optin;
@SerializedName("active")
@Expose
private String active;
@SerializedName("id_shop")
@Expose
private String idShop;
public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


@SuppressWarnings({
"unchecked"
})
public Data createFromParcel(Parcel in) {
Data instance = new Data();
instance.id = ((String) in.readValue((String.class.getClassLoader())));
instance.idDefaultGroup = ((String) in.readValue((String.class.getClassLoader())));
instance.passwd = ((String) in.readValue((String.class.getClassLoader())));
instance.lastname = ((String) in.readValue((String.class.getClassLoader())));
instance.firstname = ((String) in.readValue((String.class.getClassLoader())));
instance.email = ((String) in.readValue((String.class.getClassLoader())));
instance.idGender = ((String) in.readValue((String.class.getClassLoader())));
instance.birthday = ((String) in.readValue((String.class.getClassLoader())));
instance.optin = ((String) in.readValue((String.class.getClassLoader())));
instance.active = ((String) in.readValue((String.class.getClassLoader())));
instance.idShop = ((String) in.readValue((String.class.getClassLoader())));
return instance;
}

public Data[] newArray(int size) {
return (new Data[size]);
}

}
;

/**
* No args constructor for use in serialization
* 
*/
public Data() {
}

/**
* 
* @param id
* @param birthday
* @param passwd
* @param email
* @param idDefaultGroup
* @param active
* @param lastname
* @param idGender
* @param optin
* @param firstname
* @param idShop
*/
public Data(String id, String idDefaultGroup, String passwd, String lastname, String firstname, String email, String idGender, String birthday, String optin, String active, String idShop) {
super();
this.id = id;
this.idDefaultGroup = idDefaultGroup;
this.passwd = passwd;
this.lastname = lastname;
this.firstname = firstname;
this.email = email;
this.idGender = idGender;
this.birthday = birthday;
this.optin = optin;
this.active = active;
this.idShop = idShop;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getIdDefaultGroup() {
return idDefaultGroup;
}

public void setIdDefaultGroup(String idDefaultGroup) {
this.idDefaultGroup = idDefaultGroup;
}

public String getPasswd() {
return passwd;
}

public void setPasswd(String passwd) {
this.passwd = passwd;
}

public String getLastname() {
return lastname;
}

public void setLastname(String lastname) {
this.lastname = lastname;
}

public String getFirstname() {
return firstname;
}

public void setFirstname(String firstname) {
this.firstname = firstname;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getIdGender() {
return idGender;
}

public void setIdGender(String idGender) {
this.idGender = idGender;
}

public String getBirthday() {
return birthday;
}

public void setBirthday(String birthday) {
this.birthday = birthday;
}

public String getOptin() {
return optin;
}

public void setOptin(String optin) {
this.optin = optin;
}

public String getActive() {
return active;
}

public void setActive(String active) {
this.active = active;
}

public String getIdShop() {
return idShop;
}

public void setIdShop(String idShop) {
this.idShop = idShop;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(id);
dest.writeValue(idDefaultGroup);
dest.writeValue(passwd);
dest.writeValue(lastname);
dest.writeValue(firstname);
dest.writeValue(email);
dest.writeValue(idGender);
dest.writeValue(birthday);
dest.writeValue(optin);
dest.writeValue(active);
dest.writeValue(idShop);
}

public int describeContents() {
return 0;
}

}

