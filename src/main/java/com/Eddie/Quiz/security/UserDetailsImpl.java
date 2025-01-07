package com.Eddie.Quiz.security;

import com.Eddie.Quiz.entity.QuestionEntity;
import com.Eddie.Quiz.entity.UserEntity;
import com.Eddie.Quiz.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;


@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private String password;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl build(UserEntity user) {
        String password = user.getPassword();
        String username = user.getUsername();
        return new UserDetailsImpl(
                password,
                username,
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
