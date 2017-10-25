package pl.mm.election.model.to;

import java.util.Date;

public class DatesTo implements Transfer {
	
	public static class DatesToBuilder {
		
		private Date birthDate;
		private Date deathDate;
		
		private DatesToBuilder() {
			//
		}
		
		public static DatesToBuilder create() {
			return new DatesToBuilder();
		}
		
		public DatesToBuilder deathDate(Date deathDate) {
			this.deathDate = deathDate;
			return this;
		}
		
		public DatesToBuilder birthDate(Date birthDate) {
			this.birthDate = birthDate;
			return this;
		}
		
		public DatesTo build() {
			DatesTo dates = new DatesTo();
			dates.setBirthDate(birthDate);
			dates.setDeathDate(deathDate);
			return dates;
		}
	}

	private Date birthDate;

	private Date deathDate;

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}
	
	public static DatesToBuilder create() {
		return DatesToBuilder.create();
	}

}
