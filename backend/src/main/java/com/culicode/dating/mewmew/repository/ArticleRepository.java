package com.culicode.dating.mewmew.repository;

import com.culicode.dating.mewmew.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    @Query("from Article a where a.userId = :userId")
    List<Article> findAllByUserId(@Param("userId") int userId);
}
