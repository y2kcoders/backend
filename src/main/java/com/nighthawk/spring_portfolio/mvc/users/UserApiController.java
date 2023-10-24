package com.nighthawk.spring_portfolio.mvc.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserJpaRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<User>> getSkateparks() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedSkatepark = repository.save(user);
        return new ResponseEntity<>(savedSkatepark, HttpStatus.CREATED);
    }

    // @PostMapping("/like/{name}")
    // public ResponseEntity<Skatepark> updateLikes(@PathVariable String name, @RequestBody int likeValue) {
    //     List<Skatepark> skateparks = repository.findBySkateparkName(name);
    //     if (!skateparks.isEmpty()) {
    //         Skatepark skatepark = skateparks.get(0); // Assuming you want to work with the first matching skatepark
    //         int currentLikes = skatepark.getTotalLikes();
    //         skatepark.setTotalLikes(currentLikes + likeValue);
    //         // You can also update the author who liked the skatepark
    //         repository.save(skatepark);
    //         return new ResponseEntity<>(skatepark, HttpStatus.OK);
    //     }
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }

    



    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteSkatepark(@PathVariable String name) {
        List<User> skateparks = repository.findByName(name);
        if (!skateparks.isEmpty()) {
            repository.delete(skateparks.get(0)); // Assuming you want to delete the first matching skatepark
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}