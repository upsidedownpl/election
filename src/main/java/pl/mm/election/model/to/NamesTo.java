package pl.mm.election.model.to;

public class NamesTo {

	public static class NamesToBuilder {
		
		private String firstName;
		private String lastName;
		
		private NamesToBuilder() {
			//
		}
		
		public static NamesToBuilder create() {
			return new NamesToBuilder();
		}
		
		public NamesToBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public NamesToBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public NamesTo build() {
			NamesTo names = new NamesTo();
			names.setFirstName(firstName);
			names.setLastName(lastName);
			return names;
		}
	}
	
	private String firstName;

	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public static NamesToBuilder create() {
		return NamesToBuilder.create();
	}

}
