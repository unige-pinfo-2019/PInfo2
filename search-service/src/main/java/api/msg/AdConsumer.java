package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import domain.model.Ad;
import domain.service.SearchService;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class AdConsumer {

	@Inject
	private SearchService searchService;
	
	@Consumer(topics = "adsCreate", groupId = "PInfo2")
	public void createAdIndex(Ad ad) {
		log.info("Consumer got following ad to create : " + ad);
		searchService.createItem(ad);
	}
	
	@Consumer(topics = "adsUpdate", groupId = "PInfo2")
	public void updateAdIndex(Ad ad) {
		log.info("Consumer got following ad to update : " + ad);
		searchService.updateItem(ad);
	}
	
	@Consumer(topics = "adsDelete", groupId = "PInfo2")
	public void deleteAdIndex(Ad ad) {
		log.info("Consumer got following ad to delete : " + ad);
		searchService.deleteItem(ad);
	}
	
}
