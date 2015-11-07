package com.populyx.cerbero.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.populyx.cerbero.model.Register;
import com.populyx.entities.User;

@Repository
public class RegisterDAO {

	EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	public Register findByUser(User user) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		List<Predicate> predicates = new ArrayList<Predicate>();

		CriteriaQuery<Register> cq = cb.createQuery(Register.class);
		Root<Register> register = cq.from(Register.class);
		TypedQuery<Register> query;

		cq.select(register);
		predicates.add(cb.equal(register.<Long>get("userId"), user.getId()));
		cq.where(cb.or(predicates.toArray(new Predicate[] {})));
		query = entityManager.createQuery(cq);
		Register userRegister = query.getSingleResult();
		return userRegister;
	}

	public void registerUserLogin(Long userId) {
		User user=getUser(userId);
		// user.setIsLogged(true);
		Register userRegister = this.findByUser(user);
		userRegister.setLogInAt(new Date());
	}
	
	public void unRegisterUserLogin(Long userId){
		User user=getUser(userId);
//		user.setIsLogged(false);
		Register userRegister=this.findByUser(user);
		userRegister.setLogOffAt(new Date());
	}
	
	private User getUser(Long userId){
		return entityManager.find(User.class,userId);
	}
}
