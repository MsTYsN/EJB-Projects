package ma.entites;

import java.io.Serializable;
import java.lang.Long;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Monument
 *
 */
@Entity

public class Monument implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String adresse;
	private String description;
	private double latitude;
	private double longitude;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ville_id")
	private Ville ville;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createur_id")
	private Createur createur;
	/*
	 * @OneToMany(fetch = FetchType.EAGER, mappedBy = "monument", cascade =
	 * CascadeType.ALL) private List<Image> images = new ArrayList<>();
	 */

	private static final long serialVersionUID = 1L;

	public Monument() {
		super();
	}

	public Monument(String nom, String adresse, String description, double latitude, double longitude) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Monument(String nom, String adresse, String description, double latitude, double longitude, Ville ville) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.ville = ville;
	}

	public Monument(String nom, String adresse, String description, double latitude, double longitude,
			Createur createur) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.createur = createur;
	}

	public Monument(String nom, String adresse, String description, double latitude, double longitude, Ville ville,
			Createur createur) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.ville = ville;
		this.createur = createur;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public Createur getCreateur() {
		return createur;
	}

	public void setCreateur(Createur createur) {
		this.createur = createur;
	}

	/*
	 * public List<Image> getImages() { return images; }
	 * 
	 * public void setImages(List<Image> images) { this.images = images; }
	 */

}
