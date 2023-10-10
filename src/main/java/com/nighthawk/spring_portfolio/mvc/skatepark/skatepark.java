package com.nighthawk.spring_portfolio.mvc.skatepark;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.nighthawk.spring_portfolio.mvc.skatepark.Skatepark;

@Data  // Annotations to simplify writing code (ie constructors, setters)
@NoArgsConstructor
@AllArgsConstructor
@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
public class Skatepark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = false)
    private String skateparkName;
    private String author;
    private String title;
    private String address;
    private double starRating; // Change to an appropriate data type
    private String description;
    private Long totalLikes;    // Change to an appropriate data type

    public Skatepark(String skateparkName, String author, String title, String address, double starRating, String description) {
        this.skateparkName = skateparkName;
        this.author = author;
        this.title = title;
        this.address = address;
        this.starRating = starRating;
        this.description = description;
        this.totalLikes = 0L; // Initialize likes to 0
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return skateparkName;
    }

    public void setName(String skateparkName) {
        this.skateparkName = skateparkName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }
}
