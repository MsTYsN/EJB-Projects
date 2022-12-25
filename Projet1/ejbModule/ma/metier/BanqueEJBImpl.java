package ma.metier;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ma.entites.Compte;

@Stateless(name = "Banque")
public class BanqueEJBImpl implements BanqueLocal, BanqueRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	@PermitAll
	public Compte addCompte(Compte c) {
		em.persist(c);
		return c;
	}

	@Override
	@PermitAll
	public Compte getCompte(Long code) {
		Compte cm = em.find(Compte.class, code);
		if (cm == null)
			throw new RuntimeException("Compte introvable");
		return cm;
	}

	@Override
	@PermitAll
	public List<Compte> listComptes() {
		Query query = em.createQuery("from Compte");
		return query.getResultList();
	}

	@Override
	@PermitAll
	public void verser(Long c, double mt) {
		Compte compte = getCompte(c);
		compte.setSolde(compte.getSolde() + mt);
	    em.persist(compte);
	}

	@Override
	@PermitAll
	public void retirer(Long c, double mt) {
		Compte compte = getCompte(c);
		if(compte != null) {
			if(compte.getSolde() - mt > 0) {
				compte.setSolde(compte.getSolde() - mt);
				em.persist(compte);
			}else 
				throw new RuntimeException("solde insuffisant");
		}

	}

	@Override
	@PermitAll
	public void virement(Long c1, Long c2, double mt) {
		retirer(c1, mt);
		verser(c2, mt);
	
	}



}
