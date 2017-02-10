package shop.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import shop.dto.filter.ItemFilter;
import shop.entity.Item;

public class ItemSpecification implements Specification<Item>{
	
	private final ItemFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	public ItemSpecification(ItemFilter filter) {
		this.filter = filter;
	}
	
	private void filterByProducer(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!filter.getProducerIds().isEmpty()){
			predicates.add(root.get("producer").in(filter.getProducerIds()));
		}
	}
	
	private void filterByPrice(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMax()!=null&&filter.getMin()!=null){
			predicates.add(cb.between(root.get("price"), filter.getMin(), filter.getMax()));
		}else if(filter.getMax()!=null){
			predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMax()));
		}else if(filter.getMin()!=null){
			predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMin()));
		}
	}
	
	private void filterBySearch(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearch().isEmpty()){
			predicates.add(cb.like(root.get("name"), filter.getSearch()+"%"));
		}
	}
	
	private void fetch(Root<Item> root, CriteriaQuery<?> query){
		if(query.getResultType()!=Long.class){
			root.fetch("producer", JoinType.LEFT);
		}
	}

	@Override
	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		query.distinct(true);
		filterBySearch(root, query, cb);
		filterByPrice(root, query, cb);
		filterByProducer(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);
	}

	
}
