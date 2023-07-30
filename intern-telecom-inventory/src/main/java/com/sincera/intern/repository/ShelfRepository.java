package com.sincera.intern.repository;

import com.sincera.intern.model.Shelf;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShelfRepository extends CrudRepository<Shelf, Integer> {
    @Query("SELECT a from Shelf a Where a.name = :name")
    List<Shelf> getShelfsFromName(@Param("name") String name);

    @Query("SELECT a from Shelf a Where a.name like %:name%")
    List<Shelf> getShelfsFromWildcardName(@Param("name") String name);

    @Query("SELECT a from Shelf a Where a.type like %:type%")
    List<Shelf> getShelfsFromType(@Param("type") String type);

    // dynamic queries which accepts multiple combinations

    @Query("SELECT a FROM Shelf a WHERE a.name = :name AND a.type = :type AND a.status = :status")
    List<Shelf> getShelfsByNameTypeStatus(@Param("name") String name, @Param("type") String type, @Param("status") String status);
    @Query("SELECT a FROM Shelf a WHERE a.name = :name AND a.type = :type")
    List<Shelf> getShelfsByNameType(@Param("name") String name, @Param("type") String type);
    @Query("SELECT a FROM Shelf a WHERE a.name = :name AND a.status = :status")
    List<Shelf> getShelfsByNameStatus(@Param("name") String name, @Param("status") String status);
    @Query("SELECT a FROM Shelf a WHERE a.status = :status AND a.type = :type")
    List<Shelf> getShelfsByStatusType(@Param("status") String status, @Param("type") String type);

    @Query("SELECT a from Shelf a Where a.status like %:status%")
    List<Shelf> getShelfsFromStatus(@Param("status") String status);
}
