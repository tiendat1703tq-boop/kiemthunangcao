<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Quản lý Video - Admin</title>
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
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0"><i class="fab fa-youtube"></i> QUẢN LÝ VIDEO</h4>
                    </div>
                    <div class="card-body">
                        
                        <c:if test="${not empty message}">
                            <div class="alert alert-success">${message}</div>
                        </c:if>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>

                        <form action="" method="post">
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Youtube ID (Mã video):</label>
                                    <input type="text" name="id" value="${video.id}" class="form-control" 
                                           placeholder="Ví dụ: GjTq7ke4wT4" ${not empty video.id ? 'readonly' : ''} required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Tiêu đề:</label>
                                    <input type="text" name="title" value="${video.title}" class="form-control" required>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label class="form-label fw-bold">Poster (Link ảnh):</label>
                                <input type="text" name="poster" value="${video.poster}" class="form-control" placeholder="https://..." required>
                                <c:if test="${not empty video.poster}">
                                    <div class="mt-2">
                                        <img src="${video.poster}" alt="Preview" style="height: 100px; border-radius: 5px; border: 1px solid #ccc;">
                                    </div>
                                </c:if>
                            </div>

                            <div class="mb-3">
                                <label class="form-label fw-bold">Mô tả:</label>
                                <textarea name="description" class="form-control" rows="3">${video.description}</textarea>
                            </div>

                            <div class="form-check mb-4">
                                <input type="checkbox" name="active" value="true" class="form-check-input" id="activeCheck" ${video.active ? 'checked' : ''}>
                                <label class="form-check-label text-success fw-bold" for="activeCheck">Đang hoạt động</label>
                            </div>

                            <div class="d-flex gap-2">
                                <button formaction="${pageContext.request.contextPath}/admin/video/create" 
                                        class="btn btn-success" ${not empty video.id ? 'disabled' : ''}>
                                    <i class="fas fa-plus-circle"></i> Thêm mới
                                </button>
                                
                                <button formaction="${pageContext.request.contextPath}/admin/video/update" 
                                        class="btn btn-warning" ${empty video.id ? 'disabled' : ''}>
                                    <i class="fas fa-save"></i> Cập nhật
                                </button>
                                        
                                <a href="${pageContext.request.contextPath}/admin/video/delete?id=${video.id}" 
                                   class="btn btn-danger ${empty video.id ? 'disabled' : ''}" 
                                   onclick="return confirm('Bạn có chắc muốn xóa video này không?')">
                                   <i class="fas fa-trash"></i> Xóa
                                </a>
                                        
                                <a href="${pageContext.request.contextPath}/admin/videos" class="btn btn-secondary">
                                    <i class="fas fa-redo"></i> Làm mới (Reset)
                                </a>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="card mt-4 shadow">
                    <div class="card-header bg-dark text-white">
                        <h5 class="mb-0">Danh sách Video</h5>
                    </div>
                    <div class="card-body p-0">
                        <table class="table table-striped table-hover mb-0">
                            <thead class="table-primary">
                                <tr>
                                    <th>ID</th>
                                    <th>Tiêu đề</th>
                                    <th>Lượt xem</th>
                                    <th>Trạng thái</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${items}">
                                    <tr>
                                        <td>${item.id}</td>
                                        <td>${item.title}</td>
                                        <td>${item.views}</td>
                                        <td>
                                            <span class="badge ${item.active ? 'bg-success' : 'bg-secondary'}">
                                                ${item.active ? 'Hoạt động' : 'Ẩn'}
                                            </span>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/admin/video/edit?id=${item.id}" class="btn btn-sm btn-primary">
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