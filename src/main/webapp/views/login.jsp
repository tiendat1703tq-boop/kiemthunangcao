
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/views/common/head.jsp" %>
    <title>Đăng nhập</title>
</head>
<body>
    <%@include file="/views/common/menu.jsp" %>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header bg-primary text-white">ĐĂNG NHẬP</div>
                    <div class="card-body">
                        <h5 class="text-danger">${message}</h5> <form action="login" method="post">
                            <div class="form-group">
                                <label>Username:</label>
                                <input type="text" class="form-control" name="username" required>
                            </div>
                            <div class="form-group">
                                <label>Password:</label>
                                <input type="password" class="form-control" name="password" required>
                            </div>
                            <div class="form-check mb-3">
                                <label class="form-check-label">
                                    <input class="form-check-input" type="checkbox" name="remember"> Ghi nhớ tài khoản?
                                </label>
                            </div>
                            <button type="submit" class="btn btn-primary">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>