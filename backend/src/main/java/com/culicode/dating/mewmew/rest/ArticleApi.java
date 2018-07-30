package com.culicode.dating.mewmew.rest;


import com.culicode.dating.mewmew.domain.Article;
import com.culicode.dating.mewmew.domain.Comment;
import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.service.ArticleService;
import com.culicode.dating.mewmew.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    private final String FEED_URI = BASE_URI + "/feed/{userId}";
    private final String LIKE_URI = BASE_URI + "/{articleId}/like";
    private final String UNLIKE_URI = BASE_URI + "/{articleId}/unlike";
    private final String COMMENT_URI = BASE_URI + "/comments";
    private final String GET_COMMENTS_URI = BASE_URI + "/{articleId}/comments";
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
    void delete(@PathVariable int articleId) {
        articleService.delete(articleId);
    }


    @RequestMapping(
            value = BASE_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Article> getArticles(@RequestParam(name = "author", required = false) String username, @RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        LOG.info(username);
        LOG.info(limit);
        if (username != null) {
            return articleService.getAllArticlesByUsername(username);
        }
        return articleService.globalFeed(limit);
    }

    @RequestMapping(
            value = LIKE_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    int like(@PathVariable int articleId, @RequestBody User user) {
        LOG.info(user.getUsername() + " want to like article " + articleId);
        return articleService.like(user.getId(), articleId);
    }

    @RequestMapping(
            value = UNLIKE_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    int unLike(@PathVariable int articleId, @RequestBody User user) {
        LOG.info(user.getUsername() + " want to unlike article " + articleId);
        return articleService.unLike(user.getId(), articleId);
    }

    @RequestMapping(
            value = IS_FAVORITE_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isLike(@PathVariable int articleId, @PathVariable int userId) {
        return this.articleService.isLike(userId, articleId);
    }

    @RequestMapping(
            value = COMMENT_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    int comment(@RequestBody Comment comment) {
        LOG.info(JsonUtil.encode(comment));
        return articleService.comment(comment);
    }

    @RequestMapping(
            value = REMOVE_COMMENT_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    int removeComment(@RequestBody Comment comment) {
        return articleService.comment(comment);
    }

    @RequestMapping(
            value = GET_COMMENTS_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<Comment> getComments(@PathVariable int articleId) {
//        Arrays.deepToString(articleService.getComments(articleId))
        return articleService.getComments(articleId);
    }

    @RequestMapping(
            value = FEED_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<Article> feed(@PathVariable int userId, @Param("limit") int limit, @Param("offset") int offset) {
        LOG.info(userId);
        LOG.info(limit);
        LOG.info(offset);
        return articleService.feed(userId, limit);
    }
}
