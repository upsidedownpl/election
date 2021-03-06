package pl.mm.election.imports.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import pl.mm.election.imports.repository.JobCompletionNotificationListener;
import pl.mm.election.service.user.UserService;

@Configuration
public class UserImportBatchConfiguration {

	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @PersistenceContext(unitName="pu")
	private EntityManager em;
    
    @Autowired
    private UserService userService;
    
    @Bean
    @StepScope
    public FlatFileItemReader<UserImport> userImportReader(@Value("#{jobParameters[pathToFile]}") String pathToFile) {
        FlatFileItemReader<UserImport> reader = new FlatFileItemReader<UserImport>();
        reader.setResource(new ClassPathResource(pathToFile));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<UserImport>() {
        	
        	@Override
			public UserImport mapLine(String line, int lineNumber) throws Exception {
				UserImport result = super.mapLine(line, lineNumber);
				result.setLineNumber(lineNumber);
				return result;
			}
        	
        	{
        	
	            setLineTokenizer(new DelimitedLineTokenizer() {{
	                setNames(new String[] { "login", "password", "salt" });
	                setDelimiter(";");
	            }});
	            setFieldSetMapper(new BeanWrapperFieldSetMapper<UserImport>() {{
	                setTargetType(UserImport.class);
	            }});
        	}
        });
        return reader;
    }
    
    @Bean
    public UserImportProcessor userImportProcessor() {
        return new UserImportProcessor(userService);
    }
    
    @Bean
    public ItemWriter<UserImportTo> userImportWriter() {
        UserImportWriter writer = new UserImportWriter();
        writer.setUserService(userService);
        return writer;
    }
    
    @Bean
    public Job userImportJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("userImportJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(userImportStep())
                .end()
                .build();
    }
    
    @Bean
    public Step userImportStep() {
        return stepBuilderFactory.get("userImportStep")
                .<UserImport, UserImportTo> chunk(10)
                .faultTolerant()
                .skip(AlreadyExistsUserImportException.class)
                .skipLimit(Integer.MAX_VALUE)
                .reader(userImportReader(null))
                .processor(userImportProcessor())
                .writer(userImportWriter())
                .throttleLimit(4)
                .build();
    }
}
