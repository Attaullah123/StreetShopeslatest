package bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer_ implements Parcelable
{

@SerializedName("id")
@Expose
private String id;
@SerializedName("id_default_group")
@Expose
private String idDefaultGroup;
@SerializedName("id_lang")
@Expose
private String idLang;
@SerializedName("newsletter_date_add")
@Expose
private NewsletterDateAdd_ newsletterDateAdd;
@SerializedName("ip_registration_newsletter")
@Expose
private IpRegistrationNewsletter_ ipRegistrationNewsletter;
@SerializedName("last_passwd_gen")
@Expose
private String lastPasswdGen;
@SerializedName("secure_key")
@Expose
private String secureKey;
@SerializedName("deleted")
@Expose
private Deleted_ deleted;
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
@SerializedName("newsletter")
@Expose
private Newsletter_ newsletter;
@SerializedName("optin")
@Expose
private Optin_ optin;
@SerializedName("website")
@Expose
private Website_ website;
@SerializedName("company")
@Expose
private Company_ company;
@SerializedName("siret")
@Expose
private Siret_ siret;
@SerializedName("ape")
@Expose
private Ape_ ape;
@SerializedName("outstanding_allow_amount")
@Expose
private OutstandingAllowAmount_ outstandingAllowAmount;
@SerializedName("show_public_prices")
@Expose
private ShowPublicPrices_ showPublicPrices;
@SerializedName("id_risk")
@Expose
private IdRisk_ idRisk;
@SerializedName("max_payment_days")
@Expose
private MaxPaymentDays_ maxPaymentDays;
@SerializedName("active")
@Expose
private String active;
@SerializedName("note")
@Expose
private Note_ note;
@SerializedName("is_guest")
@Expose
private IsGuest_ isGuest;
@SerializedName("id_shop")
@Expose
private String idShop;
@SerializedName("id_shop_group")
@Expose
private String idShopGroup;
@SerializedName("date_add")
@Expose
private String dateAdd;
@SerializedName("date_upd")
@Expose
private String dateUpd;
@SerializedName("reset_password_token")
@Expose
private ResetPasswordToken_ resetPasswordToken;
@SerializedName("reset_password_validity")
@Expose
private ResetPasswordValidity_ resetPasswordValidity;
@SerializedName("associations")
@Expose
private Associations_ associations;
public final static Creator<Customer_> CREATOR = new Creator<Customer_>() {


@SuppressWarnings({
"unchecked"
})
public Customer_ createFromParcel(Parcel in) {
Customer_ instance = new Customer_();
instance.id = ((String) in.readValue((String.class.getClassLoader())));
instance.idDefaultGroup = ((String) in.readValue((String.class.getClassLoader())));
instance.idLang = ((String) in.readValue((String.class.getClassLoader())));
instance.newsletterDateAdd = ((NewsletterDateAdd_) in.readValue((NewsletterDateAdd_.class.getClassLoader())));
instance.ipRegistrationNewsletter = ((IpRegistrationNewsletter_) in.readValue((IpRegistrationNewsletter_.class.getClassLoader())));
instance.lastPasswdGen = ((String) in.readValue((String.class.getClassLoader())));
instance.secureKey = ((String) in.readValue((String.class.getClassLoader())));
instance.deleted = ((Deleted_) in.readValue((Deleted_.class.getClassLoader())));
instance.passwd = ((String) in.readValue((String.class.getClassLoader())));
instance.lastname = ((String) in.readValue((String.class.getClassLoader())));
instance.firstname = ((String) in.readValue((String.class.getClassLoader())));
instance.email = ((String) in.readValue((String.class.getClassLoader())));
instance.idGender = ((String) in.readValue((String.class.getClassLoader())));
instance.birthday = ((String) in.readValue((String.class.getClassLoader())));
instance.newsletter = ((Newsletter_) in.readValue((Newsletter_.class.getClassLoader())));
instance.optin = ((Optin_) in.readValue((Optin_.class.getClassLoader())));
instance.website = ((Website_) in.readValue((Website_.class.getClassLoader())));
instance.company = ((Company_) in.readValue((Company_.class.getClassLoader())));
instance.siret = ((Siret_) in.readValue((Siret_.class.getClassLoader())));
instance.ape = ((Ape_) in.readValue((Ape_.class.getClassLoader())));
instance.outstandingAllowAmount = ((OutstandingAllowAmount_) in.readValue((OutstandingAllowAmount_.class.getClassLoader())));
instance.showPublicPrices = ((ShowPublicPrices_) in.readValue((ShowPublicPrices_.class.getClassLoader())));
instance.idRisk = ((IdRisk_) in.readValue((IdRisk_.class.getClassLoader())));
instance.maxPaymentDays = ((MaxPaymentDays_) in.readValue((MaxPaymentDays_.class.getClassLoader())));
instance.active = ((String) in.readValue((String.class.getClassLoader())));
instance.note = ((Note_) in.readValue((Note_.class.getClassLoader())));
instance.isGuest = ((IsGuest_) in.readValue((IsGuest_.class.getClassLoader())));
instance.idShop = ((String) in.readValue((String.class.getClassLoader())));
instance.idShopGroup = ((String) in.readValue((String.class.getClassLoader())));
instance.dateAdd = ((String) in.readValue((String.class.getClassLoader())));
instance.dateUpd = ((String) in.readValue((String.class.getClassLoader())));
instance.resetPasswordToken = ((ResetPasswordToken_) in.readValue((ResetPasswordToken_.class.getClassLoader())));
instance.resetPasswordValidity = ((ResetPasswordValidity_) in.readValue((ResetPasswordValidity_.class.getClassLoader())));
instance.associations = ((Associations_) in.readValue((Associations_.class.getClassLoader())));
return instance;
}

public Customer_[] newArray(int size) {
return (new Customer_[size]);
}

}
;

/**
* No args constructor for use in serialization
*
*/
public Customer_() {
}

/**
*
* @param passwd
* @param birthday
* @param idDefaultGroup
* @param lastname
* @param idRisk
* @param idShop
* @param idShopGroup
* @param id
* @param dateUpd
* @param lastPasswdGen
* @param ape
* @param showPublicPrices
* @param newsletterDateAdd
* @param note
* @param idLang
* @param website
* @param newsletter
* @param resetPasswordValidity
* @param maxPaymentDays
* @param idGender
* @param firstname
* @param optin
* @param secureKey
* @param deleted
* @param ipRegistrationNewsletter
* @param dateAdd
* @param isGuest
* @param resetPasswordToken
* @param email
* @param company
* @param associations
* @param active
* @param outstandingAllowAmount
* @param siret
*/
public Customer_(String id, String idDefaultGroup, String idLang, NewsletterDateAdd_ newsletterDateAdd, IpRegistrationNewsletter_ ipRegistrationNewsletter, String lastPasswdGen, String secureKey, Deleted_ deleted, String passwd, String lastname, String firstname, String email, String idGender, String birthday, Newsletter_ newsletter, Optin_ optin, Website_ website, Company_ company, Siret_ siret, Ape_ ape, OutstandingAllowAmount_ outstandingAllowAmount, ShowPublicPrices_ showPublicPrices, IdRisk_ idRisk, MaxPaymentDays_ maxPaymentDays, String active, Note_ note, IsGuest_ isGuest, String idShop, String idShopGroup, String dateAdd, String dateUpd, ResetPasswordToken_ resetPasswordToken, ResetPasswordValidity_ resetPasswordValidity, Associations_ associations) {
super();
this.id = id;
this.idDefaultGroup = idDefaultGroup;
this.idLang = idLang;
this.newsletterDateAdd = newsletterDateAdd;
this.ipRegistrationNewsletter = ipRegistrationNewsletter;
this.lastPasswdGen = lastPasswdGen;
this.secureKey = secureKey;
this.deleted = deleted;
this.passwd = passwd;
this.lastname = lastname;
this.firstname = firstname;
this.email = email;
this.idGender = idGender;
this.birthday = birthday;
this.newsletter = newsletter;
this.optin = optin;
this.website = website;
this.company = company;
this.siret = siret;
this.ape = ape;
this.outstandingAllowAmount = outstandingAllowAmount;
this.showPublicPrices = showPublicPrices;
this.idRisk = idRisk;
this.maxPaymentDays = maxPaymentDays;
this.active = active;
this.note = note;
this.isGuest = isGuest;
this.idShop = idShop;
this.idShopGroup = idShopGroup;
this.dateAdd = dateAdd;
this.dateUpd = dateUpd;
this.resetPasswordToken = resetPasswordToken;
this.resetPasswordValidity = resetPasswordValidity;
this.associations = associations;
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

public String getIdLang() {
return idLang;
}

public void setIdLang(String idLang) {
this.idLang = idLang;
}

public NewsletterDateAdd_ getNewsletterDateAdd() {
return newsletterDateAdd;
}

public void setNewsletterDateAdd(NewsletterDateAdd_ newsletterDateAdd) {
this.newsletterDateAdd = newsletterDateAdd;
}

public IpRegistrationNewsletter_ getIpRegistrationNewsletter() {
return ipRegistrationNewsletter;
}

public void setIpRegistrationNewsletter(IpRegistrationNewsletter_ ipRegistrationNewsletter) {
this.ipRegistrationNewsletter = ipRegistrationNewsletter;
}

public String getLastPasswdGen() {
return lastPasswdGen;
}

public void setLastPasswdGen(String lastPasswdGen) {
this.lastPasswdGen = lastPasswdGen;
}

public String getSecureKey() {
return secureKey;
}

public void setSecureKey(String secureKey) {
this.secureKey = secureKey;
}

public Deleted_ getDeleted() {
return deleted;
}

public void setDeleted(Deleted_ deleted) {
this.deleted = deleted;
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

public Newsletter_ getNewsletter() {
return newsletter;
}

public void setNewsletter(Newsletter_ newsletter) {
this.newsletter = newsletter;
}

public Optin_ getOptin() {
return optin;
}

public void setOptin(Optin_ optin) {
this.optin = optin;
}

public Website_ getWebsite() {
return website;
}

public void setWebsite(Website_ website) {
this.website = website;
}

public Company_ getCompany() {
return company;
}

public void setCompany(Company_ company) {
this.company = company;
}

public Siret_ getSiret() {
return siret;
}

public void setSiret(Siret_ siret) {
this.siret = siret;
}

public Ape_ getApe() {
return ape;
}

public void setApe(Ape_ ape) {
this.ape = ape;
}

public OutstandingAllowAmount_ getOutstandingAllowAmount() {
return outstandingAllowAmount;
}

public void setOutstandingAllowAmount(OutstandingAllowAmount_ outstandingAllowAmount) {
this.outstandingAllowAmount = outstandingAllowAmount;
}

public ShowPublicPrices_ getShowPublicPrices() {
return showPublicPrices;
}

public void setShowPublicPrices(ShowPublicPrices_ showPublicPrices) {
this.showPublicPrices = showPublicPrices;
}

public IdRisk_ getIdRisk() {
return idRisk;
}

public void setIdRisk(IdRisk_ idRisk) {
this.idRisk = idRisk;
}

public MaxPaymentDays_ getMaxPaymentDays() {
return maxPaymentDays;
}

public void setMaxPaymentDays(MaxPaymentDays_ maxPaymentDays) {
this.maxPaymentDays = maxPaymentDays;
}

public String getActive() {
return active;
}

public void setActive(String active) {
this.active = active;
}

public Note_ getNote() {
return note;
}

public void setNote(Note_ note) {
this.note = note;
}

public IsGuest_ getIsGuest() {
return isGuest;
}

public void setIsGuest(IsGuest_ isGuest) {
this.isGuest = isGuest;
}

public String getIdShop() {
return idShop;
}

public void setIdShop(String idShop) {
this.idShop = idShop;
}

public String getIdShopGroup() {
return idShopGroup;
}

public void setIdShopGroup(String idShopGroup) {
this.idShopGroup = idShopGroup;
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

public ResetPasswordToken_ getResetPasswordToken() {
return resetPasswordToken;
}

public void setResetPasswordToken(ResetPasswordToken_ resetPasswordToken) {
this.resetPasswordToken = resetPasswordToken;
}

public ResetPasswordValidity_ getResetPasswordValidity() {
return resetPasswordValidity;
}

public void setResetPasswordValidity(ResetPasswordValidity_ resetPasswordValidity) {
this.resetPasswordValidity = resetPasswordValidity;
}

public Associations_ getAssociations() {
return associations;
}

public void setAssociations(Associations_ associations) {
this.associations = associations;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(id);
dest.writeValue(idDefaultGroup);
dest.writeValue(idLang);
dest.writeValue(newsletterDateAdd);
dest.writeValue(ipRegistrationNewsletter);
dest.writeValue(lastPasswdGen);
dest.writeValue(secureKey);
dest.writeValue(deleted);
dest.writeValue(passwd);
dest.writeValue(lastname);
dest.writeValue(firstname);
dest.writeValue(email);
dest.writeValue(idGender);
dest.writeValue(birthday);
dest.writeValue(newsletter);
dest.writeValue(optin);
dest.writeValue(website);
dest.writeValue(company);
dest.writeValue(siret);
dest.writeValue(ape);
dest.writeValue(outstandingAllowAmount);
dest.writeValue(showPublicPrices);
dest.writeValue(idRisk);
dest.writeValue(maxPaymentDays);
dest.writeValue(active);
dest.writeValue(note);
dest.writeValue(isGuest);
dest.writeValue(idShop);
dest.writeValue(idShopGroup);
dest.writeValue(dateAdd);
dest.writeValue(dateUpd);
dest.writeValue(resetPasswordToken);
dest.writeValue(resetPasswordValidity);
dest.writeValue(associations);
}

public int describeContents() {
return 0;
}

}
