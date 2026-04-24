package com.poly.asm.controller;

import com.poly.asm.dao.ShareDAO;
import com.poly.asm.entity.Share;
import com.poly.asm.entity.User;
import com.poly.asm.entity.Video;
import com.poly.asm.util.EmailUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/share")
public class ShareServlet extends HttpServlet {

    // --- 1. THÊM HÀM NÀY ĐỂ SỬA LỖI 405 ---
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Nếu người dùng cố tình gõ /share trên trình duyệt (GET request)
        // Thay vì báo lỗi, ta chuyển hướng họ về trang chủ
        resp.sendRedirect(req.getContextPath() + "/home");
    }
    // ------------------------------------

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Đặt định dạng tiếng Việt cho phản hồi
        resp.setCharacterEncoding("UTF-8");
        
        try {
            User user = (User) req.getSession().getAttribute("user");
            if (user == null) {
                resp.getWriter().write("LoginRequired"); // Phản hồi cho AJAX
                return;
            }

            String videoId = req.getParameter("videoId");
            String emailTo = req.getParameter("email");
            
            // Kiểm tra dữ liệu đầu vào cơ bản
            if (videoId == null || emailTo == null || emailTo.isEmpty()) {
                 resp.getWriter().write("Error");
                 return;
            }

            Video video = new Video();
            video.setId(videoId);

            // Lưu vào CSDL
            Share share = new Share();
            share.setUser(user);
            share.setVideo(video);
            share.setEmails(emailTo);
            share.setShareDate(new Date());

            ShareDAO dao = new ShareDAO();
            dao.create(share);

            // Gửi mail
            String subject = "Chia sẻ video hay từ Online Entertainment";
            // Tạo link video (dùng request để lấy đúng domain hiện tại nếu cần, hoặc hardcode youtube)
            String body = "Xin chào,<br><br>" 
                        + "Bạn <b>" + user.getFullname() + "</b> đã chia sẻ video này cho bạn:<br>"
                        + "<a href='https://www.youtube.com/watch?v=" + videoId + "'>Xem video ngay</a><br><br>"
                        + "Trân trọng.";
            
            EmailUtil.send(emailTo, subject, body);

            resp.getWriter().write("Success");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Error");
        }
    }
}