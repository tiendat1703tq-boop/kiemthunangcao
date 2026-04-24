<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Video Yêu Thích - Online Entertainment</title>
    <jsp:include page="/views/common/head.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="/views/common/menu.jsp"></jsp:include>

    <div class="container mt-4">
        <h2 class="text-primary border-bottom pb-2">Danh sách Video Yêu Thích Của Bạn</h2>
        
        <div class="row mt-4">
            <c:if test="${empty favorites}">
                <div class="col-12 text-center">
                    <p>Bạn chưa thích video nào.</p>
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Xem video ngay</a>
                </div>
            </c:if>

            <c:forEach var="item" items="${favorites}">
                <div class="col-md-4 mb-4">
                    <div class="card h-100 shadow-sm">
                        <a href="${pageContext.request.contextPath}/video/detail?id=${item.video.id}">
                            <img src="${item.video.poster}" class="card-img-top" alt="${item.video.title}" style="height: 200px; object-fit: cover;">
                        </a>
                        <div class="card-body">
                            <h5 class="card-title text-truncate">${item.video.title}</h5>
                            
                            <p class="card-text text-muted">
                                <small>Ngày thích: <fmt:formatDate value="${item.likeDate}" pattern="dd-MM-yyyy HH:mm:ss" /></small>
                            </p>
                            
                        </div>
                        <div class="card-footer bg-white border-top-0 d-flex justify-content-between">
                             <a href="${pageContext.request.contextPath}/like?id=${item.video.id}" class="btn btn-outline-danger btn-sm btn-block">
                                <i class="fas fa-thumbs-down"></i> Bỏ thích (Unlike)
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>