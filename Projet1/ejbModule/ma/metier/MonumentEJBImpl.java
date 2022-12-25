package ma.metier;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ma.entites.Monument;

@Stateless(name = "Monument")
public class MonumentEJBImpl implements MonumentLocal, MonumentRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	@PermitAll
	public Monument addMonument(Monument m) {
		em.persist(m);
		return m;
	}

	@Override
	@PermitAll
	public Monument getMonument(int id) {
		Monument m = em.find(Monument.class, id);
		if (m== null)
			throw new RuntimeException("Monument introvable");
		return m;
	}

	@Override
	@PermitAll
	public List<Monument> listMonuments() {
		Query query = em.createQuery("from Monument");
		return query.getResultList();
	}

	@Override
	@PermitAll
	public void updateMonument(int id, Monument m) {
		Monument mo = getMonument(id);
		if(mo != null) {
			mo.setNom(m.getNom());
			mo.setAdresse(m.getAdresse());
			mo.setDescription(m.getDescription());
			mo.setVille(m.getVille());
			mo.setCreateur(m.getCreateur());
			mo.setLatitude(m.getLatitude());
			mo.setLongitude(m.getLongitude());
		    em.persist(mo);	
		}
	}

	@Override
	@PermitAll
	public void deleteMonument(int id) {
		Monument m = getMonument(id);
		em.remove(m);
	}

	@Override
	@PermitAll
	public List<Monument> listMonumentsParNom(String nom) {
		Query query = em.createQuery("from Monument m where m.nom like :nom");
		query.setParameter("nom", "%" + nom + "%");
		return query.getResultList();
	}

}
