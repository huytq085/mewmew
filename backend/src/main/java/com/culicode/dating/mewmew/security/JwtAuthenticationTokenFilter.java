package com.culicode.dating.mewmew.security;

import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.repository.UserRepository;
import com.culicode.dating.mewmew.repository.UserRoleRepository;
import com.culicode.dating.mewmew.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
    private final static String TOKEN_HEADER = "authorization";
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(TOKEN_HEADER);
        if (jwtService.validateTokenLogin(authToken)) {
            String username = jwtService.getUsernameFromToken(authToken);
            com.culicode.dating.mewmew.domain.User appUser;
            Optional<User> userOptional = userRepository.findByUsername(username);
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

            if (appUser != null) {
                boolean enabled = true;
                boolean accountNonExpired = true;
                boolean credentialsNonExpired = true;
                boolean accountNonLocked = true;
                UserDetails userDetail = new org.springframework.security.core.userdetails.User(username, appUser.getPassword(), enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked, grantList);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
                        null, userDetail.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}

