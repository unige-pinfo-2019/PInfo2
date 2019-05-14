package domain.model;

import java.util.List;

import com.google.gson.Gson;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Ad implements Searchable {
	
	private Long id;
	
	private String title;
	
	private String description;
	
	private double price;
	
	private String date;
	
	private Long categoryId;
	
	private Long userId;
	
	private List<Long> imageIds;
	
	private String index = "ads";
	
	private String type = "ad";
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
