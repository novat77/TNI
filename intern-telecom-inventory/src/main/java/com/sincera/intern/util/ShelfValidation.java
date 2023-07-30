package com.sincera.intern.util;

import com.sincera.intern.dto.ShelfDto;

public class ShelfValidation {

    public static String validateShelfCreate(ShelfDto shelfDto) {

        String result = "";
        if (shelfDto != null && shelfDto.getName() != null) {
            result = "OK";
        } else {
            result = "Error: Shelf name is empty!";
        }

        // Add additional validation logic for the shelf DTO if needed

        return result;
    }
}





