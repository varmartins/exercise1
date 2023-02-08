package com.varmartins.exercise1.persistence.repositories;

import com.varmartins.exercise1.persistence.entities.Person;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Vitor Martins
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    /**
     *
     * @param name
     * @return
     */
    List<Person> findByName(String name);
}
