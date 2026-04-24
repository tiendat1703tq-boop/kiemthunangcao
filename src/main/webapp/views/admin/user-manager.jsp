<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Quản lý Tài khoản - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="bg-light">

    <%@include file="/views/common/menu.jsp" %>

    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <div class="card shadow">
                    <div class="card-header bg-success text-white">
                        <h4 class="mb-0"><i class="fas fa-users-cog"></i> QUẢN LÝ TÀI KHOẢN (USER)</h4>
                    </div>
                    <div class="card-body">
                        
                        <c:if test="${not empty message}">
                            <div class="alert alert-info">${message}</div>
                        </c:if>

                        <form action="" method="post">
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Tên đăng nhập (ID):</label>
                                    <input type="text" name="id" value="${user.id}" class="form-control" 
                                           placeholder="Nhập username..." ${not empty user.id ? 'readonly' : ''} required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Mật khẩu:</label>
                                    <input type="password" name="password" value="${user.password}" class="form-control" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Họ và tên:</label>
                                    <input type="text" name="fullname" value="${user.fullname}" class="form-control" required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Email:</label>
                                    <input type="email" name="email" value="${user.email}" class="form-control" required>
                                </div>
                            </div>

                            <div class="form-check mb-4">
                                <input class="form-check-input" type="checkbox" name="admin" value="true" id="adminCheck" ${user.admin ? 'checked' : ''}>
                                <label class="form-check-label text-danger fw-bold" for="adminCheck">
                                    Vai trò Quản trị viên (Admin)
                                </label>
                            </div>

                            <div class="d-flex gap-2">
                                <button formaction="${pageContext.request.contextPath}/admin/user/create" 
                                        class="btn btn-success" ${not empty user.id ? 'disabled' : ''}>
                                    <i class="fas fa-plus-circle"></i> Thêm mới
                                </button>
                                
                                <button formaction="${pageContext.request.contextPath}/admin/user/update" 
                                        class="btn btn-warning" ${empty user.id ? 'disabled' : ''}>
                                    <i class="fas fa-save"></i> Cập nhật
                                </button>
                                        
                                <a href="${pageContext.request.contextPath}/admin/user/delete?id=${user.id}" 
                                   class="btn btn-danger ${empty user.id ? 'disabled' : ''}" 
                                   onclick="return confirm('Bạn có chắc muốn xóa tài khoản này không?')">
                                   <i class="fas fa-trash"></i> Xóa
                                </a>
                                        
                                <a href="${pageContext.request.contextPath}/admin/users" class="btn btn-secondary">
                                    <i class="fas fa-redo"></i> Làm mới (Reset)
                                </a>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="card mt-4 shadow">
                    <div class="card-header bg-dark text-white">
                        <h5 class="mb-0">Danh sách Tài khoản</h5>
                    </div>
                    <div class="card-body p-0">
                        <table class="table table-striped table-hover mb-0">
                            <thead class="table-success">
                                <tr>
                                    <th>Username</th>
                                    <th>Họ và tên</th>
                                    <th>Email</th>
                                    <th>Vai trò</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${items}">
                                    <tr>
                                        <td>${item.id}</td>
                                        <td>${item.fullname}</td>
                                        <td>${item.email}</td>
                                        <td>
                                            <span class="badge ${item.admin ? 'bg-danger' : 'bg-primary'}">
                                                ${item.admin ? 'Admin' : 'User'}
                                            </span>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/admin/user/edit?id=${item.id}" class="btn btn-sm btn-primary">
                                                <i class="fas fa-edit"></i> Chọn sửa
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
</body>
</html>