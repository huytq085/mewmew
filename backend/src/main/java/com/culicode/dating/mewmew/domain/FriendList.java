package com.culicode.dating.mewmew.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "friendlist")
public class FriendList implements Serializable {
    @Id
    @Column(name = "sender_id", nullable = false)
    private int senderId;
    @Id
    @Column(name = "recipient_id", nullable = false)
    private int recipientId;
    @Column(name = "status", insertable = false)
    private int status;
    @Column(name = "date_added", insertable = false)
    private Date dateAdded;

    public FriendList() {
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
