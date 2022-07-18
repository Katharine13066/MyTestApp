package by.intexsoft.study.security;

import by.intexsoft.study.LibraryApplication;
import by.intexsoft.study.mapper.UserMapper;
import by.intexsoft.study.model.User;
import by.intexsoft.study.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private static final Logger logger = LogManager.getLogger(LibraryApplication.class);

    private final UserService userService;

    private final UserMapper userMapper;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public JwtUserDetailsService(@Qualifier("userService") UserService userService, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.fromDto(userService.findByUserName(username));
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        JwtUser jwtUser = JwtUserFactory.create(user);
        logger.info("User successfully loaded");
        return jwtUser;
    }

}