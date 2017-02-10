package shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shop.dto.filter.BasicFilter;
import shop.dto.form.ProducerForm;
import shop.entity.Producer;
import shop.repository.ProducerRepository;
import shop.service.ProducerService;
import shop.service.specification.ProducerSpecification;

@Service
public class ProducerServiceImpl implements ProducerService {
	@Autowired
	private ProducerRepository repository;

	@Override
	public Producer findOne(long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Producer> findAll() {
		return repository.findAll();
	}

	@Override
	public void delete(long id) {
		repository.delete(id);
	}

	@Override
	public void save(Producer producer) {
		repository.save(producer);
	}

	public void save(ProducerForm producer) {
		Producer entity=new Producer();
		entity.setCategory(producer.getCategory());
		entity.setName(producer.getName());
		repository.save(entity);
	}

	@Override
	public Producer findOne(String name) {
		return repository.findByName(name);
	}

	@Override
	public Page<Producer> findAll(BasicFilter filter, Pageable pageable) {
		return repository.findAll(new ProducerSpecification(filter), pageable);
	}

}
