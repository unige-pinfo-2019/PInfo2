package domain.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.google.gson.Gson;

import domain.model.Searchable;
import lombok.extern.java.Log;

@ApplicationScoped
@Default
@Log
public class SearchServiceImpl implements SearchService {

	private RestHighLevelClient client = new RestHighLevelClient(
	        RestClient.builder(
	                new HttpHost("elasticsearch", 9200, "http")));
	
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
			log.throwing("SearchServiceImpl", "createItem", e);
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
			log.throwing("SearchServiceImpl", "deleteItem", e);
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
			log.throwing("SearchServiceImpl", "updateItem", e);
		}
		
		log.info("Item updated : " + item);
	}
	
	@Override 
	public <T extends Searchable> List<T> match(String query, Class<T> type) {
		// Sanity check
		if (query.isEmpty()) {
			return new ArrayList<>();
		}
		
		SearchRequest searchRequest = new SearchRequest(); 
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		
		QueryBuilder matchQueryBuilder = QueryBuilders.queryStringQuery("*" + query.toLowerCase() + "*");
		
		log.info("Query created : " + matchQueryBuilder.toString());
		
		searchSourceBuilder.query(matchQueryBuilder);
		searchRequest.source(searchSourceBuilder);
		
		SearchResponse searchResponse = null;
		
		try {
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			log.throwing("SearchServiceImpl", "match", e);
		}

		Gson gson = new Gson();
		SearchHits hits = searchResponse.getHits();
		SearchHit[] searchHits = hits.getHits();
		List<T> matchedList = new ArrayList<>();
		for (SearchHit hit : searchHits) {
			T item = gson.fromJson(hit.getSourceAsString(), type);
			matchedList.add(item);
		}
		
		log.info("Matched " + matchedList.size() + " items with query \'" + query + "\'.");
		
		return matchedList;
	}
	
	@PreDestroy
    public void cleanup() {
        try {
            log.info("Closing the ES REST client");
            this.client.close();
        } catch (IOException e) {
            log.throwing("SearchServiceImpl", "cleanup", e);
        }
    }
	
	
}
