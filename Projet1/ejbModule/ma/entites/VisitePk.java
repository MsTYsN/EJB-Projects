package ma.entites;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class VisitePk implements Serializable {
	private int user;
	private int monument;

	public VisitePk() {
		super();
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getMonument() {
		return monument;
	}

	public void setMonument(int monument) {
		this.monument = monument;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, monument);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VisitePk other = (VisitePk) obj;
		return user == other.user && monument == other.monument;
	}

}
