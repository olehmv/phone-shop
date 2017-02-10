package shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import shop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> ,JpaSpecificationExecutor<Category>{
	Category findByName(String name);

}
