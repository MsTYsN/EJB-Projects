package ma.metier;

import java.util.List;

import javax.ejb.Local;

import ma.entites.Image;

@Local
public interface ImageLocal {
	Image addImage(Image i);
	Image getImage(int id);
	List<Image> listImages();
	List<Image> listImagesParMonument(String nom);
	void updateImage(int id, Image i);
	void deleteImage(int id);
}
