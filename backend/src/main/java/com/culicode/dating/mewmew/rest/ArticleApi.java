package com.culicode.dating.mewmew.rest;


import com.culicode.dating.mewmew.domain.Article;
import com.culicode.dating.mewmew.service.ArticleService;
import com.culicode.dating.mewmew.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({"/api"})
@RestController
public class ArticleApi {
    private static final Logger LOG = LogManager.getLogger(Article.class);
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
        LOG.info("Create new Article");
        LOG.info(JsonUtil.encode(article));
        return articleService.save(article);
    }

    @RequestMapping(
            value = ARTICLE_ID_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Article get(@PathVariable int articleId) {
        LOG.info("Get article");
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
    public List<Article> getByUsername(@RequestParam("author") String username) {
        LOG.info("Get list by username");
        System.out.println(username);
        return articleService.getAllArticlesByUsername(username);
    }
}
