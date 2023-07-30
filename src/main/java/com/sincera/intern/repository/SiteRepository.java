package com.sincera.intern.repository;

import com.sincera.intern.model.Site;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractAuditable_.createdBy;

public interface SiteRepository extends CrudRepository<Site, Integer> {
    // Save, Update, Delete data

    @Query("SELECT a FROM Site a WHERE a.siteName = :siteName")
    Site getSiteBySiteName(@Param("siteName") String siteName);

    @Query("SELECT a from Site a Where a.siteName = :name")
    List<Site> getSitesFromName(@Param("name") String name);
    @Query("SELECT a.siteId from Site a Where a.siteName = :name")
    int getSiteIdFromName(@Param("name") String name);


    @Query("SELECT s FROM Site s WHERE " +
            "(:siteName is null or :siteName = '' or s.siteName like %:siteName%) " +
            "AND (:Id is null or s.siteId = :Id) " +
            "AND (:status is null or :status = '' or s.status = :status) " +
            "AND (:siteType is null or :siteType = '' or s.siteType = :siteType) " +
            "AND (:latitude is null or :latitude = '' or s.latitude = :latitude) " +
            "AND (:longitude is null or :longitude = '' or s.longitude = :longitude) " +
            "AND (:address1 is null or :address1 = '' or s.address1 = :address1) " +
            "AND (:address2 is null or :address2 = '' or s.address2 = :address2) " +
            "AND (:city is null or :city = '' or s.city = :city) " +
            "AND (:state is null or :state = '' or s.state = :state) " +
            "AND (:country is null or :country = '' or s.country = :country) " +
            "AND (:pin is null or s.pin = :pin) " +
            "AND (:createdBy is null or :createdBy = '' or s.createdBy = :createdBy) " +
            "AND (:createdAt is null or s.createdAt = :createdAt) " +
            "AND (:lastModifiedAt is null or s.lastModifiedAt = :lastModifiedAt)")
    List<Site> getSitesBy(
            @Param("Id") Integer Id,
            @Param("siteName") String name,
            @Param("status") String status,
            @Param("siteType") String type,
            @Param("latitude") String latitude,
            @Param("longitude") String longitude,
            @Param("address1") String address1,
            @Param("address2") String address2,
            @Param("city") String city,
            @Param("state") String state,
            @Param("country") String country,
            @Param("pin") Integer pin,
            @Param("createdBy") String createdBy,
            @Param("createdAt") LocalDate createdAt,
            @Param("lastModifiedAt") LocalDate lastModifiedAt);



    @Modifying
    @Query("UPDATE Site s SET s.status = :newStatus, s.siteType = :newType, s.latitude = :newLatitude, " +
            "s.longitude = :newLongitude, s.address1 = :newAddress1, s.address2 = :newAddress2, " +
            "s.city = :newCity, s.state = :newState, s.country = :newCountry, s.pin = :newPin, " +
            "s.createdBy = :newCreatedBy, s.createdAt = :newCreatedAt, s.lastModifiedAt = :newLastModifiedAt " +
            "WHERE (:siteId is null or s.siteId = :instId)")
    List<Site> updateSitesByAttributes(
            @Param("newStatus") String newStatus,
            @Param("newType") String newType,
            @Param("newLatitude") String newLatitude,
            @Param("newLongitude") String newLongitude,
            @Param("newAddress1") String newAddress1,
            @Param("newAddress2") String newAddress2,
            @Param("newCity") String newCity,
            @Param("newState") String newState,
            @Param("newCountry") String newCountry,
            @Param("newPin") Integer newPin,
            @Param("newCreatedBy") String newCreatedBy,
            @Param("newCreatedAt") LocalDate newCreatedAt,
            @Param("newLastModifiedAt") LocalDate newLastModifiedAt,
            @Param("instId") Integer instId);



//    @Query("SELECT a from Site a Where a.name like %:name%")
//    List<Site> getSitesFromWildcardName(@Param("name") String name);

//    @Query("SELECT a from Site a Where a.type = :type")
//    List<Site> getSitesFromType(@Param("type") String type);
//
//    // dynamic queries which accepts multiple combinations
//
//    @Query("SELECT a FROM Site a WHERE a.name = :name AND a.type = :type AND a.status = :status")
//    List<Site> getSitesByNameTypeStatus(@Param("name") String name, @Param("type") String type, @Param("status") String status);
//    @Query("SELECT a FROM Site a WHERE a.name = :name AND a.type = :type")
//    List<Site> getSitesByNameType(@Param("name") String name, @Param("type") String type);
//    @Query("SELECT a FROM Site a WHERE a.name = :name AND a.status = :status")
//    List<Site> getSitesByNameStatus(@Param("name") String name, @Param("status") String status);
//    @Query("SELECT a FROM Site a WHERE a.status = :status AND a.type = :type")
//    List<Site> getSitesByStatusType(@Param("status") String status, @Param("type") String type);
//
//    @Query("SELECT a from Site a Where a.status = :status")
//    List<Site> getSitesFromStatus(@Param("status") String status);





}
