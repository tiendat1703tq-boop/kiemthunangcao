package com.poly.asm.controller;

import com.poly.asm.dao.UserDAO;
import com.poly.asm.entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/account/edit-profile")
public class EditProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/user/edit-profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // Để nhận tiếng Việt
        
        User user = (User) req.getSession().getAttribute("user");
        
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        
        try {
            user.setFullname(fullname);
            user.setEmail(email);
            
            UserDAO dao = new UserDAO();
            dao.update(user);
            
            // QUAN TRỌNG: Cập nhật lại user trong session để giao diện đổi ngay lập tức
            req.getSession().setAttribute("user", user);
            
            req.setAttribute("message", "Cập nhật hồ sơ thành công!");
        } catch (Exception e) {
            req.setAttribute("error", "Lỗi cập nhật: " + e.getMessage());
        }
        
        req.getRequestDispatcher("/views/user/edit-profile.jsp").forward(req, resp);
    }
}