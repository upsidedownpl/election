package pl.mm.election.model.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Candidate {

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
