package api;

import java.net.URI;
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
import javax.ws.rs.core.Response.Status;

import domain.model.Message;
import domain.service.MessageService;

@ApplicationScoped
@Path("/chat")
public class MessageRestService {
	
	@Inject
	private MessageService messageService;
	
	// !!! Comment passer en parametres les IDs des users pour update le chat ? //
	@GET
	@Produces("application/json")
	public List<Message> getChat() {
		return messageService.updateChat();
	}
	


}
