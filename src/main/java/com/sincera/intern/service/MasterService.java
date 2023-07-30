package com.sincera.intern.service;

import com.mysql.cj.Query;
import com.mysql.cj.Session;
import com.sincera.intern.model.*;
import com.sincera.intern.repository.*;
import com.sincera.intern.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class MasterService {
    private static final Logger log = LoggerFactory.getLogger(MasterService.class);
    @Autowired
    SiteValidation siteValidation;
    @Autowired
    ShelfValidation shelfValidation;
    @Autowired
    SlotValidation slotValidation;
    @Autowired
    CardValidation cardValidation;
    @Autowired
    PortValidation portValidation;

    @Autowired
    SiteRepository siteRepository;
    @Autowired
    ShelfRepository shelfRepository;
    @Autowired
    SlotRepository slotRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    PortRepository portRepository;
    @Transactional
    public List<Object> loadAndSaveData(MultipartFile file) throws DataProcessingException {
        List<Object> masterList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);

                switch (sheet.getSheetName()) {
                    case "Site":
                        List<Object> processedSite = processSite(sheet);
                        if (processedSite.size() == 1 && processedSite.get(0) instanceof String) {
                            // Error message received
                            masterList.add(processedSite.get(0));
                            throw new DataProcessingException((String) processedSite.get(0)); // Rollback the transaction and propagate the error
                        } else {
                            masterList.addAll(processedSite);
                        }
                        break;
                    case "Shelf":
                        List<Object> processedShelf = processShelf(sheet);
                        if (processedShelf.size() == 1 && processedShelf.get(0) instanceof String) {
                            // Error message received
                            masterList.add(processedShelf.get(0));
                            throw new DataProcessingException((String) processedShelf.get(0)); // Rollback the transaction and propagate the error
                        } else {
                            masterList.addAll(processedShelf);
                        }
                        break;
                    case "Slot":
                        List<Object> processedSlot = processSlot(sheet);
                        if (processedSlot.size() == 1 && processedSlot.get(0) instanceof String) {
                            // Error message received
                            masterList.add(processedSlot.get(0));
                            throw new DataProcessingException((String) processedSlot.get(0)); // Rollback the transaction and propagate the error
                        } else {
                            masterList.addAll(processedSlot);
                        }
                        break;
                    case "Card":
                        List<Object> processedCard = processCard(sheet);
                        if (processedCard.size() == 1 && processedCard.get(0) instanceof String) {
                            // Error message received
                            masterList.add(processedCard.get(0));
                            throw new DataProcessingException((String) processedCard.get(0)); // Rollback the transaction and propagate the error
                        } else {
                            masterList.addAll(processedCard);
                        }
                        break;
                    case "Port":
                        List<Object> processedPort = processPort(sheet);
                        if (processedPort.size() == 1 && processedPort.get(0) instanceof String) {
                            // Error message received
                            masterList.add(processedPort.get(0));
                            throw new DataProcessingException((String) processedPort.get(0)); // Rollback the transaction and propagate the error
                        } else {
                            masterList.addAll(processedPort);
                        }
                        break;
                    default:
                        log.warn("Unsupported sheet name: " + sheet.getSheetName());
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            masterList.add("Error reading the file");
        }

        return masterList;
    }
    private List<Object> processSite(Sheet sheet) {
        List<Object> siteList = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            rowIterator.next(); // Skip header row
        }
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Site site = new Site();

            site.setSiteId(getIntegerValue(row.getCell(0)));
            site.setSiteName(getStringValue(row.getCell(1)));
            site.setSiteType(getStringValue(row.getCell(2)));
            site.setStatus(getStringValue(row.getCell(3)));
            site.setLatitude(getStringValue(row.getCell(4)));
            site.setLongitude(getStringValue(row.getCell(5)));
            site.setAddress1(getStringValue(row.getCell(6)));
            site.setAddress2(getStringValue(row.getCell(7)));
            site.setCity(getStringValue(row.getCell(8)));
            site.setState(getStringValue(row.getCell(9)));
            site.setCountry(getStringValue(row.getCell(10)));
            site.setPin(getIntegerValue(row.getCell(11)));

            List<String> validationErrors = siteValidation.validateSite(site);
            if (!validationErrors.isEmpty()) {
                // Construct error message
                StringBuilder errorMessageBuilder = new StringBuilder("Error: Some fields are invalid in the 'Site' sheet:\n");
                for (String error : validationErrors) {
                    errorMessageBuilder.append(error).append("\n");
                }
                return Collections.singletonList(errorMessageBuilder.toString());
            } else {
                // Save the siteDTO or convert it back to Site entity and save
                siteRepository.save(site);
                siteList.add(site);
            }
        }
        log.info("Site data loaded successfully!");
        return siteList;
    }

    private List<Object> processShelf(Sheet sheet) {
        List<Object> shelfList = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Shelf shelf = new Shelf();

            shelf.setShelfId(getIntegerValue(row.getCell(0)));
            shelf.setShelfName(getStringValue(row.getCell(1)));
            shelf.setShelfType(getStringValue(row.getCell(2)));
            shelf.setVendor(getStringValue(row.getCell(3)));
            shelf.setModel(getIntegerValue(row.getCell(4)));
            shelf.setSerialNumber(getStringValue(row.getCell(5)));
            shelf.setParentSiteInstId(getIntegerValue(row.getCell(6)));
            shelf.setParentSite(getStringValue(row.getCell(7)));


            List<String> shelfValidationErrors = shelfValidation.validateShelf(shelf);
            if (!shelfValidationErrors.isEmpty()) {
                // Construct error message
                StringBuilder errorMessageBuilder = new StringBuilder("Error: Some fields are invalid in the 'Shelf' sheet:\n");
                for (String error : shelfValidationErrors) {
                    errorMessageBuilder.append(error).append("\n");
                }
                return Collections.singletonList(errorMessageBuilder.toString());
            } else {
                shelfRepository.save(shelf);
                shelfList.add(shelf);
            }
        }
        log.info("Shelf data loaded successfully!");
        return shelfList;
    }
    private List<Object> processSlot(Sheet sheet) {
        List<Object> slotList = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            rowIterator.next(); // Skip header row
        }
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Slot slot = new Slot();

            slot.setSlotId(getIntegerValue(row.getCell(0)));
            slot.setSlotName(getStringValue(row.getCell(1)));
            slot.setParentShelfId(getIntegerValue(row.getCell(2)));
            slot.setParentShelfName(getStringValue(row.getCell(3)));
            slot.setParentSiteId(getIntegerValue(row.getCell(4)));
            slot.setParentSiteName(getStringValue(row.getCell(5)));

            List<String> slotValidationErrors = slotValidation.validateSlot(slot);
            if (!slotValidationErrors.isEmpty()) {
                // Construct error message
                StringBuilder errorMessageBuilder = new StringBuilder("Error: Some fields are invalid in the 'Slot' sheet:\n");
                for (String error : slotValidationErrors) {
                    errorMessageBuilder.append(error).append("\n");
                }
                return Collections.singletonList(errorMessageBuilder.toString());
            } else {
                slotRepository.save(slot);
                slotList.add(slot);
            }
        }
        log.info("Slot data loaded successfully!");
        return slotList;
    }

    private List<Object> processCard(Sheet sheet) {
        List<Object> cardList = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            rowIterator.next(); // Skip header row
        }
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Card card = new Card();

            card.setCardId(getIntegerValue(row.getCell(0)));
            card.setCardName(getStringValue(row.getCell(1)));
            card.setCardSerialNumber(getStringValue(row.getCell(2)));
            card.setNetworkId(getIntegerValue(row.getCell(3)));
            card.setSlotId(getIntegerValue(row.getCell(4)));
            card.setSlotName(getStringValue(row.getCell(5)));
            card.setShelfId(getIntegerValue(row.getCell(6)));
            card.setShelfName(getStringValue(row.getCell(7)));
            card.setParentSiteId(getIntegerValue(row.getCell(8)));
            card.setParentSiteName(getStringValue(row.getCell(9)));

            List<String> cardValidationErrors = cardValidation.validateCard(card);
            if (!cardValidationErrors.isEmpty()) {
                // Construct error message
                StringBuilder errorMessageBuilder = new StringBuilder("Error: Some fields are invalid in the 'Card' sheet:\n");
                for (String error : cardValidationErrors) {
                    errorMessageBuilder.append(error).append("\n");
                }
                return Collections.singletonList(errorMessageBuilder.toString());
            } else {
                cardRepository.save(card);
                cardList.add(card);
            }
        }
        log.info("Card data loaded successfully!");
        return cardList;
    }

    private List<Object> processPort(Sheet sheet) {
        List<Object> portList = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            rowIterator.next(); // Skip header row
        }
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Port port = new Port();

            port.setPortId(getIntegerValue(row.getCell(0)));
            port.setPortName(getStringValue(row.getCell(1)));
            port.setPortType(getStringValue(row.getCell(2)));
            port.setIpAddress(getStringValue(row.getCell(3)));
            port.setBandwidth(getStringValue(row.getCell(4)));
            port.setTrail(getStringValue(row.getCell(5)));
            port.setParentCardId(getIntegerValue(row.getCell(6)));
            port.setParentCardName(getStringValue(row.getCell(7)));

            List<String> portValidationErrors = portValidation.validatePort(port);
            if (!portValidationErrors.isEmpty()) {
                // Construct error message
                StringBuilder errorMessageBuilder = new StringBuilder("Error: Some fields are invalid in the 'Port' sheet:\n");
                for (String error : portValidationErrors) {
                    errorMessageBuilder.append(error).append("\n");
                }
                return Collections.singletonList(errorMessageBuilder.toString());
            } else {
                portRepository.save(port);
                portList.add(port);
            }
        }
        log.info("Port data loaded successfully!");
        return portList;
    }

    private Integer getIntegerValue(Cell cell) {
        if (cell != null && cell.getCellTypeEnum() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        return null;
    }

    private String getStringValue(Cell cell) {
        DataFormatter dataFormatter = new DataFormatter();
        if (cell != null) {
            CellType cellType = cell.getCellTypeEnum();
            if (cellType == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cellType == CellType.NUMERIC) {
                return dataFormatter.formatCellValue(cell);
            } else if (cellType == CellType.BLANK) {
                return "";
            }
        }
        return null;
    }
}
