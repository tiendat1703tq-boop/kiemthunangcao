package com.poly.asm.filter;

import com.poly.asm.entity.User;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Chặn tất cả các request tới trang admin và các chức năng cần đăng nhập
@WebFilter({"/admin/*", "/like", "/share", "/account/*"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        
        User user = (User) req.getSession().getAttribute("user");
        String error = "";

        if (user == null) {
            // 1. Chưa đăng nhập
            error = "Vui lòng đăng nhập!";
        } else if (!user.getAdmin() && uri.contains("/admin/")) {
            // 2. Đã đăng nhập nhưng không phải Admin mà cố vào trang admin
            error = "Vui lòng đăng nhập với vai trò Admin!";
        }

        if (!error.isEmpty()) {
            // Lưu url hiện tại để sau khi login quay lại đúng trang đó (nếu muốn)
            req.getSession().setAttribute("security-uri", uri);
            resp.sendRedirect(req.getContextPath() + "/login?error=" + java.net.URLEncoder.encode(error, "UTF-8"));
        } else {
            // Cho qua
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}