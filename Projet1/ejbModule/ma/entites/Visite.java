package ma.entites;

import java.io.Serializable;
import java.lang.Long;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Visite
 *
 */
@Entity

public class Visite implements Serializable {
	@EmbeddedId
	private VisitePk pk;
	private Date date;
	private int note;
	@ManyToOne
	@JoinColumn(name = "user", insertable = false, updatable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "monument", insertable = false, updatable = false)
	private Monument monument;

	private static final long serialVersionUID = 1L;

	public Visite() {
		super();
	}

	public VisitePk getPk() {
		return pk;
	}

	public void setPk(VisitePk pk) {
		this.pk = pk;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Monument getMonument() {
		return monument;
	}

	public void setMonument(Monument monument) {
		this.monument = monument;
	}

}
