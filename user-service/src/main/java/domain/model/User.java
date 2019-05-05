package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="USERS")
@Data
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4226055603113841802L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private Long id;
	
	@Column(name="USERNAME")
	@NotNull
	private String username;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="RATING")
	@Min(0)
	@Max(5)
	private int rating;
	

	
	
	
	
}
