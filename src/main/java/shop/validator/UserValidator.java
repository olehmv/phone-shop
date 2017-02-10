package shop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import shop.entity.User;

public class UserValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(User.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","","Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","","Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","","Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","","Can't be empty");
	}

}
