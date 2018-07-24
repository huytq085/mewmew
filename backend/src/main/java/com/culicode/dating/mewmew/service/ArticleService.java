package com.culicode.dating.mewmew.service;

import com.culicode.dating.mewmew.domain.Article;
import com.culicode.dating.mewmew.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
  Article save(Article article);

  Article update(Article article);

  List<Article> getAllArticlesByUserId(int userId);

  List<Article> getAllArticlesByUsername(String username);

  Article findById(int id);


}
