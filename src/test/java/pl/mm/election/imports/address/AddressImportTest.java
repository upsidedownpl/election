package pl.mm.election.imports.address;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Iterables;

import pl.mm.election.dao.AddressDao;
import pl.mm.election.model.po.City;
import pl.mm.election.model.po.Country;
import pl.mm.election.model.po.Street;
import pl.mm.election.service.address.AddressService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/applicationContext-persistence-jta-h2.xml",
		"/applicationContext-dao.xml",
		"/applicationContext-imports.xml",
		"/applicationContext-service-address.xml"
	})
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AddressImportTest {
	
	@Resource
	private JobLauncher jobLauncher;
	
	@Resource(name="addressImportJob")
	private Job addressImportJob;
	
	@Resource
	private AddressDao addressDao;
	
	@Resource
	private AddressService addressService;
	
	@Test
	public void testEmptyImport() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// given
		JobParameters jobParameters = new JobParametersBuilder()
			.addString("pathToFile", "imports/address/empty.csv")
			.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(addressImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.COMPLETED));
		assertThat(addressDao.countCountry(), equalTo(0L));
		assertThat(addressDao.countCity(), equalTo(0L));
		assertThat(addressDao.countStreet(), equalTo(0L));
	}
	
	@Test
	public void testSingleImport() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// given
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pathToFile", "imports/address/single.csv")
				.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(addressImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.COMPLETED));
		assertThat(addressDao.countCountry(), equalTo(1L));
		assertThat(addressDao.countCity(), equalTo(1L));
		assertThat(addressDao.countStreet(), equalTo(1L));
		assertThat(addressDao.countAddress(), equalTo(1L));
		
		Country country = addressDao.getCountryByName("Poland");
		assertThat(country, notNullValue());
		
		City city = addressDao.getCityByZipAndCountry("12-345", country);
		assertThat(city, notNullValue());
		assertThat(city.getName(), equalTo("Kraków"));
		
		Street street = addressDao.getStreetByNameAndCity("Floriańska", city);
		assertThat(street, notNullValue());
	}
	

	@Test
	public void testWithErrorsImport() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// given
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pathToFile", "imports/address/withErrors.csv")
				.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(addressImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.FAILED));
		assertThat(addressDao.countCountry(), equalTo(2L));
		assertThat(addressDao.countCity(), equalTo(2L));
		assertThat(addressDao.countStreet(), equalTo(2L));
		assertThat(addressDao.countAddress(), equalTo(2L));
		
		StepExecution stepExecution = Iterables.getOnlyElement(execution.getStepExecutions());
		assertThat(stepExecution.getReadCount(), equalTo(3));
		assertThat(stepExecution.getSkipCount(), equalTo(0));
		assertThat(stepExecution.getWriteCount(), equalTo(2));
	}
	

	@Test
	public void testHugeImport() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// given
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pathToFile", "imports/address/huge.csv")
				.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(addressImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.COMPLETED));
		assertThat(addressDao.countCountry(), equalTo(1024L));
		assertThat(addressDao.countCity(), equalTo(1024L));
		assertThat(addressDao.countStreet(), equalTo(1024L));
		assertThat(addressDao.countAddress(), equalTo(1024L));
		
	}
	
	@Test
	public void testCountryExists() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// given
		Country country = addressService.createCountry("Poland");
		City city = addressService.createCity("Warszawa", "11-111", country);
		Street street = addressService.createStreet("Marszałkowska", city);
		addressService.createAddress("1", street);
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pathToFile", "imports/address/countryExists.csv")
				.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(addressImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.COMPLETED));
		assertThat(addressDao.countCountry(), equalTo(1L));
		assertThat(addressDao.countCity(), equalTo(2L));
		assertThat(addressDao.countStreet(), equalTo(2L));
		assertThat(addressDao.countAddress(), equalTo(2L));
	}
	
	@Test
	public void testCityExists() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// given
		Country country = addressService.createCountry("Poland");
		City city = addressService.createCity("Warszawa", "11-111", country);
		Street street = addressService.createStreet("Marszałkowska", city);
		addressService.createAddress("1", street);
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pathToFile", "imports/address/cityExists.csv")
				.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(addressImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.COMPLETED));
		assertThat(addressDao.countCountry(), equalTo(1L));
		assertThat(addressDao.countCity(), equalTo(1L));
		assertThat(addressDao.countStreet(), equalTo(2L));
		assertThat(addressDao.countAddress(), equalTo(2L));
	}
	
	@Test
	public void testStreetExists() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// given
		Country country = addressService.createCountry("Poland");
		City city = addressService.createCity("Warszawa", "11-111", country);
		Street street = addressService.createStreet("Marszałkowska", city);
		addressService.createAddress("1", street);
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pathToFile", "imports/address/streetExists.csv")
				.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(addressImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.COMPLETED));
		assertThat(addressDao.countCountry(), equalTo(1L));
		assertThat(addressDao.countCity(), equalTo(1L));
		assertThat(addressDao.countStreet(), equalTo(1L));
		assertThat(addressDao.countAddress(), equalTo(2L));
	}
	
	@Test
	public void testAddressExists() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// given
		Country country = addressService.createCountry("Poland");
		City city = addressService.createCity("Warszawa", "11-111", country);
		Street street = addressService.createStreet("Marszałkowska", city);
		addressService.createAddress("1", street);
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pathToFile", "imports/address/addressExists.csv")
				.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(addressImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.COMPLETED));
		assertThat(addressDao.countCountry(), equalTo(1L));
		assertThat(addressDao.countCity(), equalTo(1L));
		assertThat(addressDao.countStreet(), equalTo(1L));
		assertThat(addressDao.countAddress(), equalTo(1L));
		
		StepExecution stepExecution = Iterables.getOnlyElement(execution.getStepExecutions());
		assertThat(stepExecution.getReadCount(), equalTo(1));
		assertThat(stepExecution.getSkipCount(), equalTo(1));
		assertThat(stepExecution.getWriteCount(), equalTo(0));
	}
	
}