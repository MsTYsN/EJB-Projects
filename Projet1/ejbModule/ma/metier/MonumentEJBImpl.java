package ma.metier;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ma.entites.Monument;
import ma.entites.Image;

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
		Query query = em.createQuery("from Image i where i.monument = :monument");
		query.setParameter("monument", m);
		List<Image> images = new ArrayList<>();
		for(Object o : query.getResultList()) {
			images.add(new Image(((Image)o).getId(), ((Image)o).getUrl()));
		}
		m.setImages(images);
		return m;
	}

	@Override
	@PermitAll
	public List<Monument> listMonuments() {
		List<Monument> monuments = new ArrayList<>();
		Query query = em.createQuery("from Monument");
		for(Object o : query.getResultList()) {
			Monument m = (Monument)o;
			Query queryImages = em.createQuery("from Image i where i.monument = :monument");
			queryImages.setParameter("monument", m);
			List<Image> images = new ArrayList<>();
			for(Object i : queryImages.getResultList()) {
				images.add(new Image(((Image)i).getId(), ((Image)i).getUrl()));
			}
			m.setImages(images);
			monuments.add(m);
		}
		return monuments;
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
