package shop.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import shop.entity.Category;
import shop.entity.Item;
import shop.entity.Producer;
import shop.entity.Role;
import shop.entity.User;
import shop.repository.CategoryRepository;
import shop.repository.UserRepository;
import shop.service.FileWriter;
import shop.service.FileWriter.Folder;
import shop.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private FileWriter writer;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return repository.findByUsername(userName);
	}

	@Override
	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER);
		repository.save(user);
	}

	@Override
	public User findUserByEmail(String email) {
		return repository.findUserByEmail(email);
	}

	@PostConstruct
	public void admin() throws IOException {
		User user = repository.findByUsername("admin");
		if (user == null) {
			user = new User();
			user.setEmail("");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			user.setUsername("admin");
			repository.save(user);
		}
		File[] files = new File(System.getProperty("catalina.home") + "\\wtpwebapps\\shop\\resources\\images")
				.listFiles();
		Category category =categoryRepository.findByName("phones");
		if(category==null){
			category=new Category();
			category.setName("phones");
		}
		if(!category.getProducers().isEmpty()){
			System.out.println("not empty");
			return;
		}
		for (File file : files) {
			String name = file.getName();
			byte[] bytes = Files.readAllBytes(file.toPath());
			MultipartFile result = new MockMultipartFile(name, name, "image/jpeg", bytes);
			writer.write(Folder.ITEM, result);
			Item item = new Item();
			item.setImage(name);
			item.setName(name.split("jpg")[0]);
			Random r = new Random();
			int Low = 10;
			int High = 100;
			int Result = r.nextInt(High-Low) + Low;
			item.setPrice(new BigDecimal(Result));
			Producer producer=new Producer();
			producer.setName(name.split("-")[0]);
			if(!category.getProducers().contains(producer)){
				category.getProducers().add(producer);
				producer.setCategory(category);
			}
			Iterator<Producer> itr = category.getProducers().iterator();
			while (itr.hasNext()) {
				producer = itr.next();
				if (producer.getName().equals(item.getName().split("-")[0])) {
					producer.getItems().add(item);
					item.setProducer(producer);
				} 
				
			}
		}
		categoryRepository.save(category);
	}
}
