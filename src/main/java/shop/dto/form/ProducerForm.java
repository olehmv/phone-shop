package shop.dto.form;

import java.util.HashSet;
import java.util.Set;

import shop.entity.Category;
import shop.entity.Item;

public class ProducerForm {

	private Long id;
	private String name;
	private Category category;
	Set<Item> items = new HashSet<>();

	public ProducerForm() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
