package com.poly.asm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; 
import com.poly.asm.dao.UserDAO;
import com.poly.asm.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Thay đổi thành public để có thể test doGet nếu cần
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    // QUAN TRỌNG: Đổi từ protected sang public để class testNG có thể gọi được phương thức này
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("username");
        String password = req.getParameter("password");
        
        UserDAO dao = new UserDAO();
        User user = dao.findById(id);

        // Kiểm tra đăng nhập
        if (user != null && user.getPassword().equals(password)) {
            // 1. Lưu thông tin vào Session
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // 3. Chuyển hướng về Trang chủ
            resp.sendRedirect("index");

        } else {
            // Đăng nhập thất bại
            req.setAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}