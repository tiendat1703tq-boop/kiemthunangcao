<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ - Online Entertainment</title>
    <jsp:include page="/views/common/head.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="/views/common/menu.jsp"></jsp:include>
    
    <div class="jumbotron jumbotron-fluid bg-info text-white text-center" style="background: linear-gradient(45deg, #17a2b8, #138496);">
        <div class="container">
            <h1 class="display-4">Thế giới giải trí đỉnh cao</h1>
            <p class="lead">Xem phim, video hài kịch miễn phí chất lượng cao.</p>
        </div>
    </div>

    <div class="container mt-4 mb-5">
        <div class="row">
            <c:forEach var="item" items="${videos}">
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <a href="${pageContext.request.contextPath}/video/detail?id=${item.id}">
                            <img class="card-img-top video-poster" src="${item.poster}" alt="${item.title}">
                            <div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); color: white; opacity: 0.8;">
                                <i class="far fa-play-circle fa-4x"></i>
                            </div>
                        </a>
                        <div class="card-body">
                            <h5 class="card-title text-truncate">
                                <a href="${pageContext.request.contextPath}/video/detail?id=${item.id}" class="text-dark text-decoration-none">
                                    ${item.title}
                                </a>
                            </h5>
                            <div class="d-flex justify-content-between align-items-center mt-3">
                                <div>
                                    <a href="${pageContext.request.contextPath}/like?id=${item.id}" class="btn btn-sm btn-outline-danger">
                                        <i class="fas fa-thumbs-up"></i> Like
                                    </a>
                                    <button type="button" class="btn btn-sm btn-outline-primary" onclick="shareVideo('${item.id}')">
                                        <i class="fas fa-share"></i> Share
                                    </button>
                                </div>
                                <small class="text-muted"><i class="far fa-eye"></i> ${item.views}</small>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        </div>
    
    <footer class="text-center">
        <p>&copy; 2025 FPT Polytechnic - Nguyễn Quang Nhật - Java 4 Assignment</p>
    </footer>
</body>
</html>