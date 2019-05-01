package domain.model;

import com.google.gson.Gson;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Ad extends Searchable {
	
	private Long id;
	
	private String title;
	
	private String description;
	
	private double price;
	
	private String date;
	
	private Long categoryId;
	
	private String index = "ads";
	
	private String type = "ad";
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
