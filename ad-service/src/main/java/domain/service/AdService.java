package domain.service;

import java.util.ArrayList;
import java.util.Date;

import domain.model.Ad;

public interface AdService {

	public Ad createAd(String title, String description, double price, Date date);
	
	public ArrayList<Ad> getAd(String title);
	public ArrayList<Ad> getAd(Date date);
	public ArrayList<Ad> getAll();
	
}
