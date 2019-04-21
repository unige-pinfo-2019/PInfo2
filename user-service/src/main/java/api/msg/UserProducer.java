package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import domain.model.User;
import domain.service.UserService;

@ApplicationScoped
@KafkaConfig(bootstrapServers="#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
public class UserProducer {
	
	@Producer
	private SimpleKafkaProducer<String, User>producer;
	
	@Inject
	private UserService userService;
	
//	public void sendAllAds(String topic) {
//		log.info("Send the current state of ALL users to the topic " + topic);
//		for (User user : userService.getAll()) {
//			producer.send(topic, user);	
//		}
//	}

	public void send(User user, String topic) {
		log.info("Send the state of an user to the topic " + topic + " with id " + ad.getId() );
		producer.send(topic, user);			
	}

	public void send(Long userId, String topic) {
		log.info("Send the state of an ad to the topic " + topic + " with id " + adId);
		User user = userService.get(userId);
		if (user != null) {
			this.send(user, topic);
		}
	}
}
