package api.msg;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Ad;
import domain.service.AdService;

@ExtendWith(MockitoExtension.class)
public class AdProducerTest {
	
	@Mock
	private SimpleKafkaProducer<String, Ad> kafkaProducer;
	@Mock
	private AdService adService;
	
	private String topic = "ads";

	@InjectMocks
	private AdProducer producer;
	
	@Test
	void testSendAllAds() {
		List<Ad> ads = getAds();
		when(adService.getAll()).thenReturn(ads);
		producer.sendAllAds(this.topic);
		verify(kafkaProducer, times(ads.size())).send(eq("ads"), any(Ad.class));
	}

	@Test
	void testSendAd() {
		Ad ad = getRandomAd();
		producer.send(ad, this.topic);
		verify(kafkaProducer, times(1)).send("ads", ad);
	}

	@Test
	void testSendLong() {
		Ad ad = getRandomAd();
		when(adService.get(ad.getId())).thenReturn(ad);
		producer.send(ad.getId(), this.topic);
		verify(kafkaProducer, times(1)).send("ads", ad);
	}

	@Test
	void testSendLongNull() {
		Ad ad = getRandomAd();
		when(adService.get(ad.getId())).thenReturn(null);
		producer.send(ad.getId(), this.topic);
		verify(kafkaProducer, times(0)).send("ads", ad);
	}
	
	private List<Ad> getAds() {

		List<Ad> ads = new ArrayList<>();
		long numberOfAd = Math.round((Math.random() * 10));
		for (int i = 0; i < numberOfAd; i++) {
			ads.add(getRandomAd());
		}
		return ads;
	}
	
	private Ad getRandomAd() {
		Ad ad = new Ad();
		ad.setTitle(UUID.randomUUID().toString());
		ad.setDescription(UUID.randomUUID().toString());
		
		Random r = new Random();
		ad.setPrice(r.nextDouble());
		
		Long randomDate = -946771200000L + (Math.abs(r.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
		ad.setDate(new Date(randomDate));

		return ad;
	}
}
