package pl.mm.election.imports.address;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author mmo
 *
 */
public class AddressImport {
	private String country;
	private String city;
	private String zip;
	private String street;
	private String number;
	private int lineNumber;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("country", country)
				.append("zip", zip)
				.append("city", city)
				.append("street", street)
				.append("number", number).toString();
	}

}