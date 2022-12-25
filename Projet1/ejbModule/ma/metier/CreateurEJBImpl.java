package ma.metier;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ma.entites.Createur;

@Stateless(name = "Createur")
public class CreateurEJBImpl implements CreateurLocal, CreateurRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	@PermitAll
	public Createur addCreateur(Createur c) {
		em.persist(c);
		return c;
	}

	@Override
	@PermitAll
	public Createur getCreateur(int id) {
		Createur c = em.find(Createur.class, id);
		if (c== null)
			throw new RuntimeException("Createur introvable");
		return c;
	}

	@Override
	@PermitAll
	public List<Createur> listCreateurs() {
		Query query = em.createQuery("from Createur");
		return query.getResultList();
	}

	@Override
	@PermitAll
	public void updateCreateur(int id, Createur c) {
		Createur cr = getCreateur(id);
		if(cr != null) {
			cr.setNom(c.getNom());
			cr.setPrenom(c.getPrenom());
			cr.setDebut(c.getDebut());
			cr.setFin(c.getFin());
			cr.setDescription(c.getDescription());
		    em.persist(cr);	
		}
	}

	@Override
	@PermitAll
	public void deleteCreateur(int id) {
		Createur c = getCreateur(id);
		em.remove(c);
	}

	@Override
	@PermitAll
	public List<Createur> listCreateursParNom(String nom) {
		Query query = em.createQuery("from Createur c where c.nom like :nom");
		query.setParameter("nom", "%" + nom + "%");
		return query.getResultList();
	}


}
