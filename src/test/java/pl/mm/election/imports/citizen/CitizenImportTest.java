package pl.mm.election.imports.citizen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mm.election.dao.citizen.CitizenDao;
import pl.mm.election.dao.citizen.NonUniqueCitizenQuery;
import pl.mm.election.model.to.CitizenTo;
import pl.mm.election.model.to.DatesTo;
import pl.mm.election.model.to.NamesTo;
import pl.mm.election.model.to.NumbersTo;
import pl.mm.election.service.citizen.CitizenService;
import pl.mm.election.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/applicationContext-persistence-jta-h2.xml",
		"/applicationContext-imports-repository.xml",
		"/applicationContext-imports-citizen.xml",
		"/applicationContext-service-encryption.xml",
		"/applicationContext-user.xml",
		"/applicationContext-citizen.xml"
	})
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CitizenImportTest {
	
	@Resource
	private JobLauncher jobLauncher;
	
	@Resource(name="citizenImportJob")
	private Job citizenImportJob;
	
	@Resource
	private CitizenDao citizenDao;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CitizenService citizenService;
	
	@Test
	public void testEmptyImport() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// given
		JobParameters jobParameters = new JobParametersBuilder()
			.addString("pathToFile", "imports/citizen/empty.csv")
			.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(citizenImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.COMPLETED));
		assertThat(citizenDao.queryBy(null, null, null), hasSize(0));
	}
	
	@Test
	public void testSingleImportUserNotExists() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, NonUniqueCitizenQuery {
		// given
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pathToFile", "imports/citizen/single.csv")
				.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(citizenImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.COMPLETED));
		assertThat(citizenDao.queryBy(null, null, null), hasSize(1));
		
		CitizenTo citizen = citizenService.getBy(
				NumbersTo.create().pesel("12321509717").build(),
				NamesTo.create().firstName("Jan").lastName("Kowalski").build(),
				DatesTo.create().birthDate(
						Date.from(
								LocalDateTime.of(2001, 07, 4, 12, 8, 56).toInstant(OffsetDateTime.now().getOffset()))
						).build()
			);
				
		
		assertThat(citizen, notNullValue());
		assertThat(citizen.getUser(), nullValue());
	}
	
}