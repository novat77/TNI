package com.sincera.intern.util;

import com.sincera.intern.dto.CardDto;

public class CardValidation {

    public static String ValidateCardCreate(CardDto cardDto){
        String result;
        if(cardDto != null && cardDto.getCardName() != null)
        {
            result = "OK";
        }
        else
            result ="Error: Card name not provided";
        if (cardDto.getParentSiteName() == null)
            result= " Error: Parent Site Name not given";

        return result;
    }
}
