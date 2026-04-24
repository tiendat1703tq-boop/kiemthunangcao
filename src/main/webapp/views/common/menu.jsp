<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top shadow-sm">
    <div class="container">
        <a class="navbar-brand font-weight-bold" href="${pageContext.request.contextPath}/home">
            <i class="fas fa-play-circle text-warning"></i> ONLINE ENTERTAINMENT
        </a>
        
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav ml-auto align-items-center">
                
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/home">
                        <i class="fas fa-home"></i> Trang chủ
                    </a>
                </li>
                
                <c:choose>
                    <%-- TRƯỜNG HỢP: ĐÃ ĐĂNG NHẬP --%>
                    <c:when test="${not empty sessionScope.user}">
                        
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/favorite">
                                <i class="fas fa-heart text-danger"></i> Yêu thích
                            </a>
                        </li>

                        <c:if test="${sessionScope.user.admin}">
                            <li class="nav-item">
                                <span class="text-secondary mx-2">|</span> </li>
                            
                            <li class="nav-item">
                                <a class="nav-link text-warning font-weight-bold" href="${pageContext.request.contextPath}/admin/videos">
                                    <i class="fab fa-youtube"></i> QL Video
                                </a>
                            </li>
                            
                            <li class="nav-item">
                                <a class="nav-link text-info font-weight-bold" href="${pageContext.request.contextPath}/admin/users">
                                    <i class="fas fa-users-cog"></i> QL User
                                </a>
                            </li>
                            
                            <li class="nav-item">
                                <span class="text-secondary mx-2">|</span>
                            </li>
                        </c:if>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                                <i class="fas fa-user-circle"></i> Xin chào, ${sessionScope.user.fullname}
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/account/change-password">
                                    <i class="fas fa-key"></i> Đổi mật khẩu
                                </a>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/account/edit-profile">
                                    <i class="fas fa-id-card"></i> Cập nhật hồ sơ
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/logout">
                                    <i class="fas fa-sign-out-alt"></i> Đăng xuất
                                </a>
                            </div>
                        </li>
                    </c:when>
                    
                    <%-- TRƯỜNG HỢP: CHƯA ĐĂNG NHẬP --%>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">
                                <i class="fas fa-sign-in-alt"></i> Đăng nhập
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/register">
                                <i class="fas fa-user-plus"></i> Đăng ký
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>