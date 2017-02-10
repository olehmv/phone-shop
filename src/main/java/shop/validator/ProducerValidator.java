package shop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import shop.entity.Producer;
import shop.service.ProducerService;

public class ProducerValidator implements Validator{

	private final ProducerService producerService;

	public ProducerValidator(ProducerService producerService) {
		this.producerService = producerService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Producer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Producer producer = (Producer)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "", "Can`t be empty");
		if(producerService.findOne(producer.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
		
	}
	
	
}
