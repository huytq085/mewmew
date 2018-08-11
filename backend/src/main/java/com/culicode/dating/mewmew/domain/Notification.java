package com.culicode.dating.mewmew.domain;

import java.util.Date;

public class Notification {
    private int id;
    private String username;
    private Date dadeAdded;
    private String type;
    private String content;
    private User sender;

    public Notification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDadeAdded() {
        return dadeAdded;
    }

    public void setDadeAdded(Date dadeAdded) {
        this.dadeAdded = dadeAdded;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
