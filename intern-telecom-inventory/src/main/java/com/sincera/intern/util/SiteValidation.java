package com.sincera.intern.util;

import com.sincera.intern.dto.SiteDto;

public class SiteValidation {

    public static String validateSiteCreate(SiteDto siteDto){

        String result = "";
        if(siteDto != null && siteDto.getName() != null){
            result = "OK";
        }
        else
        {
            result = "Error : Site name is empty!";
        }

        // to do
        return result;
    }
}
