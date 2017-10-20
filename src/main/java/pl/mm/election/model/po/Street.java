package pl.mm.election.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"city", "name"})
	)
@Entity
public class Street {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_street")
	@SequenceGenerator(name="seq_street", sequenceName = "seq_street", allocationSize=50)
	private Integer id;
	
	@Column(nullable = false, unique=true)
	private String name;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="city")
	private City city;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}
