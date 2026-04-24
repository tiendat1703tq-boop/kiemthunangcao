package com.poly.asm.controller;

import com.poly.asm.dao.VideoDAO;
import com.poly.asm.entity.Video;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/video/detail")
public class VideoDetailServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        VideoDAO dao = new VideoDAO();
        Video video = dao.findById(id);

        if (video != null) {
            // 1. Tăng view
            video.setViews(video.getViews() + 1);
            dao.update(video);
            req.setAttribute("video", video);

            // 2. Xử lý "Video đã xem" bằng Cookie
            // Logic: Lấy cookie cũ -> Cộng thêm ID mới vào -> Lưu lại
            String history = "";
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("history")) {
                        history = c.getValue();
                        break;
                    }
                }
            }

            // Nếu ID chưa có trong lịch sử thì thêm vào (ngăn cách bằng dấu #)
            if (!history.contains(id)) {
                history += id + "#";
            }

            Cookie c = new Cookie("history", history);
            c.setMaxAge(60 * 60 * 24 * 7); // Lưu 7 ngày
            c.setPath("/"); // Quan trọng: Để cookie dùng được toàn bộ web
            resp.addCookie(c);

            // 3. Load danh sách video đã xem từ Cookie để hiển thị ra màn hình
            List<Video> historyVideos = new ArrayList<>();
            // Cắt chuỗi history: id1#id2# -> [id1, id2]
            String[] ids = history.split("#"); 
            for (String vidId : ids) {
                if(!vidId.isEmpty()){
                    Video v = dao.findById(vidId);
                    if(v != null) historyVideos.add(v);
                }
            }
            req.setAttribute("historyVideos", historyVideos);

            // 4. Load video ngẫu nhiên (trừ video đang xem)
            List<Video> allVideos = dao.findAllActive();
            req.setAttribute("randomVideos", allVideos); 

            req.getRequestDispatcher("/views/user/detail.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}