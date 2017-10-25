package pl.mm.election.model.to;

public class NumbersTo implements Transfer {
	
	public static class NumbersToBuilder {
		
		private String pesel;
		
		private NumbersToBuilder() {
			//
		}
		
		public static NumbersToBuilder create() {
			return new NumbersToBuilder();
		}
		
		public NumbersToBuilder pesel(String pesel) {
			this.pesel = pesel;
			return this;
		}
		
		public NumbersTo build() {
			NumbersTo numbers = new NumbersTo();
			numbers.setPesel(pesel);
			return numbers;
		}
	}

	private String pesel;

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	
	public static NumbersToBuilder create() {
		return NumbersToBuilder.create();
	}

}
