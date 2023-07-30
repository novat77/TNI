package com.sincera.intern.repository;

import com.sincera.intern.model.Card;
import com.sincera.intern.model.Shelf;
import com.sincera.intern.model.Slot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Integer> {
    @Query("SELECT a from Card a Where a.cardName = :cardName")
    Card getCardByCardName(@Param("cardName") String cardName);
    @Query("SELECT a from Card a Where a.cardName = :cardName")
    List<Card> getCardsByCardName(@Param("cardName") String cardName);
    @Query("SELECT c FROM Card c WHERE " +
            "(:cardId IS NULL OR c.cardId = :cardId) " +
            "AND (:cardName IS NULL OR :cardName = '' OR c.cardName LIKE %:cardName%) " +
            "AND (:cardSerialNumber IS NULL OR :cardSerialNumber = '' OR c.cardSerialNumber = :cardSerialNumber) " +
            "AND (:networkId IS NULL OR c.networkId = :networkId) " +
            "AND (:slotId IS NULL OR c.slotId = :slotId) " +
            "AND (:slotName IS NULL OR :slotName = '' OR c.slotName LIKE %:slotName%) " +
            "AND (:shelfId IS NULL OR c.shelfId = :shelfId) " +
            "AND (:shelfName IS NULL OR :shelfName = '' OR c.shelfName LIKE %:shelfName%) " +
            "AND (:parentSiteId IS NULL OR c.parentSiteId = :parentSiteId) " +
            "AND (:parentSiteName IS NULL OR :parentSiteName = '' OR c.parentSiteName = :parentSiteName)")
    List<Card> getCardsByAttributes(
            @Param("cardId") Integer cardId,
            @Param("cardName") String cardName,
            @Param("cardSerialNumber") String cardSerialNumber,
            @Param("networkId") Integer networkId,
            @Param("slotId") Integer slotId,
            @Param("slotName") String slotName,
            @Param("shelfId") Integer shelfId,
            @Param("shelfName") String shelfName,
            @Param("parentSiteId") Integer parentSiteId,
            @Param("parentSiteName") String parentSiteName);
    @Query("SELECT a.cardId from Card a Where a.cardName = :cardName")
    int getCardIdFromCardName(@Param("cardName") String cardName);

}
