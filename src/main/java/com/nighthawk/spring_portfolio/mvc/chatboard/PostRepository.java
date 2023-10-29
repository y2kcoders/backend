package com.nighthawk.spring_portfolio.mvc.chatboard;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
