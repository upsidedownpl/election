package pl.mm.election.model.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Candidate implements Persistent {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_candidate")
	@SequenceGenerator(name = "seq_candidate", sequenceName = "seq_candidate", allocationSize = 50)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
