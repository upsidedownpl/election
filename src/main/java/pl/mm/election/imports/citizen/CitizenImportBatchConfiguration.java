package pl.mm.election.imports.citizen;

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
import pl.mm.election.model.to.CitizenTo;
import pl.mm.election.service.citizen.CitizenService;
import pl.mm.election.service.user.UserService;

@Configuration
public class CitizenImportBatchConfiguration {

	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @PersistenceContext(unitName="pu")
	private EntityManager em;
    
    @Autowired
    private CitizenService citizenService;
    
    @Autowired
    private UserService userService;
    
    @Bean
    @StepScope
    public FlatFileItemReader<CitizenImport> citizenImportReader(@Value("#{jobParameters[pathToFile]}") String pathToFile) {
        FlatFileItemReader<CitizenImport> reader = new FlatFileItemReader<CitizenImport>();
        reader.setResource(new ClassPathResource(pathToFile));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<CitizenImport>() {
        	
        	@Override
			public CitizenImport mapLine(String line, int lineNumber) throws Exception {
        		CitizenImport result = super.mapLine(line, lineNumber);
				result.setLineNumber(lineNumber);
				return result;
			}
        	
        	{
        	
	            setLineTokenizer(new DelimitedLineTokenizer() {{
	                setNames(new String[] { "pesel", "firstName", "lastName", "birthDate", "deathDate", "user"});
	                setDelimiter(";");
	            }});
	            setFieldSetMapper(new BeanWrapperFieldSetMapper<CitizenImport>() {{
	                setTargetType(CitizenImport.class);
	            }});
        	}
        });
        return reader;
    }
    
    @Bean
    public CitizenImportProcessor citizenImportProcessor() {
        return new CitizenImportProcessor(citizenService, userService);
    }
    
    @Bean
    public ItemWriter<CitizenTo> citienImportWriter() {
    	CitizenImportWriter writer = new CitizenImportWriter();
    	writer.setCitizenService(citizenService);
        return writer;
    }
    
    @Bean
    public Step citizenImportStep() {
        return stepBuilderFactory.get("citizenImport")
                .<CitizenImport, CitizenTo> chunk(10)
                .faultTolerant()
                .skip(AlreadyExistsCitizenImportException.class)
                .skipLimit(Integer.MAX_VALUE)
                .reader(citizenImportReader(null))
                .processor(citizenImportProcessor())
                .writer(citienImportWriter())
                .throttleLimit(4)
                .build();
    }
    
    @Bean
    public Job citizenImportJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("citizenImportJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(citizenImportStep())
                .end()
                .build();
    }
}
