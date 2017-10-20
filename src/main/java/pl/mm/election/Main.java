package pl.mm.election;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.mm.election.model.User;
import pl.mm.election.service.encryption.EncryptionException;
import pl.mm.election.service.encryption.EncryptionService;
import pl.mm.election.service.user.UserCreationException;
import pl.mm.election.service.user.UserService;

public class Main {

	public static void main(String[] args) throws BeansException, UserCreationException, EncryptionException {
//		ClassPathXmlApplicationContext ctx = 
//		        new ClassPathXmlApplicationContext("applicationContext.xml");
//		ctx.registerShutdownHook();
//		
//		EncryptionService encService = ctx.getBean(EncryptionService.class);
		
//		User admin = new User();
//		admin.setLogin("admin");
//		encService.setPassword(admin, "passwd");
		
//		printUser(admin);
		
		for(int i = 0; i < 1024; i++) {	
			System.out.println("\"Country" + i +
				"\";\"12-345\";\"City" + i + "\";\"Street" + i + "\";\"" + i +"\"");
		}
	}

	private static void printUser(User u) {
		System.out.println("\"" + u.getLogin() + "\";\"" + Arrays.toString(u.getPassword()) + "\";\"" + Arrays.toString(u.getSalt()) + "\"");
	}

}
