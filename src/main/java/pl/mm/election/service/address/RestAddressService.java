package pl.mm.election.service.address;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import pl.mm.election.model.po.Country;

@Path("/address")
public class RestAddressService {

	@Autowired
	private AddressService addressService;

	@GET
	@Path("/country/{name}")
	public Response getCountry(@PathParam("name") String name) {
		Country country = addressService.getCountryByName(name);
		if(country == null) {
			return Response.status(404).build();
		} else {
			return Response.status(200).entity("ok").build();
		}
	}
}
