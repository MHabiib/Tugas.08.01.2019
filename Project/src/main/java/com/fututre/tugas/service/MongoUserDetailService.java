package com.fututre.tugas.service;

import com.fututre.tugas.model.Users;
import com.fututre.tugas.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MongoUserDetailService implements UserDetailsService{
    @Autowired
    private UsersRepository usersRepository;
    private List<GrantedAuthority> buildAuthority(Users users) {
        List<GrantedAuthority> grantedAuthoritys = new ArrayList<>();
        grantedAuthoritys.add(new SimpleGrantedAuthority(users.getRole()));

        return grantedAuthoritys;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
