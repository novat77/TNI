package com.sincera.intern.util;

import com.sincera.intern.dto.PortDto;
import com.sincera.intern.model.Port;
import com.sincera.intern.repository.CardRepository;
import com.sincera.intern.repository.PortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PortValidation {
    @Autowired
    PortRepository portRepository;
    @Autowired
    CardRepository cardRepository;
    public static String validatePortCreate(PortDto portDto){

        String result = "";
        if(portDto != null && portDto.getPortName() != null){
            result = "OK";
        }
        else
        {
            result = "Error : Port name is empty!";
        }
        // to do
        return result;
    }
    public List<String> validatePort(Port port) {
        List<String> errorMessage = new ArrayList<>();

        if (port.getParentCardName() != null) {
            boolean parentCardNotExists = cardRepository.getCardsByCardName(port.getParentCardName()).isEmpty();

            if (parentCardNotExists) {
                errorMessage.add("Parent Card Name '" + port.getParentCardName() + "' not found in site.");
            }
        }

        if (port.getPortName() != null) {
            boolean portNameUnique = portRepository.getPortsByPortName(port.getPortName()).isEmpty();

            if (!portNameUnique) {
                errorMessage.add("Port Name '" + port.getPortName() + "' already exists.");
            }
        }

        if (port.getPortId() == null) {
            errorMessage.add("Port ID cannot be null.");
        }
        if (port.getPortName() == null || port.getPortName().isEmpty()) {
            errorMessage.add("Port Name cannot be empty.");
        }
        if (port.getPortType() == null || port.getPortType().isEmpty()) {
            errorMessage.add("Port Type cannot be empty.");
        }
        if (port.getIpAddress() == null || port.getIpAddress().isEmpty()) {
            errorMessage.add("IP Address cannot be empty.");
        }
        if (port.getBandwidth() == null || port.getBandwidth().isEmpty()) {
            errorMessage.add("Bandwidth cannot be empty.");
        }
        if (port.getTrail() == null || port.getTrail().isEmpty()) {
            errorMessage.add("Trail cannot be empty.");
        }
        if (port.getParentCardId() == null) {
            errorMessage.add("Parent Card ID cannot be null.");
        }
        if (port.getParentCardName() == null || port.getParentCardName().isEmpty()) {
            errorMessage.add("Parent Card Name cannot be empty.");
        }

        return errorMessage;
    }
}
