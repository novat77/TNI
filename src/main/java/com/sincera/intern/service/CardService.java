package com.sincera.intern.service;

import com.sincera.intern.dto.CardDto;
import com.sincera.intern.model.Card;
import com.sincera.intern.repository.CardRepository;
import com.sincera.intern.repository.ShelfRepository;
import com.sincera.intern.repository.SiteRepository;
import com.sincera.intern.repository.SlotRepository;
import com.sincera.intern.util.CardValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@Service
public class CardService {
    private static final Logger log = LoggerFactory.getLogger(CardService.class);

    @Autowired
    CardRepository cardRepository;
    @Autowired
    SlotRepository slotRepository;
    @Autowired
    ShelfRepository shelfRepository;
    @Autowired
    SiteRepository siteRepository;

    public CardDto createSingleCard(CardDto cardDto) {
        String response = "";
        String validationResult = CardValidation.validateCardCreate(cardDto);

        if (validationResult != null && validationResult.equals("OK")) {
            boolean data = cardRepository.getCardsByCardName(cardDto.getCardName()).isEmpty();

            if (data) {
                Card card = new Card();

                card.setCardName(cardDto.getCardName());
                card.setCardSerialNumber(cardDto.getCardSerialNumber());
                card.setNetworkId(cardDto.getNetworkId());
                card.setSlotId(slotRepository.getSlotIdFromSlotName(cardDto.getSlotName()));
                card.setSlotName(cardDto.getSlotName());
                card.setShelfId(shelfRepository.getShelfIdFromName(cardDto.getShelfName()));
                card.setShelfName(cardDto.getShelfName());
                card.setParentSiteId(siteRepository.getSiteIdFromName(cardDto.getParentSiteName()));
                card.setParentSiteName(cardDto.getParentSiteName());

                log.info("Card to be created = " + card.toString());

                cardRepository.save(card);

                log.info("Card created successfully");
                response = "Card Created Successfully with data \n" + card.toString();

                Card cardCreated = getCard(cardDto);
                if (cardCreated != null) {
                    CardDto dto = new CardDto();
                    dto.setCardName(cardCreated.getCardName());
                    dto.setCardSerialNumber(cardCreated.getCardSerialNumber());
                    dto.setNetworkId(cardCreated.getNetworkId());
                    dto.setSlotId(cardCreated.getSlotId());
                    dto.setSlotName(cardCreated.getSlotName());
                    dto.setShelfId(cardCreated.getShelfId());
                    dto.setShelfName(cardCreated.getShelfName());
                    dto.setParentSiteId(cardCreated.getParentSiteId());
                    dto.setParentSiteName(cardCreated.getParentSiteName());

                    return dto;
                }

            } else {
                cardDto.setErrorMessage("Card name already exists: " + cardDto.getCardName());
            }
        }
        return null;
    }

    private Card getCard(CardDto cardDto) {
        List<Card> cards = cardRepository.getCardsByCardName(cardDto.getCardName());
        if (cards != null && !cards.isEmpty()) {
            return cards.get(0);
        }
        return null;
    }
    public List<CardDto> getCards(CardDto cardDto) {
        Integer cardId = cardDto.getCardId();
        String cardName = cardDto.getCardName();
        String cardSerialNumber = cardDto.getCardSerialNumber();
        Integer networkId = cardDto.getNetworkId();
        Integer slotId = cardDto.getSlotId();
        String slotName = cardDto.getSlotName();
        Integer shelfId = cardDto.getShelfId();
        String shelfName = cardDto.getShelfName();
        Integer parentSiteId = cardDto.getParentSiteId();
        String parentSiteName = cardDto.getParentSiteName();{

            return convertToCardDtoList(cardRepository.getCardsByAttributes(cardId, cardName, cardSerialNumber, networkId, slotId,
                    slotName, shelfId, shelfName, parentSiteId, parentSiteName));
        }
    }
    private List<CardDto> convertToCardDtoList(List<Card> cards) {
        List<CardDto> cardDtoList = new ArrayList<>();
        if (cards != null && !cards.isEmpty()) {
            for (Card card : cards) {
                CardDto dto = new CardDto();
                dto.setCardId(card.getCardId());
                dto.setCardName(card.getCardName());
                dto.setCardSerialNumber(card.getCardSerialNumber());
                dto.setNetworkId(card.getNetworkId());
                dto.setSlotId(card.getSlotId());
                dto.setSlotName(card.getSlotName());
                dto.setShelfId(card.getShelfId());
                dto.setShelfName(card.getShelfName());
                dto.setParentSiteId(card.getParentSiteId());
                dto.setParentSiteName(card.getParentSiteName());
                cardDtoList.add(dto);
            }
        }
        return cardDtoList;
    }
    public CardDto updateAndGetCard(CardDto cardDto) {

        Card card = cardRepository.getCardByCardName(cardDto.getCardName());
        if (card != null) {
            card.setCardId(cardDto.getCardId());
            card.setCardSerialNumber(cardDto.getCardSerialNumber());
            card.setNetworkId(cardDto.getNetworkId());
            card.setSlotId(slotRepository.getSlotIdFromSlotName(cardDto.getSlotName()));
            card.setSlotName(cardDto.getSlotName());
            card.setShelfId(shelfRepository.getShelfIdFromName(cardDto.getShelfName()));
            card.setShelfName(cardDto.getShelfName());
            card.setParentSiteId(siteRepository.getSiteIdFromName(cardDto.getParentSiteName()));
            card.setParentSiteName(cardDto.getParentSiteName());
            card.setCreatedBy(cardDto.getCreatedBy());
            card.setCreatedAt(cardDto.getCreatedAt());
            card.setLastModifiedAt(cardDto.getLastModifiedAt());

            cardRepository.save(card);

            Card cardUpdated = getCard(cardDto);
            if (cardUpdated != null) {
                CardDto dto = new CardDto();
                dto.setCardId(cardUpdated.getCardId());
                dto.setCardName(cardUpdated.getCardName());
                dto.setCardSerialNumber(cardUpdated.getCardSerialNumber());
                dto.setNetworkId(cardUpdated.getNetworkId());
                dto.setSlotId(slotRepository.getSlotIdFromSlotName(cardDto.getSlotName()));
                dto.setSlotName(cardUpdated.getSlotName());
                dto.setShelfId(shelfRepository.getShelfIdFromName(cardDto.getShelfName()));
                dto.setShelfName(cardUpdated.getShelfName());
                dto.setParentSiteId(siteRepository.getSiteIdFromName(cardDto.getParentSiteName()));
                dto.setParentSiteName(cardUpdated.getParentSiteName());
                dto.setCreatedBy(cardUpdated.getCreatedBy());
                dto.setCreatedAt(cardUpdated.getCreatedAt());
                dto.setLastModifiedAt(cardUpdated.getLastModifiedAt());

                return dto;
            }
        } else {
            throw new IllegalArgumentException("No card found with the provided name");
        }
        return null;
    }

}