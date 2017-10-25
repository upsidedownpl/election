package pl.mm.election.imports.user;

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

import pl.mm.election.dao.user.UserDao;
import pl.mm.election.model.po.User;
import pl.mm.election.model.to.UserTo;
import pl.mm.election.service.encryption.AuthenticateService;
import pl.mm.election.service.encryption.EncryptionException;
import pl.mm.election.service.user.UserCreationException;
import pl.mm.election.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/applicationContext-persistence-jta-h2.xml",
		"/applicationContext-imports-repository.xml",
		"/applicationContext-imports-user.xml",
		"/applicationContext-service-encryption.xml",
		"/applicationContext-address.xml",
		"/applicationContext-user.xml"
	})
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserImportTest {
	
	@Resource
	private JobLauncher jobLauncher;
	
	@Resource(name="userImportJob")
	private Job userImportJob;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private UserService userService;
	
	@Resource
	private AuthenticateService authenticateService;
	
	@Test
	public void testEmptyImport() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// given
		JobParameters jobParameters = new JobParametersBuilder()
			.addString("pathToFile", "imports/user/empty.csv")
			.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(userImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.COMPLETED));
		assertThat(userDao.count(), equalTo(0L));
	}
	
	@Test
	public void testSingleImport() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// given
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pathToFile", "imports/user/single.csv")
				.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(userImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.COMPLETED));
		assertThat(userDao.count(), equalTo(1L));
		
		User admin = userDao.read("admin");
		assertThat(admin, notNullValue());
		
		assertThat(authenticateService.authenticateUser("admin", "passwd"), 
				equalTo(true));
	}
	
	@Test
	public void testDontUpdateImport() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, EncryptionException, UserCreationException {
		// given
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pathToFile", "imports/user/single.csv")
				.toJobParameters();
		
		UserTo admin = new UserTo();
		admin.setLogin("admin");
		userService.create(admin, "old");
		
		
		// when
		
		JobExecution execution = jobLauncher.run(userImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.COMPLETED));
		assertThat(userDao.count(), equalTo(1L));
		
		User adminPersistent = userDao.read("admin");
		assertThat(adminPersistent, notNullValue());
		
		assertThat(authenticateService.authenticateUser("admin", "old"), 
				equalTo(true));
		
		StepExecution stepExecution = Iterables.getOnlyElement(execution.getStepExecutions());
		assertThat(stepExecution.getReadCount(), equalTo(1));
		assertThat(stepExecution.getSkipCount(), equalTo(1));
		assertThat(stepExecution.getWriteCount(), equalTo(0));
		
	}
	
	@Test
	public void testWithErrorsImport() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// given
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pathToFile", "imports/user/withErrors.csv")
				.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(userImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.FAILED));
	}
	
	@Test
	public void testHugeImport() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// given
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pathToFile", "imports/user/huge.csv")
				.toJobParameters();
		
		// when
		
		JobExecution execution = jobLauncher.run(userImportJob, jobParameters);
		
		// then
		assertThat(execution.getStatus(), equalTo(BatchStatus.COMPLETED));
		assertThat(userDao.count(), equalTo(1024L));
		
	}
	
}