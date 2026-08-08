package com.harrison.curso.springboot.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harrison.curso.springboot.app.entities.Role;
import com.harrison.curso.springboot.app.entities.User;
import com.harrison.curso.springboot.app.repositories.RoleRepository;
import com.harrison.curso.springboot.app.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            @Autowired UserRepository repository,
            @Autowired RoleRepository roleRepository,
            @Autowired PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) this.repository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        optionalRoleUser.ifPresent(roles::add);

        if (user.getAdmin() != null && user.getAdmin().booleanValue()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        User userToSave = new User(user.getId(), user.getUsername(), passwordEncoder.encode(user.getPassword()),
                user.getEnabled(), roles,
                user.getAdmin());
        return repository.save(userToSave);
    }

}
