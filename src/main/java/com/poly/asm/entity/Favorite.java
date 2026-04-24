package com.poly.asm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Favorites", uniqueConstraints={
    @UniqueConstraint(columnNames={"UserId", "VideoId"}) // Một người chỉ like 1 video 1 lần
})
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    // Khóa ngoại trỏ về User
    @ManyToOne
    @JoinColumn(name="UserId")
    private User user;

    // Khóa ngoại trỏ về Video
    @ManyToOne
    @JoinColumn(name="VideoId")
    private Video video;

    @Column(name="LikeDate")
    @Temporal(TemporalType.TIMESTAMP) // Sửa DATE thành TIMESTAMP
    private Date likeDate = new Date();

    // --- Constructors ---

    public Favorite() {
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }
}