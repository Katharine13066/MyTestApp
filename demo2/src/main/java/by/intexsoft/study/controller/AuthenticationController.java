package by.intexsoft.study.controller;

import by.intexsoft.study.api.AuthenticationApi;
import by.intexsoft.study.mapper.UserMapper;
import by.intexsoft.study.model.AuthenticationRequestDto;
import by.intexsoft.study.model.AuthenticationResponseDto;
import by.intexsoft.study.model.User;
import by.intexsoft.study.security.JwtTokenProvider;
import by.intexsoft.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final UserMapper userMapper;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<AuthenticationResponseDto> loginUser(AuthenticationRequestDto authenticationRequestDto) {
        try {
            String username = authenticationRequestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequestDto.getPassword()));
            User user = userMapper.fromDto(userService.findByUserName(username));

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            AuthenticationResponseDto response = new AuthenticationResponseDto();
            response.setUsername(username);
            response.setToken(token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
