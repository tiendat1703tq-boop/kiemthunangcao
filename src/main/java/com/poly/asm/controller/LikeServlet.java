package com.poly.asm.controller;

import com.poly.asm.dao.FavoriteDAO;
import com.poly.asm.dao.VideoDAO;
import com.poly.asm.entity.Favorite;
import com.poly.asm.entity.User;
import com.poly.asm.entity.Video;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String videoId = req.getParameter("id");

        // 1. Chưa đăng nhập -> Bắt đăng nhập
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        // 2. Kiểm tra ID video
        if (videoId == null || videoId.trim().isEmpty()) {
            resp.sendRedirect("index");
            return;
        }

        // 3. KIỂM TRA VIDEO CÓ TỒN TẠI KHÔNG? (Đây là bước chặn lỗi 500)
        VideoDAO videoDAO = new VideoDAO();
        Video video = videoDAO.findById(videoId);

        if (video == null) {
            System.out.println("Cảnh báo: Đang cố like video không tồn tại ID=" + videoId);
            resp.sendRedirect("index"); // Video ma -> Quay về trang chủ ngay
            return;
        }

        // 4. Xử lý Like/Unlike an toàn
        FavoriteDAO dao = new FavoriteDAO();
        Favorite favorite = dao.findByUserIdAndVideoId(user.getId(), videoId);

        if (favorite == null) {
            favorite = new Favorite();
            favorite.setUser(user);
            favorite.setVideo(video); // Dùng video thật lấy từ DB
            favorite.setLikeDate(new Date());
            dao.create(favorite);
        } else {
            dao.remove(favorite.getId());
        }

        resp.sendRedirect(req.getHeader("referer"));
    }
}