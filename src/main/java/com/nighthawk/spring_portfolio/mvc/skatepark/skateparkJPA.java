import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nighthawk.spring_portfolio.mvc.skatepark.Skatepark;

public interface SkateparkRepository extends JpaRepository<Skatepark, Long> {
    // You can add custom query methods here if needed.
    
    // Example of a custom query method to find skateparks by name
    List<Skatepark> findBySkateparkName(String name);
    
    // Example of a custom query method to find skateparks by star rating
    List<Skatepark> findByStarRatingGreaterThan(double rating);
}
