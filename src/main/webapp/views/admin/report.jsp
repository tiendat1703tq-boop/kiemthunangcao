<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/views/common/head.jsp" %>
    <title>Báo cáo thống kê</title>
</head>
<body>
    <div class="container mt-3">
        <h2 class="text-center text-danger">BÁO CÁO THỐNG KÊ</h2>
        <%@include file="layout.jsp" %>
        
        <ul class="nav nav-tabs" role="tablist">
            <li class="nav-item">
                <a class="nav-link ${tab=='favorites' || tab==null ? 'active' : ''}" href="?tab=favorites">Favorites</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${tab=='favorite-users' ? 'active' : ''}" href="?tab=favorite-users">Favorite Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${tab=='share-friends' ? 'active' : ''}" href="?tab=share-friends">Share Friends</a>
            </li>
        </ul>

        <div class="tab-content border p-3">
            
            <c:if test="${tab=='favorites' || tab==null}">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Video Title</th>
                            <th>Favorite Count</th>
                            <th>Newest Date</th>
                            <th>Oldest Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="row" items="${reportData}">
                            <tr>
                                <td>${row[0]}</td>
                                <td>${row[1]}</td>
                                <td><fmt:formatDate value="${row[2]}" pattern="dd-MM-yyyy"/></td>
                                <td><fmt:formatDate value="${row[3]}" pattern="dd-MM-yyyy"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${tab=='favorite-users'}">
                <form class="form-inline mb-3">
                    <input type="hidden" name="tab" value="favorite-users">
                    <label class="mr-2">Video Title:</label>
                    <select name="videoId" class="form-control mr-2" onchange="this.form.submit()">
                        <option value="">-- Chọn Video --</option>
                        <c:forEach var="vid" items="${videos}">
                            <option value="${vid.id}" ${param.videoId==vid.id?'selected':''}>${vid.title}</option>
                        </c:forEach>
                    </select>
                </form>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>Fullname</th>
                            <th>Email</th>
                            <th>Favorite Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="row" items="${reportData}">
                            <tr>
                                <td>${row[0]}</td>
                                <td>${row[1]}</td>
                                <td>${row[2]}</td>
                                <td><fmt:formatDate value="${row[3]}" pattern="dd-MM-yyyy"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
</body>
</html>