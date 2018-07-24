package com.culicode.dating.mewmew.service;

import com.culicode.dating.mewmew.domain.Article;
import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
  @Autowired
  ArticleRepository articleRepository;

  @Override
  public Article save(Article article) {
    return this.articleRepository.save(article);
  }

  @Override
  public Article update(Article article) {
    return this.articleRepository.save(article);
  }

  @Override
  public List<Article> getAllArticlesByUserId(int userId) {
    return this.articleRepository.findAllByUserId(userId);
  }

  @Override
  public List<Article> getAllArticlesByUsername(String username) {
    return null;
  }

  @Override
  public Article findById(int id) {
    return this.articleRepository.findById(id).orElse(null);
  }
}
