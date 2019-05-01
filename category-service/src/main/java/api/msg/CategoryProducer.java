package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import domain.model.Category;
import domain.service.CategoryService;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class CategoryProducer {
	
	@Producer
	private SimpleKafkaProducer<String, Category> producer;
	
	@Inject
	private CategoryService categoryService;
	
	public void sendAllCategories(String topic) {
		log.info("Send the current state of ALL categories to the topic " + topic);
		for (Category category : categoryService.getAll()) {
			producer.send(topic, category);	
		}
	}

	public void send(Category category, String topic) {
		log.info("Send the state of a category to the topic " + topic + " with id " + category.getId() );
		producer.send(topic, category);			
	}

	public void send(Long categoryId, String topic) {
		Category category = categoryService.get(categoryId);
		if (category != null) {
			this.send(category, topic);
			log.info("Send the state of a category to the topic " + topic + " with id " + categoryId);
		}
	}

}