package service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.faces.push.Push;
import javax.jws.WebParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ma.entites.Image;
import ma.metier.ImageLocal;
import ma.metier.MonumentLocal;

@Path("/")
@Stateless
public class ImageWSRest {

	@EJB
	private ImageLocal service;
	@EJB
	private MonumentLocal serviceMonument;

	@POST
	@Path("/images")
	@Produces(MediaType.APPLICATION_JSON)
	public Image addImage(Image i) {
		return service.addImage(i);
	}

	@GET
	@Path("/images/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Image getImage(@PathParam(value = "id") int id) {
		return service.getImage(id);
	}

	@GET
	@Path("/images")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Image> listImages() {
		return service.listImages();
	}

	@PUT
	@Path("/images")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateImage(@PathParam(value = "id") int id, Image i) {
		service.updateImage(id,i);
	}
	
	@DELETE
	@Path("/images/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteImage(@PathParam(value = "id") int id) {
		service.deleteImage(id);
	}
	
	@GET
	@Path("/images/search/{nom}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Image> listImagesParMonument(@PathParam(value = "nom") String nom) {
		return service.listImagesParMonument(nom);
	}
}
