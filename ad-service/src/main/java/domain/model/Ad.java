package domain.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="ADS")
@Data
public class Ad implements Serializable {
	
	private static final long serialVersionUID = 7543806998039911375L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AD_ID")
	private Long id;
	
	@Column(name="TITLE")
	@NotNull
	private String title;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="PRICE")
	private double price;
	
	@Column(name="DATE")
	@NotNull
	private String date;
	
	@Column(name="CATEGORY_ID")
	@NotNull
	private Long categoryId;
	
	@Column(name="USER_ID")
	@NotNull
	private Long userId;
	
	@ElementCollection
	private List<Long> imageIds;
	
	// Specific setter for date to have a pretty JSON string date format
	public void setDate(Date date) {
		String pattern = "dd/MM/yyyy HH:mm";
		DateFormat df = new SimpleDateFormat(pattern);
		
		this.date = df.format(date);
	}

}
