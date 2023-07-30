package com.sincera.intern.service;

import com.sincera.intern.dto.PortDto;
import com.sincera.intern.model.Port;
import com.sincera.intern.repository.CardRepository;
import com.sincera.intern.repository.PortRepository;
import com.sincera.intern.util.PortValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortService {
    private static final Logger log = LoggerFactory.getLogger(PortService.class);

    @Autowired
    PortRepository portRepository;
    @Autowired
    CardRepository cardRepository;


    public PortDto createSinglePort(PortDto portDto) {
        String response = "";
        String validationResult = PortValidation.validatePortCreate(portDto);

        if (validationResult != null && validationResult.equals("OK")) {

            boolean data = portRepository.getPortsByPortName(portDto.getPortName()).isEmpty();

            if (data) {
                Port port = new Port();

                port.setPortName(portDto.getPortName());
                port.setPortType(portDto.getPortType());
                port.setIpAddress(portDto.getIpAddress());
                port.setBandwidth(portDto.getBandwidth());
                port.setTrail(portDto.getTrail());
                port.setParentCardId(portDto.getParentCardId());
                port.setParentCardId(cardRepository.getCardIdFromCardName(portDto.getParentCardName()));
                port.setParentCardName(portDto.getParentCardName());

                log.info("Port to be created = " + port.toString());

                portRepository.save(port);

                log.info("Port created successfully");
                response = "Port Created Successfully with data \n" + port.toString();

                Port portCreated = getPort(portDto);
                if (portCreated != null) {
                    PortDto dto = new PortDto();
                    dto.setPortName(portCreated.getPortName());
                    dto.setPortType(portCreated.getPortType());
                    dto.setIpAddress(portCreated.getIpAddress());
                    dto.setBandwidth(portCreated.getBandwidth());
                    dto.setTrail(portCreated.getTrail());
                    dto.setParentCardId(portCreated.getParentCardId());
                    dto.setParentCardName(portCreated.getParentCardName());

                    return dto;
                }

            } else {
                portDto.setErrorMessage("Port name already exists: " + portDto.getPortName());
            }
        }
        return null;
    }

    private Port getPort(PortDto portDto) {
        List<Port> ports = portRepository.getPortsByPortName(portDto.getPortName());
        if (ports != null && !ports.isEmpty()) {
            return ports.get(0);
        }
        return null;
    }

    public List<PortDto> getPorts(PortDto portDto) {
        Integer portId = portDto.getPortId();
        String portName = portDto.getPortName();
        String portType = portDto.getPortType();
        String ipAddress = portDto.getIpAddress();
        String bandwidth = portDto.getBandwidth();
        String trail = portDto.getTrail();
        Integer parentCardId = portDto.getParentCardId();
        String parentCardName = portDto.getParentCardName();

        return convertToPortDtoList(portRepository.getPortsBy(portName,portId, portType, ipAddress, bandwidth, trail, parentCardId, parentCardName));
    }

    private List<PortDto> convertToPortDtoList(List<Port> ports) {
        List<PortDto> portDtoList = new ArrayList<>();
        if (ports != null && !ports.isEmpty()) {
            for (Port port : ports) {
                PortDto dto = new PortDto();
                dto.setPortId(port.getPortId());
                dto.setPortName(port.getPortName());
                dto.setPortType(port.getPortType());
                dto.setIpAddress(port.getIpAddress());
                dto.setBandwidth(port.getBandwidth());
                dto.setTrail(port.getTrail());
                dto.setParentCardId(port.getParentCardId());
                dto.setParentCardName(port.getParentCardName());
                portDtoList.add(dto);
            }
        }
        return portDtoList;
    }
    public PortDto updateAndGetPort(PortDto portDto) {

        Port port = portRepository.getPortByPortName(portDto.getPortName());
        if (port != null) {
            port.setPortName(portDto.getPortName());
            port.setPortType(portDto.getPortType());
            port.setIpAddress(portDto.getIpAddress());
            port.setBandwidth(portDto.getBandwidth());
            port.setTrail(portDto.getTrail());
            port.setParentCardId(portDto.getParentCardId());
            port.setParentCardId(cardRepository.getCardIdFromCardName(portDto.getParentCardName()));
            port.setParentCardName(portDto.getParentCardName());

            portRepository.save(port);

            Port portUpdated = getPort(portDto);
            if (portUpdated != null) {
                PortDto dto = new PortDto();
                dto.setPortName(portUpdated.getPortName());
                dto.setPortType(portUpdated.getPortType());
                dto.setIpAddress(portUpdated.getIpAddress());
                dto.setBandwidth(portUpdated.getBandwidth());
                dto.setTrail(portUpdated.getTrail());
                dto.setParentCardId(portUpdated.getParentCardId());
                dto.setParentCardName(portUpdated.getParentCardName());

                return dto;
            }
        } else {
            throw new IllegalArgumentException("No port found with the provided name");
        }
        return null;
    }

}
