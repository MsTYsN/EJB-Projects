package ma.metier;

import java.util.List;

import javax.ejb.Local;

import ma.entites.Createur;

@Local
public interface CreateurLocal {
	Createur addCreateur(Createur c);
	Createur getCreateur(int id);
	List<Createur> listCreateurs();
	List<Createur> listCreateursParNom(String nom);
	void updateCreateur(int id, Createur c);
	void deleteCreateur(int id);
}
