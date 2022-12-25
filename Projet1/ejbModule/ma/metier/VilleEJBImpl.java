package ma.metier;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ma.entites.Monument;
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
		Query query = em.createQuery("from Monument m where m.ville = :ville");
		query.setParameter("ville", v);
		List<Monument> monuments = new ArrayList<>();
		for(Object o : query.getResultList()) {
			monuments.add(new Monument(((Monument)o).getId(), ((Monument)o).getNom(), ((Monument)o).getAdresse(), ((Monument)o).getDescription(), ((Monument)o).getLatitude(), ((Monument)o).getLongitude()));
		}
		v.setMonuments(monuments);
		return v;
	}

	@Override
	@PermitAll
	public List<Ville> listVilles() {
		List<Ville> villes = new ArrayList<>();
		Query query = em.createQuery("from Ville");
		for(Object o : query.getResultList()) {
			Ville v = (Ville)o;
			Query queryMonuments = em.createQuery("from Monument m where m.ville = :ville");
			queryMonuments.setParameter("ville", v);
			List<Monument> monuments = new ArrayList<>();
			for(Object i : queryMonuments.getResultList()) {
				monuments.add(new Monument(((Monument)i).getId(), ((Monument)i).getNom(), ((Monument)i).getAdresse(), ((Monument)i).getDescription(), ((Monument)i).getLatitude(), ((Monument)i).getLongitude()));
			}
			v.setMonuments(monuments);
			villes.add(v);
		}
		return villes;
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
