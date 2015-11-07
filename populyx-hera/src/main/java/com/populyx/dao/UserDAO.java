package com.populyx.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.populyx.entities.User;

@Repository
public class UserDAO {

	EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	public User findByUserName(String username) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		TypedQuery<User> query;

		cq.select(user);
		predicates.add(cb.equal(user.<String> get("username"), username));
		cq.where(cb.or(predicates.toArray(new Predicate[] {})));
		query = entityManager.createQuery(cq);
		User userLogged=query.getSingleResult();
		return userLogged ;
	}
}
