package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/ad")
public class AdRestService {
	
	@GET
	@Path("create")
	@Produces("text/plain")
	public Response create(@QueryParam("title") String title) {
		return Response.ok("Ad with title : " + title + " created.").build();
	}

}
