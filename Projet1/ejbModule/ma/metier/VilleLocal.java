package ma.metier;

import java.util.List;

import javax.ejb.Local;

import ma.entites.Ville;

@Local
public interface VilleLocal {
	Ville addVille(Ville v);
	Ville getVille(int id);
	List<Ville> listVilles();
	List<Ville> listVillesParNom(String nom);
	void updateVille(int id, Ville v);
	void deleteVille(int id);
}
