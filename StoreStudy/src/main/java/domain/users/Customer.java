package domain.users;

import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class Customer extends AppUserAbstract {
	
	
	private String mobilePhoneNumber;
	private String firstName;
	private String lastName;
	private String shippingAddress;

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
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

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if ((object == null) || (getClass() != object.getClass()))
			return false;
		Customer objectAsCustomer = (Customer) object;
		return (id == objectAsCustomer.getId() && email.equals(objectAsCustomer.getEmail())
				&& password.equals(objectAsCustomer.getPassword())
				&& mobilePhoneNumber.equals(objectAsCustomer.getMobilePhoneNumber())
				&& firstName.equals(objectAsCustomer.getFirstName()) && lastName.equals(objectAsCustomer.getLastName())
				&& shippingAddress.equals(objectAsCustomer.getShippingAddress()));
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + Objects.hashCode(id);
		return result;
	}
}