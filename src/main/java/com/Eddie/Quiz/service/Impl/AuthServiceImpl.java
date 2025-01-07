package com.Eddie.Quiz.service.Impl;

import com.Eddie.Quiz.common.RoleEnum;
import com.Eddie.Quiz.dto.request.UserLoginRequest;
import com.Eddie.Quiz.dto.request.UserRegisterRequest;
import com.Eddie.Quiz.dto.response.UserRegisterResponse;
import com.Eddie.Quiz.entity.RoleEntity;
import com.Eddie.Quiz.entity.UserEntity;
import com.Eddie.Quiz.repository.UserRepository;
import com.Eddie.Quiz.security.UserDetailServiceImpl;
import com.Eddie.Quiz.security.UserDetailsImpl;
import com.Eddie.Quiz.service.AuthService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Logger;

@Service
public class AuthServiceImpl implements AuthService {
    @Value("${spring.security.oauth2.resourceserver.jwt.secret-key}")
    private String secretKey;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserDetailServiceImpl userDetailService;
    @Override
    public String generateToken(String username) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .issuer("Eddie")
                .subject(username)
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + 3600 * 1000))
                .build();

        try {
            JWSSigner signer = new MACSigner(secretKey.getBytes());
            SignedJWT signedJWT = new SignedJWT(header, claimsSet);

            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (JOSEException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean verifierToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            String subject = signedJWT.getJWTClaimsSet().getSubject();
            String algorithm = signedJWT.getHeader().getAlgorithm().toString();
            System.out.println(subject + " " + algorithm);
            JWSVerifier verifier = new MACVerifier(secretKey.getBytes());
            return signedJWT.verify(verifier);
        } catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String login(UserLoginRequest request){
        UserDetails userDetails =  userDetailService.loadUserByUsername(request.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                request.getPassword(),
                userDetails.getAuthorities()
        );
        authenticationProvider.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return generateToken(userDetails.getUsername());
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        UserEntity user = new UserEntity();
        String password = passwordEncoder.encode(request.getPassword());

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(password);

        RoleEntity role = new RoleEntity();
        role.setName(RoleEnum.USER);
        user.getRoleEntities().add(role);



        UserEntity userExisted = userRepository.save(user);

        return UserRegisterResponse.builder()
                .avatar(userExisted.getAvatar())
                .name(userExisted.getName())
                .username(userExisted.getUsername())
                .email(userExisted.getEmail())
                .build();
    }

    @Override
    public UserEntity getMyInfo() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findByUsername(userDetails.getUsername());
        return user;
    }
}
