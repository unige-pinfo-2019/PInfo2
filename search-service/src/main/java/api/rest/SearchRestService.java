package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import domain.service.SearchService;

@ApplicationScoped
@Path("/search")
public class SearchRestService {
	@Inject
	private SearchService searchService;
	
	@GET
	@Path("ad")
	@Produces("application/json")
	public List<String> searchAd(@QueryParam("q") String query) throws IOException {
		
		List<String> titleHits = searchService.match("title", query);
		List<String> descriptionHits = searchService.match("description", query);
		
		List<String> allHits = new ArrayList<String>(titleHits);
		allHits.addAll(descriptionHits);
		
		return allHits;
	}

}
