package com.culicode.dating.mewmew.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "friendship")
public class FriendShip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "following_id")
    private int followingId;
    @Column(name = "follower_id")
    private int followerId;

    @Column(name = "date_added")
    private Date dateAdded;

    public FriendShip() {
    }

    public FriendShip(int followingId, int followerId, Date dateAdded) {
        this.followingId = followingId;
        this.followerId = followerId;
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
