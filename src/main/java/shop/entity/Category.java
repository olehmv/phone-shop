package shop.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Producer>producers=new HashSet<>();
	
	public Category() {
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
	public Set<Producer> getProducers() {
		return producers;
	}
	public void setProducers(Set<Producer> producers) {
		this.producers = producers;
	}

	
	
}
