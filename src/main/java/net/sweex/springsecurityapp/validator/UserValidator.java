package net.sweex.springsecurityapp.validator;

import net.sweex.springsecurityapp.model.User;
import net.sweex.springsecurityapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link net.sweex.springsecurityapp.model.User} class,
 * implement {@link Validator} interface.
 *
 * @author Uladzimir Razhanski
 * @version 1.0
 */

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "username", "Required");
        if (user.getUsername().length() < 8 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if(userService.findByUsername(user.getUsername()) != null) { // если есть в нашей БД пользователь с таким username
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        // если ничего не ввели, то будет сообщение, что данное поле является обязательным
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        // если не идентичны новый пароль и подтверждение
        // confirmPassword находится в model.User - transient private String confirmPassword
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
