package pl.mm.election.imports.address;

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
import org.springframework.core.task.TaskExecutor;

import pl.mm.election.imports.repository.JobCompletionNotificationListener;
import pl.mm.election.service.address.AddressService;

@Configuration
public class AddressImportBatchConfiguration {

	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @PersistenceContext(unitName="pu")
	private EntityManager em;
    
    @Autowired
    private AddressService addressService;
    
    @Bean
    @StepScope
    public FlatFileItemReader<AddressImport> addressImportReader(@Value("#{jobParameters[pathToFile]}") String pathToFile) {
        FlatFileItemReader<AddressImport> reader = new FlatFileItemReader<AddressImport>();
        reader.setResource(new ClassPathResource(pathToFile));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<AddressImport>() {
        	
        	@Override
			public AddressImport mapLine(String line, int lineNumber) throws Exception {
        		AddressImport result = super.mapLine(line, lineNumber);
				result.setLineNumber(lineNumber);
				return result;
			}
        	
        	{
        	
	            setLineTokenizer(new DelimitedLineTokenizer() {{
	                setNames(new String[] { "country", "zip", "city", "street", "number" });
	                setDelimiter(";");
	            }});
	            setFieldSetMapper(new BeanWrapperFieldSetMapper<AddressImport>() {{
	                setTargetType(AddressImport.class);
	            }});
        	}
        });
        return reader;
    }
    
    @Bean
    public AddressImportProcessor addressImportProcessor() {
        return new AddressImportProcessor(addressService);
    }
    
    @Bean
    public ItemWriter<AddressImportTo> addressImportWriter() {
    	AddressImportWriter writer = new AddressImportWriter();
        writer.setAddressService(addressService);
        return writer;
    }
    
    @Bean
    public Step addressImportStep() {
        return stepBuilderFactory.get("addressImport")
                .<AddressImport, AddressImportTo> chunk(10)
                .faultTolerant()
                .skip(AlreadyExistsAddressImportException.class)
                .skipLimit(Integer.MAX_VALUE)
                .reader(addressImportReader(null))
                .processor(addressImportProcessor())
                .writer(addressImportWriter())
                .throttleLimit(4)
                .build();
    }
    
    @Bean
    public Job addressImportJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("addressImportJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(addressImportStep())
                .end()
                .build();
    }
}
