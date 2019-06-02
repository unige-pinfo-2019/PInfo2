package api.rest;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import api.msg.AdProducer;
import domain.model.Ad;
import domain.service.AdService;


@ApplicationScoped
@Path("/ad")
@Default // To be injected in the Test
@Transactional
public class AdRestService {
	
	@Inject
	private AdService adService;
	@Inject
	private AdProducer adProducer;
	
	@POST
	@Consumes("application/json")
	public Response create(Ad ad) {
		Long newId = null;
		try {
			newId = adService.create(ad);
		} catch(IllegalArgumentException i) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch(Exception e) {
			return Response.status(Status.BAD_GATEWAY).build();
		}
		
		adProducer.send(ad, "adsCreate");	
		return Response.status(Status.CREATED).location(URI.create("/ad/" + newId.toString())).build();
	}
	
	@GET
	@Produces("application/json")
	public List<Ad> getAll() {
		return adService.getAll();
	
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Ad get(@PathParam("id") Long adId) {
		try {
			return adService.get(adId);
		}
		catch(NullPointerException e) {
			return null;
		}
		
	}
	
	@PUT
	@Consumes("application/json")
	public Response update(Ad ad) {
		try {
			adService.update(ad);
			adProducer.send(ad, "adsUpdate");
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long adId) {
		try {
			adService.delete(adService.get(adId));
			adProducer.send(adId, "adsDelete");
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return Response.ok().build();
	}

}
