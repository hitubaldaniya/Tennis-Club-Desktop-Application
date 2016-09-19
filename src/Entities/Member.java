package Entities;
/**
 *
 * @author Liliana Paiva, Diogo Novais
 */
public class Member {
    
    private int id, memberNumber, mobilePhone, telephone, taxIdNumber, postalCode, postalCodeSuffix;
    private String name, birthDate, idCardNumber, address, residencyAddress, photo, email;

    // Empty object constructor
    public Member(){}
    
    /**
     * @param id Database ID
     * @param memberNumber Member number
     * @param mobilePhone Mobile phone number
     * @param telephone Landline number
     * @param postalCode Postal code
     * @param postalCodeSuffix Postal suffix
     * @param taxIdNumber Tax identification number
     * @param idCardNumber ID card number
     * @param name Name
     * @param birthDate Birth date
     * @param address Address
     * @param residencyAddress Residency address
     * @param photo Photo
     * @param email Email
     */
    public Member(int id, int memberNumber, String name, String address, String residencyAddress, int postalCode, int postalCodeSuffix, String birthDate, String idCardNumber, int taxIdNumber, int telephone, int mobilePhone, String email, String photo) {
        this.id = id;
        this.memberNumber = memberNumber;
        this.mobilePhone = mobilePhone;
        this.telephone = telephone;
        this.postalCode = postalCode;
        this.postalCodeSuffix = postalCodeSuffix;
        this.taxIdNumber = taxIdNumber;
        this.idCardNumber = idCardNumber;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.residencyAddress = residencyAddress;
        this.photo = photo;
        this.email = email;
    }
    
    
    // ******************** Getters ********************

    /**
     * Returns member id
     * 
     * @return id 
     */
    public int getId() {
        return id;
    }
    
        /**
     * Returns member number
     * 
     * @return memberNumber
     */
    public int getMemberNumber() {
        return memberNumber;
    }

    /**
     * Returns mobile phone number
     * 
     * @return mobilePhone
     */
    public int getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Returns landline number
     * 
     * @return telephone
     */
    public int getTelephone() {
        return telephone;
    }

    /**
     * Returns postcode
     * 
     * @return postalCode
     */
    public int getPostalCode() {
        return postalCode;
    }
    
    /**
     * Returns postcode suffix
     * 
     * @return postalCodeSuffix
     */
    public int getPostalCodeSuffix() {
        return postalCodeSuffix;
    }

    /**
     * Returns tax identification number
     * 
     * @return taxIdNumber
     */
    public int getTaxIdNumber() {
        return taxIdNumber;
    }

    /**
     * 
     * Returns identification card number
     * 
     * @return idCardNumber 
     */
    public String getIdCardNumber() {
        return idCardNumber;
    }

    /**
     * Returns member name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns member birth date
     * 
     * @return birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Returns member address
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns member residency address
     * 
     * @return residencyAddress
     */
    public String getResidencyAddress() {
        return residencyAddress;
    }

    /**
     * Returns member photo file name
     * 
     * @return photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Returns member email
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }
    
    
    // ******************** Setters ********************
    
    /**
     * Changes member id
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Changes member number
     * 
     * @param memberNumber
     */
    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    /**
     * Changes mobile phone number
     * 
     * @param mobilePhone 
     */
    public void setMobilePhone(int mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * Changes landline number
     * 
     * @param telephone 
     */
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    /**
     * Changes postcode
     * 
     * @param postalCode 
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
    
    /**
     * Changes postal code suffix
     * 
     * @param postalCodeSuffix 
     */
    public void setPostalCodeSuffix(int postalCodeSuffix) {
        this.postalCodeSuffix = postalCodeSuffix;
    }

    /**
     * Changes tax identification number
     * 
     * @param taxIdNumber 
     */
    public void setTaxIdNumber(int taxIdNumber) {
        this.taxIdNumber = taxIdNumber;
    }

    /**
     * Changes identification card number
     * 
     * @param idCardNumber 
     */
    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    /**
     * Changes member name
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes member birth date
     * 
     * @param birthDate 
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Changes member address
     * 
     * @param address 
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Changes member residency address
     * 
     * @param residencyAddress 
     */
    public void setResidencyAddress(String residencyAddress) {
        this.residencyAddress = residencyAddress;
    }

    /**
     * Changes member photo file name
     * 
     * @param photo 
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Changes email
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
