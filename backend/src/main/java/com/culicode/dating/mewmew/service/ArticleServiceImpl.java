package com.culicode.dating.mewmew.service;

import com.culicode.dating.mewmew.domain.Article;
import com.culicode.dating.mewmew.domain.Comment;
import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.repository.ArticleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
  private static final Logger LOG = LogManager.getLogger(Article.class);
  @Autowired
  ArticleRepository articleRepository;

  @Autowired
  UserService userService;

  @Override
  public Article save(Article article) {
    return this.articleRepository.save(article);
  }

  @Override
  public Article update(Article article) {
    return this.articleRepository.save(article);
  }

  @Override
  public void delete(int id) {
    articleRepository.deleteById(id);
  }

  @Override
  public List<Article> getAllArticlesByUserId(int userId) {
    return this.articleRepository.findAllByUserId(userId);
  }

  @Override
  public List<Article> getAllArticlesByUsername(String username) {
    User user = userService.findByUsername(username);
    if (user == null) {
      return Collections.emptyList();
    }
    LOG.info(user.getUsername());
    return this.articleRepository.findAllByUserId(user.getId());
  }

  @Override
  public Article findById(int id) {
    return this.articleRepository.findById(id).orElse(null);
  }

  @Override
  public int like(int user, int article) {
    return articleRepository.doLike(user, article);
  }

  @Override
  public boolean isLike(int user, int article) {
    return 1 == articleRepository.isLike(user, article);
  }

  @Override
  public int unLike(int user, int article) {
    return articleRepository.unLike(user, article);
  }



  @Override
  public int comment(Comment comment) {
    return articleRepository.comment(comment.getAuthor().getId(), comment.getArticleId(), comment.getContent());
  }

  @Override
  public int removeComment(Comment comment) {
    return articleRepository.removeComment(comment.getAuthor().getId(), comment.getArticleId());
  }


}
