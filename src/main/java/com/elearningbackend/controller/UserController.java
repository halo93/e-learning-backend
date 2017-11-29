package com.elearningbackend.controller;

import com.elearningbackend.customerrorcode.Errors;
import com.elearningbackend.customexception.ElearningException;
import com.elearningbackend.dto.Pager;
import com.elearningbackend.dto.UserDto;
import com.elearningbackend.service.IAbstractService;
import com.elearningbackend.utility.ServiceUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api")
public class UserController {
    @Autowired
    @Qualifier("userService")
    private IAbstractService<UserDto, String> abstractService;

    @GetMapping("/users")
    public Pager<UserDto> loadAll(
            @Param("currentPage") int currentPage,
            @Param("noOfRowInPage") int noOfRowInPage){
        return abstractService.loadAll(currentPage, noOfRowInPage);
    }

    @GetMapping("/user/{key}")
    public UserDto getByKey(@PathVariable("key") String key){
        return abstractService.getOneByKey(key);
    }

    @PostMapping("/user")
    public String add(@RequestBody UserDto userDto){
        JSONObject result = new JSONObject();
        try {
            checkUserDataMissing(ServiceUtils.validateRequired(userDto,
                    "username", "passwordDigest", "email", "phone", "role"));
            abstractService.add(userDto);
            result.put("code","200");
            result.put("message","OK");
            return result.toString();
        } catch (ElearningException ex){
            result.put("code",ex.getErrorCode());
            result.put("message",ex.getMessage());
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code","999");
            result.put("message",e);
            return result.toString();
        }
    }

    @PutMapping("/user/{key}")
    public String edit(@PathVariable String key, @RequestBody UserDto userDto){
        JSONObject result = new JSONObject();
        userDto.setUsername(key);
        try {
            checkUserDataMissing(ServiceUtils.validateRequired(userDto,
                    "username", "passwordDigest"));
            abstractService.edit(userDto);
            result.put("code","200");
            result.put("message","OK");
            return result.toString();
        } catch (ElearningException ex){
            result.put("code",ex.getErrorCode());
            result.put("message",ex.getMessage());
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code","999");
            result.put("message",e);
            return result.toString();
        }
    }

    @DeleteMapping("/user/{key}")
    public String delete(@PathVariable("key") String key){
        JSONObject result = new JSONObject();
        try {
            abstractService.delete(key);
            result.put("code","200");
            result.put("message","OK");
            return result.toString();
        }catch (ElearningException ex){
            result.put("code",ex.getErrorCode());
            result.put("message",ex.getMessage());
            return result.toString();
        }catch (Exception e) {
            e.printStackTrace();
            result.put("message",e);
            return result.toString();
        }
    }

    private boolean checkUserDataMissing(Map<String, String> errorsMap) throws ElearningException {
        ObjectMapper mapper = new ObjectMapper();
        if (!errorsMap.isEmpty()){
            try {
                throw new ElearningException(Errors.ERROR_FIELD_MISS.getId(), mapper.writeValueAsString(errorsMap));
            } catch (JsonProcessingException e) {
                return false;
            }
        }
        return true;
    }
}
