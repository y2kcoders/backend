package com.nighthawk.spring_portfolio.mvc.skatepark;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class Skatepark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = false)
    private String skateparkName;
    private String author;
    private String title;
    private String address;
    private double starRating;
    private String description;
    private int totalLikes;

    public Skatepark() {
        // Default constructor
    }

    public Skatepark(String skateparkName, String author, String title, String address, double starRating, String description, int totalLikes) {
        this.skateparkName = skateparkName;
        this.author = author;
        this.title = title;
        this.address = address;
        this.starRating = starRating;
        this.description = description;
        this.totalLikes = totalLikes;
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

    public double getStarRating() {
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

    public static List<Skatepark> createInitialData() {
        List<Skatepark> initialData = new ArrayList<>();

        initialData.add(new Skatepark("Del Sur Skatepark", "Finn Carpenter", "Del Sur midpark", "15820 Paseo Montenero", 3.5, "I understand with compact space gives little room for opportunity. Although look at parks like Solona Beach skatepark, it's smaller than Del sur and accomplishes more in flows better.", 100));
        // Add more skatepark instances as needed

        return initialData;
    }

    public static List<Skatepark> init() {
        return createInitialData();
    }

}