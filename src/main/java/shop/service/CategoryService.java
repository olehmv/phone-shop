package shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import shop.dto.filter.BasicFilter;
import shop.entity.Category;

public interface CategoryService {
	Category findOne(String name);
	Category findOne(long id);
	List<Category>findAll();
	Page<Category> findAll(BasicFilter filter, Pageable pageable);
	void delete(long id);
	void save(Category category);

}
