package com.culicode.dating.mewmew.rest;


import com.culicode.dating.mewmew.domain.Article;
import com.culicode.dating.mewmew.service.ArticleService;
import com.culicode.dating.mewmew.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({"/api"})
@RestController
public class ArticleApi {
  @Autowired
  ArticleService articleService;

  private final String BASE_URI = "/articles";
  private final String ARTICLE_ID_URI = BASE_URI + "/{articleId}";
  private final String USER_ID_URI = BASE_URI + "/user/{userId}";

  @RequestMapping(
    value = BASE_URI,
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Article create(@RequestBody Article article) {
    System.out.println(JsonUtil.encode(article));
    return articleService.save(article);
  }

  @RequestMapping(
    value = ARTICLE_ID_URI,
    method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
  public Article get(@PathVariable int articleId) {
    System.out.println("Article Id: " + articleId);
    return articleService.findById(articleId);
  }



//  @RequestMapping(
//    value = USER_ID_URI,
//    method = RequestMethod.GET,
//    produces = MediaType.APPLICATION_JSON_VALUE)
//  public List<Article> getAll(@PathVariable int userId) {
//    System.out.println("User Id: " + userId);
//    return articleService.getAllArticlesByUserId(userId);
//  }

  @RequestMapping(
          value = BASE_URI,
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public List<Article> getByUserId(@RequestParam("author") int userId) {
    System.out.println(userId);
    return articleService.getAllArticlesByUserId(userId);
  }
}
