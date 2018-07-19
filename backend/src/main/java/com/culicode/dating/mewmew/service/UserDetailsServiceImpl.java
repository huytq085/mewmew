package com.culicode.dating.mewmew.service;

import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.domain.UserRole;
import com.culicode.dating.mewmew.repository.UserRepository;
import com.culicode.dating.mewmew.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.culicode.dating.mewmew.domain.User appUser;
        Optional<com.culicode.dating.mewmew.domain.User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        } else {
            appUser = userOptional.get();
        }

        System.out.println("Found user: " + appUser.getUsername());

        List<String> roleNames = userRoleRepository.getRoleNameByUsername(username);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(appUser.getUsername(), //
                appUser.getPassword(), grantList);

        return userDetails;
    }
}
