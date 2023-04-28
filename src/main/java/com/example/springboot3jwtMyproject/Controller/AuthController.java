package com.example.springboot3jwtMyproject.Controller;

import com.example.springboot3jwtMyproject.Config.JwtTokenHelper;
import com.example.springboot3jwtMyproject.Entity.UserToken;
import com.example.springboot3jwtMyproject.Entity.Users;
import com.example.springboot3jwtMyproject.Error.ResourceNotFoundException;
import com.example.springboot3jwtMyproject.Repository.UserServiceImpl;
import com.example.springboot3jwtMyproject.Repository.UserTokenRepository;
import com.example.springboot3jwtMyproject.Service.CustomUserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/app-auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private CustomUserDetailService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) {


        Users userDetails = (Users) userDetailsService.loadUserByUsername(request.getUserName());

        if (passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            authenticate(request.getUserName(), request.getPassword());
            String token = this.jwtTokenHelper.generateToken(userDetails);
            System.out.println("-------------------------------");
            System.out.println(token);
            System.out.println("-------------------------------");

            JwtAuthResponse response = new JwtAuthResponse();


            response.setUserId(userDetails.getId());
            response.setUserType(userDetails.getUserType());
            response.setUserName(userDetails.getUserName());
            response.setEmail(userDetails.getEmail());
            response.setAbout(userDetails.getAbout());
            response.setToken(token);

            UserToken userToken = new UserToken();
            System.out.println(userDetails.getId());
            UserToken userToken1 = userTokenRepository.findByUserId(userDetails.getId());
            if (userToken1 == null && jwtTokenHelper.validationToken(token, userDetails)) {
                userToken.setUserToken(token);
                userToken.setUserId(userDetails.getId());
                userTokenRepository.save(userToken);
            } else if (Objects.equals(userToken1.getUserId(), userDetails.getId())) {
                userToken1.setUserToken(token);
                userToken1.setUserId(userDetails.getId());
                userTokenRepository.save(userToken1);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Invalid Password");
    }

    private void authenticate(String userName, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);

        authenticationManager.authenticate(authenticationToken);
    }

    @PostMapping("/create-user")
    public ResponseEntity<Users> createUser(@RequestBody Users users, HttpServletRequest httpServletRequest) throws Exception {
        return ResponseEntity.ok(userService.createUser(users));
    }
}
