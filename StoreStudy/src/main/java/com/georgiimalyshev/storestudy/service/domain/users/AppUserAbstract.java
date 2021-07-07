package com.georgiimalyshev.storestudy.service.domain.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "appuser")
public abstract class AppUserAbstract implements AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appuser_generator")
	@SequenceGenerator(name = "appuser_generator", sequenceName = "appuser_id_seq")
	@Column(updatable = false, nullable = false)
	protected int id;
	@Column(nullable = false)
	protected String email;
	@Column(nullable = false)
	protected String password;

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getEmail() {
		return this.email;
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
}