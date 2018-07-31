package com.culicode.dating.mewmew.repository;

import com.culicode.dating.mewmew.domain.Article;
import com.culicode.dating.mewmew.domain.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    String favoritedQuery = "select * from article a where a.id in ( select l.article_id from likes l where l.user_id=(select u.id from user u where u.username = :username))";
    @Query("from Article a where a.author.id = :userId order by a.dateAdded desc")
    List<Article> findAllByUserId(@Param("userId") int userId);

    @Query("from Article a where a.author.id in (select f.followerId from FriendShip f where f.followingId = :userId) order by a.dateAdded desc")
    List<Article> feed(@Param("userId") int userId, Pageable pageable);

    List<Article> findAllByOrderByDateAddedDesc(Pageable pageable);

    @Query(value = favoritedQuery, nativeQuery = true)
    List<Article> getFavorited(@Param("username") String username, Pageable pageable);

    @Query(value = "select count(*) from likes l where l.article_id = :articleId", nativeQuery = true)
    int getLikesCount(@Param("articleId") int articleId);

    @Procedure
    int doLike(int user, int article);

    @Procedure
    int isLike(int user, int article);

    @Procedure
    int unLike(int user, int article);

    @Procedure
    int comment(int user, int article, String content);

    @Procedure
    int removeComment(int user, int article);

}
