package com.sincera.intern.service;

import com.sincera.intern.dto.CardDto;
import com.sincera.intern.model.Card;
import com.sincera.intern.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {


    private static final Logger log = LoggerFactory.getLogger(CardService.class);

    @Autowired
    CardRepository cardRepository;

    public String createSingleCard(CardDto cardDto){

        String response = "";

        String ValidationResult = CardValidation.ValidateCardCreate(cardDto);

        if (ValidationResult != null && ValidationResult.equals("OK")){

            Card ncard = new Card();

            ncard.setCardName(cardDto.getCardName());
//            ncard.setCardSerialNumber(cardDto.getcardSerialNumber());
            ncard.setNetworkID(cardDto.getNetworkID());
            ncard.setSlotID(cardDto.getSlotID());
            ncard.setSlotName(cardDto.getSlotName());
            ncard.setShelfID(cardDto.getShelfID());
            ncard.setShelfName(cardDto.getShelfName());
            ncard.setParentSiteID(cardDto.getParentSiteID());
            ncard.setParentSiteName(cardDto.getParentSiteName());

            log.info("Shelf to be created = "+ncard.toString());

            cardRepository.save(ncard);

            log.info("Shelf created successfully");
            response = "Shelf Created Successfully with data \n"+ncard.toString();
        }
        else
            response = ValidationResult;

        return response;

    }


}
