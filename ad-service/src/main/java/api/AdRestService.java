package api;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import domain.model.Ad;
import domain.service.AdService;

@Path("/ad")
public class AdRestService {
	
	
	@Inject
	private AdService adservice;
	
	
	public void setPersonservice(AdService as) {
		adservice = as;
	}

	
	@GET
	@Path("/new/{title}/{description}/{price}")
	@Produces("text/plain")
	public Response addNewAd(@PathParam("title") String title, 
			@PathParam("description") String description, @PathParam("price") double price) {
		Ad a = new Ad(title, description, price);
		if(adservice.createAd(a)) {
			return Response.ok("You inserted "+ a.toString()).build();	
		} else {
			return Response.ok("Error").build();	
		}


		
	}
	
	

	@GET
	@Path("/")
	@Produces("text/plain")
	public String getAll() {
		ArrayList<Ad> all = adservice.getAll();
		return all.stream().map(p -> p.toString()).collect(Collectors.joining("\n"));
	}
	
	
	
	@GET
	@Path("create")
	@Produces("text/plain")
	public Response create(@QueryParam("title") String title) {
		return Response.ok("Ad with title : " + title + " created.").build();
	}

}
