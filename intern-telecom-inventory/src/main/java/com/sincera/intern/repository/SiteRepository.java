package com.sincera.intern.repository;

import com.sincera.intern.model.Site;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SiteRepository extends CrudRepository<Site, Integer> {
    // Save, Update, Delete data

    @Query("SELECT a from Site a Where a.name = :name")
    List<Site> getSitesFromName(@Param("name") String name);

    @Query("SELECT a from Site a Where a.name like %:name%")
    List<Site> getSitesFromWildcardName(@Param("name") String name);

    @Query("SELECT a from Site a Where a.type like %:type%")
    List<Site> getSitesFromType(@Param("type") String type);

    // dynamic queries which accepts multiple combinations

    @Query("SELECT a FROM Site a WHERE a.name = :name AND a.type = :type AND a.status = :status")
    List<Site> getSitesByNameTypeStatus(@Param("name") String name, @Param("type") String type, @Param("status") String status);
    @Query("SELECT a FROM Site a WHERE a.name = :name AND a.type = :type")
    List<Site> getSitesByNameType(@Param("name") String name, @Param("type") String type);
    @Query("SELECT a FROM Site a WHERE a.name = :name AND a.status = :status")
    List<Site> getSitesByNameStatus(@Param("name") String name, @Param("status") String status);
    @Query("SELECT a FROM Site a WHERE a.status = :status AND a.type = :type")
    List<Site> getSitesByStatusType(@Param("status") String status, @Param("type") String type);

    @Query("SELECT a from Site a Where a.status like %:status%")
    List<Site> getSitesFromStatus(@Param("status") String status);


}
