package com.georgiimalyshev.storestudy.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.georgiimalyshev.storestudy.service.domain.users.AppUser;
import com.georgiimalyshev.storestudy.service.domain.users.AppUserAbstract;

@Repository
@Transactional
public class AppUserDao {
	@PersistenceContext protected EntityManager entityManager;

	public void persist(AppUser appUser) {
		entityManager.persist(appUser);
	}
	
	public Optional<AppUser> findById(int id) {
		AppUser appUser = entityManager.find(AppUserAbstract.class, id);
		return Optional.ofNullable(appUser);
	}

	public Optional<AppUser> findByCredentials(String email, String password) {
		TypedQuery<AppUserAbstract> typedQuery = entityManager
				.createQuery("SELECT u FROM AppUserAbstract u WHERE u.email = :email AND u.password = :password", AppUserAbstract.class);
		typedQuery.setParameter("email", email);
		typedQuery.setParameter("password", password);
		typedQuery.setMaxResults(1);
		Stream<AppUserAbstract> resultStream = typedQuery.getResultStream();
		Optional<AppUserAbstract> optionalOfAppUserAbstract = resultStream.findFirst();
		// TODO consider using Optional.map
		AppUser appUser = optionalOfAppUserAbstract.orElse(null);
		return Optional.ofNullable(appUser);
	}
	
	public Optional<AppUser> findByEmail(String email) {
		TypedQuery<AppUserAbstract> typedQuery = entityManager
				.createQuery("SELECT u FROM AppUserAbstract u WHERE u.email = :email", AppUserAbstract.class);
		typedQuery.setParameter("email", email);
		typedQuery.setMaxResults(1);
		Stream<AppUserAbstract> resultStream = typedQuery.getResultStream();
		Optional<AppUserAbstract> optionalOfAppUserAbstract = resultStream.findFirst();
		// TODO consider using Optional.map
		AppUser appUser = optionalOfAppUserAbstract.orElse(null);
		return Optional.ofNullable(appUser);
	}
	
	public Set<AppUser> getAllUsers() {
		TypedQuery<AppUserAbstract> typedQuery = entityManager.
				createQuery("SELECT u FROM AppUserAbstract u", AppUserAbstract.class);
		List<AppUserAbstract> resultList = typedQuery.getResultList();
		Set<AppUser> appUsers = new HashSet<AppUser>(resultList);
		return appUsers;
	}
}