package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ad")
public class AdRestService {
	
	@GET
	@Path("create")
	@Produces(MediaType.TEXT_PLAIN)
	public String createAd(@PathParam("title") String name) {
		return "Ad created with title : " + name;
	}

}
