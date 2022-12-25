package service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import ma.entites.Compte;
import ma.metier.BanqueLocal;

@WebService
public class CompteWSSoap {
	@EJB
	private BanqueLocal service;

	@WebMethod
	public Compte addCompte(@WebParam(name = "solde") double solde) {
		Compte c = new Compte();
		c.setSolde(solde);
		c.setDateCreation(new Date());
		return service.addCompte(c);
	}

	@WebMethod
	public Compte getCompte(@WebParam(name = "code") Long code) {
		return service.getCompte(code);
	}

	@WebMethod
	public List<Compte> listComptes() {
		return service.listComptes();
	}

	public void verser(Long c, double mt) {
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
