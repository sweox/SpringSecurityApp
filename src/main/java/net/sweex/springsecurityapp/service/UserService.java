package net.sweex.springsecurityapp.service;

import net.sweex.springsecurityapp.model.User;

/**
 * Service class for {@link net.sweex.springsecurityapp.model.User}
 *
 * @Author Uladzimir Razhanski
 * @version 1.0
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
