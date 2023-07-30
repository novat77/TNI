package com.sincera.intern.service;

import com.sincera.intern.dto.PortDto;
import com.sincera.intern.model.Port;
import com.sincera.intern.repository.PortRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortService {
    private static final Logger log = LoggerFactory.getLogger(PortService.class);

    @Autowired
    PortRepository portRepository;

    public String createSinglePort(PortDto portDto){

        String response = "";
        // validate the dto first
        String validationResult = PortValidation.ValidatePortCreate(portDto);

        if(validationResult != null && validationResult.equals("OK")){

            Port port = new Port();

            port.setPortName(portDto.getPortName());
            port.setPortType(portDto.getPortType());
            port.setIPAdderss(portDto.getIPAdderss());
            port.setBandwidth(port.getBandwidth());
            port.setTrail(portDto.getTrail());
            port.setParentCardID(portDto.getParentCardID());
            port.setParentCardName(portDto.getParentCardName());

            log.info("Port to be created = "+port.toString());

            portRepository.save(port);

            log.info("Port created successfully");
            response = "Port Created Successfully with data \n"+port.toString();
        }
        else{
            response = validationResult;
        }
        return response;
    }


}
