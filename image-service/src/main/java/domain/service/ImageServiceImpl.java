package domain.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import domain.model.Image;

@ApplicationScoped
@Transactional
@Default
public class ImageServiceImpl implements ImageService {
	
	@PersistenceContext(unitName = "ImagePU")
	private EntityManager em;

	@Override
	public Long create(Image image) {
		em.persist(image);
		// Flush to get the newly generated id
		em.flush();
		return image.getId();
	}

	@Override
	public void delete(Long imageId) {
		Image im = this.get(imageId);
		em.remove(im);
	}
	
	@Override 
	public void update(Image image, Long imageId) {
		Image oldImage = get(imageId);
		if (oldImage == null) {
			throw new IllegalArgumentException("Image does not exist : " + image.getId());
		} else {
			image.setId(oldImage.getId());
			em.merge(image);
		}
	}

	@Override
	public Image get(Long adId) {
		return em.find(Image.class, adId);
	}

}
