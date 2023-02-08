package com.varmartins.exercise1.persistence.repositories;

import com.varmartins.exercise1.persistence.entities.Person;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    /**
     *
     * @param name the name of the person we're trying to find
     * @return a list of people matching the name passed as a parameter
     */
    List<Person> findByName(String name);
}
