package pl.mm.election.model.po;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Citizen {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_citizen")
	@SequenceGenerator(name = "seq_citizen", sequenceName = "seq_citizen", allocationSize = 50)
	private Integer id;

	@Embedded
	private Names names;

	@Embedded
	private Dates dates;

	@Embedded
	private Numbers numbers;

	@OneToOne
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Names getNames() {
		return names;
	}

	public void setNames(Names names) {
		this.names = names;
	}

	public Dates getDates() {
		return dates;
	}

	public void setDates(Dates dates) {
		this.dates = dates;
	}

	public Numbers getNumbers() {
		return numbers;
	}

	public void setNumbers(Numbers numbers) {
		this.numbers = numbers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
