package com.sincera.intern.repository;

import com.sincera.intern.model.Slot;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface SlotTruncate extends CrudRepository<Slot,Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE Slot", nativeQuery = true)
    void truncateSlotTable();
}
