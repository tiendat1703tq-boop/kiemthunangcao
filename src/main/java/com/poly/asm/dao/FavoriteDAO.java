package com.poly.asm.dao;

import com.poly.asm.entity.Favorite;
import com.poly.asm.util.JpaUtil;
import java.util.List; // Quan trọng: Nhớ import List
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class FavoriteDAO extends AbstractDAO<Favorite> {

    // 1. Constructor bắt buộc
    public FavoriteDAO() {
        super(Favorite.class);
    }

    // 2. Hàm kiểm tra xem User đã like Video cụ thể chưa (Dùng cho nút Like/Unlike)
    public Favorite findByUserIdAndVideoId(String userId, String videoId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT f FROM Favorite f WHERE f.user.id = :uid AND f.video.id = :vid";
            TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
            query.setParameter("uid", userId);
            query.setParameter("vid", videoId);
            
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // Chưa like
        } finally {
            em.close();
        }
    }

    // 3. (MỚI) Hàm lấy danh sách TẤT CẢ video yêu thích của một User
    // Dùng cho trang "My Favorites"
    public List<Favorite> findByUserId(String userId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            // Lấy danh sách Favorite dựa trên UserID
            String jpql = "SELECT f FROM Favorite f WHERE f.user.id = :uid";
            TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
            query.setParameter("uid", userId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    // 4. Hàm xóa
    @Override
    public void remove(Object id) {
        super.remove(id);
    }
}