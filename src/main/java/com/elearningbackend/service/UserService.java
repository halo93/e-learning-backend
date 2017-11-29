package com.elearningbackend.service;

import com.elearningbackend.customerrorcode.Errors;
import com.elearningbackend.customexception.ElearningException;
import com.elearningbackend.dto.Pager;
import com.elearningbackend.dto.UserDto;
import com.elearningbackend.entity.User;
import com.elearningbackend.repository.IUserRepository;
import com.elearningbackend.utility.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends AbstractUserService<UserDto, String, User> {

    @Autowired
    public UserService(JpaRepository<User, String> repository) {
        super(repository, new Paginator<>(UserDto.class));
    }

    @Override
    public Pager<UserDto> loadAll(int currentPage, int noOfRowInPage) {
        Page<User> pager = getUserRepository().findAll(new PageRequest(currentPage, noOfRowInPage));
        return paginator.paginate(currentPage, pager, noOfRowInPage, mapper);
    }

    @Override
    public UserDto getOneByKey(String key) {
        User user = getUserRepository().findOne(key);
        return user == null ? null : mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto add(UserDto userDto) throws Exception {
        if (getOneByKey(userDto.getUsername()) != null)
            throw new ElearningException(Errors.USER_ERROR_FIELD_EXISTS.getId(),Errors.USER_ERROR_FIELD_EXISTS.getMessage());
        if (isUniqueFieldExists(userDto)){
            saveUser(userDto);
            return userDto;
        }
        throw new ElearningException(Errors.USER_ERROR.getId(),Errors.USER_ERROR.getMessage());
    }

    @Override
    public UserDto edit(UserDto userDto) throws Exception {
        UserDto userDtoCheck = getOneByKey(userDto.getUsername());
        if (validateUserDto(userDto, userDtoCheck)) return userDtoCheck;
        throw new ElearningException(Errors.USER_ERROR.getId(),Errors.USER_ERROR.getMessage());
    }

    @Override
    public UserDto delete(String key) throws Exception {
        UserDto userDto = getOneByKey(key);
        if (userDto.getUsername()!=null){
            getUserRepository().delete(userDto.getUsername());
            return userDto;
        }
        throw new ElearningException(Errors.USER_ERROR.getId(),Errors.USER_ERROR.getMessage());
    }

    boolean validateUserDto(UserDto userDto, UserDto userDtoCheck) throws ElearningException {
        if (userDtoCheck == null)
            throw new ElearningException(Errors.USER_NOT_FOUND.getId(), Errors.USER_NOT_FOUND.getMessage());
        if(!userDtoCheck.getPasswordDigest().equals(userDto.getPasswordDigest()))
            throw new ElearningException(Errors.USER_PASSWORD_NOT_MATCH.getId(), Errors.USER_PASSWORD_NOT_MATCH.getMessage());
        if (isUniqueFieldExists(userDtoCheck)){
            saveUser(userDtoCheck);
        }
        return true;

    }

    boolean isUniqueFieldExists(UserDto userDto) throws ElearningException {
        if (getUserRepository().findByEmail(userDto.getEmail()) != null
             || getUserRepository().findByPhone(userDto.getPhone()) != null) {
            throw new ElearningException(Errors.USER_ERROR_FIELD_EXISTS.getId(),Errors.USER_ERROR_FIELD_EXISTS.getMessage());
        }
        return true;
    }

    void saveUser (UserDto userDto){
        getUserRepository().save(mapper.map(userDto, User.class));
    }

    IUserRepository getUserRepository() {
        return (IUserRepository) getRepository();
    }
}
