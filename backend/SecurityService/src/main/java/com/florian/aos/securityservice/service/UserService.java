package com.florian.aos.securityservice.service;

import com.florian.aos.securityservice.config.jwt.JwtTokenProvider;
import com.florian.aos.securityservice.dto.TokenDtoGet;
import com.florian.aos.securityservice.dto.user.UserDtoPost;
import com.florian.aos.securityservice.dto.user.UserLoginDto;
import com.florian.aos.securityservice.entity.Role;
import com.florian.aos.securityservice.entity.User;
import com.florian.aos.securityservice.exception.NotFoundException;
import com.florian.aos.securityservice.repository.RoleRepository;
import com.florian.aos.securityservice.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       @Lazy AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    private Role findRole(String role) {
        return roleRepository.findByRole("ROLE_" + role.toUpperCase())
                .orElseThrow(() -> new NotFoundException("Role are not found"));
    }

    public TokenDtoGet registerUser(UserDtoPost dtoPost){
        if (createUser(dtoPost)){
            long id = getIdByEmail(dtoPost.getEmail());
            TokenDtoGet dtoGet = TokenDtoGet.builder()
                    .token(generateJwtToken(dtoPost.getEmail(), dtoPost.getPassword(), id))
                    .build();
            return dtoGet;
        }else {
            throw new IllegalArgumentException("User could not be registered");
        }
    }

    private boolean createUser(UserDtoPost userDtoPost){
        try {
            User user = userDtoPost.convertToUser();
            user.setPassword(passwordEncoder.encode(userDtoPost.getPassword()));
            try {
                if (userDtoPost.getRoles() !=null && !userDtoPost.getRoles().isEmpty()){
                    List<Role> roles = userDtoPost.getRoles().stream()
                            .map(this::findRole).toList();
                    user.setRoles(roles);
                }else {
                    user.setRoles(List.of(findRole("ROLE_USER")));
                }
            }catch (Exception e){
                throw new IllegalArgumentException("problem with role allocation");
            }
            userRepository.save(user);
            return true;
        }catch (Exception e){
            throw new BadCredentialsException("invalid email or password");
        }
    }

    public TokenDtoGet loginUser(UserLoginDto dtoPost){
        if (checkUserNameExists(dtoPost.getEmail())){
            if (verifyUserPassword(dtoPost.getEmail(), dtoPost.getPassword())){
                return TokenDtoGet.builder()
                        .token(generateJwtToken(dtoPost.getEmail(),
                                dtoPost.getPassword(),
                                getIdByEmail(dtoPost.getEmail())
                        ))
                        .build();
            }
        }
        throw new BadCredentialsException("invalid email or password");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    private boolean verifyUserPassword(String email, String password) {
        return userRepository.findByEmail(email).map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new BadCredentialsException("invalid email or password"));
    }

    private boolean checkUserNameExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private String generateJwtToken(String email, String password, long userId) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication, userId);
        return token;
    }

    private long getIdByEmail(String email) {

        if (userRepository.findByEmail(email).isPresent()) {
            return userRepository.findByEmail(email).get().getId();
        }else throw new NotFoundException("User not found");
    }

}
