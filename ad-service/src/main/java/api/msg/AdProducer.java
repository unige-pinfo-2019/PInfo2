package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import domain.model.Ad;
import domain.service.AdService;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "kafka:9092")
@Log
public class AdProducer {
	
	@Producer
	private SimpleKafkaProducer<String, Ad> producer;
	
	@Inject
	private AdService adService;
	
	public void sendAllAds(String topic) {
		log.info("Send the current state of ALL ads to the topic " + topic);
		for (Ad ad : adService.getAll()) {
			producer.send(topic, ad);	
		}
	}

	public void send(Ad ad, String topic) {
		log.info("Send the state of an ad to the topic " + topic + " with id " + ad.getId() );
		producer.send(topic, ad);			
	}

	public void send(Long adId, String topic) {
		Ad ad = adService.get(adId);
		if (ad != null) {
			this.send(ad, topic);
			log.info("Send the state of an ad to the topic " + topic + " with id " + adId);
		}
	}

}
