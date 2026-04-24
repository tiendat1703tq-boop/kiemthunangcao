package com.poly.asm.controller;

import com.poly.asm.dao.FavoriteDAO;
import com.poly.asm.entity.Favorite;
import com.poly.asm.entity.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/favorite")
public class MyFavoriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        
        // Nếu chưa đăng nhập thì đẩy về trang login
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        FavoriteDAO dao = new FavoriteDAO();
        List<Favorite> list = dao.findByUserId(user.getId());
        
        req.setAttribute("favorites", list);
        req.getRequestDispatcher("/views/user/favorite.jsp").forward(req, resp);
    }
}