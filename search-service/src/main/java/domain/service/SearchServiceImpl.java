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
	public <T extends Searchable> List<T> match(String query, Class<T> type) {
		// Sanity check
		if (query.isEmpty()) {
			return new ArrayList<>();
		}
		
		SearchRequest searchRequest = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		
		if (type.isInstance(new Ad())) {
			searchSourceBuilder = adQueryBuilder(query);
		} else {
			searchSourceBuilder = defaultQueryBuilder(query);
		}
		
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
		
		log.info("Matched " + matchedList.size() + " items with query \'" + query + "\'.");
		
		return matchedList;
	}
	
	public SearchSourceBuilder adQueryBuilder(String query) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		
		String wildcardsQuery = "*" + query.toLowerCase() + "*";
		QueryBuilder matchQueryBuilder = QueryBuilders.boolQuery()
										    .should(QueryBuilders.matchQuery("title", wildcardsQuery))
										    .should(QueryBuilders.matchQuery("description", wildcardsQuery));
		
		searchSourceBuilder.query(matchQueryBuilder);
		searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC)); 
		searchSourceBuilder.sort(new FieldSortBuilder("_id").order(SortOrder.DESC));
		
		return searchSourceBuilder;	
	}
	
	// Default query matches on all fields without an ordering policy
	public SearchSourceBuilder defaultQueryBuilder(String query) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		
		String wildcardsQuery = "*" + query.toLowerCase() + "*";
		QueryBuilder matchQueryBuilder = QueryBuilders.queryStringQuery(wildcardsQuery);
		
		searchSourceBuilder.query(matchQueryBuilder);

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
