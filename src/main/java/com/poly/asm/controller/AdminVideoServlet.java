package com.poly.asm.controller;

import com.poly.asm.dao.VideoDAO;
import com.poly.asm.entity.Video;
import org.apache.commons.beanutils.BeanUtils; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

// Lưu ý: URL chính là /admin/videos (số nhiều)
@WebServlet({"/admin/videos", "/admin/video/create", "/admin/video/update", "/admin/video/delete", "/admin/video/edit"})
public class AdminVideoServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String uri = req.getRequestURI();
        VideoDAO dao = new VideoDAO();
        Video video = new Video();
        String message = "";

        try {
            if (uri.contains("edit")) {
                String id = req.getParameter("id");
                if(id != null && !id.isEmpty()) {
                    video = dao.findById(id);
                }
            } else if (uri.contains("delete")) {
                String id = req.getParameter("id");
                try {
                    dao.remove(id);
                    message = "Xóa video thành công!";
                } catch (Exception e) {
                    message = "Không thể xóa! Video này đã có người xem hoặc yêu thích.";
                }
            }
        } catch (Exception e) {
            message = "Lỗi tải dữ liệu: " + e.getMessage();
        }

        req.setAttribute("video", video);
        req.setAttribute("message", message);
        req.setAttribute("items", dao.findAll()); 
        req.getRequestDispatcher("/views/admin/video-manager.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. QUAN TRỌNG: Phải có dòng này đầu tiên để không lỗi font Tiếng Việt
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        VideoDAO dao = new VideoDAO();
        Video video = new Video();
        String uri = req.getRequestURI();
        
        try {
            // Map dữ liệu từ form vào entity
            BeanUtils.populate(video, req.getParameterMap());
            
            if (uri.contains("create")) {
                // Kiểm tra xem ID đã tồn tại chưa
                if(dao.findById(video.getId()) != null){
                    req.setAttribute("message", "Lỗi: Mã video (ID) này đã tồn tại!");
                } else {
                    dao.create(video);
                    req.setAttribute("message", "Thêm mới thành công!");
                    video = new Video(); // Reset form sau khi thêm
                }
            } else if (uri.contains("update")) {
                dao.update(video);
                req.setAttribute("message", "Cập nhật thành công!");
            }
        } catch (Exception e) {
            req.setAttribute("message", "Lỗi thao tác: " + e.getMessage());
            e.printStackTrace(); // In lỗi ra console để dễ debug
        }

        req.setAttribute("video", video);
        req.setAttribute("items", dao.findAll());
        req.getRequestDispatcher("/views/admin/video-manager.jsp").forward(req, resp);
    }
}