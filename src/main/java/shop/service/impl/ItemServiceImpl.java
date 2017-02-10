package shop.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shop.dto.filter.ItemFilter;
import shop.dto.form.ItemForm;
import shop.entity.Item;
import shop.repository.ItemRepository;
import shop.service.FileWriter;
import shop.service.FileWriter.Folder;
import shop.service.ItemService;
import shop.service.specification.ItemSpecification;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemRepository repository;
	@Autowired
	private FileWriter fileWriter;

	@Override
	public ItemForm findItem(long id) {
		ItemForm item = new ItemForm();
		Item entity = repository.findOne(id);
		item.setId(entity.getId());
		item.setName(entity.getName());
		item.setPrice(String.valueOf(entity.getPrice()));
		return item;
	}

	@Override
	public Page<Item> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	@PostConstruct
	public List<Item> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(Item item) {
		repository.save(item);
	}

	@Override
	public void save(ItemForm item) {
		System.out.println(item.getImage().getOriginalFilename());
		Item entity = new Item();
		fileWriter.write(Folder.ITEM, item.getImage());
		entity.setImage(item.getImage().getOriginalFilename());
		entity.setPrice(BigDecimal.valueOf(Double.valueOf(item.getPrice())));
		entity.setName(item.getName());
		entity.setProducer(item.getProducer());
		repository.save(entity);
	}

	@Override
	public void delete(long id) {
		repository.delete(id);
	}

	@Override
	public Item findOne(String name) {
		return repository.findByName(name);
	}

	@Override
	public Page<Item> findAll(ItemFilter filter, Pageable pageable) {
		return repository.findAll(new ItemSpecification(filter), pageable);
	}

	@Override
	public Item findOne(long id) {
		return repository.findOne(id);
	}

	@Override
	public void update(ItemForm item) {
		fileWriter.write(Folder.ITEM, item.getImage());
		Item entity = findOne(item.getId());
		entity.setImage(item.getImage().getOriginalFilename());
		entity.setPrice(BigDecimal.valueOf(Double.valueOf(item.getPrice())));
		entity.setName(item.getName());
		entity.setProducer(item.getProducer());
		repository.save(entity);
		
	}

}
