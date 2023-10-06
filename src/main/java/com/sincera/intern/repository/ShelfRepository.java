package com.sincera.intern.repository;

import com.sincera.intern.model.Shelf;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShelfRepository extends CrudRepository<Shelf, Integer> {

    @Query("SELECT a from Shelf a Where a.shelfName = :name")
    Shelf getShelfsFromShelfName(@Param("name") String name);
    @Query("SELECT a from Shelf a Where a.shelfName = :name")
    List<Shelf> getShelfsFromName(@Param("name") String name);

    @Query("SELECT a.shelfId from Shelf a Where a.shelfName = :name")
    int getShelfIdFromName(@Param("name") String name);

    @Query("SELECT s FROM Shelf s WHERE " +
            "(:shelfName IS NULL OR :shelfName = '' OR s.shelfName LIKE %:shelfName%) " +
            "AND (:shelfId IS NULL OR :shelfId = '' OR s.shelfId = :shelfId) " +
//            "AND (:status IS NULL OR :status = '' OR s.status = :status) " +
            "AND (:shelfType IS NULL OR :shelfType = '' OR s.shelfType = :shelfType) " +
            "AND (:vendor IS NULL OR :vendor = '' OR s.vendor = :vendor)"+
            "AND (:model IS NULL OR :model ='' OR s.model = :model)"+
            "AND (:serialNumber IS NULL OR :serialNumber = '' OR s.serialNumber = :serialNumber) " +
            "AND (:parentSite IS NULL OR :parentSite = '' OR s.parentSite = :parentSite) " +
            "AND (:parentSiteInstId IS NULL OR s.parentSiteInstId = :parentSiteInstId)")
    List<Shelf> getShelvfByAttributes(
            @Param("shelfId") Integer shelfId,
            @Param("shelfName") String shelfName,
//            @Param("status") String status,
            @Param("shelfType") String shelfType,
            @Param("vendor")String vendor,
            @Param("model") Integer model,
            @Param("serialNumber") String serialNumber,
            @Param("parentSite") String parentSite,
            @Param("parentSiteInstId") Integer parentSiteInstId);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE Shelf",nativeQuery = true)
    void truncateShelf();

//    @Query("SELECT a from Shelf a Where a.name like %:name%")
//    List<Shelf> getShelfsFromWildcardName(@Param("name") String name);
//
//    @Query("SELECT a from Shelf a Where a.type = :type")
//    List<Shelf> getShelfsFromType(@Param("type") String type);
//
//    // dynamic queries which accepts multiple combinations
//
//    @Query("SELECT a FROM Shelf a WHERE a.name = :name AND a.type = :type AND a.status = :status")
//    List<Shelf> getShelfsByNameTypeStatus(@Param("name") String name, @Param("type") String type, @Param("status") String status);
//    @Query("SELECT a FROM Shelf a WHERE a.name = :name AND a.type = :type")
//    List<Shelf> getShelfsByNameType(@Param("name") String name, @Param("type") String type);
//    @Query("SELECT a FROM Shelf a WHERE a.name = :name AND a.status = :status")
//    List<Shelf> getShelfsByNameStatus(@Param("name") String name, @Param("status") String status);
//    @Query("SELECT a FROM Shelf a WHERE a.status = :status AND a.type = :type")
//    List<Shelf> getShelfsByStatusType(@Param("status") String status, @Param("type") String type);
//
//    @Query("SELECT a from Shelf a Where a.status = :status")
//    List<Shelf> getShelfsFromStatus(@Param("status") String status);


//    @Query("SELECT a.instId from Site a Where a.name = :name")
//    int getSiteIdFromName(@Param("name") String name);


}
