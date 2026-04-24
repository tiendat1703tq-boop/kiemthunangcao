USE master;
GO
CREATE DATABASE PolyOE1;
GO
USE PolyOE1;
GO
-- Bảng User
CREATE TABLE [User] (
    Id NVARCHAR(50) PRIMARY KEY,
    Password NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL UNIQUE, -- Chú ý: Email là duy nhất
    Fullname NVARCHAR(100) NOT NULL,
    Admin BIT DEFAULT 0
);

-- Bảng Video
CREATE TABLE Video (
    Id NVARCHAR(50) PRIMARY KEY,
    Title NVARCHAR(255) NOT NULL,
    Poster NVARCHAR(255),
    [Views] INT DEFAULT 0,
    [Description] NVARCHAR(MAX),
    Active BIT DEFAULT 1
);

-- Bảng Favorite
CREATE TABLE Favorite (
    Id BIGINT PRIMARY KEY IDENTITY(1,1),
    UserId NVARCHAR(50) REFERENCES [User](Id),
    VideoId NVARCHAR(50) REFERENCES Video(Id),
    LikeDate DATE NOT NULL,
    -- Chú ý: Cặp (UserId, VideoId) là duy nhất
    UNIQUE (UserId, VideoId)
);

-- Bảng Share
CREATE TABLE Share (
    Id BIGINT PRIMARY KEY IDENTITY(1,1),
    UserId NVARCHAR(50) REFERENCES [User](Id),
    VideoId NVARCHAR(50) REFERENCES Video(Id),
    Emails NVARCHAR(255) NOT NULL,
    ShareDate DATE NOT NULL
);

INSERT INTO [User] (Id, Password, Email, Fullname, Admin)
VALUES
('NVTeo', '12345', 'teo@gmail.com', N'Nguyễn Văn Tèo', 0),
('LTPhuc', '67890', 'phuc@fpt.edu.vn', N'Lê Tấn Phúc', 1),
('TTHoa', 'abcde', 'hoa@gmail.com', N'Trần Thị Hoa', 0);

INSERT INTO Video (Id, Title, Poster, [Views], [Description], Active)
VALUES
('KV3', N'Kén vợ tập 3', 'ken-vo-3.jpg', 15000, N'Một bộ phim hài hước về hành trình tìm vợ.', 1),
('JAVA4', N'Lập trình Java 4', 'java-4.png', 2500, N'Hướng dẫn Lab 3: JPA và Hibernate.', 1),
('MUSIC1', N'Top Hits 2025', 'music-2025.jpg', 100000, N'Tuyển tập các bài hát hay nhất.', 0);

INSERT INTO Favorite (UserId, VideoId, LikeDate)
VALUES
('NVTeo', 'KV3', '2024-01-12'), -- Dữ liệu mẫu trong Bài 4
('NVTeo', 'JAVA4', '2025-02-20'), -- Tèo cũng thích video Java 4
('TTHoa', 'KV3', '2025-03-15');  -- Hoa cũng thích video Kén vợ

INSERT INTO Share (UserId, VideoId, Emails, ShareDate)
VALUES
('NVTeo', 'JAVA4', 'banbe@gmail.com,dongnghiep@fpt.edu.vn', '2025-02-21'),
('LTPhuc', 'KV3', 'admin@poly.edu.vn', '2025-01-30'),
('TTHoa', 'MUSIC1', 'hoatran-friend@yahoo.com', '2025-04-01');