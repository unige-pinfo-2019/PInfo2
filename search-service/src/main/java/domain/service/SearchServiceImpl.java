package domain.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import domain.model.Searchable;

@ApplicationScoped
@Default
public class SearchServiceImpl implements SearchService {

	private RestHighLevelClient client = new RestHighLevelClient(
	        RestClient.builder(
	                new HttpHost("localhost", 9200, "http"),
	                new HttpHost("localhost", 9201, "http")));
	
	@Override
	public void createItem(Searchable item) throws IOException {
		
		IndexRequest request = new IndexRequest(
		        item.getIndex(), 
		        item.getType(),  
		        item.getId().toString());
		
		request.source(item.toJson(), XContentType.JSON);
		
		client.index(request, RequestOptions.DEFAULT);
	}
	
	@Override
	public void deleteItem(Searchable item) throws IOException {
		
		DeleteRequest deleteRequest = new DeleteRequest(
		        item.getIndex(), 
		        item.getType(),  
		        item.getId().toString());	
		
		client.delete(deleteRequest, RequestOptions.DEFAULT);
		
	}
	
	@Override
	public void updateItem(Searchable item) throws IOException {
		
		UpdateRequest updateRequest = new UpdateRequest(
		        item.getIndex(), 
		        item.getType(),  
		        item.getId().toString());
		
		updateRequest.doc(item.toJson(), XContentType.JSON);
		
		client.update(updateRequest, RequestOptions.DEFAULT);
		
	}
	
	@Override 
	public List<String> match(String attribute, Object value) throws IOException {
		SearchRequest searchRequest = new SearchRequest(); 
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		
		QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(attribute, value)
                									  .fuzziness(Fuzziness.AUTO);
		
		searchSourceBuilder.query(matchQueryBuilder);
		
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		
		SearchHit[] hits = searchResponse.getHits().getHits();
		List<String> matchedList = new ArrayList<>();
		for (SearchHit hit : hits) {
			matchedList.add(hit.getSourceAsString());
		}
		
		return matchedList;
	}
	
}
