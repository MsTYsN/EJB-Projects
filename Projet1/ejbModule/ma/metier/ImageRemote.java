package ma.metier;

import java.util.List;

import javax.ejb.Remote;

import ma.entites.Image;

@Remote
public interface ImageRemote {
	Image addImage(Image i);
	Image getImage(int id);
	List<Image> listImages();
	List<Image> listImagesParMonument(String nom);
	void updateImage(int id, Image i);
	void deleteImage(int id);
}
