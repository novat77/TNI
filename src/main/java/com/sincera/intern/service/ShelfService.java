package com.sincera.intern.service;

import com.sincera.intern.dto.ShelfDto;
import com.sincera.intern.model.Shelf;
import com.sincera.intern.repository.ShelfRepository;
import com.sincera.intern.repository.SiteRepository;
import com.sincera.intern.util.ShelfValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShelfService {

    private static final Logger log = LoggerFactory.getLogger(ShelfService.class);

    @Autowired
    ShelfRepository shelfRepository;
    @Autowired
    SiteRepository siteRepository;

    public ShelfDto createAndGetShelf(ShelfDto shelfDto) {

        String response = "";

        String validationResult = ShelfValidation.validateShelfCreate(shelfDto);

        if (validationResult != null && validationResult.equals("OK")) {
            boolean siteExist = !(siteRepository.getSitesFromName(shelfDto.getParentSite()).isEmpty());


            if(siteExist){
                boolean data = shelfRepository.getShelfsFromName(shelfDto.getShelfName()).isEmpty();
                if (data) {
                    Shelf shelf = new Shelf();

                    shelf.setShelfName(shelfDto.getShelfName());
                    shelf.setShelfType(shelfDto.getShelfType());
                    shelf.setVendor(shelfDto.getVendor());
                    shelf.setModel(shelfDto.getModel());
//                    shelf.setStatus(shelfDto.getStatus());
                    shelf.setSerialNumber(shelfDto.getSerialNumber());
                    shelf.setParentSite(shelfDto.getParentSite());
                    shelf.setParentSiteInstId(siteRepository.getSiteIdFromName(shelfDto.getParentSite()));

                    log.info("Shelf to be created = " + shelf.toString());

                    shelfRepository.save(shelf);

                    log.info("Shelf created successfully");
                    response = "Shelf Created Successfully with data \n" + shelf.toString();

                    Shelf shelfCreated = getShelf(shelfDto);
                    if (shelfCreated != null) {
                        ShelfDto dto = new ShelfDto();
                        dto.setShelfName(shelfCreated.getShelfName());
                        dto.setShelfId(shelfCreated.getShelfId());
//                        dto.setStatus(shelfCreated.getStatus());
                        dto.setShelfType(shelfCreated.getShelfType());
                        dto.setVendor(shelfCreated.getVendor());
                        dto.setModel(shelf.getModel());
                        dto.setParentSite(shelfCreated.getParentSite());
                        dto.setSerialNumber(shelfCreated.getSerialNumber());
                        dto.setParentSiteInstId(shelfCreated.getParentSiteInstId());

                        return dto;
                    }
                }else{
                    shelfDto.setErrorMessage("Shelf name already exists: " + shelfDto.getShelfName());
                    return shelfDto;}
            }else   {
                if(!siteExist)
                {
                    shelfDto.setErrorMessage("Parent site not found: " + shelfDto.getParentSite());
                }
                return shelfDto;
            }
        }
        return null;
    }

    public Shelf getShelf(ShelfDto shelfDto) {
        List<Shelf> shelfs = shelfRepository.getShelfsFromName(shelfDto.getShelfName());
        if (null != shelfs && !shelfs.isEmpty()) {
            return shelfs.get(0);
        }
        return null;
    }
    public List<ShelfDto> getShelfs(ShelfDto shelfDto) {
        String name = shelfDto.getShelfName();
        Integer Id = shelfDto.getShelfId();
//        String status = shelfDto.getStatus();
        String type = shelfDto.getShelfType();
        String vendor = shelfDto.getVendor();
        Integer model = shelfDto.getModel();
        String serialNumber = shelfDto.getSerialNumber();
        String parentSite = shelfDto.getParentSite();
        Integer parentSiteInstId = shelfDto.getParentSiteInstId();

        return convertToShelfDtoList(shelfRepository.getShelvfByAttributes(Id,name, type,vendor, model, serialNumber, parentSite, parentSiteInstId));
    }



    private List<ShelfDto> convertToShelfDtoList(List<Shelf> shelfs) {
        List<ShelfDto> shelfDtoList = new ArrayList<>();
        if (shelfs != null && !shelfs.isEmpty()) {
            for (Shelf shelf : shelfs) {
                ShelfDto dto = new ShelfDto();
                dto.setShelfName(shelf.getShelfName());
                dto.setShelfId(shelf.getShelfId());
                dto.setShelfType(shelf.getShelfType());
                dto.setVendor(shelf.getVendor());
                dto.setModel(shelf.getModel());
//                dto.setStatus(shelf.getStatus());
                dto.setSerialNumber(shelf.getSerialNumber());
                dto.setParentSite(shelf.getParentSite());
                dto.setParentSiteInstId(shelf.getParentSiteInstId());
                shelfDtoList.add(dto);
            }
        }
        return shelfDtoList;
    }
    public ShelfDto updateAndGetShelf(ShelfDto shelfDto) {
        boolean siteExist = !(siteRepository.getSitesFromName(shelfDto.getParentSite()).isEmpty());


        if(siteExist) {

            Shelf shelf = shelfRepository.getShelfsFromShelfName(shelfDto.getShelfName());
            if (shelf != null) {
                shelf.setShelfId(shelfDto.getShelfId());
                shelf.setShelfName(shelfDto.getShelfName());
                //            shelf.setStatus(shelfDto.getStatus());
                shelf.setShelfType(shelfDto.getShelfType());
                shelf.setVendor(shelfDto.getVendor());
                shelf.setModel(shelfDto.getModel());
                shelf.setSerialNumber(shelfDto.getSerialNumber());
                shelf.setShelfName(shelfDto.getShelfName());
                shelf.setShelfId(shelfDto.getShelfId());
                shelf.setParentSiteInstId(siteRepository.getSiteIdFromName(shelfDto.getParentSite()));

                shelfRepository.save(shelf);

                Shelf shelfUpdated = getShelf(shelfDto);
                if (shelfUpdated != null) {
                    ShelfDto dto = new ShelfDto();
                    dto.setShelfName(shelfUpdated.getShelfName());
                    dto.setShelfId(shelfUpdated.getShelfId());
                    //                dto.setStatus(shelfUpdated.getStatus());
                    dto.setShelfType(shelfUpdated.getShelfType());
                    dto.setVendor(shelfUpdated.getVendor());
                    dto.setModel(shelfUpdated.getModel());
                    dto.setSerialNumber(shelfUpdated.getSerialNumber());
                    dto.setParentSite(shelfUpdated.getParentSite());
                    dto.setParentSiteInstId(siteRepository.getSiteIdFromName(shelfDto.getParentSite()));
                    dto.setCreatedAt(shelfUpdated.getCreatedAt());
                    dto.setCreatedBy(shelfUpdated.getCreatedBy());

                    return dto;
                }
            } else {
                throw new IllegalArgumentException("No shelf found with the provided name");
            }
        }else   {
                if(!siteExist)
                {
                    shelfDto.setErrorMessage("Parent site not found: " + shelfDto.getParentSite());
                }
                return shelfDto;
            }
        return null;
    }

}