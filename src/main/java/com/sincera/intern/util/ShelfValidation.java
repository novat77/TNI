package com.sincera.intern.util;

import com.sincera.intern.dto.ShelfDto;
import com.sincera.intern.model.Shelf;
import com.sincera.intern.repository.ShelfRepository;
import com.sincera.intern.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ShelfValidation {
    @Autowired
    ShelfRepository shelfRepository;
    @Autowired
    SiteRepository siteRepository;

    public static String validateShelfCreate(ShelfDto shelfDto) {

        String result = "";
        if (shelfDto != null && shelfDto.getShelfName() != null) {
            result = "OK";
        } else {
            result = "Error: Shelf name is empty!";
        }

        // Add additional validation logic for the shelf DTO if needed

        return result;
    }
    public List<String> validateShelf(Shelf shelf) {
        List<String> errorMessage = new ArrayList<>();

        if (shelf.getParentSite() != null) {
            boolean parentShelfNotExists = siteRepository.getSitesFromName(shelf.getParentSite()).isEmpty();
            if (parentShelfNotExists) {
                errorMessage.add("Parent Shelf Name '" + shelf.getParentSite() + "' not found in site.");
            }
        }
        if (shelf.getShelfName() != null) {
            boolean shelfNameUnique = shelfRepository.getShelfsFromName(shelf.getShelfName()).isEmpty();
            if (!shelfNameUnique) {
                errorMessage.add("Shelf Name '" + shelf.getShelfName() + "' already exists.");
            }
        }
        if (shelf.getShelfId() == null) {
            errorMessage.add("Shelf ID cannot be null.");
        }
        if (shelf.getShelfName() == null || shelf.getShelfName().isEmpty()) {
            errorMessage.add("Shelf Name cannot be empty.");
        }
        if (shelf.getShelfType() == null || shelf.getShelfType().isEmpty()) {
            errorMessage.add("Shelf Type cannot be empty.");
        }
        if (shelf.getVendor() == null || shelf.getVendor().isEmpty()) {
            errorMessage.add("Vendor cannot be empty.");
        }
        if (shelf.getModel() == null ) {
            errorMessage.add("Model cannot be empty.");
        }
        if (shelf.getSerialNumber() == null || shelf.getSerialNumber().isEmpty()) {
            errorMessage.add("Serial Number cannot be empty.");
        }
        if (shelf.getParentSiteInstId() == null) {
            errorMessage.add("Parent Site ID cannot be null.");
        }
        if (shelf.getParentSite() == null || shelf.getParentSite().isEmpty()) {
            errorMessage.add("Parent Site Name cannot be empty.");
        }

        return errorMessage;
    }
}





