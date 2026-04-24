package com.poly.asm.dao;

import com.poly.asm.entity.Video;
import com.poly.asm.util.JpaUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class VideoDAO extends AbstractDAO<Video> {

    public VideoDAO() {
        super(Video.class);
    }

    // 1. Lấy 6 video được xem nhiều nhất (Active = true) cho Trang Chủ
    public List<Video> findTop6ActiveVideos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT v FROM Video v WHERE v.active = true ORDER BY v.views DESC"; //sắp xếp tăng dần
            TypedQuery<Video> query = em.createQuery(jpql, Video.class);
            query.setMaxResults(6);
            return query.getResultList();// Trả về một List (Danh sách)
        } finally {
            em.close();
        }
    }

    // 2. Lấy tất cả video đang hoạt động (Active = true) - Dùng cho trang người dùng xem
    public List<Video> findAllActive() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT v FROM Video v WHERE v.active = true";
            TypedQuery<Video> query = em.createQuery(jpql, Video.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}