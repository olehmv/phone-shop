package shop.controller.admin;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import shop.dto.filter.ItemFilter;
import shop.dto.form.ItemForm;
import shop.editor.CategoryEditor;
import shop.editor.ProducerEditor;
import shop.entity.Category;
import shop.entity.Producer;
import shop.service.CategoryService;
import shop.service.ItemService;
import shop.service.ProducerService;
import shop.validator.ItemValidator;

@Controller
@RequestMapping("/admin")
@SessionAttributes(names = "item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	ProducerService producerService;

	@InitBinder("item")
	protected void initBinder(WebDataBinder br) {
		br.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
		br.registerCustomEditor(Producer.class, new ProducerEditor(producerService));
		br.setValidator(new ItemValidator());
	}

	@ModelAttribute("item")
	public ItemForm getItem() {
		return new ItemForm();
	}

	@ModelAttribute("filter")
	public ItemFilter getFilter() {
		return new ItemFilter();
	}

	@GetMapping("item")
	private String show(@PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter, Model model,
			SessionStatus status) {
		model.addAttribute("page", itemService.findAll(filter, pageable));
		model.addAttribute("producers", producerService.findAll());
		status.setComplete();
		return "admin-item";
	}

	@PostMapping("item")
	public String save(@RequestPart("image") MultipartFile image,
@ModelAttribute("item") @Valid ItemForm item, BindingResult br,
			@PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter, SessionStatus status,
			Model model) throws IOException {
		if (br.hasErrors()) {
			model.addAttribute("page", itemService.findAll(filter, pageable));
			model.addAttribute("producers", producerService.findAll());
			return "admin-item";
		}
		if(itemService.findOne(item.getId())!=null){
			itemService.update(item);	
		}else{
			itemService.save(item);	
		}
		status.setComplete();
		return "redirect:/admin/item" + getParams(pageable, filter);
	}

	@GetMapping("item/delete/{id}")
	public String delete(@PathVariable("id") long id, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") ItemFilter filter) {
		itemService.delete(id);
		System.out.println("after delete");
		return "redirect:/admin/item" + getParams(pageable, filter);
	}

	@GetMapping("item/update/{id}")
	public String update(@PathVariable("id") long id, @PageableDefault Pageable pageable, 
			@ModelAttribute("filter") ItemFilter filter, Model model) {
		model.addAttribute("item", itemService.findItem(id));
		model.addAttribute("producers", producerService.findAll());
		model.addAttribute("page", itemService.findAll(filter, pageable));
		return "admin-item";
	}

	private String getParams(Pageable pageable, ItemFilter filter) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber() + 1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if (pageable.getSort() != null) {
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order) -> {
				buffer.append(order.getProperty());
				if (order.getDirection() != Direction.ASC)
					buffer.append(",desc");
			});
		}
		if (!filter.getSearch().isEmpty()) {
			buffer.append("&search=");
			buffer.append(filter.getSearch());
		}
		if (!filter.getMaxPrice().isEmpty()) {
			buffer.append("&maxPrice=");
			buffer.append(filter.getMaxPrice());
		}
		if (!filter.getMinPrice().isEmpty()) {
			buffer.append("&minPrice=");
			buffer.append(filter.getMinPrice());
		}
		for (Integer id : filter.getProducerIds()) {
			buffer.append("&producerIds=");
			buffer.append(id);
		}
		return buffer.toString();
	}

}
