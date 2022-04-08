package com.backend.anmLogin.demo.service;

import com.backend.anmLogin.demo.config.PassEncConfig;
import com.backend.anmLogin.demo.entity.UserRole;
import com.backend.anmLogin.demo.repository.RoleRepository;
import com.backend.anmLogin.demo.repository.UserRepository;
import com.backend.anmLogin.demo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PassEncConfig encConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByName(username);

        if (!user.isPresent())
        {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        List<String> roleNames = roleRepository.findAllByUserID(user.get().getId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = new User(
                user.get().getName(),
                user.get().getEncrytedPassword(),
                grantList);

        return userDetails;
    }

}
