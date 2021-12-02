package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

/**
 * The interface Tag dao.
 */
public interface TagDao {

    /**
     * Create one tag.
     *
     * @param tag the tag to create
     * @return the int number of created elements
     */
    int create(Tag tag);

    /**
     * Find by id one tag.
     *
     * @param id the id for search
     * @return the optional result as there may not be a record in the database
     */
    Optional<Tag> findById(Long id);

    /**
     * Find by name one tag.
     *
     * @param name the name for search
     * @return the optional result as there may not be a record in the database
     */
    Optional<Tag> findByName(String name);

    /**
     * Find list of tags by certificate id.
     *
     * @param id the certificate id
     * @return the list of all tags in the database satisfying the condition
     */
    List<Tag> findByCertificateId(Long id);

    /**
     * Find all tags.
     *
     * @return the list of all tags in the database
     */
    List<Tag> findAll();

    /**
     * Delete tags by id.
     *
     * @param id the tag id
     * @return number of tags removed
     */
    int deleteById(Long id);
}
