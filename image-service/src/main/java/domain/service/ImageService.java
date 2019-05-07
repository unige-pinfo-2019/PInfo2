package domain.service;

import domain.model.Image;

public interface ImageService {

	public Long create(Image image);

	public void delete(Long imageId);
	
	public void update(Image image, Long imageId);

	public Image get(Long adId);

}
