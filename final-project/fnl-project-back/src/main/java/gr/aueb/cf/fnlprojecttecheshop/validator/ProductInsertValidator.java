package gr.aueb.cf.fnlprojecttecheshop.validator;

import gr.aueb.cf.fnlprojecttecheshop.dto.ProductInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductInsertValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {return ProductInsertDTO.class == clazz; }

    @Override
    public void validate(Object target, Errors errors) {
        ProductInsertDTO productInsertDTO = (ProductInsertDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "empty");
        if (productInsertDTO.getProductName().length() < 3 || productInsertDTO.getProductName().length() > 300) {
            errors.reject("productName", "size");
        }

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "empty");
    }
}
