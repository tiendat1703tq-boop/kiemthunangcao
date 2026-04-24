
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/views/common/head.jsp" %>
    <title>Trang chủ</title>
</head>
<body>
    <%@include file="/views/common/menu.jsp" %>
    
    <div class="container mt-4">
        <div class="row">
            <c:forEach var="item" items="${videos}">
                <div class="col-sm-4 mb-4">
                    <div class="card h-100">
                        <a href="video/detail?id=${item.id}">
                            <img class="card-img-top" src="${item.poster}" alt="Poster">
                        </a>
                        <div class="card-body">
                            <h5 class="card-title text-truncate">${item.title}</h5>
                            <div class="d-flex justify-content-between">
                                <a href="like?id=${item.id}" class="btn btn-success btn-sm">Like</a>
                                <a href="video/detail?id=${item.id}" class="btn btn-info btn-sm">Share</a>
                            </div>
                        </div>
                        <div class="card-footer text-muted">
                            Views: ${item.views}
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        
        <ul class="pagination justify-content-center">
            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
    </div>
    
    <footer class="text-center">Nguyễn Quang Nhật &copy; 2025</footer>
</body>
</html>