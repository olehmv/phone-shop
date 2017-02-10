package shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import shop.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> ,JpaSpecificationExecutor<Item>{
	Item findByName(String name);
	Page<Item> findAll(Pageable pageable);

}
