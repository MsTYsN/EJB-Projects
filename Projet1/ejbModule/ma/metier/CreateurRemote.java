package ma.metier;

import java.util.List;

import javax.ejb.Remote;

import ma.entites.Createur;

@Remote
public interface CreateurRemote {
	Createur addCreateur(Createur c);
	Createur getCreateur(int id);
	List<Createur> listCreateurs();
	List<Createur> listCreateursParNom(String nom);
	void updateCreateur(int id, Createur c);
	void deleteCreateur(int id);
}
