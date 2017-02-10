package shop.controller.admin;

import static shop.service.utils.ParamBuilder.getParams;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import shop.dto.filter.BasicFilter;
import shop.entity.Category;
import shop.service.CategoryService;
import shop.validator.CategoryValidator;

@Controller
@RequestMapping("/admin")
@SessionAttributes(names = "category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}

	@ModelAttribute("filter")
	public BasicFilter getFilter() {
		return new BasicFilter();
	}

	@InitBinder("category")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new CategoryValidator(categoryService));
	}

	@GetMapping("category")
	public String show(SessionStatus status, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter) {
		model.addAttribute("page", categoryService.findAll(filter, pageable));
		status.setComplete();
		return "admin-category";
	}

	@PostMapping("category")
	public String save(@ModelAttribute("category") @Valid Category category, BindingResult br, SessionStatus status,
			Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
		if (br.hasErrors()) {
			model.addAttribute("page", categoryService.findAll(filter, pageable));
			return "admin-category";
		}
		categoryService.save(category);
		status.setComplete();
		return "redirect:/admin/category" + getParams(pageable, filter);
	}

	@GetMapping("category/delete/{id}")
	public String delete(@PathVariable("id") long id, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter) {
		categoryService.delete(id);
		return "redirect:/admin/category" + getParams(pageable, filter);
	}

	@GetMapping("category/update/{id}")
	public String update(@PathVariable("id") int id, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter, Model model) {
		model.addAttribute("category", categoryService.findOne(id));
		model.addAttribute("page", categoryService.findAll(filter, pageable));
		return "admin-category";
	}

}
