package com.sincera.intern.util;

import com.sincera.intern.dto.PortDto;

public class PortValidation {
    public static String ValidatePortCreate(PortDto portDto){
        String result;
        if(portDto != null && portDto.getPortName()!= null)
        {
            result = "OK";
        }
        else
            result ="Error: Port name not provided";
        if (portDto.getParentCardName()== null)
            result= " Error: Parent Card Name not given";

        return result;
    }
}
