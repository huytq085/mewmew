package com.culicode.dating.mewmew.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "recipient_id", nullable = false)
    private int recipientId;
    @Column(name = "date_added", insertable=false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    private String content;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @Column(name = "is_read")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isRead;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
