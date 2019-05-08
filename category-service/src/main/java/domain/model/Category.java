package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="CATEGORIES")
@Data
public class Category implements Serializable {

	private static final long serialVersionUID = -8677180318520117547L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CATEGORY_ID")
	private Long id;
	
	@Column(name="NAME")
	@NotNull
	private String name;
	
	@Column(name="PARENT")
	private Long parentId;
	
}
