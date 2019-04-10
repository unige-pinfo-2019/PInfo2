package api;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import domain.model.Category;
import domain.service.CategoryService;

@ApplicationScoped
@Path("/category")
public class CategoryRestService {
	
	@Inject
	private CategoryService categoryService;
	
	@GET
	@Produces("application/json")
	public List<Category> getAll() {
		return categoryService.getAll();
	}
	
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public List<Category> getSubCategories(@PathParam("id") Long id) {
		return categoryService.getSubCategories(categoryService.get(id));
	}
	
	
	@POST
	@Consumes("application/json")
	public Response create(Category category) {
		try {
			categoryService.create(category);
		} catch(IllegalArgumentException i) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
	
	
	@DELETE
	@Path("{name}")
	public Response delete(@PathParam("name") Long id) {
		categoryService.delete(categoryService.get(id));
		return Response.ok().build();
	}


}
