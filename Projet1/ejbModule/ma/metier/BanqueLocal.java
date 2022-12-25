package ma.metier;

import java.util.List;

import javax.ejb.Local;

import ma.entites.Compte;

@Local
public interface BanqueLocal {
	Compte addCompte(Compte c);
	Compte getCompte(Long code);
	List<Compte> listComptes();
	void verser(Long c, double mt);
	void retirer(Long c, double mt);
	void virement(Long c1, Long c2, double mt);

}
