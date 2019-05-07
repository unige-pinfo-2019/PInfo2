package api.rest;

import java.io.ByteArrayInputStream;
import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import domain.model.Image;
import domain.service.ImageService;

@ApplicationScoped
@Path("/image")
public class ImageRestService {
	
	@Inject
	private ImageService imageService;
	
	private final int MAX_FILE_SIZE_MB = 10;
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@MultipartForm Image image) {
		
		Long imageId = null;
		if (image == null) {
		    return Response.status(Status.BAD_REQUEST).build();
		} 
		
		if (image.getSize() > MAX_FILE_SIZE_MB * 1024 * 1024) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		
		try {
			imageId = imageService.create(image);
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
        return Response.status(Status.CREATED).location(URI.create("/image/" + imageId.toString())).build();
	}
	
    @GET
    @Path("{id}")
    @Produces({"image/png", "image/jpeg"})
    public Response getImage(@PathParam("id") Long id){     
    	try {
    		Image image = imageService.get(id);
    		byte[] imageData = image.getData();
    		
            return Response.ok(new ByteArrayInputStream(imageData)).build();
    	} catch(Exception e) {
    		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    	}     
    }
    
    @DELETE
    @Path("{id}")
    public Response deleteImage(@PathParam("id") Long id) {
    	try {
    		imageService.delete(id);

            return Response.ok().build();
    	} catch(Exception e) {
    		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    	}  
    }
    
}
