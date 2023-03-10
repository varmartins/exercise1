package com.varmartins.exercise1.web;

import com.varmartins.exercise1.persistence.entities.Person;
import com.varmartins.exercise1.persistence.repositories.PersonRepository;
import com.varmartins.exercise1.web.exceptions.PersonIdMismatchException;
import com.varmartins.exercise1.web.exceptions.PersonNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/api/people")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    /**
     *
     * @return
     */
    @GetMapping
    public Iterable findAll() {
        return personRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Person findOne(@PathVariable Long id) {
        return personRepository.findById(id)
          .orElseThrow(PersonNotFoundException::new);
    }

    /**
     *
     * @param personName
     * @return
     */
    @GetMapping("/name/{personName}")
    public List findByName(@PathVariable String personName) {
        return personRepository.findByName(personName);
    }

    /**
     *
     * @param personName
     * @return
     */
    @GetMapping("/nameExists/{personName}")
    public Boolean nameExists(@PathVariable String personName) {
        List<Person> people = personRepository.findByName(personName);
        
        return people != null && !people.isEmpty();
    }

    /**
     *
     * @param person
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    List<Person> createAll(@RequestBody List<Person> people){
        for(Person person : people) {
            personRepository.save(person);
        }
        
        return people;
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personRepository.findById(id)
          .orElseThrow(PersonNotFoundException::new);
        personRepository.deleteById(id);
    }

    /**
     *
     * @param person
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable Long id) {
        if (person.getId() != id) {
          throw new PersonIdMismatchException();
        }
        personRepository.findById(id)
          .orElseThrow(PersonNotFoundException::new);
        return personRepository.save(person);
    }
}
