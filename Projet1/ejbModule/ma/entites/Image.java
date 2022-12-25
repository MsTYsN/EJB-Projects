package ma.entites;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Image
 *
 */
@Entity

public class Image implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String url;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "monument_id")
	private Monument monument;

	private static final long serialVersionUID = 1L;

	public Image() {
		super();
	}

	public Image(int id, String url) {
		super();
		this.id = id;
		this.url = url;
	}

	public Image(String url) {
		super();
		this.url = url;
	}

	public Image(String url, Monument monument) {
		super();
		this.url = url;
		this.monument = monument;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Monument getMonument() {
		return monument;
	}

	public void setMonument(Monument monument) {
		this.monument = monument;
	}

}
