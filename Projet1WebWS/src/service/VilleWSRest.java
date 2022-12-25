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

import ma.entites.Ville;
import ma.metier.VilleLocal;

@Path("/")
@Stateless
public class VilleWSRest {

	@EJB
	private VilleLocal service;

	@POST
	@Path("/villes")
	@Produces(MediaType.APPLICATION_JSON)
	public Ville addVille(Ville v) {
		return service.addVille(v);
	}

	@GET
	@Path("/villes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ville getVille(@PathParam(value = "id") int id) {
		return service.getVille(id);
	}

	@GET
	@Path("/villes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ville> listVilles() {
		return service.listVilles();
	}

	@PUT
	@Path("/villes")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateCreateur(@PathParam(value = "id") int id, Ville v) {
		service.updateVille(id,v);
	}
	
	@DELETE
	@Path("/villes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteVille(@PathParam(value = "id") int id) {
		service.deleteVille(id);
	}
	
	@GET
	@Path("/villes/search/{nom}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ville> listVillesParNom(@PathParam(value = "nom") String nom) {
		return service.listVillesParNom(nom);
	}
}
