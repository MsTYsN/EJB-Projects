package ma.metier;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ma.entites.Ville;

@Stateless(name = "Ville")
public class VilleEJBImpl implements VilleLocal, VilleRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	@PermitAll
	public Ville addVille(Ville v) {
		em.persist(v);
		return v;
	}

	@Override
	@PermitAll
	public Ville getVille(int id) {
		Ville v = em.find(Ville.class, id);
		if (v == null)
			throw new RuntimeException("Ville introvable");
		return v;
	}

	@Override
	@PermitAll
	public List<Ville> listVilles() {
		Query query = em.createQuery("from Ville");
		return query.getResultList();
	}

	@Override
	@PermitAll
	public void updateVille(int id, Ville v) {
		Ville vi = getVille(id);
		if(vi != null) {
			vi.setNom(v.getNom());
		    em.persist(vi);	
		}
	}

	@Override
	@PermitAll
	public void deleteVille(int id) {
		Ville v = getVille(id);
		em.remove(v);
	}

	@Override
	@PermitAll
	public List<Ville> listVillesParNom(String nom) {
		Query query = em.createQuery("from Ville v where v.nom like :nom");
		query.setParameter("nom", "%" + nom + "%");
		return query.getResultList();
	}


}
