<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
    /* Màu nền xám nhẹ giúp mắt dễ chịu hơn màu trắng tinh */
    body {
        background-color: #f4f6f9;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        /* Nếu dùng sticky-top ở menu thì không cần padding-top body nữa */
    }

    /* CSS cho các Card Video (Hiệu ứng đổ bóng và nổi lên) */
    .card {
        border: none; /* Bỏ viền xấu xí mặc định */
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05); /* Đổ bóng nhẹ */
        transition: transform 0.3s ease, box-shadow 0.3s ease; /* Hiệu ứng mượt */
    }

    /* Khi di chuột vào card video */
    .card:hover {
        transform: translateY(-5px); /* Nổi lên 5px */
        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15); /* Bóng đậm hơn */
    }

    /* Chỉnh ảnh Poster video để luôn đẹp, không bị méo */
    .video-poster {
        height: 200px;
        width: 100%;
        object-fit: cover; /* Cắt ảnh vừa khung, không co kéo */
        border-top-left-radius: 0.25rem;
        border-top-right-radius: 0.25rem;
    }

    /* CSS cho nút Like/Share */
    .btn-sm {
        border-radius: 20px; /* Bo tròn nút bấm */
    }

    /* CSS cho Footer */
    footer {
        background: #343a40;
        color: #adb5bd;
        padding: 30px 0;
        margin-top: 50px;
        font-size: 0.9rem;
    }
</style>