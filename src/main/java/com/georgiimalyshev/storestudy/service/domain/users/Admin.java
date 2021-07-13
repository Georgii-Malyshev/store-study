package com.georgiimalyshev.storestudy.service.domain.users;

import java.util.Objects;

public class Admin extends AppUserAbstract {
	public Admin() {
	}

	public Admin(String email, String password) {
		this.email = email;
		this.password = password;
	}

	// TODO must double-check equals() and hashCode() later!
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if ((object == null) || (getClass() != object.getClass()))
			return false;
		Admin objectAsAdmin = (Admin) object;
		return (this.id == objectAsAdmin.getId() && this.email.equals(objectAsAdmin.getEmail())
				&& this.password.equals(objectAsAdmin.getPassword()));
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + Objects.hashCode(id);
		return result;
	}
}