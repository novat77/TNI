package com.sincera.intern.service;

import com.sincera.intern.dto.SlotDto;
import com.sincera.intern.model.Slot;
import com.sincera.intern.model.User;
import com.sincera.intern.repository.ShelfRepository;
import com.sincera.intern.repository.SiteRepository;
import com.sincera.intern.repository.SlotRepository;
import com.sincera.intern.util.SlotValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SlotService {
    private static final Logger log = LoggerFactory.getLogger(SlotService.class);

    @Autowired
    SlotRepository slotRepository;
    @Autowired
    ShelfRepository shelfRepository;
    @Autowired
    SiteRepository siteRepository;

    public SlotDto createSingleSlot(SlotDto slotDto) {
        // validate the dto first
        String validationResult = SlotValidation.validateSlotCreate(slotDto);

        if (validationResult != null && validationResult.equals("OK")) {
            boolean shelfExists = !(shelfRepository.getShelfsFromName(slotDto.getParentShelfName()).isEmpty());
            boolean siteExists = !(siteRepository.getSitesFromName(slotDto.getParentSiteName()).isEmpty());

            if (shelfExists && siteExists) {
                boolean slotExists = slotRepository.getSlotsFromSlotName(slotDto.getSlotName()).isEmpty();

                if (slotExists) {
                    Slot slot = new Slot();

                    slot.setSlotName(slotDto.getSlotName());
                    slot.setParentShelfName(slotDto.getParentShelfName());
                    slot.setParentShelfId(shelfRepository.getShelfIdFromName(slotDto.getParentShelfName()));
                    slot.setParentSiteName(slotDto.getParentSiteName());
                    slot.setParentSiteId(siteRepository.getSiteIdFromName(slotDto.getParentSiteName()));

                    log.info("Slot to be created = " + slot.toString());

                    slotRepository.save(slot);

                    log.info("Slot created successfully");

                    Slot slotCreated = getSlot(slotDto);
                    if (slotCreated != null) {
                        SlotDto dto = new SlotDto();
                        dto.setSlotName(slotCreated.getSlotName());
                        dto.setSlotId(slotCreated.getSlotId());
                        dto.setParentShelfId(slotCreated.getParentShelfId());
                        dto.setParentShelfName(slotCreated.getParentShelfName());
                        dto.setParentSiteId(slotCreated.getParentSiteId());
                        dto.setParentSiteName(slotCreated.getParentSiteName());

                        return dto;
                    }
                } else {
                    slotDto.setErrorMessage("Slot name already exists: " + slotDto.getSlotName());
                    return slotDto;
                }
            } else {
                if (!shelfExists && !siteExists) {
                    slotDto.setErrorMessage("Invalid shelf & site names: " + slotDto.getParentShelfName() + " " + slotDto.getParentSiteName());
                } else if (!shelfExists) {
                    slotDto.setErrorMessage("Invalid shelf name: " + slotDto.getParentShelfName());
                } else {
                    slotDto.setErrorMessage("Invalid site name: " + slotDto.getParentSiteName());
                }
                return slotDto;
            }
        }
        return null;
    }

    private Slot getSlot(SlotDto slotDto) {
        List<Slot> slots = slotRepository.getSlotsFromSlotName(slotDto.getSlotName());
        if (null != slots && !slots.isEmpty()) {
            return slots.get(0);
        }
        return null;
    }
    public List<SlotDto> getSlots(SlotDto slotDto) {
        String slotName = slotDto.getSlotName();
        Integer parentShelfId = slotDto.getParentShelfId();
        String parentShelfName = slotDto.getParentShelfName();
        Integer parentSiteId = slotDto.getParentSiteId();
        String parentSiteName = slotDto.getParentSiteName();
//        log.info(slotRepository.getSlotsFromSlotName(slotName));
        return convertToSlotDtoList(slotRepository.getSlotsBy(
                slotName, parentShelfId, parentShelfName, parentSiteId, parentSiteName));
    }

    private List<SlotDto> convertToSlotDtoList(List<Slot> slots) {
        List<SlotDto> slotDtoList = new ArrayList<>();
        if (slots != null && !slots.isEmpty()) {
            for (Slot slot : slots) {
                SlotDto dto = new SlotDto();
                dto.setSlotName(slot.getSlotName());
                dto.setParentShelfId(slot.getParentShelfId());
                dto.setParentShelfName(slot.getParentShelfName());
                dto.setParentSiteId(slot.getParentSiteId());
                dto.setParentSiteName(slot.getParentSiteName());
                slotDtoList.add(dto);
            }
        }
        return slotDtoList;
    }
    public SlotDto updateAndGetSlot(SlotDto slotDto) {

        Slot slot = slotRepository.getSlotBySlotName(slotDto.getSlotName());
        if (slot != null) {
            slot.setSlotId(slotDto.getSlotId());
            slot.setParentShelfId(shelfRepository.getShelfIdFromName(slotDto.getParentShelfName()));
            slot.setParentShelfName(slotDto.getParentShelfName());
            slot.setParentSiteId(siteRepository.getSiteIdFromName(slotDto.getParentSiteName()));
            slot.setParentSiteName(slotDto.getParentSiteName());
            slot.setCreatedBy(slotDto.getCreatedBy());
            slot.setCreatedAt(slotDto.getCreatedAt());
            slot.setLastModifiedAt(slotDto.getLastModifiedAt());

            slotRepository.save(slot);

            Slot slotUpdated = getSlot(slotDto);
            if (slotUpdated != null) {
                SlotDto dto = new SlotDto();
                dto.setSlotId(slotUpdated.getSlotId());
                dto.setSlotName(slotUpdated.getSlotName());
                dto.setParentShelfId(shelfRepository.getShelfIdFromName(slotDto.getParentShelfName()));
                dto.setParentShelfName(slotUpdated.getParentShelfName());
                dto.setParentSiteId(siteRepository.getSiteIdFromName(slotDto.getParentSiteName()));
                dto.setParentSiteName(slotUpdated.getParentSiteName());
                dto.setCreatedBy(slotUpdated.getCreatedBy());
                dto.setCreatedAt(slotUpdated.getCreatedAt());
                dto.setLastModifiedAt(slotUpdated.getLastModifiedAt());

                return dto;
            }
        } else {
            throw new IllegalArgumentException("No slot found with the provided name");
        }
        return null;
    }
//    public void delete(SlotDto slotDto) {
//        Integer id = slotDto.getSlotId();
//        slotRepository.deleteAll(id);
//    }
    public void delete(List<Slot> id) {
        slotRepository.deleteAll(id);
    }

    public List<Slot> listAll() {
        return (List<Slot>) slotRepository.findAll();
    }
}