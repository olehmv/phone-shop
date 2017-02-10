package shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import shop.dto.filter.BasicFilter;
import shop.dto.form.ProducerForm;
import shop.entity.Producer;


public interface ProducerService {
	Producer findOne(String name);
	Producer findOne(long id);
	List<Producer>findAll();
	void delete(long id);
	void save(ProducerForm producer);
	void save(Producer producer);
	Page<Producer>findAll(BasicFilter filter,Pageable pageable);

}
