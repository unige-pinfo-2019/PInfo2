package domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class Ad {
	private String title;
	private String description;
	private double price;
	private Date date;
	
	// private Category category;
	// private ArrayList<BufferedImage> photos
}
