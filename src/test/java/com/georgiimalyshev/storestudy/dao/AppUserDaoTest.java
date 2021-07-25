package com.georgiimalyshev.storestudy.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

// TODO consider using Docker for testing DAO layer against a test SQL database (not an in-memory one)
@ExtendWith(MockitoExtension.class)
public class AppUserDaoTest {
	@Test
	public void givenExistingId_WhenFindById_ThenReturnOptionalOfAppUserWithThatId() {
	}
}