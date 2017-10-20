package pl.mm.election.imports;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import pl.mm.election.imports.address.AddressImportBatchConfiguration;
import pl.mm.election.imports.user.UserImportBatchConfiguration;

@EnableBatchProcessing
@Configuration
@Import({UserImportBatchConfiguration.class, AddressImportBatchConfiguration.class})
public class ImportConfiguration implements BatchConfigurer {
	
	@Autowired
    private PlatformTransactionManager transactionManager;
    
    @Autowired
    private DataSource dataSource;
	
	@Override
    public JobRepository getJobRepository() throws Exception {
    	JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDataSource(dataSource);
		factory.setTransactionManager(transactionManager);
		factory.afterPropertiesSet();
		return  (JobRepository) factory.getObject();
    }
    
    @Override
    public JobLauncher getJobLauncher() throws Exception {
    	SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(getJobRepository());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
    }
    
    @Override
    public PlatformTransactionManager getTransactionManager() throws Exception {
    	return transactionManager;
    }
    
    @Override
    public JobExplorer getJobExplorer() throws Exception {
    	return null;
    }
	
}
