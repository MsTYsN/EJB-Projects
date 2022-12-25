package service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.faces.push.Push;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ma.entites.Compte;
import ma.metier.BanqueLocal;

@Path("/")
@Stateless
public class CompteWSRest {

	@EJB
	private BanqueLocal service;

	@POST
	@Path("/comptes")
	@Produces(MediaType.APPLICATION_JSON)
	public Compte addCompte(@FormParam(value = "solde") double solde) {
		Compte c = new Compte();
		c.setSolde(solde);
		c.setDateCreation(new Date());
		return service.addCompte(c);
	}

	@GET
	@Path("/comptes/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Compte getCompte(@PathParam(value = "code") Long code) {
		return service.getCompte(code);
	}

	@GET
	@Path("/comptes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Compte> listComptes() {
		return service.listComptes();
	}

	@PUT
	@Path("/comptes/verser")
	@Produces(MediaType.APPLICATION_JSON)
	public void verser(@FormParam(value = "code") Long c, @FormParam(value = "montant") double mt) {
		service.verser(c, mt);
	}

	public void retirer(Long c, double mt) {
		service.retirer(c, mt);
	}

	public void virement(Long c1, Long c2, double mt) {
		service.virement(c1, c2, mt);
	}

//	public void delete(Compte c) {
//		service.delete(c);
//	}
}
