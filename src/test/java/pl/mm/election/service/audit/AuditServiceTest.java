package pl.mm.election.service.audit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/applicationContext-mq.xml",
		"/applicationContext-jms.xml"
	})
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class AuditServiceTest {

	@Resource
	private AuditMessageListener auditMessageListener;
	
	@Resource
	private AuditMessageSender auditMessageSender;
	
	@Resource
	private AuditService auditService;
	
	@Test
	public void testSendAuditMessage() {
		// given
		AuditMessage msg = new AuditMessage("test");
		
		// when
		auditService.send(msg);
		
		// then
		System.out.println();
		
	}
}
