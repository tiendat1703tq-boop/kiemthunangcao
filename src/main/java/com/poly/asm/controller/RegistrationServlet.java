package com.poly.asm.controller;

import com.poly.asm.dao.UserDAO;
import com.poly.asm.entity.User;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // SỬA: Thêm /user/ vào đường dẫn
        req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Thiết lập tiếng Việt cho request
            req.setCharacterEncoding("UTF-8");
            
            User user = new User();
            BeanUtils.populate(user, req.getParameterMap());
            user.setAdmin(false); // Mặc định là user thường

            UserDAO dao = new UserDAO();
            
            // Kiểm tra ID đã tồn tại chưa
            if(dao.findById(user.getId()) != null) {
                req.setAttribute("message", "Tên đăng nhập đã tồn tại!");
            } else {
                dao.create(user);
                req.setAttribute("message", "Đăng ký thành công! Hãy đăng nhập.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Lỗi đăng ký: " + e.getMessage());
        }
        
        // SỬA: Thêm /user/ vào đường dẫn để hiển thị lại form (kèm thông báo)
        req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
    }
}