package com.poly.asm.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.poly.asm.util.JpaUtil; // Đảm bảo bạn đã có class JpaUtil

public abstract class AbstractDAO<T> {
    private Class<T> entityClass;

    public AbstractDAO(Class<T> cls) {
        this.entityClass = cls;
    }

    public void create(T entity) {
        EntityManager em = JpaUtil.getEntityManager(); // 1. Mở kết nối
        try {
            em.getTransaction().begin();    // 2. Mở giao dịch (Mở két sắt)
            em.persist(entity);             // 3. Lệnh INSERT vào Database
            em.getTransaction().commit();   // 4. Lưu thay đổi (Đóng két)
        } catch (Exception e) {
            em.getTransaction().rollback(); // 5. Nếu lỗi -> Hoàn tác (Undo)
            throw new RuntimeException(e);
        } finally {
            em.close();                     // 6. Đóng kết nối để giải phóng RAM
        }
    }

    public void update(T entity) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public void remove(Object id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            T entity = em.find(entityClass, id);
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public T findById(Object id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(entityClass, id);//Hibernate sẽ tự động dịch sang SQL là: SELECT * FROM [Tên_Bảng] WHERE Id = '...'
        } finally {
            em.close();
        }
    }

    public List<T> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
            cq.select(cq.from(entityClass));
            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }
    
    // Hàm hỗ trợ phân trang (dùng cho trang chủ)
    public List<T> findAll(int pageNumber, int pageSize) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
            Root<T> root = cq.from(entityClass);
            cq.select(root);
            TypedQuery<T> query = em.createQuery(cq);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}