package com.sincera.intern.service;

import com.sincera.intern.dto.SiteDto;

import com.sincera.intern.model.Site;
import com.sincera.intern.repository.SiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SiteService {

    private static final Logger log = LoggerFactory.getLogger(SiteService.class);

    @Autowired
    SiteRepository siteRepository;

    public SiteDto createAndGetSite(SiteDto siteDto){

        String response = "";
        // validate the dto first
        String validationResult = SiteValidation.validateSiteCreate(siteDto);

        if(validationResult != null && validationResult.equals("OK")){

           boolean data = siteRepository.getSitesFromName(siteDto.getName()).isEmpty();

           if(data){

               Site site = new Site();

               site.setName(siteDto.getName());
               site.setType(siteDto.getType());
               site.setStatus(siteDto.getStatus());
               site.setLatitude(siteDto.getLatitude());
               site.setLongitude(siteDto.getLongitude());
               site.setAddress1(siteDto.getAddress1());
               site.setAddress2(siteDto.getAddress2());
               site.setCity(siteDto.getCity());
               site.setState(siteDto.getState());
               site.setCountry(siteDto.getCountry());
               site.setPin(siteDto.getPin());
               site.setCreatedAt(LocalDate.now());
               site.setLastModifiedAt(LocalDate.now());
               site.setCreatedBy(siteDto.getCreatedBy());

               log.info("Site to be created = "+site.toString());

               siteRepository.save(site);

               log.info("Site created successfully");
               response = "Site Created Successfully with data \n"+site.toString();

               Site siteCreated = getSite(siteDto);
               if(siteCreated != null){
                   SiteDto dto = new SiteDto();
                   dto.setName(siteCreated.getName());
                   dto.setStatus(siteCreated.getStatus());
                   dto.setType(siteCreated.getType());

                   return dto;
               }

           }else {
               siteDto.setErrorMessage("Site name already exists: " + siteDto.getName());
                //throw new NameAlreadyExistException("User already exists with this name " + siteDto.getName());
           }
        }
        return null;
    }

    public Site getSite(SiteDto siteDto){
        List<Site> sites = siteRepository.getSitesFromName(siteDto.getName());
        if(null != sites){
            return sites.get(0);
        }
        return null;
    }

    public List<SiteDto> getSites(String name, String type,String status) {
        if (name != null && !(name.isEmpty()) && type != null && !(type.isEmpty()) && status !=null && !(status.isEmpty())) {
            return convertToSiteDtoList(siteRepository.getSitesByNameTypeStatus(name, type,status));
        } else if (name != null && !(name.isEmpty()) && type != null && !(type.isEmpty())) {
            return convertToSiteDtoList(siteRepository.getSitesByNameType(name,type));
        } else if (name != null && !(name.isEmpty()) && status !=null && !(status.isEmpty())) {
            return convertToSiteDtoList(siteRepository.getSitesByNameStatus(name,status));
        }else if (type != null && !(type.isEmpty()) && status !=null && !(status.isEmpty())) {
            return convertToSiteDtoList(siteRepository.getSitesByStatusType(status,type));
        }else if ( status !=null && !(status.isEmpty())) {
            return convertToSiteDtoList(siteRepository.getSitesFromStatus(status));
        }else if ( name != null && !(name.isEmpty())) {
            return convertToSiteDtoList(siteRepository.getSitesFromWildcardName(name));
        }else if ( type != null && !(type.isEmpty())) {
            return convertToSiteDtoList(siteRepository.getSitesFromType(type));
        }

        return new ArrayList<>();
    }

    private List<SiteDto> convertToSiteDtoList(List<Site> sites) {
        List<SiteDto> siteDtoList = new ArrayList<>();
        if (sites != null && !sites.isEmpty()) {
            for (Site site : sites) {
                SiteDto dto=new SiteDto();
                dto.setName(site.getName());
                dto.setType(site.getType());
                dto.setStatus(site.getStatus());
                siteDtoList.add(dto);
            }
        }
        return siteDtoList;
    }
}
