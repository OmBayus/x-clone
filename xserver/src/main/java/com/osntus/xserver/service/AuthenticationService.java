package com.osntus.xserver.service;

import com.osntus.xserver.model.User;
import com.osntus.xserver.dto.AuthenticationResponse;
import com.osntus.xserver.dto.LoginRequest;
import com.osntus.xserver.dto.RegisterRequest;
import com.osntus.xserver.model.Token;
import com.osntus.xserver.repository.TokenRepository;
import com.osntus.xserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static com.osntus.xserver.model.TokenType.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        //check if user exists
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            //throw exception in console
            System.out.println("User already exists");
            return null;
        }
        else {
            //create user
            var user = User.builder()
                    .name(registerRequest.getName())
                    .username(registerRequest.getUsername())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .email(registerRequest.getEmail())
                    .birthDate(registerRequest.getBirthDate())
                    .isDeleted(false)
                    .build();
            //save user
            var savedUser = userRepository.save(user);
            //generate token
            var jwtToken = jwtService.generateToken(user);
            saveUserToken(savedUser, jwtToken);
            //return token
            return AuthenticationResponse
                    .builder()
                    .accessToken(jwtToken)
                    .build();
        }
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        var user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

}
