package gr.aueb.cf.fnlprojecttecheshop.validator;

import gr.aueb.cf.fnlprojecttecheshop.dto.ProductUpdateDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductUpdateValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductUpdateDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductUpdateDTO productUpdateDTO = (ProductUpdateDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "empty");
        if (productUpdateDTO.getProductName().length() < 3 || productUpdateDTO.getProductName().length() > 300) {
            errors.reject("productName", "size");
        }

    }
}
