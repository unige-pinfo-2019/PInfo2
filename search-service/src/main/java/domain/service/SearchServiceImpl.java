package domain.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.http.HttpHost;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import com.google.gson.Gson;

import domain.model.Ad;
import domain.model.Searchable;
import lombok.extern.java.Log;

@ApplicationScoped
@Default
@Log
public class SearchServiceImpl implements SearchService {
	
	@Inject
	public SearchServiceImpl(@ConfigProperty(name = "ELASTICSEARCH_HOST", defaultValue = "localhost")
							 String searchHostname,
							 @ConfigProperty(name = "ELASTICSEARCH_SERVICE_PORT", defaultValue = "9200")
							 int searchPort) {
		
		this.client = new RestHighLevelClient(RestClient.builder(
		        					new HttpHost(searchHostname, searchPort, "http")));
	}

	private RestHighLevelClient client;
	
	@Override
	public void createItem(Searchable item) {
		
		IndexRequest request = new IndexRequest(
		        item.getIndex(), 
		        item.getType(),  
		        item.getId().toString());
		
		request.source(item.toJson(), XContentType.JSON);
		
		try {
			client.index(request, RequestOptions.DEFAULT);
		} catch (IOException e) {
			log.throwing(this.getClass().getName(), "createItem", e);
		}
		
		log.info("Item created : " + item);
	}
	
	@Override
	public void deleteItem(Searchable item) {
		
		DeleteRequest deleteRequest = new DeleteRequest(
		        item.getIndex(), 
		        item.getType(),  
		        item.getId().toString());	
		
		try {
			client.delete(deleteRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			log.throwing(this.getClass().getName(), "deleteItem", e);
		}
		
		log.info("Item deleted : " + item);
	}
	
	@Override
	public void updateItem(Searchable item) {
		
		UpdateRequest updateRequest = new UpdateRequest(
		        item.getIndex(), 
		        item.getType(),  
		        item.getId().toString());
		
		updateRequest.doc(item.toJson(), XContentType.JSON);
		
		try {
			client.update(updateRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			log.throwing(this.getClass().getName(), "updateItem", e);
		}
		
		log.info("Item updated : " + item);
	}
	
	@Override
	public List<Ad> matchAd(String query, Optional<Long> categoryId, Optional<Long> userId) {
		return match(adQueryBuilder(query, categoryId, userId), Ad.class);
	}
	
	
	public <T extends Searchable> List<T> match(SearchSourceBuilder searchSourceBuilder, Class<T> type) {
		
		SearchRequest searchRequest = new SearchRequest();
		
		log.info("Query created : " + searchSourceBuilder.toString());
		searchRequest.source(searchSourceBuilder);
		
		SearchHit[] searchHits = {};
		
		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHits hits = searchResponse.getHits();
			searchHits = hits.getHits();
		} catch (IOException e) {
			log.throwing(this.getClass().getName(), "match", e);
		}

		Gson gson = new Gson();
		List<T> matchedList = new ArrayList<>();
		for (SearchHit hit : searchHits) {
			T item = gson.fromJson(hit.getSourceAsString(), type);
			matchedList.add(item);
		}
		
		return matchedList;
	}
	
	public SearchSourceBuilder adQueryBuilder(String query, Optional<Long> categoryId, Optional<Long> userId) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		
		String descriptionField = "description";
		String titleField = "title";
		
		String wildcardsQuery = query.toLowerCase();
		QueryBuilder matchQueryBuilder = QueryBuilders.boolQuery()
										    .should(QueryBuilders.matchQuery(titleField, wildcardsQuery)
										    		             .fuzziness(Fuzziness.AUTO))
										    .should(QueryBuilders.matchQuery(descriptionField, wildcardsQuery)
										    		             .fuzziness(Fuzziness.AUTO));
		
		// TODO : find a way to remove those ugly if blocks
		if (categoryId.isPresent() && userId.isPresent()) {
			matchQueryBuilder = QueryBuilders.boolQuery()
			.must(QueryBuilders.termQuery("categoryId", categoryId.get()))
			.must(QueryBuilders.termQuery("userId", userId.get()))
		    .should(QueryBuilders.matchQuery(titleField, wildcardsQuery)
		    		             .fuzziness(Fuzziness.AUTO))
		    .should(QueryBuilders.matchQuery(descriptionField, wildcardsQuery)
		    		             .fuzziness(Fuzziness.AUTO));
		} else if (categoryId.isPresent()) {
			matchQueryBuilder = QueryBuilders.boolQuery()
			.must(QueryBuilders.termQuery("categoryId", categoryId.get()))
		    .should(QueryBuilders.matchQuery(titleField, wildcardsQuery)
		    		             .fuzziness(Fuzziness.AUTO))
		    .should(QueryBuilders.matchQuery(descriptionField, wildcardsQuery)
		    		             .fuzziness(Fuzziness.AUTO));
		} else if (userId.isPresent()) {
			matchQueryBuilder = QueryBuilders.boolQuery()
			.must(QueryBuilders.termQuery("userId", userId.get()))
		    .should(QueryBuilders.matchQuery(titleField, wildcardsQuery)
		    		             .fuzziness(Fuzziness.AUTO))
		    .should(QueryBuilders.matchQuery(descriptionField, wildcardsQuery)
		    		             .fuzziness(Fuzziness.AUTO));
		} else if (query.isEmpty()) {
			matchQueryBuilder = QueryBuilders.matchAllQuery();
		}
		
		searchSourceBuilder.query(matchQueryBuilder);
		searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC)); 
		searchSourceBuilder.sort(new FieldSortBuilder("_id").order(SortOrder.DESC));
		
		return searchSourceBuilder;	
	}
	
	@PreDestroy
    public void cleanup() {
        try {
            log.info("Closing the ES REST client");
            this.client.close();
        } catch (IOException e) {
            log.throwing(this.getClass().getName(), "cleanup", e);
        }
    }
	
	
}
