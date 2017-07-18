package bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer implements Parcelable
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
private NewsletterDateAdd newsletterDateAdd;
@SerializedName("ip_registration_newsletter")
@Expose
private IpRegistrationNewsletter ipRegistrationNewsletter;
@SerializedName("last_passwd_gen")
@Expose
private String lastPasswdGen;
@SerializedName("secure_key")
@Expose
private String secureKey;
@SerializedName("deleted")
@Expose
private Deleted deleted;
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
private Newsletter newsletter;
@SerializedName("optin")
@Expose
private Optin optin;
@SerializedName("website")
@Expose
private Website website;
@SerializedName("company")
@Expose
private Company company;
@SerializedName("siret")
@Expose
private Siret siret;
@SerializedName("ape")
@Expose
private Ape ape;
@SerializedName("outstanding_allow_amount")
@Expose
private OutstandingAllowAmount outstandingAllowAmount;
@SerializedName("show_public_prices")
@Expose
private ShowPublicPrices showPublicPrices;
@SerializedName("id_risk")
@Expose
private IdRisk idRisk;
@SerializedName("max_payment_days")
@Expose
private MaxPaymentDays maxPaymentDays;
@SerializedName("active")
@Expose
private String active;
@SerializedName("note")
@Expose
private Note note;
@SerializedName("is_guest")
@Expose
private IsGuest isGuest;
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
private ResetPasswordToken resetPasswordToken;
@SerializedName("reset_password_validity")
@Expose
private ResetPasswordValidity resetPasswordValidity;
@SerializedName("associations")
@Expose
private Associations associations;
public final static Creator<Customer> CREATOR = new Creator<Customer>() {


@SuppressWarnings({
"unchecked"
})
public Customer createFromParcel(Parcel in) {
Customer instance = new Customer();
instance.id = ((String) in.readValue((String.class.getClassLoader())));
instance.idDefaultGroup = ((String) in.readValue((String.class.getClassLoader())));
instance.idLang = ((String) in.readValue((String.class.getClassLoader())));
instance.newsletterDateAdd = ((NewsletterDateAdd) in.readValue((NewsletterDateAdd.class.getClassLoader())));
instance.ipRegistrationNewsletter = ((IpRegistrationNewsletter) in.readValue((IpRegistrationNewsletter.class.getClassLoader())));
instance.lastPasswdGen = ((String) in.readValue((String.class.getClassLoader())));
instance.secureKey = ((String) in.readValue((String.class.getClassLoader())));
instance.deleted = ((Deleted) in.readValue((Deleted.class.getClassLoader())));
instance.passwd = ((String) in.readValue((String.class.getClassLoader())));
instance.lastname = ((String) in.readValue((String.class.getClassLoader())));
instance.firstname = ((String) in.readValue((String.class.getClassLoader())));
instance.email = ((String) in.readValue((String.class.getClassLoader())));
instance.idGender = ((String) in.readValue((String.class.getClassLoader())));
instance.birthday = ((String) in.readValue((String.class.getClassLoader())));
instance.newsletter = ((Newsletter) in.readValue((Newsletter.class.getClassLoader())));
instance.optin = ((Optin) in.readValue((Optin.class.getClassLoader())));
instance.website = ((Website) in.readValue((Website.class.getClassLoader())));
instance.company = ((Company) in.readValue((Company.class.getClassLoader())));
instance.siret = ((Siret) in.readValue((Siret.class.getClassLoader())));
instance.ape = ((Ape) in.readValue((Ape.class.getClassLoader())));
instance.outstandingAllowAmount = ((OutstandingAllowAmount) in.readValue((OutstandingAllowAmount.class.getClassLoader())));
instance.showPublicPrices = ((ShowPublicPrices) in.readValue((ShowPublicPrices.class.getClassLoader())));
instance.idRisk = ((IdRisk) in.readValue((IdRisk.class.getClassLoader())));
instance.maxPaymentDays = ((MaxPaymentDays) in.readValue((MaxPaymentDays.class.getClassLoader())));
instance.active = ((String) in.readValue((String.class.getClassLoader())));
instance.note = ((Note) in.readValue((Note.class.getClassLoader())));
instance.isGuest = ((IsGuest) in.readValue((IsGuest.class.getClassLoader())));
instance.idShop = ((String) in.readValue((String.class.getClassLoader())));
instance.idShopGroup = ((String) in.readValue((String.class.getClassLoader())));
instance.dateAdd = ((String) in.readValue((String.class.getClassLoader())));
instance.dateUpd = ((String) in.readValue((String.class.getClassLoader())));
instance.resetPasswordToken = ((ResetPasswordToken) in.readValue((ResetPasswordToken.class.getClassLoader())));
instance.resetPasswordValidity = ((ResetPasswordValidity) in.readValue((ResetPasswordValidity.class.getClassLoader())));
instance.associations = ((Associations) in.readValue((Associations.class.getClassLoader())));
return instance;
}

public Customer[] newArray(int size) {
return (new Customer[size]);
}

}
;

