package com.georgiimalyshev.storestudy.dao;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
// TODO consider using Docker for testing DAO layer against a test SQL database (not an in-memory one)
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
		when(appUserAbstract1.getId()).thenReturn(1);
		when(appUserAbstract1.getEmail()).thenReturn("email1@mail.com");
		when(appUserAbstract1.getPassword()).thenReturn("password1");
	}
	
	@Test
	public void givenExistingId_WhenFindById_ThenReturnOptionalOfAppUserWithThatId() {
		when(entityManagerMock.find(AppUserAbstract.class, 1)).thenReturn(appUserAbstract1);
		id = 1;
		Optional<AppUser> optionalOfAppUser = appUserDao.findById(id);
		assertAll(
			() -> assertEquals(1, optionalOfAppUser.get().getId()),
			() -> assertEquals("email1@mail.com", optionalOfAppUser.get().getEmail()),
			() -> assertEquals("password1", optionalOfAppUser.get().getPassword())
		);
	}
}