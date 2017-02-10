package shop.dto.form;

import java.sql.Clob;

import org.springframework.web.multipart.MultipartFile;

import shop.entity.Producer;

public class ItemForm {
	
	private long id;
	private String name;
	private Clob description;
	private String price;
	private Producer producer;
	private MultipartFile image;
	public ItemForm() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public Clob getDescription() {
		return description;
	}

	public void setDescription(Clob description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	

	

}
