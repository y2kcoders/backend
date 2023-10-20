package com.nighthawk.spring_portfolio.mvc.skatepark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skatepark")
public class SkateparkController {
    @Autowired
    private SkateparkJPA repository;

    @GetMapping("/")
    public ResponseEntity<List<Skatepark>> getSkateparks() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Skatepark> createSkatepark(@RequestBody Skatepark skatepark) {
        Skatepark savedSkatepark = repository.save(skatepark);
        return new ResponseEntity<>(savedSkatepark, HttpStatus.CREATED);
    }

    @PutMapping("/like/{name}")
    public ResponseEntity<Skatepark> updateLikes(@PathVariable String name, @RequestBody int likeValue) {
        List<Skatepark> skateparks = repository.findBySkateparkName(name);
        if (!skateparks.isEmpty()) {
            Skatepark skatepark = skateparks.get(0); // Assuming you want to work with the first matching skatepark
            int currentLikes = skatepark.getTotalLikes();
            skatepark.setTotalLikes(currentLikes + likeValue);
            // You can also update the author who liked the skatepark
            repository.save(skatepark);
            return new ResponseEntity<>(skatepark, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteSkatepark(@PathVariable String name) {
        List<Skatepark> skateparks = repository.findBySkateparkName(name);
        if (!skateparks.isEmpty()) {
            repository.delete(skateparks.get(0)); // Assuming you want to delete the first matching skatepark
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}