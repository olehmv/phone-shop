package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findUserByEmail(String email);
	User findByUsername(String userName);
}
