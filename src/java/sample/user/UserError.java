/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

public class UserError {

    private String userIDError;
    private String fullNameError;
    private String roleIDError;
    private String phoneError;
    private String addressError;
    private String emailError;
    private String passwordError;
    private String confirmError;
    private String messageError;

    public UserError() {
        this.userIDError = "";
        this.fullNameError = "";
        this.roleIDError = "";
        this.phoneError = "";
        this.addressError = "";
        this.emailError = "";
        this.passwordError = "";
        this.confirmError = "";
        this.messageError = "";
    }

    public UserError(String userIDError, String fullNameError, String roleIDError, String phoneError, String addressError, String emailError, String passwordError, String confirmError, String messageError) {
        this.userIDError = userIDError;
        this.fullNameError = fullNameError;
        this.roleIDError = roleIDError;
        this.phoneError = phoneError;
        this.addressError = addressError;
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.messageError = messageError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

}
