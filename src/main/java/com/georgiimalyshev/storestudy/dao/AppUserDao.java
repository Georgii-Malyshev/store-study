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

import com.georgiimalyshev.storestudy.domain.users.AppUser;
import com.georgiimalyshev.storestudy.domain.users.AppUserAbstract;

@Repository
@Transactional
public class AppUserDao {
	@PersistenceContext
	protected EntityManager entityManager;

	public void persist(AppUser appUser) {
		entityManager.persist(appUser);
	}

	public Optional<AppUser> findById(int id) {
		AppUser appUser = entityManager.find(AppUserAbstract.class, id);
		return Optional.ofNullable(appUser);
	}

	// TODO get rid of duplicate code in methods that use JPQL queries

	public Optional<AppUser> findByCredentials(String email, String password) {
		// TODO implement some checks to make sure that this code ALWAYS returns a
		// single and correct result
		TypedQuery<AppUserAbstract> typedQuery = entityManager.createQuery(
				"SELECT u FROM AppUserAbstract u WHERE u.email = :email AND u.password = :password",
				AppUserAbstract.class);
		typedQuery.setParameter("email", email);
		typedQuery.setParameter("password", password);
		Stream<AppUserAbstract> resultStream = typedQuery.getResultStream();
		Optional<AppUserAbstract> optionalOfAppUserAbstract = resultStream.findFirst();
		// TODO consider using Optional.map
		AppUser appUser = optionalOfAppUserAbstract.orElse(null);
		return Optional.ofNullable(appUser);
	}

	public Optional<AppUser> findByEmail(String email) {
		// TODO implement some checks to make sure that this code ALWAYS returns a
		// single and correct result
		TypedQuery<AppUserAbstract> typedQuery = entityManager
				.createQuery("SELECT u FROM AppUserAbstract u WHERE u.email = :email", AppUserAbstract.class);
		typedQuery.setParameter("email", email);
		Stream<AppUserAbstract> resultStream = typedQuery.getResultStream();
		Optional<AppUserAbstract> optionalOfAppUserAbstract = resultStream.findFirst();
		// TODO consider using Optional.map
		AppUser appUser = optionalOfAppUserAbstract.orElse(null);
		return Optional.ofNullable(appUser);
	}

	public Set<AppUser> getAllUsers() {
		TypedQuery<AppUserAbstract> typedQuery = entityManager.createQuery("SELECT u FROM AppUserAbstract u",
				AppUserAbstract.class);
		List<AppUserAbstract> resultList = typedQuery.getResultList();
		Set<AppUser> appUsers = new HashSet<AppUser>(resultList);
		return appUsers;
	}
}