package com.georgiimalyshev.storestudy.dao;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.georgiimalyshev.storestudy.domain.store.Cart;

@Repository
@Transactional
public class CartDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void persist(Cart cart) {
		entityManager.persist(cart);
	}
	
	public void merge(Cart cart) {
		entityManager.merge(cart);
	}
	
	public Optional<Cart> findByIdAndFetchCartItems(int id) {
		TypedQuery<Cart> typedQuery = entityManager.createQuery(
				"SELECT c FROM Cart c LEFT JOIN FETCH c.cartItems ci WHERE c.id = :id", Cart.class);
		typedQuery.setParameter("id", id);
		Stream<Cart> resultStream = typedQuery.getResultStream();
		Optional<Cart> optionalOfCart= resultStream.findFirst(); 
		return optionalOfCart;
	}
}