package pl.mm.election.imports.citizen;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import pl.mm.election.model.to.CitizenTo;
import pl.mm.election.service.citizen.CitizenService;

public class CitizenImportWriter implements ItemWriter<CitizenTo>, InitializingBean {

	protected static final Log logger = LogFactory.getLog(CitizenImportWriter.class);

	private CitizenService citizenService;

	public void setCitizenDao(CitizenService citizenService) {
		this.citizenService = citizenService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(citizenService, "A citizenService is required");
	}

	@Override
	public void write(List<? extends CitizenTo> items) {
		for(CitizenTo i : items) {
			citizenService.save(i);
		}
	}

	public void setCitizenService(CitizenService citizenService) {
		this.citizenService = citizenService;
	}
	
	

}
