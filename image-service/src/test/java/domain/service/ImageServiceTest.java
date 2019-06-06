package domain.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Image;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
public class ImageServiceTest {

	@Spy
	@PersistenceContext(unitName = "ImagePUTest")
	EntityManager em;
	
	@InjectMocks
	private ImageServiceImpl imageServiceImpl;
	
	@Test
	public void testGet() {
		Image randomImage = getRandomImage();
		
		Long randomImageId = imageServiceImpl.create(randomImage);
		
		assertTrue(randomImage.equals(imageServiceImpl.get(randomImageId)));
	}
	
	@Test
	public void testCreate() {	
		Image randomImage = getRandomImage();
		
		Long randomImageId = imageServiceImpl.create(randomImage);
		
		Image i = em.find(Image.class, randomImageId);
		
		assertTrue(randomImage.equals(i));
	}
	
	@Test
	public void testDelete() {
		
		Image randomImage = getRandomImage();
		imageServiceImpl.create(randomImage);
		imageServiceImpl.delete(randomImage.getId());
		
		Image i = em.find(Image.class, randomImage.getId());
		
		assertTrue(i == null);
	}
	
	@Test
	public void testUpdate() {
		
		Image img1 = getRandomImage();
		imageServiceImpl.create(img1);
		Image img2 = getRandomImage();
		img2.setId(img2.getId());
		
		imageServiceImpl.update(img2, img1.getId());
		
		Image i = em.find(Image.class, img1.getId());
		assertTrue(img2.equals(i));
	}
	
	private Image getRandomImage() {
		byte[] randomBytes = getRandomByteArray();
		
		Image randomImage = new Image();
		randomImage.setData(randomBytes);
		
		return randomImage;
	}
	
	private byte[] getRandomByteArray() {
		byte[] b = new byte[500];
		new Random().nextBytes(b);
		
		return b;
	}
}
