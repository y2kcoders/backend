package com.nighthawk.spring_portfolio.mvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.nighthawk.spring_portfolio.mvc.jokes.Jokes;
import com.nighthawk.spring_portfolio.mvc.jokes.JokesJpaRepository;
import com.nighthawk.spring_portfolio.mvc.note.Note;
import com.nighthawk.spring_portfolio.mvc.note.NoteJpaRepository;
import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonDetailsService;

import com.nighthawk.spring_portfolio.mvc.skatepark.Skatepark;
import com.nighthawk.spring_portfolio.mvc.skatepark.SkateparkJPA;

import java.util.List;

@Component
@Configuration // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class ModelInit {  
    @Autowired JokesJpaRepository jokesRepo;
    @Autowired NoteJpaRepository noteRepo;
    @Autowired PersonDetailsService personService;
    @Autowired SkateparkJPA skateparkRepo; // Inject the SkateparkRepository


    @Bean
    CommandLineRunner run() {  // The run() method will be executed after the application starts
        return args -> {

            // Joke database is populated with starting jokes
            String[] jokesArray = Jokes.init();
            for (String joke : jokesArray) {
                List<Jokes> jokeFound = jokesRepo.findByJokeIgnoreCase(joke);  // JPA lookup
                if (jokeFound.size() == 0)
                    jokesRepo.save(new Jokes(null, joke, 0, 0)); //JPA save
            }

            // Person database is populated with test data
            Person[] personArray = Person.init();
            for (Person person : personArray) {
                //findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase
                List<Person> personFound = personService.list(person.getName(), person.getEmail());  // lookup
                if (personFound.size() == 0) {
                    personService.save(person);  // save

                    // Each "test person" starts with a "test note"
                    String text = "Test " + person.getEmail();
                    Note n = new Note(text, person);  // constructor uses new person as Many-to-One association
                    noteRepo.save(n);  // JPA Save                  
                }
            }
            String[] skateparkArray = Skatepark.init(); // You'll need to define the init() method in your Skatepark class
            for (String skateparkName : skateparkArray) {
                List<Skatepark> skateparkFound = skateparkRepo.findBySkateparkName(skateparkName);
                if (skateparkFound.size() == 0) {
                    Skatepark skatepark = new Skatepark();
                    skatepark.setSkateparkName(skateparkName);
                    skatepark.setAuthor("Author"); // Set author and other details as needed
                    skatepark.setTitle("Title");
                    skatepark.setAddress("Address");
                    skatepark.setStarRating(4); // Set the star rating as needed
                    skatepark.setDescription("Description");
                    skatepark.setTotalLikes(0); // Initialize likes to 0
                    skateparkRepo.save(skatepark);
                }
            }
        };
    }
}

