package com.meetups.model.DAO;
//package com.meetups.model.DAO;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.meetups.model.entity.User;
//
//
//@Repository("clienteDaoJpa")
//public class UserDaoImpl implements IUserDao{
//
//	@PersistenceContext
//	private EntityManager em;
//	
//	@Override
//	@SuppressWarnings("unchecked")
//	@Transactional(readOnly=true)
//	public List<User> findAll() {
//		return em.createQuery("from Cliente").getResultList();
//	}
//
//	@Override
//	@Transactional
//	public void save(User cliente) {
//		if(cliente.getId() != null && cliente.getId() > 0) {
//			em.merge(cliente);
//		}else {
//			em.persist(cliente);
//		}
//	}
//
//	@Override
//	public User findOne(Long id) {
//		return em.find(User.class, id);
//		
//	}
//
//}
