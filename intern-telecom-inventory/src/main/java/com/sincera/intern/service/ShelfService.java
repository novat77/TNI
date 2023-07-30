package com.sincera.intern.service;

import com.sincera.intern.dto.ShelfDto;
import com.sincera.intern.dto.SiteDto;
import com.sincera.intern.model.Shelf;
import com.sincera.intern.model.Site;
import com.sincera.intern.repository.ShelfRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShelfService {

    private static final Logger log = LoggerFactory.getLogger(ShelfService.class);

    @Autowired
    ShelfRepository shelfRepository;

    public ShelfDto createAndGetShelf(ShelfDto shelfDto) {

        String response = "";

        String validationResult = ShelfValidation.validateShelfCreate(shelfDto);

        if (validationResult != null && validationResult.equals("OK")) {

            boolean data = shelfRepository.getShelfsFromName(shelfDto.getName()).isEmpty();

            if (data) {
                Shelf shelf = new Shelf();

                shelf.setName(shelfDto.getName());
                shelf.setType(shelfDto.getType());
                shelf.setStatus(shelfDto.getStatus());
                shelf.setSerialNumber(shelfDto.getSerialNumber());
                shelf.setParentSite(shelfDto.getParentSite());
                shelf.setParentSiteInstId(shelfDto.getParentSiteInstId());

                log.info("Shelf to be created = " + shelf.toString());

                shelfRepository.save(shelf);

                log.info("Shelf created successfully");
                response = "Shelf Created Successfully with data \n" + shelf.toString();

                Shelf shelfCreated = getShelf(shelfDto);
                if (shelfCreated != null) {
                    ShelfDto dto = new ShelfDto();
                    dto.setName(shelfCreated.getName());
                    dto.setStatus(shelfCreated.getStatus());
                    dto.setType(shelfCreated.getType());

                    return dto;
                }
            } else {
                shelfDto.setErrorMessage("Shelf name already exists: " + shelfDto.getName());
            }
        }
        return null;
    }

    public Shelf getShelf(ShelfDto shelfDto) {
        List<Shelf> shelfs = shelfRepository.getShelfsFromName(shelfDto.getName());
        if (null != shelfs && !shelfs.isEmpty()) {
            return shelfs.get(0);
        }
        return null;
    }
    public List<ShelfDto> getShelfs(String name, String type, String status) {
        if (name != null && !name.isEmpty() && type != null && !type.isEmpty() && status != null && !status.isEmpty()) {
            return convertToShelfDtoList(shelfRepository.getShelfsByNameTypeStatus(name, type, status));
        } else if (name != null && !name.isEmpty() && type != null && !type.isEmpty()) {
            return convertToShelfDtoList(shelfRepository.getShelfsByNameType(name, type));
        } else if (name != null && !name.isEmpty() && status != null && !status.isEmpty()) {
            return convertToShelfDtoList(shelfRepository.getShelfsByNameStatus(name, status));
        } else if (type != null && !type.isEmpty() && status != null && !status.isEmpty()) {
            return convertToShelfDtoList(shelfRepository.getShelfsByStatusType(status, type));
        } else if (status != null && !status.isEmpty()) {
            return convertToShelfDtoList(shelfRepository.getShelfsFromStatus(status));
        } else if (name != null && !name.isEmpty()) {
            return convertToShelfDtoList(shelfRepository.getShelfsFromWildcardName(name));
        } else if (type != null && !type.isEmpty()) {
            return convertToShelfDtoList(shelfRepository.getShelfsFromType(type));
        }

        return new ArrayList<>();
    }

    private List<ShelfDto> convertToShelfDtoList(List<Shelf> shelfs) {
        List<ShelfDto> shelfDtoList = new ArrayList<>();
        if (shelfs != null && !shelfs.isEmpty()) {
            for (Shelf shelf : shelfs) {
                ShelfDto dto = new ShelfDto();
                dto.setName(shelf.getName());
                dto.setType(shelf.getType());
                dto.setStatus(shelf.getStatus());
                shelfDtoList.add(dto);
            }
        }
        return shelfDtoList;
    }

}