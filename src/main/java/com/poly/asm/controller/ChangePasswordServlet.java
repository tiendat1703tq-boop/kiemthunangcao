package com.poly.asm.controller;

import com.poly.asm.dao.UserDAO;
import com.poly.asm.entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/account/change-password")
public class ChangePasswordServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/user/change-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy user từ session (Vì đã qua AuthFilter nên chắc chắn user không null)
        User user = (User) req.getSession().getAttribute("user");
        
        String currentPass = req.getParameter("currentPassword");
        String newPass = req.getParameter("newPassword");
        String confirmPass = req.getParameter("confirmPassword");
        
        String message = "";
        String error = "";

        // 1. Kiểm tra mật khẩu cũ
        if (!user.getPassword().equals(currentPass)) {
            error = "Mật khẩu hiện tại không đúng!";
        } 
        // 2. Kiểm tra mật khẩu mới và xác nhận
        else if (!newPass.equals(confirmPass)) {
            error = "Mật khẩu xác nhận không trùng khớp!";
        } 
        else {
            // 3. Cập nhật
            user.setPassword(newPass);
            UserDAO dao = new UserDAO();
            dao.update(user);
            message = "Đổi mật khẩu thành công!";
        }

        req.setAttribute("message", message);
        req.setAttribute("error", error);
        req.getRequestDispatcher("/views/user/change-password.jsp").forward(req, resp);
    }
}