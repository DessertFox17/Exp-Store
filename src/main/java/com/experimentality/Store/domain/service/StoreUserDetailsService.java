package com.experimentality.Store.domain.service;


import com.experimentality.Store.domain.dto.RoleDto;
import com.experimentality.Store.domain.dto.SecurityUserDto;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StoreUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {

        SecurityUserDto user = userService.getByEmail(username);

        return new User(user.getEmail(), "{noop}" + user.getPassword(), getAuthorities(user.getRoId()));
    }

    public UserDetails userAndPasswordValidaion(String username, String password){

        SecurityUserDto user = userService.getByEmail(username);

        if(!user.getEmail().equals(username) || !user.getPassword().equals(password)){
            throw new SecurityException("Incorrect username or password");
        }

        return loadUserByUsername(username);
    }

    public Collection getAuthorities(int roId) throws NotFoundException {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(roId));
        return authList;
    }

    public List<String> getRoles(int roId) throws NotFoundException {

        List<String> roles = new ArrayList<String>();
        RoleDto role = roleService.getRoleById(roId);
        roles.add(role.getName());

        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}
