package pl.mm.election;

import java.nio.charset.Charset;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.mm.election.model.po.User;
import pl.mm.election.service.encryption.EncryptionException;
import pl.mm.election.service.encryption.EncryptionService;
import pl.mm.election.service.user.UserCreationException;
import pl.mm.election.service.user.UserService;

public class RunGeneratePassword {

	public static void main(String[] args) throws BeansException, UserCreationException, EncryptionException {
		ClassPathXmlApplicationContext ctx = 
		        new ClassPathXmlApplicationContext("applicationContext.xml");
		ctx.registerShutdownHook();
		
		EncryptionService encService = ctx.getBean(EncryptionService.class);

		User admin = new User();
		admin.setLogin("admin");
		
		encService.setPassword(admin, "passwd");
		System.out.println(new String(admin.getPassword(), Charset.forName("UTF-8")));
		System.out.println(new String(admin.getSalt(), Charset.forName("UTF-8")));
	}

}
