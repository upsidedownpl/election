package pl.mm.election.model.to;

public class CitizenTo {

	private Integer id;

	private NamesTo names;

	private DatesTo dates;

	private NumbersTo numbers;

	private UserTo user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NamesTo getNames() {
		return names;
	}

	public void setNames(NamesTo names) {
		this.names = names;
	}

	public DatesTo getDates() {
		return dates;
	}

	public void setDates(DatesTo dates) {
		this.dates = dates;
	}

	public NumbersTo getNumbers() {
		return numbers;
	}

	public void setNumbers(NumbersTo numbers) {
		this.numbers = numbers;
	}

	public UserTo getUser() {
		return user;
	}

	public void setUser(UserTo user) {
		this.user = user;
	}

}
