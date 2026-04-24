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

@Entity
@Table(name="Share")
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @Column(name="Emails")
    private String emails; // Email người nhận

    @Column(name="ShareDate")
    @Temporal(TemporalType.TIMESTAMP) // Sửa DATE thành TIMESTAMP
    private Date shareDate = new Date();

    // Khóa ngoại tới User (Người gửi)
    @ManyToOne
    @JoinColumn(name="UserId")
    private User user;

    // Khóa ngoại tới Video (Video được chia sẻ)
    @ManyToOne
    @JoinColumn(name="VideoId")
    private Video video;

    public Share() {}

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmails() { return emails; }
    public void setEmails(String emails) { this.emails = emails; }
    public Date getShareDate() { return shareDate; }
    public void setShareDate(Date shareDate) { this.shareDate = shareDate; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Video getVideo() { return video; }
    public void setVideo(Video video) { this.video = video; }
}