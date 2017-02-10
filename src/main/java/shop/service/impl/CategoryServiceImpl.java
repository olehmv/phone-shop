package shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shop.dto.filter.BasicFilter;
import shop.entity.Category;
import shop.repository.CategoryRepository;
import shop.service.CategoryService;
import shop.service.specification.CategorySpecification;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository repository;

	@Override
	public Category findOne(long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Category> findAll() {
		return repository.findAll();
	}

	@Override
	public void delete(long id) {
		repository.delete(id);
	}

	@Override
	public void save(Category category) {
		repository.save(category);
	}

	@Override
	public Category findOne(String name) {
		return repository.findByName(name);
	}

	@Override
	public Page<Category> findAll(BasicFilter filter, Pageable pageable) {
		return repository.findAll(new CategorySpecification(filter), pageable);
	}

}
