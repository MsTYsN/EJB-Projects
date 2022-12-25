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

import ma.entites.Monument;
import ma.metier.CreateurLocal;
import ma.metier.MonumentLocal;
import ma.metier.VilleLocal;

@Path("/")
@Stateless
public class MonumentWSRest {

	@EJB
	private MonumentLocal service;
	@EJB
	private VilleLocal serviceVille;
	@EJB
	private CreateurLocal serviceCreateur;

	@POST
	@Path("/monuments")
	@Produces(MediaType.APPLICATION_JSON)
	public Monument addMonument(Monument m) {
		return service.addMonument(m);
	}

	@GET
	@Path("/monuments/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Monument getMonument(@PathParam(value = "id") int id) {
		return service.getMonument(id);
	}

	@GET
	@Path("/monuments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Monument> listMonuments() {
		return service.listMonuments();
	}

	@PUT
	@Path("/monuments")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateMonument(@PathParam(value = "id") int id, Monument m) {
		service.updateMonument(id,m);
	}
	
	@DELETE
	@Path("/monuments/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMonument(@PathParam(value = "id") int id) {
		service.deleteMonument(id);
	}
	
	@GET
	@Path("/monuments/search/{nom}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Monument> listMonumentsParNom(@PathParam(value = "nom") String nom) {
		return service.listMonumentsParNom(nom);
	}
}
