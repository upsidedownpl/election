package pl.mm.election.imports.citizen;

import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import pl.mm.election.model.to.CitizenTo;
import pl.mm.election.model.to.DatesTo;
import pl.mm.election.model.to.NamesTo;
import pl.mm.election.model.to.NumbersTo;
import pl.mm.election.model.to.UserTo;
import pl.mm.election.service.citizen.CitizenService;
import pl.mm.election.service.user.UserService;

public class CitizenImportProcessor implements ItemProcessor<CitizenImport, CitizenTo>{

	private static final Logger log = LoggerFactory.getLogger(CitizenImportProcessor.class);
	
	private CitizenService citizenService;
	private UserService userService;
	
	public CitizenImportProcessor(CitizenService citizenService, UserService userService) {
		this.citizenService = citizenService;
		this.userService = userService;
	}

	@Override
	public CitizenTo process(CitizenImport c) throws Exception {
		log.info("Processing citizen {}", c);
		CitizenTo citizen = new CitizenTo();
		NumbersTo numbers = new NumbersTo();
		DatesTo dates = new DatesTo();
		NamesTo names = new NamesTo();
		citizen.setDates(dates);
		citizen.setNames(names);
		citizen.setNumbers(numbers);
		if(!StringUtils.isEmpty(c.getPesel())) {
			numbers.setPesel(c.getPesel());
		} else {
			log.warn("Row with empty pesel");
			throw new CitizenImportException();
		}
		
		if(!StringUtils.isEmpty(c.getFirstName())) {
			names.setFirstName(c.getFirstName());
		} else {
			log.warn("Row with empty first name");
			throw new CitizenImportException();
		}
		
		if(!StringUtils.isEmpty(c.getLastName())) {
			names.setLastName(c.getLastName());
		} else {
			log.warn("Row with empty last name");
			throw new CitizenImportException();
		}
		
		if(!StringUtils.isEmpty(c.getBirthDate())) {
			dates.setBirthDate(
					new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
						.parse(c.getBirthDate())
				);
		} else {
			log.warn("Row with empty birth date");
			throw new CitizenImportException();
		}
		
		if(!StringUtils.isEmpty(c.getDeathDate())) {
			dates.setDeathDate(
					new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
						.parse(c.getDeathDate())
					);
		}
		
		if(!StringUtils.isEmpty(c.getLogin())) {
			UserTo user = userService.getByLogin(c.getLogin());
			if(user == null) {
				throw new UnknownLoginCitizenImportException();
			}
			
			citizen.setUser(user);
		}
		
		if(citizenService.getBy(numbers, names, dates) != null) {
			throw new AlreadyExistsCitizenImportException();
		}
		
		return citizen;
	}

}
