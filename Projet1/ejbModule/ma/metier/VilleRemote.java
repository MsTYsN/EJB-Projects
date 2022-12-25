package ma.metier;

import java.util.List;

import javax.ejb.Remote;

import ma.entites.Ville;

@Remote
public interface VilleRemote {
	Ville addVille(Ville v);
	Ville getVille(int id);
	List<Ville> listVilles();
	List<Ville> listVillesParNom(String nom);
	void updateVille(int id, Ville v);
	void deleteVille(int id);
}
