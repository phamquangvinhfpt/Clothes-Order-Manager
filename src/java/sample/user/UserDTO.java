package sample.user;

import java.sql.Date;

/**
 *
 * @author Thien
 */
public class UserDTO {

    private String userID;
    private String fullName;

    private String roleID;
    private String password;
    private String phone;
    private String address;
    private String email;

    private String firstName;
    private String lastName;
    private String picture;
    private Date DOB;
    private String organization;
    private boolean status;

    public UserDTO() {
    }
    public UserDTO(String userID) {
        this.userID = userID;
    }

    public UserDTO(String userID, String fullName, String phone, String address, String email, String roleID, String password) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    //Google Account
    public UserDTO(String userID, String roleID, String password, String phone, String address, String email, String firstName, String lastName, String picture, Date DOB, String organization, boolean status) {
        this.userID = userID;
        this.roleID = roleID;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.DOB = DOB;
        this.organization = organization;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userID=" + userID + ", fullName=" + fullName + ", roleID=" + roleID + ", password=" + password + ", phone=" + phone + ", address=" + address + ", email=" + email + '}';
    }

}
