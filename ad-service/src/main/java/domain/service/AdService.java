package domain.service;

import java.util.List;

import domain.model.Ad;

public interface AdService {

	public Long create(Ad ad);

	public void delete(Ad ad);
	
	public void update(Ad ad);

	public List<Ad> getAll();

	public Ad get(Long adId);
}
