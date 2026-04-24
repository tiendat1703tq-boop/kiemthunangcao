<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký tài khoản - Online Entertainment</title>
    <jsp:include page="/views/common/head.jsp"></jsp:include>
</head>
<body class="bg-light">
    <jsp:include page="/views/common/menu.jsp"></jsp:include>

    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-5">
                <div class="card shadow-lg border-0 rounded-lg">
                    <div class="card-header bg-success text-white text-center">
                        <h4 class="font-weight-bold my-2"><i class="fas fa-user-plus"></i> ĐĂNG KÝ THÀNH VIÊN</h4>
                    </div>
                    <div class="card-body p-4">
                        
                        <c:if test="${not empty message}">
                            <div class="alert alert-info alert-dismissible fade show" role="alert">
                                <i class="fas fa-info-circle"></i> ${message}
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/register" method="post">
                            
                            <div class="form-group">
                                <label class="small mb-1 font-weight-bold">Tên đăng nhập</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                                    </div>
                                    <input class="form-check-input" type="hidden" name="admin" value="false">
                                    <input class="form-control py-4" name="id" type="text" placeholder="Nhập tên đăng nhập..." required />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="small mb-1 font-weight-bold">Mật khẩu</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                                    </div>
                                    <input class="form-control py-4" name="password" type="password" placeholder="Nhập mật khẩu..." required />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="small mb-1 font-weight-bold">Họ và tên</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-id-card"></i></span>
                                    </div>
                                    <input class="form-control py-4" name="fullname" type="text" placeholder="Nhập họ tên đầy đủ..." required />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="small mb-1 font-weight-bold">Email</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                                    </div>
                                    <input class="form-control py-4" name="email" type="email" placeholder="example@gmail.com" required />
                                </div>
                            </div>

                            <div class="form-group mt-4 mb-0">
                                <button class="btn btn-success btn-block font-weight-bold" type="submit">
                                    <i class="fas fa-check-circle"></i> HOÀN TẤT ĐĂNG KÝ
                                </button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer text-center py-3">
                        <div class="small">
                            <a href="${pageContext.request.contextPath}/login">Bạn đã có tài khoản? Đăng nhập ngay</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <footer class="text-center text-muted py-4">
        &copy; 2025 FPT Polytechnic Nguyễn Quang Nhật
    </footer>
</body>
</html>