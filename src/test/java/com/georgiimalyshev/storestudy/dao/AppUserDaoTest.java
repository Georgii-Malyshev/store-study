package com.georgiimalyshev.storestudy.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.georgiimalyshev.storestudy.service.domain.users.AppUser;
import com.georgiimalyshev.storestudy.service.domain.users.AppUserAbstract;

public class AppUserDaoTest {
	private int id;
	private String email;
	private String password;

	@Mock
	private EntityManager entityManagerMock;
	
	@Mock
	private AppUserAbstract appUserAbstract1;
	
	@InjectMocks
	private AppUserDao appUserDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(appUserAbstract1.getId()).thenReturn(1);
		when(appUserAbstract1.getEmail()).thenReturn("email1@mail.com");
		when(appUserAbstract1.getPassword()).thenReturn("password1");
	}
	
	@Test
	public void givenExistingIdWhenFindByIdThenReturnOptional() {
		when(entityManagerMock.find(AppUserAbstract.class, 1)).thenReturn(appUserAbstract1);
		id = 1;
		Optional<AppUser> optionalOfAppUserAbstract = appUserDao.findById(id);
		assertThat(optionalOfAppUserAbstract, isA(Optional.class));
	}
}