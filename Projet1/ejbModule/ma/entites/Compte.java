package ma.entites;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Compte
 *
 */
@Entity

public class Compte implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long code;
	private double solde;
	private Date dateCreation;
	private static final long serialVersionUID = 1L;

	public Compte() {
		super();
	}   
	
	public Compte(double solde, Date dateCreation) {
		super();
		this.solde = solde;
		this.dateCreation = dateCreation;
	}

	  
	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getSolde() {
		return this.solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}   
	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
   
}
