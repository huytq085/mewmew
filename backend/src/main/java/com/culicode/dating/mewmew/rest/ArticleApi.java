package com.culicode.dating.mewmew.rest;


import com.culicode.dating.mewmew.domain.Article;
import com.culicode.dating.mewmew.domain.User;
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
    private final String LIKE_URI = BASE_URI + "/{articleId}/like";
    private final String UNLIKE_URI = BASE_URI + "/{articleId}/unlike";
    private final String COMMENT_URI = BASE_URI + "/{articleId}/comment";
    private final String REMOVE_COMMENT_URI = BASE_URI + "/{articleId}/removecomment";
    private final String IS_FAVORITE_URI = BASE_URI + "/{articleId}/isfavorite/{userId}";

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

    @RequestMapping(
            value = ARTICLE_ID_URI,
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    void delete(@PathVariable int articleId){
        articleService.delete(articleId);
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

    @RequestMapping(
            value = LIKE_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    int like(@PathVariable int articleId, @RequestBody User user){
        LOG.info(user.getUsername() + " want to like article " + articleId);
        return articleService.like(user.getId(), articleId);
    }

    @RequestMapping(
            value = UNLIKE_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    int unLike(@PathVariable int articleId, @RequestBody User user){
        LOG.info(user.getUsername() + " want to unlike article " + articleId);
        return articleService.unLike(user.getId(), articleId);
    }

    @RequestMapping(
            value = IS_FAVORITE_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isLike(@PathVariable int articleId, @PathVariable int userId){
        return this.articleService.isLike(userId, articleId);
    }
}
