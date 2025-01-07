package com.ra.security;

import com.ra.model.entity.Role;
import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        UserPrinciple userPrinciple = new UserPrinciple();
        userPrinciple.setUser(user);
        // cach 1
//        Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
//        for (Role role : user.getRoles()) {
//            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
        userPrinciple.setAuthorities(
                user.getRoles().stream().
                        map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet())
        );
        return userPrinciple;
    }
}
