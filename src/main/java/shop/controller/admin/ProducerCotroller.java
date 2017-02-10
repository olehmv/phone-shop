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
import shop.editor.CategoryEditor;
import shop.entity.Category;
import shop.entity.Producer;
import shop.service.CategoryService;
import shop.service.ProducerService;
import shop.validator.ProducerValidator;

@Controller
@RequestMapping("/admin")
@SessionAttributes(names = "producer")
public class ProducerCotroller {
	@Autowired
	private ProducerService producerService;
	@Autowired
	private CategoryService categoryService;

	@InitBinder("producer")
	public void setBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
		binder.setValidator(new ProducerValidator(producerService));
	}

	@ModelAttribute("producer")
	public Producer getProducer() {
		return new Producer();
	}

	@ModelAttribute("filter")
	public BasicFilter getFilter() {
		return new BasicFilter();
	}

	@GetMapping("producer")
	public String show(@PageableDefault Pageable pageable, SessionStatus status,
			@ModelAttribute("filter") BasicFilter filter, Model model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("page", producerService.findAll(filter, pageable));
		status.setComplete();
		return "admin-producer";
	}

	@PostMapping("producer")
	public String save(@ModelAttribute("producer") @Valid Producer producer, BindingResult br,
			@PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter, SessionStatus status,
			Model model) {
		if (br.hasErrors()) {
			model.addAttribute("page", producerService.findAll(filter, pageable));
			model.addAttribute("categories", categoryService.findAll());
			return "admin-producer";
		}
		producerService.save(producer);
		status.setComplete();
		return "redirect:/admin/producer" + getParams(pageable, filter);
	}

	@GetMapping("producer/delete/{id}")
	public String delete(@PathVariable("id") long id, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter) {
		producerService.delete(id);
		System.out.println("after delete");
		return "redirect:/admin/producer" + getParams(pageable, filter);
	}

	@GetMapping("producer/update/{id}")
	public String update(@PathVariable("id") long id, @PageableDefault Pageable pageable, SessionStatus status,
			@ModelAttribute("filter") BasicFilter filter, Model model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("producer", producerService.findOne(id));
		model.addAttribute("page", producerService.findAll(filter, pageable));
		return "admin-producer";
	}

}
