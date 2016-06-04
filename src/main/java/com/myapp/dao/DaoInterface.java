package com.myapp.dao;

import java.util.List;

/**
 * Interface to represent CRUD operations on database
 */
public interface DaoInterface<E> {

    /**
     * Save new object
     * @param object
     */
    int save(E object);

    /**
     * Update existing object
     * @param object
     */
    void update(E object);

    /**
     * Find object by id
     * @param id
     * @return
     */
    E find(Integer id);

    /**
     * Find all objects
     * @return
     */
    List<E> find();

    /**
     * Delete object by id
     * @param id
     */
    void delete(Integer id);
}
