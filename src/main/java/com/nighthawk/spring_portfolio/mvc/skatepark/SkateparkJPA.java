package com.nighthawk.spring_portfolio.mvc.skatepark;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface SkateparkJPA extends JpaRepository<Skatepark, Long> {
    List<Skatepark> findBySkateparkName(String name);
    List<Skatepark> findByStarRatingGreaterThan(double rating);
}
