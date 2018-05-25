package net.sweex.springsecurityapp.service;

import net.sweex.springsecurityapp.dao.UserDao;
import net.sweex.springsecurityapp.model.Role;
import net.sweex.springsecurityapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Implenentation of {@link org.springframework.security.core.userdetails.UserDetailsService} interface
 *
 * @author Uladzimir Razhanski
 * @version 1.0
 */

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)  // todo: что такое transactional?
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username); // todo: создаем пользователя, которого мы ищем по имени пользователя в нашей БД

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        //мы добавляем в разрешения для данного пользователя все роли, которые у него хранятся в БД
        // получаем все роли, которые есть и записываем в разрешения для нашего пользователя
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), grantedAuthorities); //todo: разобраться
    }
}
