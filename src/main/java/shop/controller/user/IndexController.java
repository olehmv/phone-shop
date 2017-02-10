package shop.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import shop.dto.filter.ItemFilter;
import shop.dto.form.ItemForm;
import shop.entity.User;
import shop.service.ItemService;
import shop.service.ProducerService;
import shop.service.UserService;
import shop.validator.UserValidator;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private UserService userService;
	@Autowired
	private ItemService itemService;
	@Autowired
	ProducerService producerService;
	@Autowired
	UserDetailsService userDetailsService;
	@InitBinder("user")
	public void initBinder(WebDataBinder br){
		br.setValidator(new UserValidator());
		
	}
	@ModelAttribute("item")
	public ItemForm getItem() {
		return new ItemForm();
	}
	@ModelAttribute("user")
	public User getUser() {
		return new User();
	}
	
	@ModelAttribute("filter")
	public ItemFilter getFilter() {
		return new ItemFilter();
	}
	@GetMapping
	public String show(Model model, SessionStatus status, @PageableDefault Pageable pageable, ItemFilter filter) {
		model.addAttribute("producers", producerService.findAll());
		 model.addAttribute("page",itemService.findAll(filter, pageable));
		 status.setComplete();
		return "user-index";
	}



	@GetMapping("login")
	public String logIn(SessionStatus status) {
		return "user-login";
	}
	
	@GetMapping("registration")
	public String registration() {
		return "user-registration";

	}

	@PostMapping("registration")
	public String registration(@ModelAttribute("user") @Valid User user, BindingResult br, SessionStatus status,
			Model model, @PageableDefault Pageable pageable, ItemFilter filter) {
		if (br.hasErrors()) {
			return "user-registration";
		}
		userService.save(user);
		model.addAttribute("user", user);
		status.setComplete();
		return show(model, status, pageable, filter);
	}
	
}
