package com.culicode.dating.mewmew.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "articleId")
    private int articleId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User author;
    @Column(name = "content")
    private String content;
    @Column(name = "num_of_like", nullable = false)
    private int numOfLike;
    @Column(name = "date_added", nullable = false)
    private Date dateAdded;
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

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
