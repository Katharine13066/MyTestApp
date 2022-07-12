package by.intexsoft.study.service.impl;

import by.intexsoft.study.mapper.RoleMapper;
import by.intexsoft.study.mapper.UserMapper;
import by.intexsoft.study.model.Role;
import by.intexsoft.study.model.UserDto;
import by.intexsoft.study.repository.RoleDao;
import by.intexsoft.study.repository.UserDao;
import by.intexsoft.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;


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
        return userMapper.toDto(userDao.findByEmail(email));
    }

    @Override
    @Transactional
    public UserDto findByUserName(String userName) {
        return userMapper.toDto(userDao.findByUserName(userName));
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userDao.findById(id));
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.toDtos(userDao.findAll());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
       Role roleUser = roleDao.findByRoleName("ROLE_USER");
       List<Role> userRoles = new ArrayList<>();
       userRoles.add(roleUser);

       userDto.setRolesDtoList(roleMapper.toDtos(userRoles));
       userDto.setStatus(true);

        return userMapper.toDto(userDao.createEntity(userMapper.fromDto(userDto)));
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        return userMapper.toDto(userDao.updateEntity(userMapper.fromDto(userDto)));
    }

    @Override
    @Transactional
    public void patch(UserDto userDto) {
        userMapper.updateUserFromDto(userDto, userDao.findById(userDto.getId()));
    }
}
