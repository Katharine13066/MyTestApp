package by.intexsoft.study.service;

import by.intexsoft.study.model.UserDto;

public interface UserService extends LibraryService<UserDto> {

    UserDto findByEmail(String email);
    UserDto findByUserName(String userName);

}
