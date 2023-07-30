package com.sincera.intern.service;

import com.sincera.intern.dto.SiteDto;
import com.sincera.intern.dto.UserDto;
import com.sincera.intern.model.Site;
import com.sincera.intern.model.User;
import com.sincera.intern.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;

    public List<UserDto> getUser(UserDto userDto){
        Long id = userDto.getId();
        String UserName = userDto.getUsername();
        String role = userDto.getRole();
        Date date = userDto.getCreatedDate();
        return convertToUserDtoList(userRepository.getUsersByAttributes(id,date,UserName));
    }

    private List<UserDto> convertToUserDtoList(Iterable<User> Users ) {
        List<UserDto> userDtoList = new ArrayList<>();
        if (Users != null ) {
            for (User user : Users) {
                UserDto dto = new UserDto();
                dto.setUsername(user.getUsername());
                dto.setRole(user.getRoles().toString());
                dto.setCreatedDate(user.getCreatedDate());
                userDtoList.add(dto);

            }
        }
        log.info("a "+userDtoList.toString());
        return userDtoList;
    }
}
