package com.sincera.intern.service;

import com.sincera.intern.dto.UserDto;
import com.sincera.intern.model.Role;
import com.sincera.intern.model.User;
import com.sincera.intern.repository.RoleRepository;
import com.sincera.intern.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository repo;
    @Autowired
    RoleRepository roleRepository;


    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

//    public List<UserDto> getUser(UserDto userDto){
//        Long id = userDto.getId();
//        String UserName = userDto.getUsername();
//        String role = userDto.getRole();
//        Date date = userDto.getCreatedDate();
//        return convertToUserDtoList(userRepository.getUsersByAttributes(id,date,UserName));
//    }
//
//    private List<UserDto> convertToUserDtoList(Iterable<User> Users ) {
//        List<UserDto> userDtoList = new ArrayList<>();
//        if (Users != null ) {
//            for (User user : Users) {
//                UserDto dto = new UserDto();
//                dto.setUsername(user.getUsername());
//                dto.setRole(user.getRoles().toString());
//                dto.setCreatedDate(user.getCreatedDate());
//                userDtoList.add(dto);
//
//            }
//        }
//        log.info("a "+userDtoList.toString());
//        return userDtoList;
//    }

    public Iterable<User> users(UserDto userDto){
        return repo.findAll();
    }

    public UserDto addUser(UserDto userDto){
        Set<User> userSet = null;
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setRoles(userDto.getRoles());
        user.setPassword(userDto.getPassword());
        user.setCreatedDate(userDto.getCreatedDate());
        user.setEnabled(userDto.isEnabled());
        repo.save(user);
        return null;

//        Role ro = new Role();
//        User user = userRepository.getUserByUsername(userDto.getUsername());
////        role = roleRepository.getrole(userDto.getRole());
//        String roles =userDto.getRole();
//        if(roles == "1" ) {
//            String role = "ADMIN";
//        } else {
//            String role = "USER";
//        }
//        userSet = user.us
    }
}
