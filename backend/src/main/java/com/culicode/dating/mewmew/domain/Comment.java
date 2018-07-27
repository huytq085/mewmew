package com.culicode.dating.mewmew.domain;

public class Comment {
    private int articleId;
    private User author;
    private String content;
    private int numOfLike;

  public Comment() {
  }

  public Comment(int articleId, String content, int numOfLike) {
        this.articleId = articleId;
        this.content = content;
        this.numOfLike = numOfLike;
    }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
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
