package net.sweex.springsecurityapp.dao;

import net.sweex.springsecurityapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> // todo: long потомучто Id у нас имеет лонговое значение
 {
     public abstract User findByUsername(String username);
}
