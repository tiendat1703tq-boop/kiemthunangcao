<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cập Nhật Hồ Sơ</title>
    <jsp:include page="/views/common/head.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="/views/common/menu.jsp"></jsp:include>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-header bg-info text-white">
                        <h4>Cập Nhật Hồ Sơ</h4>
                    </div>
                    <div class="card-body">
                         <c:if test="${not empty message}"><div class="alert alert-success">${message}</div></c:if>
                        <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

                        <form action="edit-profile" method="post">
                            <div class="form-group">
                                <label>Tên đăng nhập</label>
                                <input type="text" value="${sessionScope.user.id}" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Họ và tên</label>
                                <input type="text" name="fullname" value="${sessionScope.user.fullname}" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Địa chỉ Email</label>
                                <input type="email" name="email" value="${sessionScope.user.email}" class="form-control" required>
                            </div>
                            <button type="submit" class="btn btn-info btn-block">Cập nhật</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>