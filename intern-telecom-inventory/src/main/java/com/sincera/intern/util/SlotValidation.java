package com.sincera.intern.util;

import com.sincera.intern.dto.SlotDto;

public class SlotValidation {

    public static String validateSlotCreate(SlotDto slotDto){

        String result;
        if(slotDto != null && slotDto.getParentShelfName() != null){
            result = "OK";
        }
        else
        {
            result = "Error : Parent Shelf name is empty!";
        }

        // to do
        return result;
    }


}
