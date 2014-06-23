package com.myeclipseide.examples.myblog.persistence;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;

/**
 * Data access object (DAO) for domain model class Post.
 * 
 * @see com.myeclipseide.examples.myblog.persistence.Post
 * @author MyEclipse Persistence Tools
 */

public class PostDAO {
	// property constants
	public static final String TITLE = "title";

	public static final String CONTENT = "content";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	public void save(Post transientInstance) {
		EntityManagerHelper.log("saving Post instance", Level.INFO, null);
		try {
			getEntityManager().persist(transientInstance);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void delete(Post persistentInstance) {
		EntityManagerHelper.log("deleting Post instance", Level.INFO, null);
		try {
			getEntityManager().remove(persistentInstance);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Post update(Post detachedInstance) {
		EntityManagerHelper.log("updating Post instance", Level.INFO, null);
		try {
			Post result = getEntityManager().merge(detachedInstance);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Post findById(Integer id) {
		EntityManagerHelper.log("finding Post instance with id: " + id,
				Level.INFO, null);
		try {
			Post instance = getEntityManager().find(Post.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Post> findByProperty(String propertyName, Object value) {
		EntityManagerHelper.log("finding Post instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			String queryString = "select model from Post model where model."
					+ propertyName + "= :propertyValue";
			return getEntityManager().createQuery(queryString).setParameter(
					"propertyValue", value).getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Post> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<Post> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	@SuppressWarnings("unchecked")
	public List<Post> findAll() {
		EntityManagerHelper.log("finding all Post instances", Level.INFO, null);
		try {
			String queryString = "select model from Post model";
			return getEntityManager().createQuery(queryString).getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}
