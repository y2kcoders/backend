package com.nighthawk.spring_portfolio.mvc.chatboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
     List<Comment>findCommentsByPost(Post post);
}