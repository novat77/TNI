package com.sincera.intern.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sincera.intern.dto.ShelfDto;
import com.sincera.intern.dto.SiteDto;
import com.sincera.intern.service.ShelfService;
import com.sincera.intern.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TNIController {

    private static final Logger log = LoggerFactory.getLogger(TNIController.class);
    private static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    @Autowired
    SiteService siteService;
    @Autowired
    ShelfService shelfService;

    @Autowired
    Environment env;

    @Value("#{'${inventory.status.list}'.split(',')}")
    private List<String> statusList;

    @Value("#{'${inventory.site.type.list}'.split(',')}")
    private List<String> siteTypeList;

    @Value("#{'${inventory.shelf.type.list}'.split(',')}")
    private List<String> shelfTypeList;

    @Value("#{'${inventory.card.type.list}'.split(',')}")
    private List<String> cardTypeList;

    @Value("#{'${inventory.port.type.list}'.split(',')}")
    private List<String> portTypeList;

   /* @PostMapping(path = "/site/create")
    public ResponseEntity<String> createSite(@RequestBody SiteDto siteDto) throws JsonProcessingException {
        ResponseEntity<String> response = null;
        log.info("Site DTO from Controller = "+siteDto.toString());
        String responseStr = siteService.createAndGetSite(siteDto);
        response = new ResponseEntity<>(responseStr, org.springframework.http.HttpStatus.OK);
        return response;
    }*/

    @RequestMapping(value = "/tni/sites", method = RequestMethod.POST, params="action=create-site")
    public String saveSingleSite(@Valid @ModelAttribute("siteDto") SiteDto siteDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "new_site";
        }
        log.info("Site DTO from Controller = "+siteDto.toString());
        SiteDto dto = siteService.createAndGetSite(siteDto);
        if (dto == null) {
            model.addAttribute("error", "Site name already exists : " + siteDto.getName());
            return "new_site";
        }
        List<SiteDto> sites = new ArrayList<>();
        sites.add(dto);
        model.addAttribute("sites", sites);
        return "search_site";
    }

    @RequestMapping(value = "/tni/sites", method = RequestMethod.POST, params = "action=search-site")
    public String searchSite(@ModelAttribute("siteDto") SiteDto siteDto, Model model) {
        log.info("Site DTO from Controller = " + siteDto.toString());
        List<SiteDto> sites = siteService.getSites(siteDto.getName(), siteDto.getType(), siteDto.getStatus());
        if (sites.isEmpty()) {
            model.addAttribute("error", "No sites found.");
        } else {
            model.addAttribute("sites", sites);
        }
        log.info("Site search completed successfully!");
        return "search_site";
    }

    @RequestMapping("/tni/site")
    public String loadSite (Model model) {
        SiteDto siteDto = new SiteDto();
        model.addAttribute("siteDto", siteDto);
        model.addAttribute("statusList", statusList);
        model.addAttribute("siteTypeList", siteTypeList);
        return "new_site";
    }

    @RequestMapping(value = "/tni/shelfs", method = RequestMethod.POST, params = "action=create-shelf")
    public String saveSingleShelf(@Valid @ModelAttribute("shelfDto") ShelfDto shelfDto,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "new_shelf";
        }
        log.info("Shelf DTO from Controller = " + shelfDto.toString());
        ShelfDto dto = shelfService.createAndGetShelf(shelfDto);
        if (dto == null) {
            model.addAttribute("error", "Site name already exists : " + shelfDto.getName());
            return "new_shelf";
        }
        List<ShelfDto> shelfs = new ArrayList<>();
        shelfs.add(dto);
        model.addAttribute("shelfs", shelfs);
        return "search_shelf";
    }
    @RequestMapping(value = "/tni/shelfs", method = RequestMethod.POST, params = "action=search-shelf")
    public String searchShelf(@ModelAttribute("shelfDto") ShelfDto shelfDto, Model model) {
        log.info("Shelf DTO from Controller = " + shelfDto.toString());
        List<ShelfDto> shelfs = shelfService.getShelfs(shelfDto.getName(), shelfDto.getType(), shelfDto.getStatus());
        if (shelfs.isEmpty()) {
            model.addAttribute("error", "No shelfs found.");
        } else {
            model.addAttribute("shelfs", shelfs);
        }
        log.info("Shelf search completed successfully!");
        return "search_shelf";
    }

    @RequestMapping("/tni/shelf")
    public String loadShelf(Model model) {
        ShelfDto shelfDto = new ShelfDto();
        model.addAttribute("shelfDto", shelfDto);
        model.addAttribute("statusList", statusList);
        model.addAttribute("shelfTypeList", shelfTypeList);
        return "new_shelf";
    }
    @RequestMapping("/tni")
    public String viewHomePage(Model model) throws JsonProcessingException {
        log.info("Index");
        return "index";
    }

}
