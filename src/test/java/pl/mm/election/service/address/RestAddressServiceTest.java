package pl.mm.election.service.address;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;

public class RestAddressServiceTest extends JerseyTest{
	
	@Override
    protected TestContainerFactory getTestContainerFactory() {
        return new GrizzlyWebTestContainerFactory();
    }

    @Override
    protected DeploymentContext configureDeployment(){
        return ServletDeploymentContext
                .forServlet(new ServletContainer(new ResourceConfig().packages("pl.mm.election.service.address")))
                .addListener(ContextLoaderListener.class)
                .contextParam("contextConfigLocation", "classpath:applicationContext-rest-service-address.xml")
                .build();
    }
	
	@Test
	public void testGetNotExistingCountry() {
		// given
		
		// when
		Response resp = target().path("/address/country/test").request().get();
		
		// then
		assertThat(resp.getStatus(), equalTo(404));
	}
	
}
