package api;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import domain.model.Ad;
import domain.service.AdService;

@ApplicationScoped
@Path("/ad")
public class AdRestService {
	
	@Inject
	private AdService adService;
	
	@POST
	@Consumes("application/json")
	public Response create(Ad ad) {
		try {
			adService.create(ad);
		} catch(IllegalArgumentException i) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
	
	@GET
	@Produces("application/json")
	public List<Ad> getAll() {
		return adService.getAll();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Ad get(@QueryParam("id") Long adId) {
		return adService.get(adId);
	}
	
	@PUT
	@Consumes("application/json")
	public Response update(Ad ad) {
		adService.update(ad);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(Long adId) {
		adService.delete(adService.get(adId));
		return Response.ok().build();
	}

}
