package com.sincera.intern.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sincera.intern.dto.*;
import com.sincera.intern.model.Role;
import com.sincera.intern.model.Site;
import com.sincera.intern.model.Slot;
import com.sincera.intern.model.User;
import com.sincera.intern.repository.RoleRepository;
import com.sincera.intern.repository.SiteRepository;
import com.sincera.intern.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class TNIController {

    private static final Logger log = LoggerFactory.getLogger(TNIController.class);
    private static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    @Autowired
    SiteService siteService;
    @Autowired
    ShelfService shelfService;
    @Autowired
    SlotService slotService;
    @Autowired
    CardService cardService;
    @Autowired
    PortService portService;
    @Autowired
    MasterService masterService;
    @Autowired
    UserService userService;
    @Autowired
    SiteRepository siteRepository;

    @Autowired
    Environment env;
    @Autowired
    RoleRepository roleRepository;

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
    @Value("#{'${inventory.port.bw.list}'.split(',')}")
    private List<String> portBandwidthList;

//    @PostMapping(path = "/site/create")
//    public ResponseEntity<SiteDto> createSite(@RequestBody SiteDto siteDto) throws JsonProcessingException {
//        ResponseEntity<SiteDto> response = null;
//        log.info("Site DTO from Controller = " + siteDto.toString());
//        SiteDto createdSite = siteService.createAndGetSite(siteDto);
//
//        if (createdSite != null) {
//            response = new ResponseEntity<>(createdSite, HttpStatus.OK);
//        } else {
//            // Handle the case when site creation fails
//            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return response;
//    }


//        @PostMapping(path = "/site/create")
//    public ResponseEntity<String> createSite(@RequestBody SiteDto siteDto) throws JsonProcessingException {
//        ResponseEntity<String> response = null;
//        log.info("Site DTO from Controller = "+siteDto.toString());
//        SiteDto responseStr = siteService.createAndGetSite(siteDto);
//        response = new ResponseEntity<>(responseStr, org.springframework.http.HttpStatus.OK);
//        return response;
//    }






//    @RequestMapping(value = "/tni/site", method = RequestMethod.GET)
//    public String getSite(@ModelAttribute("siteDto") SiteDto siteDto,Model model) {
//        List<SiteDto> existingSites = siteService.getSites(siteDto);
//        model.addAttribute("sites", existingSites);
//        model.addAttribute("siteDto", siteDto);
//        model.addAttribute("statusList", statusList);
//        model.addAttribute("siteTypeList", siteTypeList);
//        return "new_site";
//    }

    @RequestMapping(value = "/tni/sites", method = RequestMethod.POST, params = "action=update-site")
    public String updateSite(@ModelAttribute("siteDto") SiteDto siteDto, Model model) {
        log.info("Updating site: " + siteDto.toString());
        SiteDto dto = siteService.updateAndGetSite(siteDto);
        List<SiteDto> sites = new ArrayList<>();
        sites.add(dto);
        model.addAttribute("sites", sites);
    //        model.addAttribute("siteDto", siteDto);
        model.addAttribute("statusList", statusList);
        model.addAttribute("siteTypeList", siteTypeList);
        return "search_site";
    }

    @RequestMapping(value = "/tni/sites", method = RequestMethod.POST, params="action=create-site")
        public String saveSingleSite(@Valid @ModelAttribute("siteDto") SiteDto siteDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "new_site";
        }
        log.info("Site DTO from Controller = "+siteDto.toString());
        SiteDto dto = siteService.createAndGetSite(siteDto);
        if (dto == null) {
            model.addAttribute("error", "Site name already exists : " + siteDto.getSiteName());
            return "new_site";
        }
        List<SiteDto> sites = new ArrayList<>();
        sites.add(dto);
        model.addAttribute("sites", sites);
        model.addAttribute("statusList", statusList);
        model.addAttribute("siteTypeList", siteTypeList);
        return "search_site";
    }

    @RequestMapping(value = "/tni/sites", method = RequestMethod.POST, params = "action=search-site")
    public String searchSite(@ModelAttribute("siteDto") SiteDto siteDto, Model model) {
        log.info("Site DTO from Controller = " + siteDto.toString());
        List<SiteDto> sites = siteService.getSites(siteDto);
        if (sites.isEmpty()) {
            model.addAttribute("error", "No sites found.");
        } else {
            model.addAttribute("sites", sites);
        }
        log.info("Site search completed successfully!");
        model.addAttribute("statusList", statusList);
        model.addAttribute("siteTypeList", siteTypeList);
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

    @RequestMapping(value = "/tni/shelfs", method = RequestMethod.POST, params = "action=update-shelf")
    public String updateShelf(@ModelAttribute("shelfDto") ShelfDto shelfDto, Model model) {
        log.info("Updating shelf: " + shelfDto.toString());
        ShelfDto updatedShelfDto = shelfService.updateAndGetShelf(shelfDto);
        List<ShelfDto> shelfs = new ArrayList<>();
        shelfs.add(updatedShelfDto);
        model.addAttribute("shelfs", shelfs);
        model.addAttribute("statusList", statusList);
        model.addAttribute("shelfTypeList", shelfTypeList);
        return "search_shelf";
    }

    @RequestMapping(value = "/tni/shelfs", method = RequestMethod.POST, params = "action=create-shelf")
    public String saveSingleShelf(@Valid @ModelAttribute("shelfDto") ShelfDto shelfDto,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "new_shelf";
        }
        log.info("Shelf DTO from Controller = " + shelfDto.toString());
        ShelfDto dto = shelfService.createAndGetShelf(shelfDto);
        if (dto.getErrorMessage() != null) {
            String errorMessage = dto.getErrorMessage().trim();
            model.addAttribute("error", errorMessage);
            return "new_shelf";
        }
        List<ShelfDto> shelfs = new ArrayList<>();
        shelfs.add(dto);
        model.addAttribute("shelfs", shelfs);
        model.addAttribute("statusList", statusList);
        model.addAttribute("shelfTypeList", shelfTypeList);
        return "search_shelf";
    }
    @RequestMapping(value = "/tni/shelfs", method = RequestMethod.POST, params = "action=search-shelf")
    public String searchShelf(@ModelAttribute("shelfDto") ShelfDto shelfDto, Model model) {
        log.info("Shelf DTO from Controller = " + shelfDto.toString());
        List<ShelfDto> shelfs = shelfService.getShelfs(shelfDto);
        if (shelfs.isEmpty()) {
            model.addAttribute("error", "No shelfs found.");
        } else {
            model.addAttribute("shelfs", shelfs);
        }
        log.info("Shelf search completed successfully!");
        model.addAttribute("statusList", statusList);
        model.addAttribute("shelfTypeList", shelfTypeList);
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

    @RequestMapping(value = "/tni/slots", method = RequestMethod.POST, params = "action=update-slot")
    public String updateSlot(@ModelAttribute("slotDto") SlotDto slotDto, Model model) {
        log.info("Updating slot: " + slotDto.toString());

        // Call the update method in the service class to update the slot
        SlotDto updatedSlotDto = slotService.updateAndGetSlot(slotDto);
        List<SlotDto> slots = new ArrayList<>();
        slots.add(updatedSlotDto);
        model.addAttribute("slots", slots);
        model.addAttribute("statusList", statusList);
        model.addAttribute("shelfTypeList", shelfTypeList);
        return "search_slot";
    }

    @RequestMapping(value = "/tni/slots", method = RequestMethod.POST, params = "action=create-slot")
    public String saveSingleSlot(@Valid @ModelAttribute("slotDto") SlotDto slotDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "new_slot";
        }
        log.info("Slot DTO from Controller = " + slotDto.toString());
        SlotDto dto = slotService.createSingleSlot(slotDto);
        if (dto.getErrorMessage() != null) {
            String errorMessage = dto.getErrorMessage().trim();
            model.addAttribute("error", errorMessage);
            return "new_slot";
        }
        List<SlotDto> slots = new ArrayList<>();
        slots.add(dto);
        model.addAttribute("slots", slots);
        model.addAttribute("statusList", statusList);
        model.addAttribute("shelfTypeList", shelfTypeList);
        return "search_slot";
    }
    @RequestMapping(value = "/tni/slots", method = RequestMethod.POST, params = "action=search-slot")
    public String searchSlot(@ModelAttribute("slotDto") SlotDto slotDto, Model model) {
        log.info("Slot DTO from Controller = " + slotDto.toString());
        List<SlotDto> slots = slotService.getSlots(slotDto);
        if (slots.isEmpty()) {
            model.addAttribute("error", "No slots found.");
        } else {
            model.addAttribute("slots", slots);
        }
        log.info("Slot search completed successfully!");
        model.addAttribute("statusList", statusList);
        model.addAttribute("shelfTypeList", shelfTypeList);
        return "search_slot";
    }
    @RequestMapping("/tni/slot")
    public String loadSlot (Model model) {
        SlotDto slotDto = new SlotDto();
        model.addAttribute("slotDto", slotDto);
        model.addAttribute("statusList", statusList);
        model.addAttribute("shelfTypeList", shelfTypeList);
        return "new_slot";
    }

    @RequestMapping(value = "/tni/cards", method = RequestMethod.POST, params = "action=update-card")
    public String updateCard(@ModelAttribute("cardDto") CardDto cardDto, Model model) {
        log.info("Updating card: " + cardDto.toString());

        CardDto updatedCardDto = cardService.updateAndGetCard(cardDto);

        List<CardDto> cards = new ArrayList<>();
        cards.add(updatedCardDto);
        model.addAttribute("cards", cards);
        model.addAttribute("statusList", statusList);
        model.addAttribute("shelfTypeList", shelfTypeList);
        return "search_card";
    }

    @RequestMapping(value = "/tni/cards", method = RequestMethod.POST, params = "action=create-card")
    public String saveSingleCard(@Valid @ModelAttribute("cardDto") CardDto cardDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "new_card";
        }
        log.info("Card DTO from Controller = " + cardDto.toString());
        CardDto dto = cardService.createSingleCard(cardDto);
        if (dto == null) {
            model.addAttribute("error", "Card name already exists: " + cardDto.getCardName());
            return "new_card";
        }
        List<CardDto> cards = new ArrayList<>();
        cards.add(dto);
        model.addAttribute("cards", cards);

        model.addAttribute("statusList", statusList);
        model.addAttribute("shelfTypeList", shelfTypeList);
        return "search_card";
    }

    @RequestMapping(value = "/tni/cards", method = RequestMethod.POST, params = "action=search-card")
    public String searchCard(@ModelAttribute("cardDto") CardDto cardDto, Model model) {
        log.info("Card DTO from Controller = " + cardDto.toString());
        List<CardDto> cards = cardService.getCards(cardDto);
        if (cards.isEmpty()) {
            model.addAttribute("error", "No cards found.");
        } else {
            model.addAttribute("cards", cards);
        }
        log.info("Card search completed successfully!");

        model.addAttribute("statusList", statusList);
        model.addAttribute("shelfTypeList", shelfTypeList);
        return "search_card";
    }

    @RequestMapping("/tni/card")
    public String loadCard(Model model) {
        CardDto cardDto = new CardDto();
        model.addAttribute("cardDto", cardDto);
        model.addAttribute("statusList", statusList);
        model.addAttribute("shelfTypeList", shelfTypeList);
        return "new_card";
    }

    @RequestMapping(value = "/tni/ports", method = RequestMethod.POST, params = "action=update-port")
    public String updatePort(@ModelAttribute("portDto") PortDto portDto, Model model) {
        log.info("Updating port: " + portDto.toString());
        PortDto updatedPortDto = portService.updateAndGetPort(portDto);

        List<PortDto> ports = new ArrayList<>();
        ports.add(updatedPortDto);
        model.addAttribute("ports", ports);
        model.addAttribute("portTypeList",portTypeList);
        model.addAttribute("portBandwidthList",portBandwidthList);
        return "search_port";
    }

    @RequestMapping(value = "/tni/ports", method = RequestMethod.POST, params = "action=create-port")
    public String saveSinglePort(@Valid @ModelAttribute("portDto") PortDto portDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "new_port";
        }
        log.info("Port DTO from Controller = " + portDto.toString());
        PortDto dto = portService.createSinglePort(portDto);
        if (dto == null) {
            model.addAttribute("error", "Port name already exists: " + portDto.getPortName());
            return "new_port";
        }
        List<PortDto> ports = new ArrayList<>();
        ports.add(dto);
        model.addAttribute("ports", ports);
        model.addAttribute("portTypeList",portTypeList);
        model.addAttribute("portBandwidthList",portBandwidthList);
        return "search_port";
    }

    @RequestMapping(value = "/tni/ports", method = RequestMethod.POST, params = "action=search-port")
    public String searchPort(@ModelAttribute("portDto") PortDto portDto, Model model) {
        log.info("Port DTO from Controller = " + portDto.toString());
        List<PortDto> ports = portService.getPorts(portDto);
        if (ports.isEmpty()) {
            model.addAttribute("error", "No ports found.");
        } else {
            model.addAttribute("ports", ports);
        }
        log.info("Port search completed successfully!");
        model.addAttribute("portTypeList",portTypeList);
        model.addAttribute("portBandwidthList",portBandwidthList);
        return "search_port";
    }

    @RequestMapping("/tni/port")
    public String loadPort(Model model) {
        PortDto portDto = new PortDto();
        model.addAttribute("portDto", portDto);
        model.addAttribute("portTypeList",portTypeList);
        model.addAttribute("portBandwidthList",portBandwidthList);
        return "new_port";
    }

    @RequestMapping("/tni/loadMaster")
    public String loadMaster (Model model) {
        log.info("From /load-master");
        return "load_master";
    }
    @RequestMapping(value = "/loadMaster", method = RequestMethod.POST)
    public ModelAndView loadMasterData(@RequestParam("master") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView("load_master");
        if (file.isEmpty()) { String errorMessage = "Please choose a file to upload."; modelAndView.addObject("msg1", errorMessage);
            log.error(errorMessage); return modelAndView;}
        try {
            List<Object> masterList = masterService.loadAndSaveData(file);
            boolean hasErrors = false;
            for (Object data : masterList) {
                if (data instanceof String) {
                    String errorMessage = (String) data;
                    modelAndView.addObject("msg1", errorMessage);
                    log.error(errorMessage);
                    hasErrors = true;
                    break;
                }
            }
            if (hasErrors) {
                return modelAndView; // Stop execution and return error message
            } else {
                modelAndView.addObject("msg2", "Master data saved successfully!");
                log.info("Master data saved successfully!");
            }
            modelAndView.addObject("masterList", masterList);
            return modelAndView;
        } catch (DataProcessingException e) {
            String errorMessage = e.getMessage();
            modelAndView.addObject("msg1", errorMessage);
            log.error(errorMessage);
            modelAndView.addObject("errorStackTrace", ""); // Empty stack trace
            return modelAndView;
        }
    }

    @RequestMapping(value = "/allUsers",method = RequestMethod.GET)
    public  String viewUsers(@ModelAttribute("userDto") UserDto userDto, Model model){
//        List<UserDto> users = userService.getUser(userDto);
        Iterable<User> users = userService.users(userDto);
        model.addAttribute("users",users);
//    model.addAttribute("")
        return "all_users";
    }
    @RequestMapping(value = "/newUser",method = RequestMethod.POST,params = "action=create-user")
    public  String addUser(@ModelAttribute("userDto") UserDto userDto, Model model){
        userDto.setCreatedDate(new Date());
        userDto.setPassword(generateEncodedPassword(userDto.getPassword()));
        userService.addUser(userDto);
        return "redirect:/allUsers";
    }
    private String generateEncodedPassword(String pwd){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pwd);
    }

    @RequestMapping(value = "/newUser")
    public  String loadUser( Model model){
//        List<UserDto> users = userService.getUser(userDto);
        UserDto userDto =  new UserDto();
        List<Role> roles = (List<Role>) roleRepository.findAll();

        model.addAttribute("roles",roles);
        model.addAttribute("userDto",userDto);
////    model.addAttribute("")
//        return "all_users";
        return  "new_user";
    }

    @RequestMapping(value = "/processUser/{id}", method =RequestMethod.POST, params="action=enable")
    public ModelAndView processUserEnable(@PathVariable (name="id") Long id) {
        User user = userService.get(id);
        user.setEnabled(true);
        userService.save(user);
        ModelAndView mav = new ModelAndView("all_users");
        List<User> users = userService.listAll();
        mav.addObject("users",users);
        return mav;
    }

    @RequestMapping(value = "/processUser/{id}", method =RequestMethod.POST, params="action=disable")
    public ModelAndView processUserDisable(@PathVariable (name="id") Long id) {
        User user = userService.get(id);
        user.setEnabled(false);
        userService.save(user);
        ModelAndView mav = new ModelAndView("all_users");
        List<User> users = userService.listAll();
        mav.addObject("users",users);
        return mav;
    }

    @RequestMapping(value = "/processUser/{id}", method =RequestMethod.POST, params="action=delete")
    public ModelAndView processUserDelete(@PathVariable (name="id") Long id) {
        userService.delete(id);
        ModelAndView mav = new ModelAndView("all_users");
        List<User> users = userService.listAll();
        mav.addObject("users",users);
        return mav;
    }

    @RequestMapping(value = "/tni/slots/{slotId}", method =RequestMethod.POST, params="action=deleteRecords")
//    public ModelAndView DeleteSlots(@ModelAttribute (name="slot") SlotDto slot) {
//        log.info("=================== "+slot);
//        Integer id = slot.getSlotId();
//        slotService.delete(id);
    public ModelAndView DeleteSlots(@PathVariable (name="slotId") String slotId) {
        log.info("=================== "+slotId);

        slotService.delete(Integer.valueOf(slotId));


        ModelAndView mav = new ModelAndView("Search_slot");
        List<Slot> slots = slotService.listAll();
        mav.addObject("slots",slots);
        return mav;

    }

    @RequestMapping("/tni")
    public String viewHomePage(Model model) throws JsonProcessingException {
        log.info("Index");
        return "index";
    }

}
