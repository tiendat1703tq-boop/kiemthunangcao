package com.poly.asm.dao;

import com.poly.asm.entity.User;
import com.poly.asm.util.JpaUtil;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAO {
    // Sử dụng EntityManager trực tiếp để đảm bảo ổn định
    private EntityManager em = JpaUtil.getEntityManager();

    @Override
    protected void finalize() throws Throwable {
        em.close();
    }

    public User create(User entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public User update(User entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public User remove(String id) {
        try {
            User entity = this.findById(id);
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public User findById(String id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.getResultList();
    }
}