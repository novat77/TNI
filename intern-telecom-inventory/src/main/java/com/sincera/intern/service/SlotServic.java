package com.sincera.intern.service;

import com.sincera.intern.dto.SlotDto;
import com.sincera.intern.model.Slot;
import com.sincera.intern.repository.SlotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlotServic {
    private static final Logger log = LoggerFactory.getLogger(SlotServic.class);

    @Autowired
    SlotRepository slotRepository;

    public String createSingleSlot(SlotDto slotDto){

        String response = "";
        // validate the dto first
        String validationResult = SlotValidation.validateSlotCreate(slotDto);

        if(validationResult != null && validationResult.equals("OK")){

            Slot slot = new Slot();

            slot.setSoltName(slotDto.getSoltName());
            slot.setParentShelfID(slotDto.getParentShelfID());
            slot.setParentShelfName(slotDto.getParentSitefName());
            slot.setParentSitefID(slotDto.getParentSitefID());
            slot.setParentSitefName(slotDto.getParentSitefName());


            log.info("Slot to be created = "+slot.toString());

            slotRepository.save(slot);

            log.info("Slot created successfully");
            response = "Slot Created Successfully with data \n"+slot.toString();
        }
        else{
            response = validationResult;
        }
        return response;
    }
}
