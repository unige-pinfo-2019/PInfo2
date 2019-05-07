package api.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import api.msg.UserProducer;
import domain.model.User;
import domain.service.UserService;

@ApplicationScoped
@Path("/user")
public class UserRestService {
	
	@Inject
	private UserService userService;
	@Inject
	private UserProducer userProducer;
	
	@POST
	@Consumes("application/json")
	public Response create(User user) {
		try {
			userService.create(user);
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		userProducer.send(user, "usersCreate");
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long userId) {
		userService.delete(userService.get(userId));
		userProducer.send(userId, "userDeleted");
		return Response.ok().build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response update(User user) {
		userService.update(user);
		userProducer.send(user, "userUpdate");
		return Response.ok().build();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public User get(@PathParam("id") Long userId) {
		return userService.get(userId);
	}
	
	@GET
	@Produces("application/json")
	public List<User> getAll(){
		return userService.getAll();
	}
	
	
	
	
	
	

}
