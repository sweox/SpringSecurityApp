package net.sweex.springsecurityapp.service;

/**
 * Service for Sercurity
 *
 * @author Uladzimir Razhanski
 * @version 1.0
 */

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
