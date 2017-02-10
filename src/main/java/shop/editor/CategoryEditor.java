package shop.editor;

import java.beans.PropertyEditorSupport;

import shop.entity.Category;
import shop.service.CategoryService;

public class CategoryEditor extends PropertyEditorSupport {

	private final CategoryService categoryService;

	public CategoryEditor(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Category category = categoryService.findOne(Long.valueOf(text));
		setValue(category);
	}
}
