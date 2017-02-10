package shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import shop.dto.filter.ItemFilter;
import shop.dto.form.ItemForm;
import shop.entity.Item;

public interface ItemService {
	Item findOne(String name);
	ItemForm findItem(long id);
	Item findOne(long id);

	Page<Item>findAll(Pageable pageable);
	Page<Item> findAll(ItemFilter filter, Pageable pageable);
	List<Item>findAll();
	void save(Item item);
	void delete(long id);
	void save(ItemForm item);
	void update(ItemForm item);

	

}
