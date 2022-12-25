package ma.metier;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ma.entites.Image;

@Stateless(name = "Image")
public class ImageEJBImpl implements ImageLocal, ImageRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	@PermitAll
	public Image addImage(Image i) {
		em.persist(i);
		return i;
	}

	@Override
	@PermitAll
	public Image getImage(int id) {
		Image i = em.find(Image.class, id);
		if (i== null)
			throw new RuntimeException("Image introvable");
		return i;
	}

	@Override
	@PermitAll
	public List<Image> listImages() {
		Query query = em.createQuery("from Image");
		return query.getResultList();
	}

	@Override
	@PermitAll
	public void updateImage(int id, Image i) {
		Image im = getImage(id);
		if(im != null) {
			im.setUrl(i.getUrl());
			im.setMonument(i.getMonument());
		    em.persist(im);	
		}
	}

	@Override
	@PermitAll
	public void deleteImage(int id) {
		Image i = getImage(id);
		em.remove(i);
	}

	@Override
	@PermitAll
	public List<Image> listImagesParMonument(String nom) {
		Query query = em.createQuery("from Image i where i.monument.nom like :nom");
		query.setParameter("nom", "%" + nom + "%");
		return query.getResultList();
	}


}
