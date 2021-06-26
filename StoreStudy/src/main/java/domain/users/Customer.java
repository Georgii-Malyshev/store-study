package domain.users;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import domain.store.Cart;

@Entity
public class Customer extends AppUserAbstract {
	public Customer(String email, String password) {
		this.email = email;
		this.password = password;
	}

	private String mobilePhoneNumber;
	private String firstName;
	private String lastName;
	private String shippingAddress;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	private Cart cart;

	public String getMobilePhoneNumber() {
		return this.mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getShippingAddress() {
		return this.shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Cart getCart() {
		return this.cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	// TODO must double-check equals() and hashCode() later!
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if ((object == null) || (getClass() != object.getClass()))
			return false;
		Customer objectAsCustomer = (Customer) object;
		return (this.id == objectAsCustomer.getId() && this.email.equals(objectAsCustomer.getEmail())
				&& this.password.equals(objectAsCustomer.getPassword())
				&& this.mobilePhoneNumber.equals(objectAsCustomer.getMobilePhoneNumber())
				&& this.firstName.equals(objectAsCustomer.getFirstName()) && this.lastName.equals(objectAsCustomer.getLastName())
				&& this.shippingAddress.equals(objectAsCustomer.getShippingAddress()));
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + Objects.hashCode(id);
		return result;
	}

}