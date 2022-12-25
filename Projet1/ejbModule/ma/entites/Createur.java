package ma.entites;

import java.io.Serializable;
import java.lang.Long;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Createur
 *
 */
@Entity

public class Createur implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private String description;
	private Date debut;
	private Date fin;
	/*
	 * @OneToMany(fetch = FetchType.EAGER, mappedBy = "createur", cascade =
	 * CascadeType.ALL) private List<Monument> monuments = new ArrayList<>();
	 */

	private static final long serialVersionUID = 1L;

	public Createur() {
		super();
	}

	public Createur(String nom, String prenom, String description, Date debut, Date fin) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.description = description;
		this.debut = debut;
		this.fin = fin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * public List<Monument> getMonuments() { return monuments; }
	 * 
	 * public void setMonuments(List<Monument> monuments) { this.monuments =
	 * monuments; }
	 */

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

}
