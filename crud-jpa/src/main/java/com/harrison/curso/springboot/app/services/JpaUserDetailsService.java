package com.harrison.curso.springboot.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harrison.curso.springboot.app.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public JpaUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).map(user -> {
            List<GrantedAuthority> authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    user.getEnabled(), true, true, true,
                    authorities);
        }).orElseThrow(() -> new UsernameNotFoundException(
                String.format("El nombre de usuario '%s' no existe en el sistema!", username)));
    }

}
