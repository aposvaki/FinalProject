package gr.aueb.cf.fnlprojecttecheshop.validator;

import gr.aueb.cf.fnlprojecttecheshop.dto.UserInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserInsertValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserInsertDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserInsertDTO userInsertDTO = (UserInsertDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "empty");
        if (userInsertDTO.getUsername().length() < 3 || userInsertDTO.getUsername().length() > 35) {
            errors.reject("userName", "size");
        }
    }
}
