package com.epam.esm.service;

import com.epam.esm.dto.TagDto;
import com.epam.esm.exception.ServiceSearchException;
import com.epam.esm.exception.ServiceValidationException;

import java.util.List;

/**
 * The interface Tag service.
 */
public interface TagService {

    /**
     * Create one tag.
     *
     * @param tagDto the tag dto for creating
     * @return he int number of created elements
     */
    int create(TagDto tagDto);

    /**
     * Find by id one tag dto.
     *
     * @param id the id for search
     * @return the tag dto
     * @throws ServiceSearchException     the service search exception
     * @throws ServiceValidationException the service validation exception
     */
    TagDto findById(String id) throws ServiceSearchException, ServiceValidationException;

    /**
     * Find by name tag dto.
     *
     * @param name the name for search
     * @return the tag dto
     * @throws ServiceSearchException     the service search exception
     * @throws ServiceValidationException the service validation exception
     */
    TagDto findByName(String name) throws ServiceSearchException, ServiceValidationException;

    /**
     * Find all tags.
     *
     * @return the list of all tags in the database
     */
    List<TagDto> findAll();

    /**
     * Delete tag by id.
     *
     * @param id the tag id
     * @return number of tags removed
     */
    int deleteById(String id);
}
