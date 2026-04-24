<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đổi Mật Khẩu</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="/views/common/menu.jsp"></jsp:include>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-header bg-warning text-white">
                        <h4>Đổi Mật Khẩu</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty message}"><div class="alert alert-success">${message}</div></c:if>
                        <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

                        <form action="change-password" method="post">
                            <div class="form-group">
                                <label>Mật khẩu hiện tại</label>
                                <input type="password" name="currentPassword" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Mật khẩu mới</label>
                                <input type="password" name="newPassword" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Xác nhận mật khẩu mới</label>
                                <input type="password" name="confirmPassword" class="form-control" required>
                            </div>
                            <button type="submit" class="btn btn-warning btn-block text-white">Lưu thay đổi</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>