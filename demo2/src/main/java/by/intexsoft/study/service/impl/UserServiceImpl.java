package by.intexsoft.study.service.impl;

import by.intexsoft.study.LibraryApplication;
import by.intexsoft.study.exception.UserNotFoundByIdException;
import by.intexsoft.study.mapper.RoleMapper;
import by.intexsoft.study.mapper.UserMapper;
import by.intexsoft.study.model.Role;
import by.intexsoft.study.model.User;
import by.intexsoft.study.model.UserDto;
import by.intexsoft.study.repository.RoleDao;
import by.intexsoft.study.repository.UserDao;
import by.intexsoft.study.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(LibraryApplication.class);

    private UserDao userDao;

    private RoleDao roleDao;

    private UserMapper userMapper;

    private RoleMapper roleMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, UserMapper userMapper, RoleMapper roleMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDto findByEmail(String email) {
        UserDto result = userMapper.toDto(userDao.findByEmail(email));
        logger.info("Find user by email");
        return result;
    }

    @Override
    @Transactional
    public UserDto findByUserName(String userName) {
        UserDto result = userMapper.toDto(userDao.findByUserName(userName));
        logger.info("Find user by username");
        return result;
    }

    @Override
    public UserDto findById(Long id) {
        UserDto result = userMapper.toDto(userDao.findById(id));

        if (result == null){
            logger.warn("No user find by id");
            throw new UserNotFoundByIdException(id);
        }

        logger.info("Find user by id");
        return result;
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> result =  userMapper.toDtos(userDao.findAll());
        logger.info("Get all users");
        return result;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (userDao.findById(id) == null){
            logger.warn("No user find by id (delete method)");
            throw new UserNotFoundByIdException(id);
        }
        userDao.deleteById(id);
        logger.info("Delete user by id");
    }

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
       Role roleUser = roleDao.findByRoleName("ROLE_USER");
       List<Role> userRoles = new ArrayList<>();
       userRoles.add(roleUser);

       userDto.setRolesDtoList(roleMapper.toDtos(userRoles));
       userDto.setStatus(true);

        logger.info("Create new user");
        return userMapper.toDto(userDao.createEntity(userMapper.fromDto(userDto)));
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        UserDto result = userMapper.toDto(userDao.updateEntity(userMapper.fromDto(userDto)));
        logger.info("Update user");
        return result;

    }

    @Override
    @Transactional
    public void patch(UserDto userDto) {
        User user = userDao.findById(userDto.getId());
        if (user == null){
            logger.warn("No user find by id");
            throw new UserNotFoundByIdException(userDto.getId());
        }
        userMapper.updateUserFromDto(userDto, user);
        logger.info("Patch user");
    }
}
