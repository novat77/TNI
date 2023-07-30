package com.sincera.intern.util;

import com.sincera.intern.dto.SlotDto;
import com.sincera.intern.model.Slot;
import com.sincera.intern.repository.ShelfRepository;
import com.sincera.intern.repository.SiteRepository;
import com.sincera.intern.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class SlotValidation {
    @Autowired
    SiteRepository siteRepository;
    @Autowired
    ShelfRepository shelfRepository;
    @Autowired
    SlotRepository slotRepository;
    public static String validateSlotCreate(SlotDto slotDto){

        String result = "";
        if(slotDto != null && slotDto.getSlotName() != null){
            result = "OK";
        }
        else
        {
            result = "Error : Slot name is empty!";
        }

        // to do
        return result;
    }
    public List<String> validateSlot(Slot slot) {
        List<String> errorMessage = new ArrayList<>();

        if (slot.getParentShelfName() != null && slot.getParentSiteName() != null) {
            boolean parentShelfNotExists = shelfRepository.getShelfsFromName(slot.getParentShelfName()).isEmpty();
            boolean parentSiteNotExists = siteRepository.getSitesFromName(slot.getParentSiteName()).isEmpty();

            if (parentShelfNotExists && parentSiteNotExists) {
                errorMessage.add("Invalid parent shelf & site names: " + slot.getParentShelfName() + " " + slot.getParentSiteName());
            } else if (parentShelfNotExists) {
                errorMessage.add("Invalid parent shelf name: " + slot.getParentShelfName());
            } else if (parentSiteNotExists) {
                errorMessage.add("Invalid parent site name: " + slot.getParentSiteName());
            }
        }

        if (slot.getSlotName() != null) {
            boolean slotNameUnique = slotRepository.getSlotsFromSlotName(slot.getSlotName()).isEmpty();
            if (!slotNameUnique) {
                errorMessage.add("Slot Name '" + slot.getSlotName() + "' already exists.");
            }
        }

        if (slot.getSlotId() == null) {
            errorMessage.add("Slot ID cannot be null.");
        }
        if (slot.getSlotName() == null || slot.getSlotName().isEmpty()) {
            errorMessage.add("Slot Name cannot be empty.");
        }
        if (slot.getParentShelfId() == null) {
            errorMessage.add("Parent Shelf ID cannot be null.");
        }
        if (slot.getParentShelfName() == null || slot.getParentShelfName().isEmpty()) {
            errorMessage.add("Parent Shelf Name cannot be empty.");
        }
        if (slot.getParentSiteId() == null) {
            errorMessage.add("Parent Site ID cannot be null.");
        }
        if (slot.getParentSiteName() == null || slot.getParentSiteName().isEmpty()) {
            errorMessage.add("Parent Site Name cannot be empty.");
        }

        return errorMessage;
    }
}
