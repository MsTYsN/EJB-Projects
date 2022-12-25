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

import ma.entites.Createur;
import ma.metier.CreateurLocal;

@Path("/")
@Stateless
public class CreateurWSRest {

	@EJB
	private CreateurLocal service;
	
	@POST
	@Path("/createurs")
	@Produces(MediaType.APPLICATION_JSON)
	public Createur addCreateur(Createur c) {
		return service.addCreateur(c);
	}

	@GET
	@Path("/createurs/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Createur getCreateur(@PathParam(value = "id") int id) {
		return service.getCreateur(id);
	}

	@GET
	@Path("/createurs")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Createur> listCreateurs() {
		return service.listCreateurs();
	}

	@PUT
	@Path("/createurs")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateCreateur(@PathParam(value = "id") int id, Createur c) {
		service.updateCreateur(id,c);
	}
	
	@DELETE
	@Path("/createurs/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteCreateur(@PathParam(value = "id") int id) {
		service.deleteCreateur(id);
	}
	
	@GET
	@Path("/createurs/search/{nom}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Createur> listCreateursParNom(@PathParam(value = "nom") String nom) {
		return service.listCreateursParNom(nom);
	}
}
