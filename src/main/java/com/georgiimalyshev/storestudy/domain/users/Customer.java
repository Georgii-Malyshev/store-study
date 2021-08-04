package com.georgiimalyshev.storestudy.domain.users;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.georgiimalyshev.storestudy.domain.store.Cart;

@Entity
@Table(name = "customer")
public class Customer extends AppUserAbstract {
	public Customer() {
	}
	
	public Customer(String email, String password) {
		this.email = email;
		this.password = password;
	}

	private String mobilePhoneNumber;
	private String firstName;
	private String lastName;
	private String shippingAddress;
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
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