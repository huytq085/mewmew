package com.culicode.dating.mewmew.domain;

public class Comment {
    private int articleId;
    private int userId;
    private String content;
    private int numOfLike;

    public Comment(int articleId, int userId, String content, int numOfLike) {
        this.articleId = articleId;
        this.userId = userId;
        this.content = content;
        this.numOfLike = numOfLike;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumOfLike() {
        return numOfLike;
    }

    public void setNumOfLike(int numOfLike) {
        this.numOfLike = numOfLike;
    }
}
