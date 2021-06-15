package domain.users;

import java.io.Serializable;
import java.util.Objects;

public class UserReal implements User, Serializable {
    // must not be null
    private int id;
    private String email;
    private String password;
    private int roleId;
    // can be null
    private String mobilePhoneNumber;
    private String firstName;
    private String lastName;
    private String shippingAddress;

    @Override
    public int getId() {
	return id;
    }

    @Override
    public void setId(int id) {
	this.id = id;
    }

    @Override
    public String getEmail() {
	return email;
    }

    @Override
    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public String getPassword() {
	return password;
    }

    @Override
    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public int getRoleId() {
	return roleId;
    }

    @Override
    public void setRoleId(int roleId) {
	this.roleId = roleId;
    }

    @Override
    public String getMobilePhoneNumber() {
	return mobilePhoneNumber;
    }

    @Override
    public void setMobilePhoneNumber(String mobilePhoneNumber) {
	this.mobilePhoneNumber = mobilePhoneNumber;
    }

    @Override
    public String getFirstName() {
	return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    @Override
    public String getLastName() {
	return lastName;
    }

    @Override
    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    @Override
    public String getShippingAddress() {
	return shippingAddress;
    }

    @Override
    public void setShippingAddress(String shippingAddress) {
	this.shippingAddress = shippingAddress;
    }

    @Override
    public boolean equals(Object object) {
	if (this == object)
	    return true;
	if ((object == null) || (getClass() != object.getClass()))
	    return false;
	User objectAsUserReal = (UserReal) object;
	return (id == objectAsUserReal.getId() && email.equals(objectAsUserReal.getEmail())
		&& password.equals(objectAsUserReal.getPassword()) && (roleId == objectAsUserReal.getRoleId())
		&& mobilePhoneNumber.equals(objectAsUserReal.getMobilePhoneNumber())
		&& firstName.equals(objectAsUserReal.getFirstName()) && lastName.equals(objectAsUserReal.getLastName())
		&& shippingAddress.equals(objectAsUserReal.getShippingAddress()));
    }

    @Override
    public int hashCode() {
	int prime = 31;
	int result = 1;
	result = prime * result + Objects.hashCode(id);
	return result;
    }

    // this is for testing
    @Override
    public String toString() {
	return this.getFirstName();
    }
}