package com.poly.asm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

// Filter này sẽ chạy cho TẤT CẢ các request (/*)
@WebFilter("/*")
public class Utf8Filter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // 1. Ép mã hóa UTF-8 cho dữ liệu nhận vào (quan trọng nhất)
        request.setCharacterEncoding("UTF-8");
        
        // 2. Ép mã hóa UTF-8 cho dữ liệu trả về
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8"); // Đặt content type mặc định

        // 3. Cho phép request đi tiếp
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}