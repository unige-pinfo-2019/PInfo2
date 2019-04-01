package domain.service;

import java.util.ArrayList;
import java.util.Date;

import domain.model.Ad;

public interface AdService {

	public Ad create(String title, String description, double price, Date date);
	
	public ArrayList<Ad> get(String title);
	public ArrayList<Ad> get(Date date);
	public ArrayList<Ad> getAll();
}
