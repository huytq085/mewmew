package com.culicode.dating.mewmew.service;

import com.culicode.dating.mewmew.domain.Article;
import com.culicode.dating.mewmew.domain.Comment;
import com.culicode.dating.mewmew.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    Article save(Article article);

    Article update(Article article);

    void delete(int id);

    List<Article> getAllArticlesByUserId(int userId);

    List<Article> getAllArticlesByUsername(String username);

    Article findById(int id);

    int like(int user, int article);

    boolean isLike(int user, int article);

    int unLike(int user, int article);

    int comment(Comment comment);

    int removeComment(Comment comment);

    List<Comment> getComments(int articleId);

    List<Article> globalFeed(int limit);

    List<Article> feed(int userId, int limit);

    List<Article> getFavorited(String username, int limit);

    int getFavoritesCount(int articleId);
}
