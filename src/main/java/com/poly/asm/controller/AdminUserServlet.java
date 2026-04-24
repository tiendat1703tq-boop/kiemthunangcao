package com.poly.asm.controller;

import com.poly.asm.dao.UserDAO;
import com.poly.asm.entity.User;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

// Map đầy đủ các đường dẫn
@WebServlet({"/admin/users", "/admin/user/create", "/admin/user/update", "/admin/user/delete", "/admin/user/edit"})
public class AdminUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        UserDAO dao = new UserDAO();
        User user = new User();
        String uri = req.getRequestURI();
        String message = "";

        try {
            if (uri.contains("edit")) {
                String id = req.getParameter("id");
                user = dao.findById(id); // Lấy user lên form để sửa
            } else if (uri.contains("delete")) {
                String id = req.getParameter("id");
                // Chặn không cho xóa chính mình đang đăng nhập
                User currentUser = (User) req.getSession().getAttribute("user");
                if (currentUser != null && currentUser.getId().equals(id)) {
                     message = "Lỗi: Bạn không thể xóa tài khoản đang đăng nhập!";
                } else {
                    dao.remove(id);
                    message = "Xóa tài khoản thành công!";
                }
            }
        } catch (Exception e) {
            message = "Lỗi thao tác: " + e.getMessage();
        }

        req.setAttribute("message", message);
        req.setAttribute("user", user); // Truyền object user xuống form
        req.setAttribute("items", dao.findAll()); // Truyền danh sách xuống bảng
        req.getRequestDispatcher("/views/admin/user-manager.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        UserDAO dao = new UserDAO();
        User user = new User();
        String uri = req.getRequestURI();
        
        try {
            // Map dữ liệu từ form vào entity User
            BeanUtils.populate(user, req.getParameterMap());
            
            if (uri.contains("create")) {
                // Kiểm tra trùng ID
                if(dao.findById(user.getId()) != null) {
                    req.setAttribute("message", "Lỗi: Tên đăng nhập này đã tồn tại!");
                } else {
                    dao.create(user);
                    req.setAttribute("message", "Thêm người dùng mới thành công!");
                    user = new User(); // Reset form sau khi thêm
                }
            } else if (uri.contains("update")) {
                dao.update(user);
                req.setAttribute("message", "Cập nhật thành công!");
            }
        } catch (Exception e) {
            req.setAttribute("message", "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }

        req.setAttribute("user", user);
        req.setAttribute("items", dao.findAll());
        req.getRequestDispatcher("/views/admin/user-manager.jsp").forward(req, resp);
    }
}