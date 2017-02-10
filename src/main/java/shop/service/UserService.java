package shop.service;

import shop.entity.User;

public interface UserService {
	void save(User user);
	User findUserByEmail(String email);
}
