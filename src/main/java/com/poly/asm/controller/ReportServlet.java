package com.poly.asm.controller;

import com.poly.asm.dao.ReportDAO;
import com.poly.asm.dao.VideoDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/reports")
public class ReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tab = req.getParameter("tab");
        String videoId = req.getParameter("videoId");
        
        ReportDAO rDao = new ReportDAO();
        VideoDAO vDao = new VideoDAO();

        // Mặc định load tab Favorites
        if (tab == null || tab.equals("favorites")) {
            req.setAttribute("reportData", rDao.reportFavorites());
        } else if (tab.equals("favorite-users")) {
            req.setAttribute("videos", vDao.findAll()); // Để đổ vào dropdown list
            if (videoId != null) {
                req.setAttribute("reportData", rDao.reportFavoriteUsersByVideo(videoId));
            }
        } else if (tab.equals("share-friends")) {
            req.setAttribute("videos", vDao.findAll());
            if (videoId != null) {
                req.setAttribute("reportData", rDao.reportSharesByVideo(videoId));
            }
        }

        req.setAttribute("tab", tab);
        req.getRequestDispatcher("/views/admin/report.jsp").forward(req, resp);
    }
}