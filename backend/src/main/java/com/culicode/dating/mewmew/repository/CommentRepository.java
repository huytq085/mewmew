package com.culicode.dating.mewmew.repository;

import com.culicode.dating.mewmew.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> getAllByArticleIdOrderByDateAddedDesc(int articleId);
}
