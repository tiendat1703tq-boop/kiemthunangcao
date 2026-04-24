package com.poly.asm.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.poly.asm.dao.VideoDAO;
import com.poly.asm.entity.Video;

@WebServlet({"/index", "/home", ""}) 
public class HomeServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VideoDAO dao = new VideoDAO();
        // SỬA: Chỉ lấy 6 video active nhiều view nhất (Hàm này đã thêm ở bước trước)
        List<Video> list = dao.findTop6ActiveVideos(); 
        req.setAttribute("videos", list);
        req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
    }
}