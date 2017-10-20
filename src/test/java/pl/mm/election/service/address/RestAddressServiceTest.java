package pl.mm.election.service.address;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.StringReader;
import java.util.EventListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.codehaus.jettison.json.JSONObject;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import pl.mm.election.model.to.CountryTo;

public class RestAddressServiceTest extends JerseyTest{
	
	public static class Listener implements ServletContextListener {
		
		@Override
		public void contextInitialized(ServletContextEvent sce) {
			Object ctx = sce.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
			RestAddressServiceTest.ctx = (ApplicationContext) ctx;
		}
		
	}
	
	private static ApplicationContext ctx;
	
	@Override
    protected TestContainerFactory getTestContainerFactory() {
        return new GrizzlyWebTestContainerFactory();
    }

    @Override
    protected DeploymentContext configureDeployment(){
        ServletDeploymentContext ctx = ServletDeploymentContext
                .forServlet(new ServletContainer(new ResourceConfig().packages("pl.mm.election.service.address")))
                .addListener(ContextLoaderListener.class)
                .addListener(Listener.class)
                .contextParam("contextConfigLocation", "classpath:applicationContext-rest-service-address.xml")
                .build();
        return ctx;
    }
	
	@Test
	public void testGetNotExistingCountry() {
		// given
		
		// when
		Response resp = target().path("/address/country/test").request().get();
		
		// then
		assertThat(resp.getStatus(), equalTo(404));
	}
	
	@Test
	public void testGetExistingCountry() throws JAXBException {
		// given
		AddressService addressService = ctx.getBean(AddressService.class);
		addressService.createCountry("Poland");
		
		// when
		Response resp = target().path("/address/country/Poland").request().get();
		
		// then
		assertThat(resp.getStatus(), equalTo(200));
		assertThat(resp.hasEntity(), equalTo(Boolean.TRUE));
		
		JAXBContext jc = JAXBContext.newInstance(CountryTo.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
		unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);

		StreamSource json = new StreamSource(new StringReader(resp.readEntity(String.class)));
		CountryTo country = unmarshaller.unmarshal(json, CountryTo.class).getValue();
		
		assertThat(country.getName(), equalTo("Poland"));
	}
	
}
