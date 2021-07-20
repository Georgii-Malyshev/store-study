package com.georgiimalyshev.storestudy.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.georgiimalyshev.storestudy.service.domain.users.AppUser;
import com.georgiimalyshev.storestudy.service.domain.users.AppUserAbstract;

@ExtendWith(MockitoExtension.class)
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
	
	@BeforeEach
	public void setUp() {
		lenient().when(appUserAbstract1.getId()).thenReturn(1);
		lenient().when(appUserAbstract1.getEmail()).thenReturn("email1@mail.com");
		lenient().when(appUserAbstract1.getPassword()).thenReturn("password1");
	}
	
	@Test
	public void givenExistingIdWhenFindByIdThenReturnOptional() {
		lenient().when(entityManagerMock.find(AppUserAbstract.class, 1)).thenReturn(appUserAbstract1);
		id = 1;
		Optional<AppUser> optionalOfAppUserAbstract = appUserDao.findById(id);
		assertThat(optionalOfAppUserAbstract, isA(Optional.class));
	}
}