/**
* No args constructor for use in serialization
*
*/
public Customer() {
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
public Customer(String id, String idDefaultGroup, String idLang, NewsletterDateAdd newsletterDateAdd, IpRegistrationNewsletter ipRegistrationNewsletter, String lastPasswdGen, String secureKey, Deleted deleted, String passwd, String lastname, String firstname, String email, String idGender, String birthday, Newsletter newsletter, Optin optin, Website website, Company company, Siret siret, Ape ape, OutstandingAllowAmount outstandingAllowAmount, ShowPublicPrices showPublicPrices, IdRisk idRisk, MaxPaymentDays maxPaymentDays, String active, Note note, IsGuest isGuest, String idShop, String idShopGroup, String dateAdd, String dateUpd, ResetPasswordToken resetPasswordToken, ResetPasswordValidity resetPasswordValidity, Associations associations) {
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

public NewsletterDateAdd getNewsletterDateAdd() {
return newsletterDateAdd;
}

public void setNewsletterDateAdd(NewsletterDateAdd newsletterDateAdd) {
this.newsletterDateAdd = newsletterDateAdd;
}

public IpRegistrationNewsletter getIpRegistrationNewsletter() {
return ipRegistrationNewsletter;
}

public void setIpRegistrationNewsletter(IpRegistrationNewsletter ipRegistrationNewsletter) {
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

public Deleted getDeleted() {
return deleted;
}

public void setDeleted(Deleted deleted) {
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

public Newsletter getNewsletter() {
return newsletter;
}

public void setNewsletter(Newsletter newsletter) {
this.newsletter = newsletter;
}

public Optin getOptin() {
return optin;
}

public void setOptin(Optin optin) {
this.optin = optin;
}

public Website getWebsite() {
return website;
}

public void setWebsite(Website website) {
this.website = website;
}

public Company getCompany() {
return company;
}

public void setCompany(Company company) {
this.company = company;
}

public Siret getSiret() {
return siret;
}

public void setSiret(Siret siret) {
this.siret = siret;
}

public Ape getApe() {
return ape;
}

public void setApe(Ape ape) {
this.ape = ape;
}

public OutstandingAllowAmount getOutstandingAllowAmount() {
return outstandingAllowAmount;
}

public void setOutstandingAllowAmount(OutstandingAllowAmount outstandingAllowAmount) {
this.outstandingAllowAmount = outstandingAllowAmount;
}

public ShowPublicPrices getShowPublicPrices() {
return showPublicPrices;
}

public void setShowPublicPrices(ShowPublicPrices showPublicPrices) {
this.showPublicPrices = showPublicPrices;
}

public IdRisk getIdRisk() {
return idRisk;
}

public void setIdRisk(IdRisk idRisk) {
this.idRisk = idRisk;
}

public MaxPaymentDays getMaxPaymentDays() {
return maxPaymentDays;
}

public void setMaxPaymentDays(MaxPaymentDays maxPaymentDays) {
this.maxPaymentDays = maxPaymentDays;
}

public String getActive() {
return active;
}

public void setActive(String active) {
this.active = active;
}

public Note getNote() {
return note;
}

public void setNote(Note note) {
this.note = note;
}

public IsGuest getIsGuest() {
return isGuest;
}

public void setIsGuest(IsGuest isGuest) {
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

public ResetPasswordToken getResetPasswordToken() {
return resetPasswordToken;
}

public void setResetPasswordToken(ResetPasswordToken resetPasswordToken) {
this.resetPasswordToken = resetPasswordToken;
}

public ResetPasswordValidity getResetPasswordValidity() {
return resetPasswordValidity;
}

public void setResetPasswordValidity(ResetPasswordValidity resetPasswordValidity) {
this.resetPasswordValidity = resetPasswordValidity;
}

public Associations getAssociations() {
return associations;
}

public void setAssociations(Associations associations) {
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
