package com.poly.asm.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.poly.asm.util.JpaUtil;

public class ReportDAO {
    // Thống kê: Tiêu đề Video | Số lượt thích | Ngày thích cũ nhất | Ngày thích mới nhất
    public List<Object[]> reportFavorites() {
        EntityManager em = JpaUtil.getEntityManager();
        String jpql = "SELECT f.video.title, COUNT(f), MIN(f.likeDate), MAX(f.likeDate) " +
                      "FROM Favorite f GROUP BY f.video.title";
        return em.createQuery(jpql, Object[].class).getResultList();
    }

    // Thống kê: Người thích video cụ thể
    public List<Object[]> reportFavoriteUsersByVideo(String videoId) {
        EntityManager em = JpaUtil.getEntityManager();
        String jpql = "SELECT f.user.id, f.user.fullname, f.user.email, f.likeDate " +
                      "FROM Favorite f WHERE f.video.id = :vid";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setParameter("vid", videoId);
        return query.getResultList();
    }
    
    // Thống kê: Người chia sẻ video
    public List<Object[]> reportSharesByVideo(String videoId) {
        EntityManager em = JpaUtil.getEntityManager();
        String jpql = "SELECT s.user.fullname, s.user.email, s.emails, s.shareDate " +
                      "FROM Share s WHERE s.video.id = :vid";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setParameter("vid", videoId);
        return query.getResultList();
    }
}