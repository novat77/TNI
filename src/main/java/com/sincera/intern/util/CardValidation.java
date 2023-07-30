package com.sincera.intern.util;

import com.sincera.intern.dto.CardDto;
import com.sincera.intern.model.Card;
import com.sincera.intern.repository.CardRepository;
import com.sincera.intern.repository.ShelfRepository;
import com.sincera.intern.repository.SiteRepository;
import com.sincera.intern.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CardValidation {
    @Autowired
    SiteRepository siteRepository;
    @Autowired
    ShelfRepository shelfRepository;
    @Autowired
    SlotRepository slotRepository;
    @Autowired
    CardRepository cardRepository;
    public static String validateCardCreate(CardDto cardDto) {
        String result = "";
        if (cardDto != null && cardDto.getCardName() != null) {
            result = "OK";
        } else {
            result = "Error: Card name is empty!";
        }

        // Additional validation logic can be added here

        return result;
    }
    public List<String> validateCard(Card card) {
        List<String> errorMessage = new ArrayList<>();

        if (card.getCardName() != null && card.getParentSiteName() != null) {
            boolean slotNotExists = slotRepository.getSlotsFromSlotName(card.getSlotName()).isEmpty();
            boolean shelfNotExists = shelfRepository.getShelfsFromName(card.getShelfName()).isEmpty();
            boolean parentSiteNotExists = siteRepository.getSitesFromName(card.getParentSiteName()).isEmpty();

            if (slotNotExists && shelfNotExists && parentSiteNotExists) {
                errorMessage.add("Invalid slot, shelf & site names: " + card.getSlotName() + " " + card.getShelfName() + " " + card.getParentSiteName());
            } else if (slotNotExists && parentSiteNotExists) {
                errorMessage.add("Invalid slot & site names: " + card.getSlotName() + " " + card.getParentSiteName());
            } else if (shelfNotExists && parentSiteNotExists) {
                errorMessage.add("Invalid shelf & site names: " + card.getShelfName() + " " + card.getParentSiteName());
            } else if (slotNotExists) {
                errorMessage.add("Invalid slot name: " + card.getSlotName());
            } else if (shelfNotExists) {
                errorMessage.add("Invalid shelf name: " + card.getShelfName());
            } else if (parentSiteNotExists) {
                errorMessage.add("Invalid site name: " + card.getParentSiteName());
            }
        }

        if (card.getCardName() != null) {
            boolean cardNameUnique = cardRepository.getCardsByCardName(card.getCardName()).isEmpty();
            if (!cardNameUnique) {
                errorMessage.add("Card Name '" + card.getCardName() + "' already exists.");
            }
        }

        if (card.getCardId() == null) {
            errorMessage.add("Card ID cannot be null.");
        }
        if (card.getCardName() == null || card.getCardName().isEmpty()) {
            errorMessage.add("Card Name cannot be empty.");
        }
        if (card.getCardSerialNumber() == null || card.getCardSerialNumber().isEmpty()) {
            errorMessage.add("Card Serial Number cannot be empty.");
        }
        if (card.getNetworkId() == null) {
            errorMessage.add("Network ID cannot be null.");
        }
        if (card.getSlotId() == null) {
            errorMessage.add("Slot ID cannot be null.");
        }
        if (card.getSlotName() == null || card.getSlotName().isEmpty()) {
            errorMessage.add("Slot Name cannot be empty.");
        }
        if (card.getShelfId() == null) {
            errorMessage.add("Shelf ID cannot be null.");
        }
        if (card.getShelfName() == null || card.getShelfName().isEmpty()) {
            errorMessage.add("Shelf Name cannot be empty.");
        }
        if (card.getParentSiteId() == null) {
            errorMessage.add("Parent Site ID cannot be null.");
        }
        if (card.getParentSiteName() == null || card.getParentSiteName().isEmpty()) {
            errorMessage.add("Parent Site Name cannot be empty.");
        }

        return errorMessage;
    }
}