<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="/views/common/head.jsp" %>
    <title>${video.title} | Online Entertainment</title>
    
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f9f9f9; /* Màu nền xám rất nhạt cho dịu mắt */
        }

        /* --- PHẦN VIDEO CHÍNH --- */
        .video-container {
            background: #fff;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.05); /* Đổ bóng nhẹ */
        }

        .video-frame {
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }

        .video-title {
            font-weight: 700;
            color: #222;
            margin-top: 15px;
            font-size: 1.6rem;
        }

        .video-desc {
            color: #555;
            background-color: #f8f9fa;
            padding: 15px;
            border-radius: 8px;
            border-left: 4px solid #007bff; /* Điểm nhấn bên trái mô tả */
            line-height: 1.6;
        }

        /* Nút bấm */
        .btn-action {
            border-radius: 50px; /* Nút hình viên thuốc */
            padding: 8px 25px;
            font-weight: 500;
            transition: all 0.3s;
        }
        
        .btn-action:hover {
            transform: translateY(-2px); /* Nhấc nhẹ nút khi hover */
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        /* --- PHẦN SIDEBAR (CÓ THỂ BẠN THÍCH) --- */
        .sidebar-title {
            font-size: 1.1rem;
            font-weight: 700;
            margin-bottom: 20px;
            border-bottom: 2px solid #ddd;
            padding-bottom: 10px;
            position: relative;
        }
        
        .sidebar-title::after {
            content: '';
            position: absolute;
            bottom: -2px;
            left: 0;
            width: 60px;
            height: 2px;
            background-color: #007bff; /* Gạch chân màu xanh */
        }

        .video-card-small {
            display: flex;
            gap: 15px;
            margin-bottom: 20px;
            text-decoration: none;
            color: inherit;
            transition: all 0.2s;
            background: #fff;
            padding: 10px;
            border-radius: 8px;
        }

        .video-card-small:hover {
            background-color: #e9ecef;
            text-decoration: none;
            transform: translateX(5px); /* Hiệu ứng đẩy sang phải */
        }

        .thumb-small {
            width: 120px;
            height: 68px; /* Tỷ lệ 16:9 */
            object-fit: cover;
            border-radius: 6px;
            flex-shrink: 0; /* Không bị co ảnh */
        }

        .meta-small h6 {
            font-size: 0.95rem;
            font-weight: 600;
            margin: 0;
            color: #333;
            line-height: 1.4;
            
            /* Cắt chữ nếu quá dài (tối đa 2 dòng) */
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            overflow: hidden;
        }
    </style>
</head>
<body>
    <%@include file="/views/common/menu.jsp" %>
    
    <div class="container mt-4 mb-5">
        <div class="row">
            <div class="col-lg-8 mb-4">
                <div class="video-container">
                    <div class="embed-responsive embed-responsive-16by9 video-frame mb-3">
                        <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${video.id}" allowfullscreen></iframe>
                    </div>
                    
                    <h1 class="video-title">${video.title}</h1>
                    
                    <div class="d-flex align-items-center mb-4 mt-3">
                        <a href="${pageContext.request.contextPath}/like?id=${video.id}" class="btn btn-outline-danger btn-action btn-lg mr-3">
                            <i class="fas fa-heart"></i> Yêu thích
                        </a>
                        <button type="button" class="btn btn-outline-primary btn-action btn-lg" data-toggle="modal" data-target="#shareModal">
                            <i class="fas fa-share"></i> Chia sẻ
                        </button>
                        <span class="ml-auto text-muted"><i class="fas fa-eye"></i> ${video.views} lượt xem</span>
                    </div>

                    <hr>
                    
                    <div class="video-desc">
                        <h5>Mô tả video:</h5>
                        <p class="mb-0">${video.description}</p>
                    </div>
                </div>
            </div>
            
            <div class="col-lg-4">
                <div class="bg-white p-3 rounded shadow-sm">
                    <h5 class="sidebar-title">CÓ THỂ BẠN THÍCH</h5>
                    
                    <c:forEach var="item" items="${randomVideos}" begin="0" end="4">
                        <a href="detail?id=${item.id}" class="video-card-small">
                            <img src="${item.poster}" class="thumb-small shadow-sm" alt="${item.title}">
                            <div class="meta-small">
                                <h6>${item.title}</h6>
                                <small class="text-muted">Xem ngay <i class="fas fa-play-circle"></i></small>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="shareModal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content border-0 shadow-lg">
                <div class="modal-header bg-primary text-white">
                    <h5 class="modal-title font-weight-bold"><i class="fas fa-paper-plane"></i> Chia sẻ với bạn bè</h5>
                    <button type="button" class="close text-white" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body p-4">
                    <form id="shareForm">
                        <input type="hidden" name="videoId" value="${video.id}">
                        <div class="form-group">
                            <label class="font-weight-bold">Nhập Email người nhận:</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                                </div>
                                <input type="email" name="email" class="form-control" placeholder="vidu: banbe@gmail.com" required>
                            </div>
                            <small class="form-text text-muted">Chúng tôi sẽ gửi liên kết video này qua email cho bạn của bạn.</small>
                        </div>
                    </form>
                </div>
                <div class="modal-footer bg-light">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                    <button type="button" onclick="sendShare()" class="btn btn-primary px-4">Gửi đi</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        function sendShare() {
            // Thêm hiệu ứng loading đơn giản (Optional)
            let btn = event.target;
            let originalText = btn.innerText;
            btn.innerText = 'Đang gửi...';
            btn.disabled = true;

            $.post('${pageContext.request.contextPath}/share', $('#shareForm').serialize(), function(data) {
                btn.innerText = originalText;
                btn.disabled = false;
                
                if(data === 'Success') {
                    alert('🎉 Đã gửi email thành công!');
                    $('#shareModal').modal('hide');
                    // Reset form
                    $('#shareForm')[0].reset();
                } else {
                    alert('⚠️ Lỗi: Có thể bạn chưa đăng nhập hoặc Email không hợp lệ.');
                }
            }).fail(function() {
                alert('Lỗi kết nối server!');
                btn.innerText = originalText;
                btn.disabled = false;
            });
        }
    </script>
</body>
</html>