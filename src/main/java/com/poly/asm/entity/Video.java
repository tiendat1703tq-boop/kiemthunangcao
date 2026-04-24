package com.poly.asm.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Video")
public class Video {
    @Id
    @Column(name="Id")
    private String id; // Mã video (YouTube ID)

    @Column(name="Title")
    private String title;

    @Column(name="Poster")
    private String poster;

    @Column(name="Views")
    private Integer views = 0; // Mặc định view là 0

    @Column(name="Description")
    private String description;

    @Column(name="Active")
    private Boolean active = true; // Mặc định là đang hoạt động

    // Quan hệ 1-N với Favorite
    @OneToMany(mappedBy="video")
    List<Favorite> favorites;

    // Quan hệ 1-N với Share
    @OneToMany(mappedBy="video")
    List<Share> shares;

    public Video() {}

    // --- Getters and Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    public Integer getViews() { return views; }
    public void setViews(Integer views) { this.views = views; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public List<Favorite> getFavorites() { return favorites; }
    public void setFavorites(List<Favorite> favorites) { this.favorites = favorites; }
    public List<Share> getShares() { return shares; }
    public void setShares(List<Share> shares) { this.shares = shares; }
}