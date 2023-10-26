package com.sincera.intern.util;

import com.sincera.intern.dto.SiteDto;
import com.sincera.intern.model.Site;
import com.sincera.intern.repository.SiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SiteValidation {
    private static final Logger log = LoggerFactory.getLogger(SiteValidation.class);
    @Autowired
    private SiteRepository siteRepository;

    public List<String> validateSite(Site site) {
        List<String> errorMessage = new ArrayList<>();

        if (site.getSiteName() != null) {
            boolean siteNameExists = !siteRepository.getSitesFromName(site.getSiteName()).isEmpty();

            if (siteNameExists) {
                errorMessage.add("Site Name '" + site.getSiteName() + "' already exists.");
            }
            if (site.getSiteId() == null) {
                errorMessage.add("Site ID cannot be null.");
            }
            if (site.getSiteName() == null || site.getSiteName().isEmpty()) {
                errorMessage.add("Site Name cannot be empty.");
            }
            if (site.getSiteType() == null || site.getSiteType().isEmpty()) {
                errorMessage.add("Site Type cannot be empty.");
            }
            if (site.getStatus() == null || site.getStatus().isEmpty()) {
                errorMessage.add("Status cannot be empty.");
            }
            if (site.getLatitude() == null || site.getLatitude().isEmpty()) {
                errorMessage.add("Latitude cannot be empty.");
            }
            if (site.getLongitude() == null || site.getLongitude().isEmpty()) {
                errorMessage.add("Longitude cannot be empty.");
            }
            if (site.getAddress1() == null || site.getAddress1().isEmpty()) {
                errorMessage.add("Address 1 cannot be empty.");
            }
//            if (site.getAddress2() == null || site.getAddress2().isEmpty()) {
//                errorMessage.add("Address 2 cannot be empty.");
//            }
            if (site.getCity() == null || site.getCity().isEmpty()) {
                errorMessage.add("City cannot be empty.");
            }
            if (site.getState() == null || site.getState().isEmpty()) {
                errorMessage.add("State cannot be empty.");
            }
            if (site.getCountry() == null || site.getCountry().isEmpty()) {
                errorMessage.add("Country cannot be empty.");
            }
            if (site.getPin() == null) {
                errorMessage.add("Pin cannot be null.");
            }

        }
        return errorMessage;
    }

    public static String validateSiteCreate(SiteDto siteDto){

        String result = "";
        if(siteDto != null && siteDto.getSiteName() != null){
            result = "OK";
        }
        else
        {
            result = "Error : Site name is empty!";
        }

        // to do
        return result;
    }

//    public static List<String> validateSite(Site site) {
//        List<String> errorMessage = new ArrayList<>();
//
//        Class<?> siteClass = site.getClass();
//        Field[] fields = siteClass.getDeclaredFields();
//
//        for (Field field : fields) {
//            field.setAccessible(true);
//            try {
//                Object value = field.get(site);
//                if (value == null || (value instanceof String && ((String) value).isEmpty())) {
//                    String fieldName = field.getName();
//                    errorMessage.add(fieldName + " cannot be empty.");
//                }
//            } catch (IllegalAccessException e) {
//                // Handle any exception that occurs while accessing field values
//                e.printStackTrace();
//            }
//        }
//
//        return errorMessage;
//    }

}
