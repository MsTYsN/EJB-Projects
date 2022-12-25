package ma.metier;

import java.util.List;

import javax.ejb.Remote;

import ma.entites.Monument;

@Remote
public interface MonumentRemote {
	Monument addMonument(Monument m);
	Monument getMonument(int id);
	List<Monument> listMonuments();
	List<Monument> listMonumentsParNom(String nom);
	void updateMonument(int id, Monument m);
	void deleteMonument(int id);
}
