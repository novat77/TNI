package com.sincera.intern.repository;

import com.sincera.intern.model.Slot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SlotRepository extends CrudRepository<Slot, Integer> {

    @Query("SELECT a from Slot a Where a.slotName = :slotName")
    Slot getSlotBySlotName(@Param("slotName") String slotName);
    @Query("SELECT s FROM Slot s WHERE " +
            "(:slotName is null or :slotName = '' or s.slotName like %:slotName%) " +
            "AND (:parentShelfId is null or s.parentShelfId = :parentShelfId) " +
            "AND (:parentShelfName is null or :parentShelfName = '' or s.parentShelfName = :parentShelfName) " +
            "AND (:parentSiteId is null or s.parentSiteId = :parentSiteId) " +
            "AND (:parentSiteName is null or :parentSiteName = '' or s.parentSiteName = :parentSiteName) " )
    List<Slot> getSlotsBy(
            @Param("slotName") String slotName,
            @Param("parentShelfId") Integer parentShelfId,
            @Param("parentShelfName") String parentShelfName,
            @Param("parentSiteId") Integer parentSiteId,
            @Param("parentSiteName") String parentSiteName);
    @Query("SELECT a.slotId from Slot a Where a.slotName = :slotName")
    int getSlotIdFromSlotName(@Param("slotName") String slotName);
    @Query("SELECT a from Slot a Where a.slotName = :slotName")
    List<Slot> getSlotsFromSlotName(@Param("slotName") String slotName);
    @Query("SELECT s FROM Slot s WHERE s.parentShelfId = :parentShelfId")
    List<Slot> getSlotsByParentShelfId(@Param("parentShelfId") Integer parentShelfId);
    @Query("SELECT s FROM Slot s WHERE s.parentShelfName = :parentShelfName")
    List<Slot> getSlotsByParentShelfName(@Param("parentShelfName") String parentShelfName);

    @Query("SELECT s FROM Slot s WHERE s.slotName = :slotName AND s.parentShelfId = :parentShelfId AND s.parentShelfName LIKE %:parentShelfName%")
    List<Slot> getSlotsBySlotNameParentShelfIdParentShelfName(@Param("slotName") String slotName, @Param("parentShelfId") Integer parentShelfId, @Param("parentShelfName") String parentShelfName);

    @Query("SELECT s FROM Slot s WHERE s.slotName = :slotName AND s.parentShelfId = :parentShelfId")
    List<Slot> getSlotsBySlotNameParentShelfId(@Param("slotName") String slotName, @Param("parentShelfId") Integer parentShelfId);

    @Query("SELECT s FROM Slot s WHERE s.slotName = :slotName AND s.parentShelfName LIKE %:parentShelfName%")
    List<Slot> getSlotsBySLotNameParentShelfName(@Param("slotName") String slotName, @Param("parentShelfName") String parentShelfName);

    @Query("SELECT s FROM Slot s WHERE s.parentShelfId = :parentShelfId AND s.parentShelfName LIKE %:parentShelfName%")
    List<Slot> getSlotsByParentShelfIdParentShelfName(@Param("parentShelfId") Integer parentShelfId, @Param("parentShelfName") String parentShelfName);


}
