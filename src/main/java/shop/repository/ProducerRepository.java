package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import shop.entity.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long>,JpaSpecificationExecutor<Producer> {
	Producer findByName(String name);

}
