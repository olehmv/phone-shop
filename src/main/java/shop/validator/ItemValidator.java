package shop.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import shop.dto.form.ItemForm;


public class ItemValidator implements Validator{
	
	private final static Pattern PATTERN = Pattern.compile("^([0-9]{1,18}\\.[0-9]{0,2})|([0-9]{1,18}\\,[0-9]{0,2})$");
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ItemForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ItemForm item = (ItemForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "producer", "", "Can`t be empty");
		if(!PATTERN.matcher(String.valueOf(item.getPrice())).matches()){
			errors.rejectValue("price", "", "Wrong format, only 2 digits after separator");
		}
		
	}

}
