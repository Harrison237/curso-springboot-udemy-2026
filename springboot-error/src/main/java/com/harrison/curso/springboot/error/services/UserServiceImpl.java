package com.harrison.curso.springboot.error.services;

import com.harrison.curso.springboot.error.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private List<User> users;

    public UserServiceImpl() {
/*        this.users = Arrays.asList(
                new User(1L, "Pepe", "Gonzalez", null),
                new User(2L, "Andres", "Mena", null),
                new User(3L, "María", "Perez", null),
                new User(4L, "Josefa", "Ramirez", null),
                new User(5L, "Ale", "Gutierrez", null)
        );*/
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
/*        User user = users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);

        return Optional.ofNullable(user);*/

        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }
}
