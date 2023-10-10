package com.nighthawk.spring_portfolio.mvc.skatepark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.nighthawk.spring_portfolio.mvc.skatepark.Skatepark;

@RestController
@RequestMapping("/api/skatepark")
public class SkateparkController {
    @Autowired
    private SkateparkRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Skatepark> > getSkateparks() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Skatepark> createSkatepark(@RequestBody Skatepark skatepark) {
        Skatepark savedSkatepark = repository.save(skatepark);
        return new ResponseEntity<>(savedSkatepark, HttpStatus.CREATED);
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Skatepark> updateLikes(@PathVariable long id, @RequestParam int likeValue) {
        Optional<Skatepark> optional = repository.findById(id);
        if (optional.isPresent()) {
            Skatepark skatepark = optional.get();
            int currentLikes = skatepark.getTotalLikes();
            skatepark.setTotalLikes(currentLikes + likeValue);
            repository.save(skatepark);
            return new ResponseEntity<>(skatepark, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteSkatepark(@PathVariable String name) {
        Optional<Skatepark> optional = repository.findBySkateparkName(name);
        if (optional.isPresent()) {
            Skatepark skatepark = optional.get();
            repository.delete(skatepark);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

